package com.ntsky.news.manage;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 类的相关操作</p>
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

public class Kind {
    private SQLDBOperator sdbo=null;
    /**
     * 显示数据库中已有类别
     */
    //判断类别是否为空
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
    //列出所有的类别
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
    //列出满足条件的类别
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
     * 插入类别
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
     * 删除类别
     */
    public void delKind(int kindId) throws SQLException{
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql_kind = "delete from newskind where kindId=?;";
        String sql_news = "delete from news where kindId=?;";
        String sql_news_newsId = "select newsId from news where kindId=?";
        String sql_reply = "delete from newsreply where newsId=?;";
        //删除类别
        sdbo.prepareStatement(sql_kind);
        sdbo.setInt(1,kindId);
        sdbo.executeUpdate();
        //删除新闻
        sdbo.prepareStatement(sql_news);
        sdbo.setInt(1,kindId);
        sdbo.executeUpdate();
        /**
         * 删除回复
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
     * 修改类别
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
     * 更新记录
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