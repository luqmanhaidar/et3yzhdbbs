package com.ntsky.news.manage;

/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */

import java.sql.*;
import java.io.*;
import java.util.*;

import com.ntsky.common.*;
import com.ntsky.servlet.*;
import com.ntsky.database.*;
import com.ntsky.persistence.*;


public class Column {
    private SQLDBOperator sdbo=null;
    /**
     * ��ʾ������Ŀ
     */
    //�ж���Ŀ�Ƿ�Ϊ��
    public boolean isNullColumn(){
        boolean isNull=false;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String Sql="select classId from newsclass;";
        try{
            rs=sdbo.executeQuery(Sql);
            try{
                rs.last();
                if (rs.getRow() > 0) {
                    isNull = true;
                }
            }
            catch(NullPointerException nullE){
                nullE.printStackTrace(System.out);
                Debug.writeLog("Column inNullColumn(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            sqlE.printStackTrace(System.out);
            Debug.writeLog("Column inNullColumn(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isNull;
    }
    //�г����е���Ŀ
    public Iterator getColumn(){
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector vector=new Vector();
        String Sql="select classId,content from newsclass;";
        try{
            rs=sdbo.executeQuery(Sql);
            try{
                while (rs.next()) {
                    NEWSClass tableClass = new NEWSClass();
                    tableClass.setClassId(rs.getInt("classId"));
                    tableClass.setContent(rs.getString("content"));
                    vector.add(tableClass);
                }
                rs.close();
            }
           catch(NullPointerException nullE){
               nullE.printStackTrace(System.out);
               Debug.writeLog("Column getColumn(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
           }
        }
        catch(SQLException sqlE){
            sqlE.printStackTrace(System.out);
            Debug.writeLog("Column getColumn(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        sdbo.Close();
        return vector.iterator();
    }
    /**
     * ������Ŀ
     */
    //�жϲ���Ŀ�����
    public boolean isIns(int classId){
        boolean isIns=false;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "select * from newsclass where classId=?";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,classId);
            ResultSet rs = sdbo.executeQuery();
            try{
                rs.last();
                if (rs.getRow() > 0) {
                    isIns = true;
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("Column isIns():" + nullE.getMessage());
                Debug.writeLog("Column isIns(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Column isIns():" + sqlE.getMessage());
            Debug.writeLog("Column isIns(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isIns;
    }
    //����������Ŀ
    public void InsColumn(int classId,String content){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        try{
            String strSql = "insert into newsclass(classId,content) values(?,?);";
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,classId);
            sdbo.setString(2,CodeFilter.toHtml(content));
            sdbo.executeUpdate();
        }
        catch(Exception e){
            System.out.print("Column delColumn() :" + e.getMessage());
            Debug.writeLog("Column delColumn() , Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     * ɾ����Ŀ
     */
    public void delColumn(int classId){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql_class = "delete from newsclass where classId=?";
        String strSql_kind = "delete from newskind where classId=?";
        String strSql_news = "delete from news where classId=?";
        String strSql_news_newsId = "select newsId from news where classId=?";
        String strSql_reply = "delete from newsreply where newsId=?";
        //ɾ����Ŀ
        try{
            sdbo.prepareStatement(strSql_class);
            sdbo.setInt(1, classId);
            sdbo.executeUpdate();
            //ɾ�����
            sdbo.prepareStatement(strSql_kind);
            sdbo.setInt(1, classId);
            sdbo.executeUpdate();
            //ɾ������
            sdbo.prepareStatement(strSql_news);
            sdbo.setInt(1, classId);
            sdbo.executeUpdate();
            /**
             * ɾ����������
             */
            //����Id��
            sdbo.prepareStatement(strSql_news_newsId);
            sdbo.setInt(1, classId);
            ResultSet rs = sdbo.executeQuery();
            //Vector vector=new Vector();
            //ɾ������
            sdbo.prepareStatement(strSql_reply);
            try{
                while (rs.next()) {
                    int newsId = rs.getInt("newsId");
                    sdbo.setInt(1, newsId);
                    sdbo.executeUpdate();
                }
            }
            catch(NullPointerException nullE){
                System.out.print("Column delColumn() :" + nullE.getMessage());
                Debug.writeLog("Column delColumn() , Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Column delColumn() :" +sqlE.getMessage());
            Debug.writeLog("Column delColumn() , Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     * �޸���Ŀ
     */
    public Iterator editColumn(int classId){
        ResultSet rs = null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector vector = new Vector();
        String strSql = "select classId,content from newsclass where classId=?";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,classId);
            rs = sdbo.executeQuery();
            try{
                while(rs.next()){
                    NEWSClass tableClass = new NEWSClass();
                    tableClass.setClassId(rs.getInt("classId"));
                    tableClass.setContent(rs.getString("content"));
                    vector.add(tableClass);
                }
            }
            catch(NullPointerException nullE){
                System.out.print("Column isIns() :" + nullE.getMessage());
                Debug.writeLog("Column isIns() , Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Column isIns() :" +sqlE.getMessage());
            Debug.writeLog("Column isIns() , Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return vector.iterator();
    }
    /**
     * ������Ŀ
     */
    public void upColumn(int classId,String content){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "update newsclass set content=? where classId=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1, CodeFilter.toHtml(content));
            sdbo.setInt(2, classId);
            sdbo.executeUpdate();
        }
        catch(Exception e){
            System.out.print("Column upColumn() :" +e.getMessage());
            Debug.writeLog("Column upColumn() , Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     * ȡ��Column��ֵ(NewsShow)
     */
    public String getColumn_newsShow(int classId){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String content=null;
        String strSql = "select content from newsclass where classId='"+classId+"';";
        try{
            rs=sdbo.executeQuery(strSql);
            try{
                rs.next();
                content = rs.getString("content");
            }
            catch(NullPointerException nullE){
                System.out.print("getColumn_newsShow(int classId) " + nullE.getMessage());
                Debug.writeLog("getColumn_newsShow(int classId), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Column getColumn_newsShow(int classId) :" +sqlE.getMessage());
            Debug.writeLog("Column getColumn_newsShow(int classId) , Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return content;
    }
}
