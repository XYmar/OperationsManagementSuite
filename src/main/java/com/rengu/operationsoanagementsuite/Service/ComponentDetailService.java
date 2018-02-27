package com.rengu.operationsoanagementsuite.Service;

import com.rengu.operationsoanagementsuite.Entity.ComponentDetailEntity;
import com.rengu.operationsoanagementsuite.Entity.ComponentEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class ComponentDetailService {

    public List<ComponentDetailEntity> getComponentDetails(ComponentEntity componentEntity, MultipartFile[] multipartFiles) throws IOException {
        String cacheFilePath = FileUtils.getTempDirectoryPath() + UUID.randomUUID().toString() + "/";
        for (MultipartFile multipartFile : multipartFiles) {
            // 复制文件到缓存文件
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(cacheFilePath + multipartFile.getOriginalFilename()));
        }
        return getComponentDetails(componentEntity, new File(cacheFilePath));
    }

    public List<ComponentDetailEntity> getComponentDetails(ComponentEntity componentEntity, File srcDir) throws IOException {
        Collection<File> fileCollection = FileUtils.listFiles(srcDir, null, true);
        List<ComponentDetailEntity> componentDetailEntityList = new ArrayList<>();
        for (File file : fileCollection) {
            // 从缓存文件中复制到组件库目录
            File componentFile = new File(componentEntity.getFilePath() + file.getAbsolutePath().replace(srcDir.getAbsolutePath(), "").replace("//", "/"));
            FileUtils.copyFile(file, componentFile);
            // 创建组件文件记录
            ComponentDetailEntity componentDetailEntity = new ComponentDetailEntity();
            componentDetailEntity.setName(file.getName());
            componentDetailEntity.setMD5(DigestUtils.md5Hex(new FileInputStream(file)));
            componentDetailEntity.setType(FilenameUtils.getExtension(file.getName()));
            componentDetailEntity.setSize(FileUtils.sizeOf(file));
            componentDetailEntity.setPath(componentFile.getAbsolutePath().replace(componentEntity.getFilePath(), "/"));
            componentDetailEntityList.add(componentDetailEntity);
        }
        return componentDetailEntityList;
    }
}