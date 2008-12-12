package com.ntsky.persistence;

/**
 * <p>Title: NTsky新闻发布</p>
 * <p>Description: 后台用户表</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */

public class NEWSAdmin{

	public String userName = null;
    public String passWd = null;
    public int purview;
    public String lastLogin=null;
    public String lastLoginIp=null;
    //用户名
    public void setUserName(String userName){
        this.userName=userName;
	}
    public String getUserName(){
        return userName;
    }
	//密码
    public void setPassWd(String passWd){
        this.passWd=passWd;
	}
    public String getPassWd(){
        return passWd;
	}
    //权限
    public void setPurview(int purview){
        this.purview=purview;
    }
    public int getPurview(){
        return purview;
    }
    //IP
    public void setLastLoginIp(String lastLoginIp){
        this.lastLoginIp=lastLoginIp;
    }
    public String getLastLoginIp(){
        return lastLoginIp;
    }
    //重复密码
    public void setLastLogin(String lastLogin){
        this.lastLogin=lastLogin;
    }
    public String getLastLogin(){
        return lastLogin;
    }
}

