package com.rengu.operationsoanagementsuite.Configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private String defultUsername = "admin";
    private String defultPassword = "admin";
    private String componentLibraryName = "lib";
    private String jsonFileName = "export.json";
    private String compressFileName = "ExportComponent.zip";
    private int hearbeatSendPort = 3086;
    private int hearbeatReceivePort = 6004;
    private int tcpReceivePort = 6005;
    private int deviceLogoutDelay = 5;

    // Device 通信端口
    public static final int deviceUDPPort = 3087;
    public static final int deviceTCPPort = 3088;

    // 不可修改项-自动从运行环境获取
    private String componentLibraryPath = "";

    public String getDefultUsername() {
        return defultUsername;
    }

    public void setDefultUsername(String defultUsername) {
        this.defultUsername = defultUsername;
    }

    public String getDefultPassword() {
        return defultPassword;
    }

    public void setDefultPassword(String defultPassword) {
        this.defultPassword = defultPassword;
    }

    public String getComponentLibraryName() {
        return componentLibraryName;
    }

    public void setComponentLibraryName(String componentLibraryName) {
        this.componentLibraryName = componentLibraryName;
    }

    public String getJsonFileName() {
        return jsonFileName;
    }

    public void setJsonFileName(String jsonFileName) {
        this.jsonFileName = jsonFileName;
    }

    public String getCompressFileName() {
        return compressFileName;
    }

    public void setCompressFileName(String compressFileName) {
        this.compressFileName = compressFileName;
    }

    public int getHearbeatSendPort() {
        return hearbeatSendPort;
    }

    public void setHearbeatSendPort(int hearbeatSendPort) {
        this.hearbeatSendPort = hearbeatSendPort;
    }

    public int getHearbeatReceivePort() {
        return hearbeatReceivePort;
    }

    public void setHearbeatReceivePort(int hearbeatReceivePort) {
        this.hearbeatReceivePort = hearbeatReceivePort;
    }

    public int getTcpReceivePort() {
        return tcpReceivePort;
    }

    public void setTcpReceivePort(int tcpReceivePort) {
        this.tcpReceivePort = tcpReceivePort;
    }

    public int getDeviceLogoutDelay() {
        return deviceLogoutDelay;
    }

    public void setDeviceLogoutDelay(int deviceLogoutDelay) {
        this.deviceLogoutDelay = deviceLogoutDelay;
    }

    public String getComponentLibraryPath() {
        return componentLibraryPath;
    }

    public void setComponentLibraryPath(String componentLibraryPath) {
        this.componentLibraryPath = componentLibraryPath;
    }
}