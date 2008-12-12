package com.ntsky.common;

import java.util.*;
import java.text.*;
import com.ntsky.*;

/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: ʱ�����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */

public class DateUtil {

    /**
     * yy-mm-dd HH:mm:SS��ʽ��ʱ��
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
     * yyyy-mm-dd hh:mm��ʽ��ʱ��
     * @return StringDate
     */

    public static String getStringDate() {
        Date dateTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strTime = format.format(dateTime);
        return strTime;
       }

    /**
     * yy-mm-dd��ʽ��ʱ��
     * @return StringDate
     */
    public static String getStringDateShort() {
        Date dateTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strTime = format.format(dateTime);
        return strTime;
    }
}