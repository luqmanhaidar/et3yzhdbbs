package com.ntsky.persistence;

/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: �û����Է���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
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
    //���Ա��
    public void setNoteId(int noteId){
        this.noteId=noteId;
    }
    public int getNoteId(){
        return noteId;
    }
    //�û���
    public void setUserName(String userName){
        this.userName=userName;
    }
    public String getUserName(){
        return userName;
    }
    //�Ա�
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
    //��������
    public void setHeadTitle(String headTitle){
        this.headTitle=headTitle;
    }
    public String getHeadTitle(){
        return headTitle;
    }
    //��������
    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
    //ͼ��
    public void setImage(String image){
        this.image=image;
    }
    public String getImage(){
        return image;
    }
    //ʱ��
    public void setNoteTime(String noteTime){
        this.noteTime = noteTime;
    }
    public String getNoteTime(){
        return noteTime;
    }
}