package com.ntsky.persistence;

/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: ���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */

public class NEWSClass {
    public int classId;
    public String content;

    //��ĿID
    public void setClassId(int classId){
        this.classId=classId;
    }
    public int getClassId(){
        return classId;
    }
    //��Ŀ����
    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
}