package com.ntsky.persistence;

/**
 * <p>Title: NTsky���ŷ���</p>
 * <p>Description: ��̨�û���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */

public class NEWSAdmin{

	public String userName = null;
    public String passWd = null;
    public int purview;
    public String lastLogin=null;
    public String lastLoginIp=null;
    //�û���
    public void setUserName(String userName){
        this.userName=userName;
	}
    public String getUserName(){
        return userName;
    }
	//����
    public void setPassWd(String passWd){
        this.passWd=passWd;
	}
    public String getPassWd(){
        return passWd;
	}
    //Ȩ��
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
    //�ظ�����
    public void setLastLogin(String lastLogin){
        this.lastLogin=lastLogin;
    }
    public String getLastLogin(){
        return lastLogin;
    }
}

