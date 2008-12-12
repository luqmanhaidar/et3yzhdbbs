package com.ntsky.news.manage;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 用户管理</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */

import java.io.*;
import java.sql.*;
import java.util.*;

import com.ntsky.common.*;
import com.ntsky.database.*;
import com.ntsky.persistence.*;

public class Usr {
    private SQLDBOperator sdbo=null;
    /**
     * 用户管理
     */
    //判断有无用户
    public boolean isUser(){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isUser=false;
        String sql = "select userName from newsusr";
        try{
            ResultSet rs = sdbo.executeQuery(sql);
            try{
                if (rs.next()) {
                    isUser = true;
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("Usr isUsr() " + nullE.getMessage());
                Debug.writeLog("Usr isUsr(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Usr isUsr() " + sqlE.getMessage());
            Debug.writeLog("Usr isUsr(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isUser;
    }
    //计算所有用户
    public int sumUser(){
        int sumUser=0;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isUser=false;
        String sql = "select userName from newsusr";
        try{
            ResultSet rs = sdbo.executeQuery(sql);
            try{
                rs.last();
                sumUser=rs.getRow();
            }
            catch(NullPointerException nullE){
                System.out.print("Usr isUsr() " + nullE.getMessage());
                Debug.writeLog("Usr isUsr(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Usr isUsr() " + sqlE.getMessage());
            Debug.writeLog("Usr isUsr(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return sumUser;
    }
    //列出所有的用户
    public Iterator userList(){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector  vector=new Vector();
        String sql = "select userName,purview from newsusr order by regTime desc;";
        try{
            ResultSet rs = sdbo.executeQuery(sql);
            try{
                while (rs.next()) {
                    NEWSUsr usrTable = new NEWSUsr();
                    usrTable.setUserName(rs.getString("userName"));
                    usrTable.setPurview(rs.getInt("purview"));
                    vector.add(usrTable);
                }
            }
            catch(NullPointerException nullE){
                System.out.print("Usr userList() " +nullE.getMessage());
                Debug.writeLog("Usr userList(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(Exception sqlE){
            System.out.print("Usr userList() " +sqlE.getMessage());
            Debug.writeLog("Usr userList(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return vector.iterator();
    }
    //取得用户的等级
    public String userGrade(int purview){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String grade=null;
        String sql = "select grade from newspopedom where gradeid=?;";
        try{
            sdbo.prepareStatement(sql);
            sdbo.setInt(1,purview);
            ResultSet rs = sdbo.executeQuery();
            try{
                rs.next();
                grade = rs.getString("grade");
            }
            catch(NullPointerException nullE){
                System.out.print("Usr userGrade() " + nullE.getMessage());
                Debug.writeLog("Usr userGrade(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Usr userList() " +sqlE.getMessage());
            Debug.writeLog("Usr userList(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return grade;
    }
    /**
     * 删除用户
     */
    public void delUser(String userName){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "delete from newsusr where userName=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1,userName);
            sdbo.executeUpdate();
        }
        catch(Exception e){
            System.out.print("Usr delUser() " + e.getMessage());
            Debug.writeLog("Usr delUser(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     * 判断有无重名的后台用户
     */
    public boolean isAdminName(String userName){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isAdmin=false;
        String sql = "select userName from newsadmin where userName=?;";
        try{
            sdbo.prepareStatement(sql);
            sdbo.setString(1,userName);
            ResultSet rs = sdbo.executeQuery();
            try{
                rs.last();
                if(rs.getRow()>0){
                    isAdmin=true;
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("Usr isAdminName() " + nullE.getMessage());
                Debug.writeLog("Usr isAdminName(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Usr isAdminName() " +sqlE.getMessage());
            Debug.writeLog("Usr isAdminName(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isAdmin;
    }
    /**
     * 插入数据
     */
    public void insAdmin(String userName,int purview){
        String passWd="www.ntsky.com";
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "insert into newsadmin(userName,passWd,purview) values(?,?,?);";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1,CodeFilter.toHtml(userName));
            sdbo.setString(2,passWd);
            sdbo.setInt(3,purview);
            sdbo.executeUpdate();
        }
        catch(Exception e){
            System.out.print("Usr insAdmin() " + e.getMessage());
            Debug.writeLog("Usr insAdmin(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
    }
    /**
     * 判断有无后台用户(不包含原始的用户)
     */
    public boolean isAdminUser(){
        String strSql = "select count(userName) as total from newsadmin where purview>0;";
        ResultSet rs = null;
        boolean isAdmin=false;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        try{
            rs = sdbo.executeQuery(strSql);
            try{
                rs.next();
                if(rs.getInt("total")>0){
                    isAdmin=true;
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("Usr isAdminName() " + nullE.getMessage());
                Debug.writeLog("Usr isAdminName(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Usr isAdminName() " +sqlE.getMessage());
            Debug.writeLog("Usr isAdminName(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isAdmin;
    }
    /**
     * 列出全部的后台用户
     */
    public Iterator listAdminUser(){
        String strSql = "select userName,purview from newsadmin where purview!=0;";
        ResultSet rs = null;
        Vector vector=new Vector();
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        try{
            rs = sdbo.executeQuery(strSql);
            try{
                while(rs.next()){
                    NEWSAdmin tableAdmin = new NEWSAdmin();
                    tableAdmin.setUserName(rs.getString("userName"));
                    tableAdmin.setPurview(rs.getInt("purview"));
                    vector.add(tableAdmin);
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("Usr isAdminName() " + nullE.getMessage());
                Debug.writeLog("Usr isAdminName(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Usr isAdminName() " +sqlE.getMessage());
            Debug.writeLog("Usr isAdminName(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return vector.iterator();
    }
    /**
     * 后台用户的级别
     */
    public String strPurview(int purview){
        String strPurview=null;
        switch(purview){
            case 1:
                strPurview="管理员";
                break;
            case 2:
                strPurview="录入员";
                break;
        }
        return strPurview;
    }
    /**
     * 删除管理用户
     */
    public void delAdmin(String userName){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "delete from newsadmin where userName=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1,userName);
            sdbo.executeUpdate();
        }
        catch(Exception e){
            System.out.print("Usr insAdmin() " + e.getMessage());
            Debug.writeLog("Usr insAdmin(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     * 管理员权限
     */
    public int adminPurview(String userName){
        String strSql = "select purview from newsadmin where userName=?";
        int purview=0;
        ResultSet rs = null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1,userName);
            rs = sdbo.executeQuery();
            try{
                rs.next();
                purview=rs.getInt("purview");
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("Usr isAdminName() " + nullE.getMessage());
                Debug.writeLog("Usr isAdminName(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Usr isAdminName() " +sqlE.getMessage());
            Debug.writeLog("Usr isAdminName(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return purview;
    }
    /**
     * 修改用户列出信息
     */
    public Iterator listAdmin(String adminName){
        String strSql = "select * from newsadmin where userName=?";
        ResultSet rs = null;
        Vector vector=new Vector();
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1,adminName);
            rs = sdbo.executeQuery();
            try{
                while(rs.next()){
                    NEWSAdmin tableAdmin = new NEWSAdmin();
                    tableAdmin.setPassWd(rs.getString("passWd"));
                    vector.add(tableAdmin);
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("Usr isAdminName() " + nullE.getMessage());
                Debug.writeLog("Usr isAdminName(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Usr isAdminName() " +sqlE.getMessage());
            Debug.writeLog("Usr isAdminName(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return vector.iterator();
    }
    /**
     * 更新管理员信息
     */
    public void upAdmin(String passWd,String adminName){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "update newsadmin set passWd=? where userName=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1,passWd);
            sdbo.setString(2,adminName);
            sdbo.executeUpdate();
        }
        catch(Exception e){
            System.out.print("Usr insAdmin() " + e.getMessage());
            Debug.writeLog("Usr insAdmin(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
}
