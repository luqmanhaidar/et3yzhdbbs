/*
 * @(#)DBPool.java 1.1 2005-5-31
 *
 * Copyright 2005, Guangdong Information & Engineer Co. Ltd.
 * GDIE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gdie.db;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;

import org.apache.log4j.Logger;

/**
 * <p>
 * Title: DBPool
 * </p>
 * <p>
 * Description: 数据库连接池程序
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: www.gdie.com
 * </p>
 * 
 * @author mofan@gdie.com
 * @version 1.0
 */
public class DBPool {
	
	private static Logger log = Logger.getLogger(DBPool.class);
	
    private static String dbDriver;

    private static String dbUrl;

    private static String dbUser;

    private static String dbPassWd;

    private static String dbEnv;

    private static String dbPoolSource;

    private static Connection staticConn = null;

    private Context env = null;

    private DataSource source = null;

    private boolean poolFlag = false; //连接是否来源于连接池

    private static boolean flag = false;

    /**
     * 构造函数
     * 初始化数据链接参数
     */
    public DBPool() {
    	String thisName = "DBPool.DBPool() ";
        if (!flag) {
            try {
                PropManager prop = new PropManager();
                dbDriver = prop.getDbDriver();
                dbUrl = prop.getDbUrl();
                dbUser = prop.getDbUser();
                dbPassWd = prop.getDbPassWd();
                dbEnv = prop.getDbEnv();
                dbPoolSource = prop.getDbPoolSource();
                flag = true;
            } catch (Exception e) {
            	log.error(thisName+" Exception:"+e.getMessage());
            }
        }
    }

    /**
     * 直接向数据库申请连接
     * @return 正常返回连接对象
     * @throws Exception 抛出错误
     */
    public Connection getNoPoolConnection() throws Exception {
        String thisName = "DBPool.getNoPoolConnection()";
        Connection noPoolConn = null;
        try {
        	Class.forName(dbDriver).newInstance();
        	noPoolConn = DriverManager.getConnection(dbUrl, dbUser, dbPassWd);
        	poolFlag = false;
        }catch(Exception e){
        	log.error(thisName+" Exception:"+e.getMessage());
        	throw new Exception("服务器太忙，请稍后再试");
        }
        return noPoolConn;
    }

    /**
     * 从连接池中获得连接
     * @return 正常返回连接对象；如果不能获得连接，则屏蔽错误, 返回null
     */
    private Connection getPoolConnection()  {
    	String thisName = "DBPool.getPoolConnection()";
    	poolFlag = false;
        try {
            /*env = (Context) new InitialContext().lookup(dbEnv);
            source = (DataSource) env.lookup(dbPoolSource);*/
        	env=new InitialContext();
        	source=(DataSource)env.lookup("java:comp/env/jdbc/gdida");
            Connection poolConn = source.getConnection();
            /*if (poolConn.isClosed()) {
                poolConn = null;
            }else{
                poolFlag = true;
            }*/
            poolFlag = true;
            return poolConn;
        } catch (Exception e) {
        	log.error(thisName+" Exception:"+e.getMessage());
        	return null;
        }
    }

    /**
     * 获得连接
     * 此链接可用于任何场合，适合事务型SQL，用完需要立即关闭。
     * @return
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
//    	long d1 = (new java.util.Date()).getTime();
        Connection conn = this.getPoolConnection();
//        long d2 = (new java.util.Date()).getTime();
//        System.out.println("getConnection elapsed milliseconds:"+(d2-d1));
        if (conn == null) {
        	throw new Exception("很抱歉，服务器忙，暂时不能接受您的请求，请稍后再试");
//        	long dn1 = (new java.util.Date()).getTime();
//            conn = this.getNoPoolConnection();
//            long dn2 = (new java.util.Date()).getTime();
//            System.out.println("getNoPoolConnection elapsed milliseconds:"+(dn2-dn1));
        }
        
        return conn;
    }

    /**
     * 获得静态连接
     * 此连接仅用于非事务型的查询(SELECT SQL)操作，用完不必关闭(con.close())，
     * 一直保持在内存中，直到执行closeStaticConnection()
     * @return
     * @throws Exception
     */
    public static Connection getStaticConnection() throws Exception {
        if (staticConn!=null) 
        {if(!staticConn.isClosed())
            return staticConn;
        }
        DBPool dbpool = new DBPool();
        staticConn = dbpool.getConnection();
        log.info("DBPool.getStaticConnection(): New a Connconction");
        return staticConn;
    }
    
    public String getDbDriverName() {
        return dbDriver;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * 当前获得连接是否来自连接池
     * @return
     */
    public boolean isPoolConnect() {
        return poolFlag;
    }
    /**
     * 静态连接关闭方法
     */
    public static void closeStaticConnection() {
        try {
            if (staticConn!=null && !staticConn.isClosed()) {
                log.info("DBPool.finalize(): staticConn.Close()");
                staticConn.close();
                staticConn = null;
            }
        }catch(Exception e){
        	log.error("DBPool.finalize() Exception:"+e.getMessage());
        }
    }
}