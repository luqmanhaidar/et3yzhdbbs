package com.ntsky.common;

import java.io.*;
import java.util.*;

/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: ע�������ļ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */
public class EnvironmentConfig {
	static EnvironmentConfig ec;//��������ec
	private static Hashtable register = new Hashtable();//��̬�����ʼ��[����������֮ǰ����]

    /**
     * ReadConfig ������ע�⡣
     */
    private EnvironmentConfig() {
        super();
    }
    /**
     * ȡ��EnvironmentConfig��һ��ʵ��
     * @return ec
     */
    public static EnvironmentConfig getInstance() {
        if (ec==null)
            ec = new EnvironmentConfig();//����EnvironmentConfig����
        return ec;//����EnvironmentConfig����
    }

    /**
     * ��ȡ�����ļ�
     * @param java.lang.String fileName
     * @return Properties
     */

    public Properties getProperties(String fileName) {
        InputStream is=null;//����������is
        Properties p = null;
        try{
            p = (Properties)register.get(fileName);//��fileName����һ��HashTable
            /**
             * ���Ϊ�վͳ���������ļ�
             */
            if (p==null) {
                try{
                    is = new FileInputStream(fileName);//����������
                }
                catch(Exception e){
                    if (fileName.startsWith("/"))
                        //��getResourceAsStream()�������ڶ�λ�����ⲿ�ļ���
                        is = EnvironmentConfig.class.getResourceAsStream(fileName);
                    else
                        is = EnvironmentConfig.class.getResourceAsStream("/"+fileName);
                }
                p = new Properties();
                p.load(is);//����������
                register.put(fileName, p);//��������HashTable
                is.close();//�ر�������
            }
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
        return p;//����Properties����
    }

    /**
     * �˴����뷽��������
     * �������ڣ�(2003-8-10 12:30:09)
     * @param fileName java.lang.String
     * @param strKey java.lang.String
     */

    public String getPropertyValue(String fileName, String strKey) {
        Properties p = getProperties(fileName);
        try{
            return (String)p.get(strKey);
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
        return null;
    }
}
