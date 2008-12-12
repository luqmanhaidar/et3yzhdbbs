package com.ntsky.persistence;

import com.ntsky.common.*;

/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */

public class NEWSTable {

    public int newsId;
    public int myOther;
    public int classId;
    public int kindId;
    public String headTitle=null;
    public String content=null;
    public String connect=null;
    public String author=null;
    public String editor=null;
    public String newsFrom=null;
    public String newsTime=null;
    public int hits;
    public int top;
    public int state;
    public int tag;

    //���±��
    public void setNewsId(int newsId){
        this.newsId=newsId;
    }
    public int getNewsId(){
        return newsId;
    }
    //��ĿID
    public void setClassId(int classId){
        this.classId=classId;
    }
    public int getClassId(){
        return classId;
    }
    //���ID
    public void setKindId(int kindId){
        this.kindId=kindId;
    }
    public int getKindId(){
        return kindId;
    }
    //�Ƿ�Ϊԭ��
    public void setMyOther(int myOther){
        this.myOther=myOther;
    }
    public int getMyOther(){
        return myOther;
    }
    //���±���
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
    //�������
    public void setConnect(String connect){
        this.connect=connect;
    }
    public String getConnect(){
        return connect;
    }
    //��������
    public void setAuthor(String author){
        this.author=author;
    }
    public String getAuthor(){
        return author;
    }
    //�༭
    public void setEditor(String editor){
        this.editor=editor;
    }
    public String getEditor(){
        return editor;
    }
    //����
    public void setNewsFrom(String newsFrom){
        this.newsFrom=newsFrom;
    }
    public String getNewsFrom(){
        return newsFrom;
    }
    //�Ƿ��ö�
    public void setTop(int top){
        this.top=top;
    }
    public int getTop(){
        return top;
    }
    //����ʱ��
    public void setNewsTime(String newsTime){
        this.newsTime=newsTime;
    }
    public String getNewsTime(){
        return newsTime;
    }
    //�������
    public void setHits(int hits){
        this.hits=hits;
    }
    public int getHits(){
        return hits;
    }
    //���µ�״̬
    public void setState(int state){
        this.state=state;
    }
    public int getState(){
        return state;
    }
}