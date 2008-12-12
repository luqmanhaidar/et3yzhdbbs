package com.ntsky.news.manage;

/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: �����ز���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */
import java.io.*;
import java.sql.*;
import java.util.*;

import com.ntsky.common.*;
import com.ntsky.database.*;
import com.ntsky.persistence.*;

public class Kind {
    private SQLDBOperator sdbo=null;
    /**
     * ��ʾ���ݿ����������
     */
    //�ж�����Ƿ�Ϊ��
    public boolean isNullKind(int classId){
        boolean isNull=false;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String Sql="select kindId from newskind where classId=?;";
        sdbo.prepareStatement(Sql);
        sdbo.setInt(1,classId);
        rs=sdbo.executeQuery();
        try{
            rs.last();
            if(rs.getRow()>0){
                isNull=true;
                rs.close();
            }
        }
        catch(Exception e){
            e.printStackTrace(System.out);
            Debug.writeLog("Kind inNullKind(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        sdbo.Close();
        return isNull;
    }
    //�г����е����
    public Iterator allKind(){
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector vector=new Vector();
        String Sql="select kindId,content,classId from newskind;";
        rs=sdbo.executeQuery(Sql);
        try{
            while(rs.next()){
                NEWSKind tableKind = new NEWSKind();
                tableKind.setKindId(rs.getInt("kindId"));
                tableKind.setContent(rs.getString("content"));
                tableKind.setClassId(rs.getInt("classId"));
                vector.add(tableKind);
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace(System.out);
            Debug.writeLog("Kind getKind(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        sdbo.Close();
        return vector.iterator();
    }
    //�г��������������
    public Iterator getKind(int classId){
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector vector=new Vector();
        String Sql="select kindId,content,classId from newskind where classId=?;";
        sdbo.prepareStatement(Sql);
        sdbo.setInt(1,classId);
        rs=sdbo.executeQuery();
        try{
            while(rs.next()){
                NEWSKind tableKind = new NEWSKind();
                tableKind.setKindId(rs.getInt("kindId"));
                tableKind.setContent(rs.getString("content"));
                tableKind.setClassId(rs.getInt("classId"));
                vector.add(tableKind);
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace(System.out);
            Debug.writeLog("Kind getKind(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        sdbo.Close();
        return vector.iterator();
    }
    /**
     * �������
     */
    public void insKind(String content,int classId) throws SQLException{
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "insert into newskind(content,classId) values(?,?);";
        sdbo.prepareStatement(sql);
        sdbo.setString(1,CodeFilter.toHtml(content));
        sdbo.setInt(2,classId);
        sdbo.executeUpdate();
        sdbo.Close();
    }
    /**
     * ɾ�����
     */
    public void delKind(int kindId) throws SQLException{
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql_kind = "delete from newskind where kindId=?;";
        String sql_news = "delete from news where kindId=?;";
        String sql_news_newsId = "select newsId from news where kindId=?";
        String sql_reply = "delete from newsreply where newsId=?;";
        //ɾ�����
        sdbo.prepareStatement(sql_kind);
        sdbo.setInt(1,kindId);
        sdbo.executeUpdate();
        //ɾ������
        sdbo.prepareStatement(sql_news);
        sdbo.setInt(1,kindId);
        sdbo.executeUpdate();
        /**
         * ɾ���ظ�
         */
        sdbo.prepareStatement(sql_news_newsId);
        sdbo.setInt(1,kindId);
        ResultSet rs=sdbo.executeQuery();
        sdbo.prepareStatement(sql_reply);
        while(rs.next()){
            int newsId=rs.getInt("newsId");
            sdbo.setInt(1,newsId);
            sdbo.executeUpdate();
        }
        sdbo.Close();
    }
    /**
     * �޸����
     */
    public Iterator editKind(int kindId){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select * from newskind where kindId=?";
        Vector vector = new Vector();
        try{
            sdbo.prepareStatement(sql);
            sdbo.setInt(1,kindId);
            ResultSet rs = sdbo.executeQuery();
            while(rs.next()){
                NEWSKind tableKind = new NEWSKind();
                tableKind.setContent(rs.getString("content"));
                tableKind.setClassId(rs.getInt("classId"));
                vector.add(tableKind);
            }
        }
        catch(Exception e){
            Debug.writeLog("Kind editKind(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        sdbo.Close();
        return vector.iterator();
    }
    /**
     * ���¼�¼
     */
    public void upKind(int classId,String content,int kindId) throws Exception{
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "update newskind set classId=?,content=? where kindId=?;";
        sdbo.prepareStatement(sql);
        sdbo.setInt(1,classId);
        sdbo.setString(2,CodeFilter.toHtml(content));
        sdbo.setInt(3,kindId);
        sdbo.executeUpdate();
        sdbo.Close();
    }
}