/*
 * 创建日期 2005-5-5
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.ntsky.bbs.webfeed.element;

/**
 * @author Administrator
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class Cloud {

    private String domain;
    private String port;
    private String path;
    private String registerProcedure;
    private String protocol;
    
    /**
     * @return 返回 domain。
     */
    public String getDomain() {
        return domain;
    }
    /**
     * @param domain 要设置的 domain。
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }
    /**
     * @return 返回 path。
     */
    public String getPath() {
        return path;
    }
    /**
     * @param path 要设置的 path。
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * @return 返回 port。
     */
    public String getPort() {
        return port;
    }
    /**
     * @param port 要设置的 port。
     */
    public void setPort(String port) {
        this.port = port;
    }
    /**
     * @return 返回 protocol。
     */
    public String getProtocol() {
        return protocol;
    }
    /**
     * @param protocol 要设置的 protocol。
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    /**
     * @return 返回 registerProcedure。
     */
    public String getRegisterProcedure() {
        return registerProcedure;
    }
    /**
     * @param registerProcedure 要设置的 registerProcedure。
     */
    public void setRegisterProcedure(String registerProcedure) {
        this.registerProcedure = registerProcedure;
    }
}
