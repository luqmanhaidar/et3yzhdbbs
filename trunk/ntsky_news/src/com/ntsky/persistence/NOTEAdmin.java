package com.ntsky.persistence;

/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: ���Ա�����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */

public class NOTEAdmin{
    public String adminName=null;
    public String adminPasswd=null;
    //���Ա������û�
    public void setAdminName(String adminName){
        this.adminName=adminName;
    }
    public String getAdminName(){
        return adminName;
    }
    //����
    public void setAdminPasswd(String adminPasswd){
        this.adminPasswd=adminPasswd;
    }
    public String getAdminPasswd(){
        return adminPasswd;
    }
}