package com.ntsky.news.note;
/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: �������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */
import java.sql.*;
import java.util.*;

import com.ntsky.common.*;
import com.ntsky.database.*;
import com.ntsky.persistence.*;

public class Manager {
    private SQLDBOperator sdbo=null;
    private String passWd=null;
    /**
     * ����Ա��½
     */
    //�û����ж�
    public boolean isAdminOk(String adminName){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isOk=false;
        String strSql = "select * from noteadmin where adminName=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1,CodeFilter.toHtml(adminName));
            ResultSet rs = sdbo.executeQuery();
            try{
                rs.last();
                if(rs.getRow()>0){
                    isOk=true;
                    passWd = rs.getString("adminPasswd");
                }
                rs.close();
            }
            catch(Exception e){
                e.printStackTrace(System.out);
                Debug.writeLog("ISLogin_rs isok(), Exception Occured ! Info :" + e.getLocalizedMessage());
            }
        }
        catch(Exception e){
            e.printStackTrace(System.out);
            Debug.writeLog("ISLogin isok(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        sdbo.Close();
        return isOk;
    }
    //�����ж�
    public boolean isPasswdOk(String adminPasswd){
        boolean isOn=false;
        if(passWd.equals(CodeFilter.toHtml(adminPasswd))){
            isOn=true;
        }
        return isOn;
    }
    /**
     * �޸��û�����
     */
    //ԭʼ�����ж�
    public boolean isOldPwd(String passWd,String adminName){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isOn=false;
        String sql = "select adminPasswd from noteadmin where adminName=?;";
        try{
            sdbo.prepareStatement(sql);
            sdbo.setString(1,adminName);
            ResultSet rs = sdbo.executeQuery();
            rs.next();
            String adminPasswd=rs.getString("adminPasswd");
            if(passWd.equalsIgnoreCase(adminPasswd)){
                isOn=true;
            }
            rs.close();
        }
        catch(Exception e){
            System.out.print("Manager isOldPwd() " + e.getMessage());
            Debug.writeLog("Manager isOldPwd(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        sdbo.Close();
        return isOn;
    }
    //��������
    public void upPasswd(String adminPasswd,String adminName){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "update noteadmin set adminPasswd=? where adminName=?;";
        try{
            sdbo.prepareStatement(sql);
            sdbo.setString(1,CodeFilter.toHtml(adminPasswd));
            sdbo.setString(2,CodeFilter.toHtml(adminName));
            sdbo.executeUpdate();
        }
        catch(Exception e){
            System.out.print("Manager upPasswd() " + e.getMessage());
            Debug.writeLog("Manager upPasswd(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     * ɾ������
     */
    public void delNote(int noteId){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "delete from noteguest where noteId=?;";
        try{
            sdbo.prepareStatement(sql);
            sdbo.setInt(1,noteId);
            sdbo.executeUpdate();
        }
        catch(Exception e){
            System.out.print("Manager delNote() " + e.getMessage());
            Debug.writeLog("Manager delNote(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     * ���ӹ���
     */
    public void insReply(int noteId,String content){
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql =
            "insert into notereply(noteId,content,replyTime) values(?,?,?);";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setInt(1,noteId);
            sdbo.setString(2,CodeFilter.toHtml(content));
            sdbo.setString(3,DateUtil.getStringDateShort());
            sdbo.executeUpdate();
        }
        catch(Exception e){
            System.out.print("Manager insReply " + e.getMessage());
            Debug.writeLog("Manager insReply, Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally {
            sdbo.Close();
        }
    }
}