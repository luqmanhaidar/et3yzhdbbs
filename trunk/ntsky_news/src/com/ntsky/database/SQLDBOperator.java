package com.ntsky.database;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.ntsky.common.*;
import com.ntsky.pool.DBConnectionManager;

/**
 * <p>Title: NTsky���ŷ���</p>
 * <p>Description: ���ݿ����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */

public class SQLDBOperator extends DBOperator {

	private DBConnectionManager conManager;
	private String poolName;
    private PreparedStatement prepstmt=null;
	private Connection conn;
	private static SQLDBOperator instance;

    /**
     * ���캯��[�����ӳ�ȡ������]
     */
    private SQLDBOperator(String strKey){
        this.poolName = strKey;
        //����getInstance()������ö����ӳ�Ψһʵ�������á�
        this.conManager = DBConnectionManager.getInstance();
        this.conn = conManager.getConnection(strKey);
    }

    /**
     * �ر����ݿ�����
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
     * ���ݸ���(����&�޸�&ɾ��)
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
      * ���ݲ�ѯ
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
      * �����������ݲ���
      * @param strSql
      */
     /**
      * �������ݿ�����Ķ���
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
     * ��������
     */
    //�ַ���
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
    //����
    public void setInt(int index,int value){
        try{
            prepstmt.setInt(index, value);
        }
        catch(Exception e){
            e.printStackTrace(System.out);
            Debug.writeLog("In setInt(int index,int value), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
    }
    //����ӿڰ���SQL��䵱ǰ���õ�ȫ������
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
    //����prepstmt
    public PreparedStatement getPreparedStatement(){
        return prepstmt;
    }

     /**
      * ���ݸ���(������)
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
      * ���ݲ�ѯ����������
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
      * getConnection ����ע�⡣
      */

     public Connection getConnection() {
         return this.conn;
     }

     public static SQLDBOperator getInstance(String strKey){
         if (instance==null)
             instance = new SQLDBOperator(strKey);
         return instance;//����һ��SQLDBOperator����
     }
 }
