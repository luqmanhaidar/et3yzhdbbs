package com.ntsky.persistence;

import com.ntsky.common.*;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
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

    //文章编号
    public void setNewsId(int newsId){
        this.newsId=newsId;
    }
    public int getNewsId(){
        return newsId;
    }
    //栏目ID
    public void setClassId(int classId){
        this.classId=classId;
    }
    public int getClassId(){
        return classId;
    }
    //类别ID
    public void setKindId(int kindId){
        this.kindId=kindId;
    }
    public int getKindId(){
        return kindId;
    }
    //是否为原创
    public void setMyOther(int myOther){
        this.myOther=myOther;
    }
    public int getMyOther(){
        return myOther;
    }
    //文章标题
    public void setHeadTitle(String headTitle){
        this.headTitle=headTitle;
    }
    public String getHeadTitle(){
        return headTitle;
    }
    //文章内容
    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
    //相关连接
    public void setConnect(String connect){
        this.connect=connect;
    }
    public String getConnect(){
        return connect;
    }
    //文章作者
    public void setAuthor(String author){
        this.author=author;
    }
    public String getAuthor(){
        return author;
    }
    //编辑
    public void setEditor(String editor){
        this.editor=editor;
    }
    public String getEditor(){
        return editor;
    }
    //出处
    public void setNewsFrom(String newsFrom){
        this.newsFrom=newsFrom;
    }
    public String getNewsFrom(){
        return newsFrom;
    }
    //是否置顶
    public void setTop(int top){
        this.top=top;
    }
    public int getTop(){
        return top;
    }
    //发布时间
    public void setNewsTime(String newsTime){
        this.newsTime=newsTime;
    }
    public String getNewsTime(){
        return newsTime;
    }
    //点击次数
    public void setHits(int hits){
        this.hits=hits;
    }
    public int getHits(){
        return hits;
    }
    //文章的状态
    public void setState(int state){
        this.state=state;
    }
    public int getState(){
        return state;
    }
}