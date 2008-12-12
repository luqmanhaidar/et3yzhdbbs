package com.ntsky.news;

import java.io.*;
import java.util.*;
import java.sql.*;

import com.ntsky.common.*;
import com.ntsky.database.*;
import com.ntsky.pool.DBConnectionManager;

/**
 * <p>Title: NTsky新闻发布</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */

public class Test {
    private SQLDBOperator sdbo=null;
    /**
    public Test() {
        super();
        sdbo = SQLDBOperator.getInstance("Connection");
    }
     */
    public int getTotalUser() {
        int total = 0;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection"); //
            //SQLDBOperator sdbo1 = SQLDBOperator.getInstance("Connection");
        String strQuery = "select count(*) as total from usr where purview=?;";

        int str = 1;
        try {
            sdbo.prepareStatement(strQuery);
            //sdbo.getPreparedStatement();
            sdbo.setInt(1, str);

            ResultSet rs = sdbo.executeQuery();

            try {
                if (rs.next()) {
                    total = rs.getInt("total");
                }
            }
            catch (Exception e) {
                e.printStackTrace(System.out);
                Debug.writeLog("In getTotalss(), Exception Occured ! Info: " +
                               e.getLocalizedMessage());

            }
        }catch (Exception e) {
                e.printStackTrace(System.out);
                Debug.writeLog("In getTotal(), Exception Occured ! Info: " +
                               e.getLocalizedMessage());
        }
        return total;
   }
    /**
    public SQLDBOperator getSQLDBOperator(){
        return this.sdbo;
    }
        */
}