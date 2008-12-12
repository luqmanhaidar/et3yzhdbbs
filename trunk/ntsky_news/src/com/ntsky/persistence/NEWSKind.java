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

public class NEWSKind {

    public int kindId;
    public String content=null;
    public int classId;

    //类别编号
    public void setKindId(int kindId){
        this.kindId=kindId;
    }
    public int getKindId(){
        return kindId;
    }
    //类别内容
    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
    //栏目ID
    public void setClassId(int classId){
        this.classId=classId;
    }
    public int getClassId(){
        return classId;
    }
}