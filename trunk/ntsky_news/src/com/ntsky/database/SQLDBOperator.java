package com.ntsky.database;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.ntsky.common.*;
import com.ntsky.pool.DBConnectionManager;

/**
 * <p>Title: NTsky新闻发布</p>
 * <p>Description: 数据库操作</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */

public class SQLDBOperator extends DBOperator {

	private DBConnectionManager conManager;
	private String poolName;
    private PreparedStatement prepstmt=null;
	private Connection conn;
	private static SQLDBOperator instance;

    /**
     * 构造函数[从连接池取得连接]
     */
    private SQLDBOperator(String strKey){
        this.poolName = strKey;
        //调用getInstance()方法获得对连接池唯一实例的引用。
        this.conManager = DBConnectionManager.getInstance();
        this.conn = conManager.getConnection(strKey);
    }

    /**
     * 关闭数据库连接
     */
    public void Close() {
        try{
            conManager.freeConnection(this.poolName, this.conn);
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
    }

    /**
     * 数据更新(插入&修改&删除)
     * @param strSql
     */

    public void executeUpdate(String strSql){
        try{
            strSql=new String(strSql.getBytes("GBK"),"ISO8859_1");
            Statement stmt = this.conn.createStatement();
            stmt.executeUpdate(strSql);
        }
        catch(Exception e){
            e.printStackTrace(System.out);
            Debug.writeLog("In executeUpdate(String), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
    }
     /**
      * 数据查询
      * @param sql
      * @return rs
      */

     public ResultSet executeQuery(String strSql) {
         ResultSet rs=null;
         try{
             strSql=new String(strSql.getBytes("GBK"),"ISO8859_1");
             Statement stmt = this.conn.createStatement();
             rs = stmt.executeQuery(strSql);
         }
         catch(Exception e){
             e.printStackTrace(System.out);
             Debug.writeLog("In executeQuery(String), Exception Occured ! Info :" + e.getLocalizedMessage());
         }
         return rs;
     }

     /**
      * 带参数的数据操作
      * @param strSql
      */
     /**
      * 创建数据库操作的对象
      * @param strSql
      */
     public void prepareStatement(String strSql){
         try{
             prepstmt = this.conn.prepareStatement(strSql);
         }
         catch(Exception e){
             e.printStackTrace(System.out);
             Debug.writeLog("In prepareStatement(String strSql), Exception Occured ! Info :" + e.getLocalizedMessage());
         }
    }
    /**
     * 参数设置
     */
    //字符串
    public void setString(int index,String value){
        try{
            value = new String(value.getBytes("GBK"), "ISO8859_1");
            prepstmt.setString(index, value);
        }
        catch(Exception e){
            e.printStackTrace(System.out);
            Debug.writeLog("In setString(int index,String value), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
    }
    //整数
    public void setInt(int index,int value){
        try{
            prepstmt.setInt(index, value);
        }
        catch(Exception e){
            e.printStackTrace(System.out);
            Debug.writeLog("In setInt(int index,int value), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
    }
    //清除接口包含SQL语句当前所用的全部参数
    public void clearParameters(){
        try{
            prepstmt.clearParameters();
            prepstmt=null;
        }
        catch(Exception e){
            e.printStackTrace(System.out);
            Debug.writeLog("In clearParameters(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
    }
    //返回prepstmt
    public PreparedStatement getPreparedStatement(){
        return prepstmt;
    }

     /**
      * 数据更新(带参数)
      */
     public void executeUpdate(){
         try{
             if(prepstmt!=null){
                 prepstmt.executeUpdate();
             }
         }
         catch(Exception e){
             e.printStackTrace(System.out);
             Debug.writeLog("In executeUpdate(String), Exception Occured ! Info :" + e.getLocalizedMessage());
         }
     }
     /**
      * 数据查询（带参数）
      * @return rs
      */
     public ResultSet executeQuery(){
         ResultSet rs=null;
         try{
             if(prepstmt!= null){
                 rs = prepstmt.executeQuery();
             }
         }
         catch(Exception e){
             e.printStackTrace(System.out);
             Debug.writeLog("In executeQuery(String), Exception Occured ! Info :" + e.getLocalizedMessage());
         }
         return rs;
     }
     /**
      * getConnection 方法注解。
      */

     public Connection getConnection() {
         return this.conn;
     }

     public static SQLDBOperator getInstance(String strKey){
         if (instance==null)
             instance = new SQLDBOperator(strKey);
         return instance;//返回一个SQLDBOperator对象
     }
 }
