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
 * Description: ���ݿ����ӳس���
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

    private boolean poolFlag = false; //�����Ƿ���Դ�����ӳ�

    private static boolean flag = false;

    /**
     * ���캯��
     * ��ʼ���������Ӳ���
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
     * ֱ�������ݿ���������
     * @return �����������Ӷ���
     * @throws Exception �׳�����
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
        	throw new Exception("������̫æ�����Ժ�����");
        }
        return noPoolConn;
    }

    /**
     * �����ӳ��л������
     * @return �����������Ӷ���������ܻ�����ӣ������δ���, ����null
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
     * �������
     * �����ӿ������κγ��ϣ��ʺ�������SQL��������Ҫ�����رա�
     * @return
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
//    	long d1 = (new java.util.Date()).getTime();
        Connection conn = this.getPoolConnection();
//        long d2 = (new java.util.Date()).getTime();
//        System.out.println("getConnection elapsed milliseconds:"+(d2-d1));
        if (conn == null) {
        	throw new Exception("�ܱ�Ǹ��������æ����ʱ���ܽ��������������Ժ�����");
//        	long dn1 = (new java.util.Date()).getTime();
//            conn = this.getNoPoolConnection();
//            long dn2 = (new java.util.Date()).getTime();
//            System.out.println("getNoPoolConnection elapsed milliseconds:"+(dn2-dn1));
        }
        
        return conn;
    }

    /**
     * ��þ�̬����
     * �����ӽ����ڷ������͵Ĳ�ѯ(SELECT SQL)���������겻�عر�(con.close())��
     * һֱ�������ڴ��У�ֱ��ִ��closeStaticConnection()
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
     * ��ǰ��������Ƿ��������ӳ�
     * @return
     */
    public boolean isPoolConnect() {
        return poolFlag;
    }
    /**
     * ��̬���ӹرշ���
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