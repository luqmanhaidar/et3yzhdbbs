package com.ntsky.persistence;

/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: ����Ա�ظ���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */

public class NOTEReply {
    public int replyId;
    public int noteId;
    public String content=null;
    public String replyTime=null;
    //�ظ�ID��
    public void setReplyId(int replyId){
        this.replyId=replyId;
    }
    public int getReplyId(){
        return replyId;
    }
    //�ظ����
    public void setNoteId(int noteId){
        this.noteId=noteId;
    }
    public int getNoteId(){
        return noteId;
    }
    //�ظ�����
    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
    //�ظ�ʱ��
    public void setReplyTime(String replyTime){
        this.replyTime=replyTime;
    }
    public String getReplyTime(){
        return replyTime;
    }
}