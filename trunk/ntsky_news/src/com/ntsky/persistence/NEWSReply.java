package com.ntsky.persistence;

/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */

public class NEWSReply {

    public int replyId;
    public int newsId;
    public String user=null;
    public String content=null;
    public String replyTime=null;

    //�ظ����
    public void setReplyId(int replyId){
        this.replyId=replyId;
    }
    public int getReplyId(){
        return replyId;
    }
    //���±��
    public void setNewsId(int newsId){
        this.newsId=newsId;
    }
    public int getNewsId(){
        return newsId;
    }
    //����
    public void setUser(String user){
        this.user=user;
    }
    public String getUser(){
        return user;
    }
    //����
    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
    //����ʱ��
    public void setReplyTime(String replyTime){
        this.replyTime=replyTime;
    }
    public String getReplyTime(){
        return replyTime;
    }
}