package com.ntsky.common;

import java.io.*;
import java.util.*;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 注册配置文件</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */
public class EnvironmentConfig {
	static EnvironmentConfig ec;//创建对象ec
	private static Hashtable register = new Hashtable();//静态对象初始化[在其它对象之前进行]

    /**
     * ReadConfig 构造子注解。
     */
    private EnvironmentConfig() {
        super();
    }
    /**
     * 取得EnvironmentConfig的一个实例
     * @return ec
     */
    public static EnvironmentConfig getInstance() {
        if (ec==null)
            ec = new EnvironmentConfig();//创建EnvironmentConfig对象
        return ec;//返回EnvironmentConfig对象
    }

    /**
     * 读取配置文件
     * @param java.lang.String fileName
     * @return Properties
     */

    public Properties getProperties(String fileName) {
        InputStream is=null;//定义输入流is
        Properties p = null;
        try{
            p = (Properties)register.get(fileName);//将fileName存于一个HashTable
            /**
             * 如果为空就尝试输入进文件
             */
            if (p==null) {
                try{
                    is = new FileInputStream(fileName);//创建输入流
                }
                catch(Exception e){
                    if (fileName.startsWith("/"))
                        //用getResourceAsStream()方法用于定位并打开外部文件。
                        is = EnvironmentConfig.class.getResourceAsStream(fileName);
                    else
                        is = EnvironmentConfig.class.getResourceAsStream("/"+fileName);
                }
                p = new Properties();
                p.load(is);//加载输入流
                register.put(fileName, p);//将其存放于HashTable
                is.close();//关闭输入流
            }
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
        return p;//返回Properties对象
    }

    /**
     * 此处插入方法描述。
     * 创建日期：(2003-8-10 12:30:09)
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
