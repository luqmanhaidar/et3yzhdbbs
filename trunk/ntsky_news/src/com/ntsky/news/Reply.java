package com.ntsky.news;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 回复文章</p>
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

public class Reply {
    private SQLDBOperator sdbo=null;
    private ResultSet rs = null;
    /**
     * 添加回复
     * @param newsId
     * @param user
     * @param content
     */
    public void insReply(int newsId,String user,String content){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "insert into newsreply(newsId,user,content,replyTime) values(?,?,?,?);";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,newsId);
            sdbo.setString(2,CodeFilter.toHtml(user));
            sdbo.setString(3,CodeFilter.toHtml(content));
            sdbo.setString(4,DateUtil.getStringDateShort());
            sdbo.executeUpdate();
        }
        catch(Exception e){
            System.out.print("Reply insReply() " +e.getMessage());
            Debug.writeLog("Reply insReply(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     * 判断有无回复
     * @param newsId
     * @return
     */
    public boolean isReply(int newsId){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isReply=false;
        String strSql = "select replyId from newsreply where newsId=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,newsId);
            rs = sdbo.executeQuery();
            try{
                rs.last();
                if(rs.getRow()>0){
                    isReply=true;
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("Reply listReply() " +nullE.getMessage());
                Debug.writeLog("Reply listReply(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Reply listReply() " +sqlE.getMessage());
            Debug.writeLog("Reply listReply(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isReply;
    }
    //列出所有回复
    public Iterator listReply(int newsId){
        Vector vector=new Vector();
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "select * from newsreply where newsId=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1,newsId);
            rs = sdbo.executeQuery();
            try{
                while(rs.next()){
                    NEWSReply tableReply = new NEWSReply();
                    tableReply.setNewsId(rs.getInt("newsId"));
                    tableReply.setContent(rs.getString("content"));
                    tableReply.setUser(rs.getString("user"));
                    tableReply.setReplyTime(rs.getString("replyTime"));
                    vector.add(tableReply);
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("Reply listReply() " +nullE.getMessage());
                Debug.writeLog("Reply listReply(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Reply listReply() " +sqlE.getMessage());
            Debug.writeLog("Reply listReply(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return vector.iterator();
    }
}