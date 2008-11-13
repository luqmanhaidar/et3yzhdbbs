package com.ntsky.bbs.webfeed.element;

import java.util.List;

public class Feed {
    
    private String title;
    private String modified;
    private String language;
    private String tagline;
    private Link link;    
    private List items;
    
    
    /**
     * @return 返回 language。
     */
    public String getLanguage() {
        return language;
    }
    /**
     * @param language 要设置的 language。
     */
    public void setLanguage(String language) {
        this.language = language;
    }
    /**
     * @return 返回 items。
     */
    public List getItems() {
        return items;
    }
    /**
     * @param items 要设置的 items。
     */
    public void setItems(List items) {
        this.items = items;
    }
    /**
     * @return 返回 modified。
     */
    public String getModified() {
        return modified;
    }
    /**
     * @param modified 要设置的 modified。
     */
    public void setModified(String modified) {
        this.modified = modified;
    }
    /**
     * @return 返回 tagline。
     */
    public String getTagline() {
        return tagline;
    }
    /**
     * @param tagline 要设置的 tagline。
     */
    public void setTagline(String tagline) {
        this.tagline = tagline;
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
     * @return 返回 link。
     */
    public Link getLink() {
        return link;
    }
    /**
     * @param link 要设置的 link。
     */
    public void setLink(Link link) {
        this.link = link;
    }
}
