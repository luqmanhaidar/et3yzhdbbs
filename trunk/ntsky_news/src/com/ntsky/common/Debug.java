package com.ntsky.common;

import java.io.*;
import java.util.Date;
import java.util.Properties;
import java.text.SimpleDateFormat;

import com.ntsky.common.EnvironmentConfig;
import com.ntsky.common.ISiteEnvironment;
/**
 *
 * <p>Title: NTsky新闻发布</p>
 * <p>Description:调试 </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */
public class Debug {
    private static int debugNo = 0;//Debug序列号
    /**
     * Debug 构造子注解。
     */
    public Debug() {
        super();
    }

    private static synchronized PrintWriter getLogStream(String logFileName) {//取得日志输入流（日志文件）
        SimpleDateFormat _dateFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentTime = null;
        PrintWriter pw = null;//打印对象
		try{
            File currentFile = new File(logFileName);
			if(currentFile.length() > 1000000){//当文件大于1M的话就备份
                File backupFile = new File(logFileName + ".bak");//自动添加.bak
                if(backupFile.exists()){//备份文件退出
                    currentTime = _dateFormatter.format(new Date(System.currentTimeMillis()));
                    File backupRenamedFile = new File(logFileName + ".bak." + currentTime);
                    try{
                        backupFile.renameTo(backupRenamedFile);//备份文件取名
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
                try{
                    currentFile.renameTo(backupFile);
                }
                catch(Exception ex){
					ex.printStackTrace();
                }
            }
            //日志编号加一
            debugNo += 1;
            FileOutputStream os = new FileOutputStream(logFileName,true);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            pw = new PrintWriter(bos, true);
            pw.println("\n");
        }
        catch(Exception ex){
            pw = new PrintWriter(System.err, true);
            _dateFormatter = new SimpleDateFormat("'In' yyyyMMdd 'at' HH a mm 'minutes' ss 'seconds'");
            currentTime = _dateFormatter.format(new Date(System.currentTimeMillis()));
            pw.println("\n");
            pw.println(currentTime + ":" + " Exception Occured While Trying To Open Log File: " + logFileName + ".[" + ex.toString() + "]");
        }
        return pw;
    }
    /**
     * 书写日志
     * @param info
     */
    public static synchronized void writeLog(String info) {//静态方法
        String isOn="";
        Properties p = EnvironmentConfig.getInstance().getProperties("/properties/ntsky.properties");//读取配置文件
        isOn = p.getProperty("isLog");//如果islog的值为isOn
        //如果isLog的值([null；或者"";或者（on和true）])成立，isOn的值为off
        if ((isOn==null) || (isOn.trim()=="")||((!(isOn.trim().equalsIgnoreCase("on")) && (!(isOn.trim().equalsIgnoreCase("true"))))))
            isOn ="off";
        String logPath = p.getProperty("logPath");//日志路径
        //如果logPath的值(null;或者"")logPath="/log/"
        if ((logPath==null) || (logPath.trim().equalsIgnoreCase("")))
            logPath="/log/";
        logPath.replace('\\','/');

        if (!logPath.endsWith(File.separator))
            logPath += File.separator;

        String logFile = p.getProperty("logFile");//取得日志名
        //如果logFile的值(null;或者"")logPath="site.log"
        if ((logFile==null) || (logFile.trim().equalsIgnoreCase("")))
            logFile="Site.log";
        String logFileName = logPath + logFile;
        SimpleDateFormat _dateFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentTime = null;
        PrintWriter pw = null;
        //isOn的值为true或者isOn的值为on,getLogStream方法取得日志
        if(isOn.equalsIgnoreCase("true")||isOn.trim().equalsIgnoreCase("on")){
            pw = getLogStream(logFileName);
            _dateFormatter = new SimpleDateFormat("'On' yyyyMMdd 'at' HH:mm:ss '---'");
            currentTime = _dateFormatter.format(new Date(System.currentTimeMillis()));
            /**
             * 输出到日志的信息
             */
            pw.println(currentTime + " Debug Starts No " + String.valueOf(debugNo) + " ************");
            pw.println("\n");//回车
            pw.println(info);//输出显示信息
            pw.flush();
            pw.close();
        }
    }
}
