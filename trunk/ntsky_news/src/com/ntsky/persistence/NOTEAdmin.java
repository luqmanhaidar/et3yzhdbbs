package com.ntsky.persistence;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 留言本管理</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */

public class NOTEAdmin{
    public String adminName=null;
    public String adminPasswd=null;
    //留言本管理用户
    public void setAdminName(String adminName){
        this.adminName=adminName;
    }
    public String getAdminName(){
        return adminName;
    }
    //密码
    public void setAdminPasswd(String adminPasswd){
        this.adminPasswd=adminPasswd;
    }
    public String getAdminPasswd(){
        return adminPasswd;
    }
}