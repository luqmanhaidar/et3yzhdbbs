package com.ntsky.news;
/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 新闻显示的相关操作</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */
import java.sql.*;
import java.util.*;

import com.ntsky.common.*;
import com.ntsky.database.*;
import com.ntsky.persistence.*;

public class NewsShow {
    private SQLDBOperator sdbo=null;
    /**
     * 本站发表的文章总数
     */
    public int sumNews(){
        int sum=0;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select count(newsId) as total from news where state=1;";
        try{
            rs = sdbo.executeQuery(strSql);
            try{
                rs.next();
                sum=rs.getInt("total");
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("NewsShow sumNews() " +nullE.getMessage());
                Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("NewsShow sumNews() " +sqlE.getMessage());
            Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return sum;
    }
    /**
     * 本站注册的用户总数
     */
    public int sumUser(){
        int sum=0;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select count(userName) as total from newsusr;";
        try{
            rs = sdbo.executeQuery(strSql);
            try{
                rs.next();
                sum = rs.getInt("total");
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("NewsShow sumUser() " +nullE.getMessage());
                Debug.writeLog("NewsShow sumUser(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("NewsShow sumUser() " +sqlE.getMessage());
            Debug.writeLog("NewsShow sumUser(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return sum;
    }
    /**
     * 网站最近更新的时间
     */
    //判断初始有无值
    public boolean isTime(){
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isTime=false;
        String strSql = "select newsId from news where state=1;";
        try{
            rs = sdbo.executeQuery(strSql);
            try{
                rs.last();
                if(rs.getRow()>0){
                    isTime=true;
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("NewsShow isTime() " +nullE.getMessage());
                Debug.writeLog("NewsShow isTime(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("NewsShow isTime() " +sqlE.getMessage());
            Debug.writeLog("NewsShow isTime(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isTime;
    }
    //取得具体的时间
    public String lastTime(){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String newsTime = null;
        String strSql = "select newsTime from news where state=1 order by newsTime desc;";
        try{
            rs = sdbo.executeQuery(strSql);
            try{
                rs.next();
                newsTime=rs.getString("newsTime");
            }
            catch(NullPointerException nullE){
                System.out.print("NewsShow lastTime() " +nullE.getMessage());
                Debug.writeLog("NewsShow lastTime(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("NewsShow lastTime() " +sqlE.getMessage());
            Debug.writeLog("NewsShow lastTime(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return newsTime;
    }
    /**
     * 判断有无的文章
     */
    public boolean isNews(){
        boolean isNews=false;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select newsId from news where state=1;";
        try{
            rs = sdbo.executeQuery(strSql);
            try{
                rs.last();
                if(rs.getRow()>0){
                    isNews=true;
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("NewsShow isTopNews() " +nullE.getMessage());
                Debug.writeLog("NewsShow isTopNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("NewsShow isTopNews() " +sqlE.getMessage());
            Debug.writeLog("NewsShow isTopNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isNews;
    }
    /**
     * 判断有无前缀的文章
     * @return
     */
    public boolean isTopNews(){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isTopNews=false;
        ResultSet rs=null;
        String strSql = "select newsId from news where state=1 and top=1;";
        try{
            rs = sdbo.executeQuery(strSql);
            try{
                rs.last();
                if(rs.getRow()>0){
                    isTopNews=true;
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("NewsShow isTopNews() " +nullE.getMessage());
                Debug.writeLog("NewsShow isTopNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("NewsShow isTopNews() " +sqlE.getMessage());
            Debug.writeLog("NewsShow isTopNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isTopNews;
    }
    /**
     * 最新的记录(前缀)
     */
    public Iterator topNews(){
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector vector=new Vector();
        String strSql = "select newsId,classId,headTitle from news where state=1 and top=1 order by newsTime desc limit 0,1;";
        try{
            rs = sdbo.executeQuery(strSql);
            try{
                while(rs.next()){
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setClassId(rs.getInt("classId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    vector.add(tableNews);
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("NewsShow topNews() " +nullE.getMessage());
                Debug.writeLog("NewsShow topNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("NewsShow topNews() " +sqlE.getMessage());
            Debug.writeLog("NewsShow topNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return vector.iterator();
    }
    /**
     *   判断最新的文章（不用前缀）
     */
    public boolean isNewNews(){
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isNewNews=false;
        Vector vector=new Vector();
        String strSql = "select newsId from news where state=1 and top=0;";
        try{
            rs = sdbo.executeQuery(strSql);
            try{
                rs.last();
                if(rs.getRow()>0){
                    isNewNews=true;
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("NewsShow isNews() " +nullE.getMessage());
                Debug.writeLog("NewsShow isNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("NewsShow isNews() " +sqlE.getMessage());
            Debug.writeLog("NewsShow isNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isNewNews;
    }
    /**
     * 最新的文章（不用前缀）
     */
    public Iterator newNews(){
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector vector=new Vector();
        String strSql = "select newsId,classId,headTitle from news where state=1 and top=0 order by newsTime desc limit 0,9;";
        try{
            rs = sdbo.executeQuery(strSql);
            try{
                while(rs.next()){
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setClassId(rs.getInt("classId"));
                    vector.add(tableNews);
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("NewsShow newNews() " +nullE.getMessage());
                Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("NewsShow newNews() " +sqlE.getMessage());
            Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return vector.iterator();
    }
    /**
     * 热门文章
     */
    public Iterator hotNews(){
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector vector=new Vector();
        String strSql = "select newsId,headTitle,hits,DATE_FORMAT(newsTime,'%Y-%m-%d') as time from news where state=1 order by hits desc limit 0,10;";
        try{
            rs = sdbo.executeQuery(strSql);
            try{
                while(rs.next()){
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setHits(rs.getInt("hits"));
                    tableNews.setNewsTime(rs.getString("time"));
                    vector.add(tableNews);
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("NewsShow newNews() " +nullE.getMessage());
                Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("NewsShow newNews() " +sqlE.getMessage());
            Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return vector.iterator();
    }
    /**
     * 列出文章
     */
    public Iterator listNews(int newsId){
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector vector=new Vector();
        String strSql = "select * from news where newsId=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,newsId);
            rs = sdbo.executeQuery();
            try{
                while(rs.next()){
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setClassId(rs.getInt("classId"));
                    tableNews.setKindId(rs.getInt("kindId"));
                    tableNews.setMyOther(rs.getInt("myOther"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setContent(rs.getString("content"));
                    tableNews.setConnect(rs.getString("connect"));
                    tableNews.setAuthor(rs.getString("author"));
                    tableNews.setEditor(rs.getString("editor"));
                    tableNews.setNewsFrom(rs.getString("newsFrom"));
                    tableNews.setHits(rs.getInt("hits"));
                    tableNews.setNewsTime(rs.getString("newsTime"));
                    vector.add(tableNews);
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("NewsShow newNews() " +nullE.getMessage());
                Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("NewsShow newNews() " +sqlE.getMessage());
            Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return vector.iterator();
    }
    //是否为原创
    public String valueMyOther(int intMyOther){
        String strMyOther="原创";
        if(intMyOther==1){
            strMyOther="转载";
        }
        return strMyOther;
    }
    /**
     * 更新文章的阅读次数
     */
    public void upReadTime(int newsId){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "update news set hits=hits+1 where newsId='"+newsId+"';";
        try{
            sdbo.executeUpdate(strSql);
        }
        catch(Exception e){
            System.out.print("NewsShow newNews() " +e.getMessage());
            Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     * 热门文章(listHotNews)
     */
    public Iterator listHotNews(int kindId){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        Vector vector=new Vector();
        String strSql = "select newsId,headTitle from news where state=1 and KindId=? order by hits desc limit 0,8;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,kindId);
            rs = sdbo.executeQuery();
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
                System.out.print("NewsShow newNews() " +nullE.getMessage());
                Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("NewsShow newNews() " +sqlE.getMessage());
            Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return vector.iterator();
    }
    //栏目的值
    public String strClass(int classId){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strClass=null;
        String strSql = "select content from newsclass where classId=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,classId);
            rs = sdbo.executeQuery();
            try{
                rs.next();
                strClass=rs.getString("content");
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("NewsShow newNews() " +nullE.getMessage());
                Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("NewsShow newNews() " +sqlE.getMessage());
            Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return strClass;
   }
   //类别的值
   public String strKind(int kindId){
       if (sdbo==null)
           sdbo = SQLDBOperator.getInstance("Connection");
       ResultSet rs=null;
       String strKind=null;
       String strSql = "select content from newskind where kindId=?;";
       try{
           sdbo.prepareStatement(strSql);
           sdbo.setInt(1,kindId);
           rs = sdbo.executeQuery();
           try{
               rs.next();
               strKind=rs.getString("content");
               rs.close();
           }
           catch(NullPointerException nullE){
               System.out.print("NewsShow newNews() " +nullE.getMessage());
               Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
           }
       }
       catch(SQLException sqlE){
           System.out.print("NewsShow newNews() " +sqlE.getMessage());
           Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
       }
       finally{
           sdbo.Close();
       }
       return strKind;
   }
}