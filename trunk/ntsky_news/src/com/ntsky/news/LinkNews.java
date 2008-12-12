package com.ntsky.news;
/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 相关链接</p>
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

public class LinkNews {
    public SQLDBOperator sdbo=null;

    /**
     * 栏目的循环显示
     * @param connect
     * @return
     */
    public Iterator showClass(){
        Vector vector = new Vector();
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql= "select * from newsclass;";
        try{
            rs=sdbo.executeQuery(strSql);
            try{
                while(rs.next()){
                    NEWSClass tableClass=new NEWSClass();
                    tableClass.setClassId(rs.getInt("classId"));
                    tableClass.setContent(rs.getString("content"));
                    vector.add(tableClass);
                }
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
        return vector.iterator();
    }
    /**
     * 判断有无相关连接
     */
    public boolean isConnectLink(String connect){
        boolean isLink = false;
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql= "select newsId from news where state=1 and connect like '%"+connect+"%';";
        try{
            rs=sdbo.executeQuery(strSql);
            try{
                rs.last();
                if(rs.getRow()>0){
                    isLink=true;
                }
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
        return isLink;
    }
    /**
     * 相关连接
     */
    public Iterator connectLink(String connect){
        Vector vector = new Vector();
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select newsId,headTitle,DATE_FORMAT(newsTime,'%Y-%m-%d') as time from news where state=1 and connect like '%"+connect+"%' order by newstime desc limit 0,8;";
        try{
            rs=sdbo.executeQuery(strSql);
            try{
                while(rs.next()){
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setNewsTime(rs.getString("time"));
                    vector.add(tableNews);
                }
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
        return vector.iterator();
    }
    /**
     * ************************************
     * 对news的相关操作
     * 根据classId的值取得具体类别
     */
    /**
     * 判断类别有无
     */
    public boolean isKind(int classId){
        boolean isKind=false;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select kindId from newskind where classId=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,classId);
            rs=sdbo.executeQuery();
            try{
                rs.last();
                if(rs.getRow()>0){
                    isKind=true;
                }
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
        return isKind;
    }
    /**
     * 根据classId的值取得具体类别
     */
    public Iterator kindShow(int classId){
        Vector vector = new Vector();
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select * from newskind where classId=? order by kindId asc;";
        try{
            sdbo.prepareStatement(strSql);
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
        return vector.iterator();
    }
    /**
     * 判断对应类别有无文章
     * @param kindId
     * @return
     */
    public boolean isNewsShow(int kindId){
        boolean isNews=false;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select newsId from news where kindId=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,kindId);
            rs=sdbo.executeQuery();
            try{
                rs.last();
                if(rs.getRow()>0){
                    isNews=true;
                }
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
        return isNews;
    }
    /**
     * 类别对应的文章按时间排序(取6条记录)
     * @param classId
     * @return
     */
    public Iterator newsShow(int kindId){
        Vector vector = new Vector();
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select newsId,headTitle,DATE_FORMAT(newsTime,'%Y-%m-%d') as time from news where state=1 and kindId=? order by newsTime desc limit 0,6;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,kindId);
            rs=sdbo.executeQuery();
            try{
                while(rs.next()){
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setNewsTime(rs.getString("time"));
                    vector.add(tableNews);
                }
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
        return vector.iterator();
    }
    /**
     * 热门文章
     */
    public Iterator hotNewsClass(int classId){
        Vector vector = new Vector();
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select newsId,headTitle from news where state=1 and classId=? order by hits desc limit 0,8;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,classId);
            rs=sdbo.executeQuery();
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
        return vector.iterator();
    }
    /**
     ****************************************
     * newsII的操作
     * newsII的具体列表
     */
    /**
     * 新闻总数
     */
    public int sumNews(int kindId){
        int sum=0;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select count(newsId) as total from news where state=1 and kindId=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,kindId);
            rs=sdbo.executeQuery();
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
     * 列出具体的新闻
     */
    public Iterator listNews(int kindId){
        Vector vector = new Vector();
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select newsId,headTitle,DATE_FORMAT(newsTime,'%Y-%m-%d') as time from news where state=1 and kindId=? order by newsTime desc;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,kindId);
            rs=sdbo.executeQuery();
            try{
                while(rs.next()){
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setNewsTime(rs.getString("time"));
                    vector.add(tableNews);
                }
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
        return vector.iterator();
    }
    /**
     * 某个类别的热门文章
     */
    public Iterator hotNewsKind(int kindId){
        Vector vector = new Vector();
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select newsId,headTitle from news where kindId=? order by hits desc limit 0,6;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,kindId);
            rs=sdbo.executeQuery();
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
        return vector.iterator();
    }
    /**
     * 类别的名字
     */
    public String strKind(int kindId){
        String strKind=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select content from newskind where kindId=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,kindId);
            rs=sdbo.executeQuery();
            try{
                rs.next();
                strKind=rs.getString("content");
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
        return strKind;
    }
    /**
     * 最近更新的时间
     */
    public String nearTime(int kindId){
        String strTime=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select DATE_FORMAT(newsTime,'%Y年%c月%e日  %k时%i分%S秒') as time from news where kindId='"+kindId+"' order by newsTime limit 0,1;";
        try{
            //sdbo.prepareStatement(strSql);
           // sdbo.setInt(1,kindId);
            rs=sdbo.executeQuery(strSql);
            try{
                rs.next();
                strTime=rs.getString("time");
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
        return strTime;
    }
}