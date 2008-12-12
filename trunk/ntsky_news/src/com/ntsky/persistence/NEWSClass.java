package com.ntsky.persistence;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 类表</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */

public class NEWSClass {
    public int classId;
    public String content;

    //栏目ID
    public void setClassId(int classId){
        this.classId=classId;
    }
    public int getClassId(){
        return classId;
    }
    //栏目内容
    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
}