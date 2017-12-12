package com.rengu.operationsoanagementsuite.Controller;

import com.rengu.operationsoanagementsuite.Entity.ComponentEntity;
import com.rengu.operationsoanagementsuite.Entity.UserEntity;
import com.rengu.operationsoanagementsuite.Service.ComponentService;
import com.rengu.operationsoanagementsuite.Utils.ResultEntity;
import com.rengu.operationsoanagementsuite.Utils.ResultUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping(value = "/components")
public class ComponentController {
    @Autowired
    private ComponentService componentService;

    // 保存组件
    @PostMapping
    public ResultEntity saveComponent(@AuthenticationPrincipal UserEntity loginUser, ComponentEntity componentEntity, @RequestParam(value = "addFilePath") String[] addFilePath, @RequestParam(value = "componentfile") MultipartFile[] multipartFiles) throws MissingServletRequestParameterException, IOException, NoSuchAlgorithmException {
        return ResultUtils.init(HttpStatus.CREATED, ResultUtils.HTTPRESPONSE, loginUser, componentService.saveComponent(loginUser, componentEntity, addFilePath, multipartFiles));
    }

    // 删除组件
    @DeleteMapping(value = "/{componentId}")
    public ResultEntity deleteComponent(@AuthenticationPrincipal UserEntity loginUser, @PathVariable String componentId) throws MissingServletRequestParameterException {
        return ResultUtils.init(HttpStatus.NO_CONTENT, ResultUtils.HTTPRESPONSE, loginUser, componentService.deleteComponent(componentId));
    }

    // 更新组件
    @PatchMapping(value = "/{componentId}")
    public ResultEntity updategetComponents(@AuthenticationPrincipal UserEntity loginUser, @PathVariable(value = "componentId") String componentId) throws IOException {
        return ResultUtils.init(HttpStatus.OK, ResultUtils.HTTPRESPONSE, loginUser, componentService.updategetComponents(componentId));
    }

    // 查询组件
    @GetMapping(value = "/{componentId}")
    public ResultEntity getComponents(@AuthenticationPrincipal UserEntity loginUser, @PathVariable(value = "componentId") String componentId) throws MissingServletRequestParameterException {
        return ResultUtils.init(HttpStatus.OK, ResultUtils.HTTPRESPONSE, loginUser, componentService.getComponents(componentId));
    }

    // 查询组件
    @GetMapping
    public ResultEntity getComponents(@AuthenticationPrincipal UserEntity loginUser, ComponentEntity componentArgs) {
        return ResultUtils.init(HttpStatus.OK, ResultUtils.HTTPRESPONSE, loginUser, componentService.getComponents(componentArgs));
    }

    // 导入组件
    @PostMapping(value = "/import")
    public ResultEntity importComponents(@AuthenticationPrincipal UserEntity loginUser) {
        List<ComponentEntity> componentEntityList = componentService.importComponents();
        return ResultUtils.init(HttpStatus.OK, ResultUtils.HTTPRESPONSE, loginUser, componentEntityList);
    }

    // 导出组件
    @GetMapping(value = "/export/{componentId}")
    public ResultEntity exportComponents(@AuthenticationPrincipal UserEntity loginUser, HttpServletResponse httpServletResponse, @PathVariable(value = "componentId") String componentId) throws MissingServletRequestParameterException, IOException {
        // 获取导出文件
        File exportComponents = componentService.exportComponents(componentId);
        // 设置请求相关信息
        httpServletResponse.setHeader("content-type", "application/octet-stream");
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + exportComponents.getName());
        // 生成下载文件
        FileInputStream fileInputStream = new FileInputStream(exportComponents);
        OutputStream outputStream = httpServletResponse.getOutputStream();
        IOUtils.copy(fileInputStream, outputStream);
        fileInputStream.close();
        outputStream.close();
        return ResultUtils.init(HttpStatus.OK, ResultUtils.HTTPRESPONSE, loginUser, exportComponents.getAbsolutePath());
    }
}