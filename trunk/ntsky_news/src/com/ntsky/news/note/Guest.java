package com.ntsky.news.note;
/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 用户留言发表</p>
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

public class Guest {
    private SQLDBOperator sdbo=null;
    /**
     * 添加留言
     */
    public void insNote(String userName,int sex,String email,String qq,String url,String headTitle,String content,String image){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "insert into noteguest(userName,sex,email,qq,url,headTitle,content,image,noteTime) values(?,?,?,?,?,?,?,?,?);";
        try{
            sdbo.prepareStatement(sql);
            sdbo.setString(1,CodeFilter.toHtml(userName));
            sdbo.setInt(2,sex);
            sdbo.setString(3,CodeFilter.toHtml(email));
            sdbo.setString(4,CodeFilter.toHtml(qq));
            sdbo.setString(5,CodeFilter.toHtml(url));
            sdbo.setString(6,CodeFilter.toHtml(headTitle));
            sdbo.setString(7,CodeFilter.toHtml(content));
            sdbo.setString(8,CodeFilter.toHtml(image));
            sdbo.setString(9,DateUtil.getNowDate());
            sdbo.executeUpdate();
        }
        catch(Exception e){
            System.out.print("Guest insNote() " + e.getMessage());
            Debug.writeLog("Guest insNote(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     * 记录总数
     */
    public int sumNote(){
        int sum=0;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select count(noteId) as total from noteguest;";
        try{
            ResultSet rs = sdbo.executeQuery(sql);
            rs.next();
            sum=rs.getInt("total");
            rs.close();
        }
        catch(Exception e){
            System.out.print("Guest sumNote() " + e.getMessage());
            Debug.writeLog("Guest sumNote(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return sum;
    }
    //列出记录
    public Iterator listNote(){
        Vector vector = new Vector();
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select * from noteguest order by noteTime desc;";
        try{
            ResultSet rs = sdbo.executeQuery(sql);
            while(rs.next()){
                NOTEGuest guestTable = new NOTEGuest();
                guestTable.setNoteId(rs.getInt("noteId"));
                guestTable.setUserName(rs.getString("userName"));
                guestTable.setSex(rs.getInt("sex"));
                guestTable.setEmail(rs.getString("email"));
                guestTable.setQq(rs.getString("qq"));
                guestTable.setUrl(rs.getString("url"));
                guestTable.setHeadTitle(rs.getString("headTitle"));
                guestTable.setContent(rs.getString("content"));
                guestTable.setImage(rs.getString("image"));
                guestTable.setNoteTime(rs.getString("noteTime"));
                vector.add(guestTable);
            }
            rs.close();
        }
        catch(Exception e){
            System.out.print("Guest listNote() " + e.getMessage());
            Debug.writeLog("Guest listNote(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return vector.iterator();
    }
    /**
     * 性别取值
     */
    public String sexStr(int sex){
        String sexStr="男";
        if(sex==1){
            sexStr="女";
            return sexStr;
        }
        return sexStr;
    }
    /**
     * 回复显示
     */
    //判断有无回复
    public boolean isReply(int noteId){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isReply=false;
        String sql = "select count(replyId) as total from notereply where noteId=?;";
        try{
            sdbo.prepareStatement(sql);
            sdbo.setInt(1, noteId);
            ResultSet rs = sdbo.executeQuery();
            rs.next();
            int sum = rs.getInt("total");
            if(sum>0){
                isReply=true;
            }
            rs.close();
        }
        catch(Exception e){
            System.out.print("Guest isReply() " + e.getMessage());
            Debug.writeLog("Guest isReply(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isReply;
    }
    //列出回复的内容
    public Iterator listReply(int noteId){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select replyTime,content from notereply where noteId=?;";
        Vector vector = new Vector();
        try{
            sdbo.prepareStatement(sql);
            sdbo.setInt(1,noteId);
            ResultSet rs = sdbo.executeQuery();
            while(rs.next()){
                NOTEReply replyTable = new NOTEReply();
                replyTable.setReplyTime(rs.getString("replyTime"));
                replyTable.setContent(rs.getString("content"));
                vector.add(replyTable);
            }
            rs.close();
        }
        catch(Exception e){
            System.out.print("Guest listreply() " + e.getMessage());
            Debug.writeLog("Guest listreply(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return vector.iterator();
    }
}