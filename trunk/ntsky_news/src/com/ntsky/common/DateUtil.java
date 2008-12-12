package com.ntsky.common;

import java.util.*;
import java.text.*;
import com.ntsky.*;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 时间参数</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */

public class DateUtil {

    /**
     * yy-mm-dd HH:mm:SS格式的时间
     * @return String Date
     */
    public static String getNowDate(){
        Date dateTime=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = format.format(dateTime);
        Debug.writeLog(strTime);
        return strTime;
    }
    /**
     * yyyy-mm-dd hh:mm格式的时间
     * @return StringDate
     */

    public static String getStringDate() {
        Date dateTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strTime = format.format(dateTime);
        return strTime;
       }

    /**
     * yy-mm-dd格式的时间
     * @return StringDate
     */
    public static String getStringDateShort() {
        Date dateTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strTime = format.format(dateTime);
        return strTime;
    }
}