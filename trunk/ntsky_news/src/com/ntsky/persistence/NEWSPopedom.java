package com.ntsky.persistence;

/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: Ȩ�ޱ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */
import com.ntsky.common.*;

public class NEWSPopedom {

    public int gradeId;
    public String grade;
    //Ȩ�ޱ��
    public void setGradeId(int gradeId){
        gradeId=gradeId;
    }
    public int getGradeId(){
        return gradeId;
    }
    //Ȩ������
    public void setGrade(String grade){
        this.grade=grade;
    }
    public String getGrade(){
        return grade;
    }
}