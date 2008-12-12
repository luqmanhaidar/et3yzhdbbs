package com.ntsky.common;

import java.io.*;
import java.util.Date;
import java.util.Properties;
import java.text.SimpleDateFormat;

import com.ntsky.common.EnvironmentConfig;
import com.ntsky.common.ISiteEnvironment;
/**
 *
 * <p>Title: NTsky���ŷ���</p>
 * <p>Description:���� </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */
public class Debug {
    private static int debugNo = 0;//Debug���к�
    /**
     * Debug ������ע�⡣
     */
    public Debug() {
        super();
    }

    private static synchronized PrintWriter getLogStream(String logFileName) {//ȡ����־����������־�ļ���
        SimpleDateFormat _dateFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentTime = null;
        PrintWriter pw = null;//��ӡ����
		try{
            File currentFile = new File(logFileName);
			if(currentFile.length() > 1000000){//���ļ�����1M�Ļ��ͱ���
                File backupFile = new File(logFileName + ".bak");//�Զ����.bak
                if(backupFile.exists()){//�����ļ��˳�
                    currentTime = _dateFormatter.format(new Date(System.currentTimeMillis()));
                    File backupRenamedFile = new File(logFileName + ".bak." + currentTime);
                    try{
                        backupFile.renameTo(backupRenamedFile);//�����ļ�ȡ��
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
            //��־��ż�һ
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
     * ��д��־
     * @param info
     */
    public static synchronized void writeLog(String info) {//��̬����
        String isOn="";
        Properties p = EnvironmentConfig.getInstance().getProperties("/properties/ntsky.properties");//��ȡ�����ļ�
        isOn = p.getProperty("isLog");//���islog��ֵΪisOn
        //���isLog��ֵ([null������"";���ߣ�on��true��])������isOn��ֵΪoff
        if ((isOn==null) || (isOn.trim()=="")||((!(isOn.trim().equalsIgnoreCase("on")) && (!(isOn.trim().equalsIgnoreCase("true"))))))
            isOn ="off";
        String logPath = p.getProperty("logPath");//��־·��
        //���logPath��ֵ(null;����"")logPath="/log/"
        if ((logPath==null) || (logPath.trim().equalsIgnoreCase("")))
            logPath="/log/";
        logPath.replace('\\','/');

        if (!logPath.endsWith(File.separator))
            logPath += File.separator;

        String logFile = p.getProperty("logFile");//ȡ����־��
        //���logFile��ֵ(null;����"")logPath="site.log"
        if ((logFile==null) || (logFile.trim().equalsIgnoreCase("")))
            logFile="Site.log";
        String logFileName = logPath + logFile;
        SimpleDateFormat _dateFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentTime = null;
        PrintWriter pw = null;
        //isOn��ֵΪtrue����isOn��ֵΪon,getLogStream����ȡ����־
        if(isOn.equalsIgnoreCase("true")||isOn.trim().equalsIgnoreCase("on")){
            pw = getLogStream(logFileName);
            _dateFormatter = new SimpleDateFormat("'On' yyyyMMdd 'at' HH:mm:ss '---'");
            currentTime = _dateFormatter.format(new Date(System.currentTimeMillis()));
            /**
             * �������־����Ϣ
             */
            pw.println(currentTime + " Debug Starts No " + String.valueOf(debugNo) + " ************");
            pw.println("\n");//�س�
            pw.println(info);//�����ʾ��Ϣ
            pw.flush();
            pw.close();
        }
    }
}
