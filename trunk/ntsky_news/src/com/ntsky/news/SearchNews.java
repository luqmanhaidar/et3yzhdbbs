package com.ntsky.news;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 查询</p>
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

public class SearchNews {
    public SQLDBOperator sdbo=null;
    private String search=null;
    private int select;
    /**
     * 取得search的值
     */
    public void setSearch(String search){
        this.search=search;
    }
    public String getSearch(){
        return CodeFilter.toHtml(search);
    }
    /**
     * select 的值
     */
    public void setSelect(int select){
        this.select=select;
    }
    public int getSelect(){
        return select;
    }
    /**
     * 分类取得新闻的总数
     */
    public int sumNews(){
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        int sumNews=0;
        switch(select){
            case 0:
                String strSql1 = "select newsId from news where state=1 and headTitle like '%"+search+"%';";
                try{
                    rs=sdbo.executeQuery(strSql1);
                    try{
                        rs.last();
                        sumNews=rs.getRow();
                        rs.close();
                    }
                    catch(NullPointerException nullE){
                        System.out.print("SearchNews sumNews() " +nullE.getMessage());
                        Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                    }
                }
                catch(SQLException sqlE){
                    System.out.print("NewsShow sumNews() " +sqlE.getMessage());
                    Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
                }
                finally{
                    sdbo.Close();
                }
            break;
            case 1:
                String strSql2 = "select newsId from news where state=1 and content like '%"+search+"%';";
               try{
                   rs=sdbo.executeQuery(strSql2);
                   try{
                       rs.last();
                       sumNews=rs.getRow();
                       rs.close();
                   }
                   catch(NullPointerException nullE){
                       System.out.print("SearchNews sumNews() " +nullE.getMessage());
                       Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                   }
               }
               catch(SQLException sqlE){
                   System.out.print("NewsShow sumNews() " +sqlE.getMessage());
                   Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
               }
               finally{
                   sdbo.Close();
               }
            break;
        }
        return sumNews;
    }
    /**
     * 显示出查出来的文章
     */
    public Iterator listNews(){
        ResultSet rs=null;
        Vector vector = new Vector();
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        int sumNews=0;
        switch(select){
            case 0:
                String strSql1 = "select newsId,headTitle,newsTime from news where state=1 and headTitle like '%"+search+"%';";
                try{
                    rs=sdbo.executeQuery(strSql1);
                    try{
                        while(rs.next()){
                            NEWSTable tableNews = new NEWSTable();
                            tableNews.setNewsId(rs.getInt("newsId"));
                            tableNews.setHeadTitle(rs.getString("headTitle"));
                            tableNews.setNewsTime(rs.getString("newsTime"));
                            vector.add(tableNews);
                        }
                        rs.close();
                    }
                    catch(NullPointerException nullE){
                        System.out.print("SearchNews sumNews() " +nullE.getMessage());
                        Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                    }
                }
                catch(SQLException sqlE){
                    System.out.print("NewsShow sumNews() " +sqlE.getMessage());
                    Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
                }
                finally{
                    sdbo.Close();
                }
            break;
            case 1:
                String strSql2 = "select newsId,headTitle,newsTime from news where state=1 and content like '%"+search+"%';";
               try{
                   rs=sdbo.executeQuery(strSql2);
                   try{
                       while(rs.next()){
                           NEWSTable tableNews = new NEWSTable();
                           tableNews.setNewsId(rs.getInt("newsId"));
                           tableNews.setHeadTitle(rs.getString("headTitle"));
                           tableNews.setNewsTime(rs.getString("newsTime"));
                           vector.add(tableNews);
                       }
                       rs.close();
                   }
                   catch(NullPointerException nullE){
                       System.out.print("SearchNews sumNews() " +nullE.getMessage());
                       Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                   }
               }
               catch(SQLException sqlE){
                   System.out.print("NewsShow sumNews() " +sqlE.getMessage());
                   Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
               }
               finally{
                   sdbo.Close();
               }
            break;
        }
        return vector.iterator();
    }
    /**
     * 用户查询
     * @return
     */
    /**
     * 分类取得新闻的总数
     */
    public int sumNews(String user){
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        int sumNews=0;
        switch(select){
            case 0:
                String strSql1 = "select newsId from news where headTitle like '%"+search+"%' and author='"+user+"';";
                try{
                    rs=sdbo.executeQuery(strSql1);
                    try{
                        rs.last();
                        sumNews=rs.getRow();
                        rs.close();
                    }
                    catch(NullPointerException nullE){
                        System.out.print("SearchNews sumNews() " +nullE.getMessage());
                        Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                    }
                }
                catch(SQLException sqlE){
                    System.out.print("NewsShow sumNews() " +sqlE.getMessage());
                    Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
                }
                finally{
                    sdbo.Close();
                }
            break;
            case 1:
                String strSql2 = "select newsId from news where content like '%"+search+"%' and author='"+user+"';";
               try{
                   rs=sdbo.executeQuery(strSql2);
                   try{
                       rs.last();
                       sumNews=rs.getRow();
                       rs.close();
                   }
                   catch(NullPointerException nullE){
                       System.out.print("SearchNews sumNews() " +nullE.getMessage());
                       Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                   }
               }
               catch(SQLException sqlE){
                   System.out.print("NewsShow sumNews() " +sqlE.getMessage());
                   Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
               }
               finally{
                   sdbo.Close();
               }
            break;
        }
        return sumNews;
    }
    /**
     * 显示出查出来的文章
     */
    public Iterator listNews(String user){
        ResultSet rs=null;
        Vector vector = new Vector();
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        int sumNews=0;
        switch(select){
            case 0:
                String strSql1 = "select newsId,headTitle,newsTime from news where headTitle like '%"+search+"%' and author='"+user+"';";
                try{
                    rs=sdbo.executeQuery(strSql1);
                    try{
                        while(rs.next()){
                            NEWSTable tableNews = new NEWSTable();
                            tableNews.setNewsId(rs.getInt("newsId"));
                            tableNews.setHeadTitle(rs.getString("headTitle"));
                            tableNews.setNewsTime(rs.getString("newsTime"));
                            vector.add(tableNews);
                        }
                        rs.close();
                    }
                    catch(NullPointerException nullE){
                        System.out.print("SearchNews sumNews() " +nullE.getMessage());
                        Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                    }
                }
                catch(SQLException sqlE){
                    System.out.print("NewsShow sumNews() " +sqlE.getMessage());
                    Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
                }
                finally{
                    sdbo.Close();
                }
            break;
            case 1:
                String strSql2 = "select newsId,headTitle,newsTime from news where content like '%"+search+"%' and author='"+user+"';";
               try{
                   rs=sdbo.executeQuery(strSql2);
                   try{
                       while(rs.next()){
                           NEWSTable tableNews = new NEWSTable();
                           tableNews.setNewsId(rs.getInt("newsId"));
                           tableNews.setHeadTitle(rs.getString("headTitle"));
                           tableNews.setNewsTime(rs.getString("newsTime"));
                           vector.add(tableNews);
                       }
                       rs.close();
                   }
                   catch(NullPointerException nullE){
                       System.out.print("SearchNews sumNews() " +nullE.getMessage());
                       Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                   }
               }
               catch(SQLException sqlE){
                   System.out.print("NewsShow sumNews() " +sqlE.getMessage());
                   Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
               }
               finally{
                   sdbo.Close();
               }
            break;
        }
        return vector.iterator();
    }
    /**
     * hot 前8条记录
     */
    public Iterator hotNews(){
        Vector vector = new Vector();
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select newsId,headTitle from news order by hits desc limit 0,8;";
        try{
            rs=sdbo.executeQuery(strSql);
            try{
                while(rs.next()){
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    vector.add(tableNews);
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("NewsShow hotNews() " +nullE.getMessage());
                Debug.writeLog("NewsShow hotNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("NewsShow hotNews() " +sqlE.getMessage());
            Debug.writeLog("NewsShow hotNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return vector.iterator();
    }
}
