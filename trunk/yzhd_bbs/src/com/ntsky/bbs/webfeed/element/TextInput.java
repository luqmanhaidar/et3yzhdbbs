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
public class TextInput {

    private String title;
    private String description;
    private String name;
    private String link;
    
    /**
     * @return 返回 description。
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description 要设置的 description。
     */
    public void setDescription(String description) {
        this.description = description;
    }

    
    /**
     * @return 返回 link。
     */
    public String getLink() {
        return link;
    }
    /**
     * @param link 要设置的 link。
     */
    public void setLink(String link) {
        this.link = link;
    }
    /**
     * @return 返回 name。
     */
    public String getName() {
        return name;
    }
    /**
     * @param name 要设置的 name。
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return 返回 title。
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title 要设置的 title。
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
