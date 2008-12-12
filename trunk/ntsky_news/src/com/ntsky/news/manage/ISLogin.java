package com.ntsky.news.manage;

import java.sql.*;
import java.io.*;
import java.util.*;

import com.ntsky.common.*;
import com.ntsky.database.*;
import com.ntsky.persistence.*;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 用户登陆</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */
public class ISLogin{
    private SQLDBOperator sdbo=null;

    //判断用户
    public boolean isUsernameOk(String useName){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isOk=false;
        String strSql = "select * from newsadmin where userName=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1,CodeFilter.toHtml(useName));
            ResultSet rs = sdbo.executeQuery();
            try{
                rs.last();
                if(rs.getRow()>0){
                    isOk=true;
                    rs.close();
                }
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
    /**
     * 密码判断
     * @return boolean
     */
    public boolean isPasswordOk(String userName,String passWd){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isOk=false;
        String strSql = "select * from newsadmin where userName=? and passWd=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1,CodeFilter.toHtml(userName));
            sdbo.setString(2,CodeFilter.toHtml(passWd));
            ResultSet rs = sdbo.executeQuery();
            try{
                rs.last();
                if(rs.getRow()>0){
                    isOk=true;
                    rs.close();
                }
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
    /**
     * 更新登陆的IP和时间
     */
    public void upTimeAndIp(String ip){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isOk=false;
        String strSql = "update newsadmin set lastLogin=?,lastLoginIP=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1,DateUtil.getNowDate());
            sdbo.setString(2,ip);
            sdbo.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace(System.out);
            Debug.writeLog("ISLogin isok(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     * 时间和ip
     */
    public String strTime(String userName){
        String strSql = "select * from newsadmin where userName='"+userName+"';";
        String strTime = null;
        ResultSet rs = null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        try{
            //sdbo.prepareStatement(strSql);
            //sdbo.setString(1,userName);
            rs = sdbo.executeQuery(strSql);
            try{
                rs.next();
                strTime=rs.getString("lastLogin");
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("ISLogin timeIp() " + nullE.getMessage());
                Debug.writeLog("ISLogin timeIp(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("ISLogin timeIp() " +sqlE.getMessage());
            Debug.writeLog("ISLogin timeIp(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return strTime;
    }
    /**
     * Ip的值
     */
    public String strIp(String userName){
        String strSql = "select * from newsadmin where userName='"+userName+"';";
        String strIp = null;
        ResultSet rs = null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        try{
            //sdbo.prepareStatement(strSql);
            //sdbo.setString(1,userName);
            rs = sdbo.executeQuery(strSql);
            try{
                rs.next();
                strIp=rs.getString("lastLoginIp");
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("ISLogin timeIp() " + nullE.getMessage());
                Debug.writeLog("ISLogin timeIp(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("ISLogin timeIp() " +sqlE.getMessage());
            Debug.writeLog("ISLogin timeIp(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return strIp;
    }
}