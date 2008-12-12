package com.ntsky.persistence;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 管理员回复表</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */

public class NOTEReply {
    public int replyId;
    public int noteId;
    public String content=null;
    public String replyTime=null;
    //回复ID号
    public void setReplyId(int replyId){
        this.replyId=replyId;
    }
    public int getReplyId(){
        return replyId;
    }
    //回复编号
    public void setNoteId(int noteId){
        this.noteId=noteId;
    }
    public int getNoteId(){
        return noteId;
    }
    //回复内容
    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
    //回复时间
    public void setReplyTime(String replyTime){
        this.replyTime=replyTime;
    }
    public String getReplyTime(){
        return replyTime;
    }
}