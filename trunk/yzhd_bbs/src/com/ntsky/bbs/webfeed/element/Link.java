/*
 * 创建日期 2005-5-6
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
public class Link {
    
    private String rel;
    private String type;
    private String href;
    
    /**
     * @return 返回 href。
     */
    public String getHref() {
        return href;
    }
    /**
     * @param href 要设置的 href。
     */
    public void setHref(String href) {
        this.href = href;
    }
    /**
     * @return 返回 rel。
     */
    public String getRel() {
        return rel;
    }
    /**
     * @param rel 要设置的 rel。
     */
    public void setRel(String rel) {
        this.rel = rel;
    }
    /**
     * @return 返回 type。
     */
    public String getType() {
        return type;
    }
    /**
     * @param type 要设置的 type。
     */
    public void setType(String type) {
        this.type = type;
    }
}
