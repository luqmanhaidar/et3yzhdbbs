package com.ntsky.bbs.webfeed.element;

public class Item {
    
    // 信息标题
    private String title;
    
    // 信息描述
    private String description;
    
    // 信息链接
    private String link;   
    
    // 作者
    private String author;
    
    // 发布时间
    private String pubDate;
    
    private String comments;
    
    /**
     * @return 返回 comments。
     */
    public String getComments() {
        return comments;
    }
    /**
     * @param comments 要设置的 comments。
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    /**
     * @return 返回 author。
     */
    public String getAuthor() {
        return author;
    }
    /**
     * @param author 要设置的 author。
     */
    public void setAuthor(String author) {
        this.author = author;
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
