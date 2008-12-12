package com.ntsky.persistence;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 用户留言发表</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */

public class NOTEGuest {
    public int noteId;
    public String userName=null;
    public int sex;
    public String email=null;
    public String qq=null;
    public String url=null;
    public String headTitle=null;
    public String content=null;
    public String image=null;
    public String noteTime=null;
    //留言编号
    public void setNoteId(int noteId){
        this.noteId=noteId;
    }
    public int getNoteId(){
        return noteId;
    }
    //用户名
    public void setUserName(String userName){
        this.userName=userName;
    }
    public String getUserName(){
        return userName;
    }
    //性别
    public void setSex(int sex){
        this.sex=sex;
    }
    public int getSex(){
        return sex;
    }
    //E-mail
    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }
    //QQ
    public void setQq(String qq){
        this.qq=qq;
    }
    public String getQq(){
        return qq;
    }
    //URL
    public void setUrl(String url){
        this.url=url;
    }
    public String getUrl(){
        return url;
    }
    //留言主题
    public void setHeadTitle(String headTitle){
        this.headTitle=headTitle;
    }
    public String getHeadTitle(){
        return headTitle;
    }
    //留言内容
    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
    //图象
    public void setImage(String image){
        this.image=image;
    }
    public String getImage(){
        return image;
    }
    //时间
    public void setNoteTime(String noteTime){
        this.noteTime = noteTime;
    }
    public String getNoteTime(){
        return noteTime;
    }
}