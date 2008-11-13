package com.ntsky.bbs.webfeed.element;

public class Image {
    
    private String title;
    private String link;
    private String url;
    private String width;
    private String height;
    private String description;
   
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
    /**
     * @return 返回 url。
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url 要设置的 url。
     */
    public void setUrl(String url) {
        this.url = url;
    }
        
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
     * @return 返回 height。
     */
    public String getHeight() {
        return height;
    }
    /**
     * @param height 要设置的 height。
     */
    public void setHeight(String height) {
        this.height = height;
    }
    /**
     * @return 返回 width。
     */
    public String getWidth() {
        return width;
    }
    /**
     * @param width 要设置的 width。
     */
    public void setWidth(String width) {
        this.width = width;
    }
}
