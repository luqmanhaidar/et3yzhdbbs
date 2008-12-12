package com.ntsky.common;
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

public class Counter {
    private SQLDBOperator sdbo=null;
    /**
     * 增加1,counter
     */
    public boolean isIp(String ip){
        boolean isIp=false;
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs=null;
        String strSql = "select * from newscommon where ip=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1,ip);
            rs = sdbo.executeQuery();
            try{
                rs.last();
                if(rs.getRow()>0){
                    isIp=true;
                }
                rs.close();
            }
            catch(NullPointerException nullE){
                System.out.print("Counter isIp() " +nullE.getMessage());
                Debug.writeLog("Counter isIp(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        }
        catch(SQLException sqlE){
            System.out.print("Counter isIp() " +sqlE.getMessage());
            Debug.writeLog("Counter isIp(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
        return isIp;
    }
    /**
     * counter的值
     * @param ip
     */
    public int counter(){
        int counter = 0;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select * from newscommon;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.next();
                counter=rs.getInt("counter");
                rs.close();
            }
            catch (NullPointerException nullE) {
                System.out.print("Counter isIp() " + nullE.getMessage());
                Debug.writeLog("Counter isIp(), Exception Occured ! Info :" +
                               nullE.getLocalizedMessage());
            }
        }
        catch (SQLException sqlE) {
            System.out.print("Counter isIp() " + sqlE.getMessage());
            Debug.writeLog("Counter isIp(), Exception Occured ! Info :" +
                           sqlE.getLocalizedMessage());
        }
        finally {
            sdbo.Close();
        }
        return counter;
    }
    /**
     * 更新浏览次数
     * @param newsId
     */
    public void upCounter(String ip){
        if (sdbo==null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "update newscommon set counter=counter+1,ip=?;";
        try{
            sdbo.prepareStatement(strSql);
            sdbo.setString(1,ip);
            sdbo.executeUpdate();
        }
        catch(Exception e){
            System.out.print("Counter upCounter() " +e.getMessage());
            Debug.writeLog("Counter upCounter(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        finally{
            sdbo.Close();
        }
    }
}