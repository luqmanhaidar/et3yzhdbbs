package com.ntsky.news;
/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 用户注册</p>
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

public class UserManage {
    private String user=null;
    private String passWd=null;
    private Vector vector=new Vector();
    private SQLDBOperator sdbo=null;
    /**
     * 用户注册
     */
    //判断有无此用户
    public boolean isUser(String userName){
        boolean isUser=false;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select userName from newsusr where userName=?;";
        try{
            sdbo.prepareStatement(sql);
            sdbo.setString(1,userName);
            ResultSet rs = sdbo.executeQuery();
            rs.last();
            if(rs.getRow()>0){
                isUser=true;
            }
            rs.close();
        }
        catch(Exception e){
            System.out.print("UserManage isUser()" + e.getMessage());
            Debug.writeLog("UserManage isUser(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isUser;
    }
    //用户注册资料
    public void RegUser(String userName,String passWd,int sex,String question,String answer,String emailAddr,String qq,String http){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "insert into newsusr(userName,passWd,sex,question,answer,emailAddr,qq,http,regTime) values(?,?,?,?,?,?,?,?,?)";
        try{
            sdbo.prepareStatement(sql);
            sdbo.setString(1,CodeFilter.toHtml(userName));
            sdbo.setString(2,CodeFilter.toHtml(passWd));
            sdbo.setInt(3,sex);
            sdbo.setString(4,CodeFilter.toHtml(question));
            sdbo.setString(5,CodeFilter.toHtml(answer));
            sdbo.setString(6,CodeFilter.toHtml(emailAddr));
            sdbo.setString(7,CodeFilter.toHtml(qq));
            sdbo.setString(8,CodeFilter.toHtml(http));
            sdbo.setString(9,DateUtil.getNowDate());
            sdbo.executeUpdate();
        }
        catch(Exception e){
            System.out.print("UserManage regUser()" + e.getMessage());
            Debug.writeLog("UserManage regUser(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     * 用户登陆更新登陆时间
     */
    public void upLoadTime(String userName){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "update newsusr set newsTime='"+DateUtil.getNowDate()+"' where userName='"+userName+"'";
        try{
            sdbo.executeUpdate(sql);
        }
        catch(Exception e){
            System.out.print("UserManage regUser()" + e.getMessage());
            Debug.writeLog("UserManage regUser(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     * *****************************************************
     * 用户登陆
     * @param user
     * @return
     */
    //判断用户
    public boolean isUsernameOk(String useName){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isOk=false;
        String strSql = "select * from newsusr where userName=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1,CodeFilter.toHtml(useName));
            ResultSet rs = sdbo.executeQuery();
            try{
                rs.last();
                if(rs.getRow()>0){
                    isOk=true;
                    passWd = rs.getString("passWd");
                    rs.close();
                }
            }
            catch(NullPointerException nullE){
                nullE.printStackTrace(System.out);
                Debug.writeLog("UserManage isUsernameOk(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            sqlE.printStackTrace(System.out);
            Debug.writeLog("UserManage isUsernameOk(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isOk;
    }
    //密码判断
    public boolean isPasswordOk(String passWord){
        boolean isOn=false;
        if(passWd.equals(CodeFilter.toHtml(passWord))){
            isOn=true;
        }
        return isOn;
    }
    /**
     * ****************************************************
     * 更改个人资料和后台用户显示
     */
    /**
     * 传递user参数
     */
    public String setUser(String user){
        this.user=user;
        return user;
    }
    /**
     * 列出个人资料
     */
    public Iterator listPersonal(){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select * from newsusr where userName=?;";
        ResultSet rs = null;
        try{
            sdbo.prepareStatement(sql);
            sdbo.setString(1,user);
            rs = sdbo.executeQuery();
            try{
                while(rs.next()){
                    NEWSUsr userTable = new NEWSUsr();
                    userTable.setUserName(rs.getString("userName"));
                    userTable.setPassWd(rs.getString("passWd"));
                    //userTable.setPwdAgain(rs.getString("pwdAgain"));
                    userTable.setSex(rs.getInt("sex"));
                    userTable.setQuestion(rs.getString("question"));
                    userTable.setAnswer(rs.getString("answer"));
                    userTable.setEmailAddr(rs.getString("emailAddr"));
                    userTable.setQq(rs.getString("qq"));
                    userTable.setHttp(rs.getString("http"));
                    vector.add(userTable);
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("UserManage listPersonal" + nullE.getMessage());
                Debug.writeLog("UserManage listPersonal(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.println("UserManage listPersonal()" + sqlE.getLocalizedMessage());
            Debug.writeLog("UserManage listPersonal(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return vector.iterator();
    }
    /**
     * 更改个人资料
     */
    public void upPersonal(String passWd,int sex,String question,String answer,String emailAddr,String qq,String http,String userName){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "update newsusr set passWd=?,sex=?,question=?,answer=?,emailAddr=?,qq=?,http=? where userName=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1,passWd);
            sdbo.setInt(2,sex);
            sdbo.setString(3,question);
            sdbo.setString(4,answer);
            sdbo.setString(5,emailAddr);
            sdbo.setString(6,qq);
            sdbo.setString(7,http);
            sdbo.setString(8,userName);
            sdbo.executeUpdate();
        }
        catch(Exception sqlE){
            System.out.println("UserManage upPersonal()" + sqlE.getLocalizedMessage());
            Debug.writeLog("UserManage upPersonal(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     * **************************
     * 取回密码
     */
    //密码判断
    public boolean isUserName(String userName){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select * from newsusr where userName=?;";
        ResultSet rs = null;
        boolean isUserName=false;
        try{
            sdbo.prepareStatement(sql);
            sdbo.setString(1,userName);
            rs = sdbo.executeQuery();
            try{
                rs.last();
                if(rs.getRow()>0){
                    isUserName=true;
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("UserManage listPersonal" + nullE.getMessage());
                Debug.writeLog("UserManage listPersonal(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.println("UserManage listPersonal()" + sqlE.getLocalizedMessage());
            Debug.writeLog("UserManage listPersonal(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isUserName;
    }
    //问题答案
    //密码判断
    public boolean isPassWd(String userName,String answer){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select passWd from newsusr where userName=? and answer=?;";
        ResultSet rs = null;
        boolean isPassWd=false;
        try{
            sdbo.prepareStatement(sql);
            sdbo.setString(1,userName);
            sdbo.setString(2,answer);
            rs = sdbo.executeQuery();
            try{
                rs.last();
                if(rs.getRow()>0){
                    isPassWd=true;
                    passWd=rs.getString("passWd");
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("UserManage listPersonal" + nullE.getMessage());
                Debug.writeLog("UserManage listPersonal(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.println("UserManage listPersonal()" + sqlE.getLocalizedMessage());
            Debug.writeLog("UserManage listPersonal(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isPassWd;
    }
    //返回answer得值
    public String getPassWd(){
        return this.passWd;
    }
}