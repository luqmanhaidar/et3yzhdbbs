package com.ntsky.persistence;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */

public class NEWSReply {

    public int replyId;
    public int newsId;
    public String user=null;
    public String content=null;
    public String replyTime=null;

    //回复编号
    public void setReplyId(int replyId){
        this.replyId=replyId;
    }
    public int getReplyId(){
        return replyId;
    }
    //文章编号
    public void setNewsId(int newsId){
        this.newsId=newsId;
    }
    public int getNewsId(){
        return newsId;
    }
    //笔名
    public void setUser(String user){
        this.user=user;
    }
    public String getUser(){
        return user;
    }
    //评论
    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
    //发表时间
    public void setReplyTime(String replyTime){
        this.replyTime=replyTime;
    }
    public String getReplyTime(){
        return replyTime;
    }
}