package com.ntsky.bbs.webfeed.element;

import java.util.List;

/**
 * 频道信息
 * @author Administrator
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */

public class Channel {
    private String title;
    private String link;
    private String description;
    private String language;
    private String copyright;
    private String managingEditor;
    private String webMaster;
    private String pubDate;
    private String docs;
    private String rating; 
    private String lastBuildDate;
    
    private List items;
    
    private Cloud cloud;
    private TextInput textInput;
    private Image image;
    
    /**
     * @return 返回 cloud。
     */
    public Cloud getCloud() {
        return cloud;
    }
    /**
     * @param cloud 要设置的 cloud。
     */
    public void setCloud(Cloud cloud) {
        this.cloud = cloud;
    }
    /**
     * @return 返回 lastBuildDate。
     */
    public String getLastBuildDate() {
        return lastBuildDate;
    }
    /**
     * @param lastBuildDate 要设置的 lastBuildDate。
     */
    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }
    /**
     * @return 返回 rating。
     */
    public String getRating() {
        return rating;
    }
    /**
     * @param rating 要设置的 rating。
     */
    public void setRating(String rating) {
        this.rating = rating;
    }
    /**
     * @return 返回 image。
     */
    public Image getImage() {
        return image;
    }
    /**
     * @param image 要设置的 image。
     */
    public void setImage(Image image) {
        this.image = image;
    }
    /**
     * @return 返回 textInput。
     */
    public TextInput getTextInput() {
        return textInput;
    }
    /**
     * @param textInput 要设置的 textInput。
     */
    public void setTextInput(TextInput textInput) {
        this.textInput = textInput;
    }
    /**
     * @return 返回 docs。
     */
    public String getDocs() {
        return docs;
    }
    /**
     * @param docs 要设置的 docs。
     */
    public void setDocs(String docs) {
        this.docs = docs;
    }
    /**
     * @return 返回 pubDate。
     */
    public String getPubDate() {
        return pubDate;
    }
    /**
     * @param pubDate 要设置的 pubDate。
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    /**
     * @return 返回 copyright。
     */
    public String getCopyright() {
        return copyright;
    }
    /**
     * @param copyright 要设置的 copyright。
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
    /**
     * @return 返回 managingEditor。
     */
    public String getManagingEditor() {
        return managingEditor;
    }
    /**
     * @param managingEditor 要设置的 managingEditor。
     */
    public void setManagingEditor(String managingEditor) {
        this.managingEditor = managingEditor;
    }
    /**
     * @return 返回 webMaster。
     */
    public String getWebMaster() {
        return webMaster;
    }
    /**
     * @param webMaster 要设置的 webMaster。
     */
    public void setWebMaster(String webMaster) {
        this.webMaster = webMaster;
    }
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
}

