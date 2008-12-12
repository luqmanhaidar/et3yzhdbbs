package com.ntsky.persistence;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 权限表</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */
import com.ntsky.common.*;

public class NEWSPopedom {

    public int gradeId;
    public String grade;
    //权限编号
    public void setGradeId(int gradeId){
        gradeId=gradeId;
    }
    public int getGradeId(){
        return gradeId;
    }
    //权限种类
    public void setGrade(String grade){
        this.grade=grade;
    }
    public String getGrade(){
        return grade;
    }
}