package com.ntsky;

import java.sql.*;
import java.util.*;

import com.ntsky.common.*;
import com.ntsky.database.*;
import com.ntsky.persistence.*;

public class abc {
    private SQLDBOperator sdbo=null;
    public void insSql(int myOther,int classId,int kindId,String headTitle,String content,String connect,String author,String editor,String newsFrom,int hits,int top,int state,int tag,String newsTime){
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "insert into news(myOther,classId,kindId,headTitle,content,connect,author,editor,newsFrom,hits,top,state,tag,newsTime) values("+myOther+",'"+classId+"','"+kindId+"','"+headTitle+"','"+content+"','"+connect+"','"+author+"','"+editor+"','"+newsFrom+"',"+hits+","+top+","+state+","+tag+",'"+newsTime+"');";
        try{
            rs = sdbo.executeQuery(strSql);

        }
        catch(Exception sqlE){
            System.out.print("NewsShow topNews() " +sqlE.getMessage());
            Debug.writeLog("NewsShow topNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
    /**
     *
     */
    public void insSql1(String userName,String passWd,int sex,String question,String answer,String emailAddr,String qq,String http,String regTime){
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "insert into newsusr(userName,passWd,sex,question,answer,emailAddr,qq,http,regTime) values('"+userName+"','"+passWd+"',"+sex+",'"+question+"','"+answer+"','"+emailAddr+"','"+qq+"','"+http+"','"+regTime+"');";
        try{
            rs = sdbo.executeQuery(strSql);

        }
        catch(Exception sqlE){
            System.out.print("NewsShow topNews() " +sqlE.getMessage());
            Debug.writeLog("NewsShow topNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }

    public void insql2(int noteId,String userName,String email,String qq,String url,String headTitle,String content,String image,String noteTime){
        ResultSet rs=null;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "insert into NOTEGuest(noteId,userName,email,qq,url,headTitle,content,image,noteTime) values('"+noteId+"','"+userName+"','"+email+"','"+qq+"','"+url+"','"+headTitle+"','"+content+"','"+image+"','"+noteTime+"');";
        try{
            rs = sdbo.executeQuery(strSql);

        }
        catch(Exception sqlE){
            System.out.print("NewsShow topNews() " +sqlE.getMessage());
            Debug.writeLog("NewsShow topNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }

}