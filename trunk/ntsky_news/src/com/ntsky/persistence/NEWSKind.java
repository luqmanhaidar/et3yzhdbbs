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

public class NEWSKind {

    public int kindId;
    public String content=null;
    public int classId;

    //�����
    public void setKindId(int kindId){
        this.kindId=kindId;
    }
    public int getKindId(){
        return kindId;
    }
    //�������
    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
    //��ĿID
    public void setClassId(int classId){
        this.classId=classId;
    }
    public int getClassId(){
        return classId;
    }
}