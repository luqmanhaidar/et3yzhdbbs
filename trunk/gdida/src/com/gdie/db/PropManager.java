/**
 * @(#)PropManager.java 1.1 2005-5-31
 *
 * Copyright 2005, Guangdong Information & Engineer Co. Ltd.
 * GDIE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gdie.db;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class PropManager {
	private static Logger log = Logger.getLogger(PropManager.class);
	private static String dbDriver;
	private static String dbUrl;
	private static String dbUser;
	private static String dbPassWd;
	private static String dbEnv;
	private static String dbPoolSource;
	private static boolean flag;
	
	public PropManager() {
		if(!flag){
			init();
		}
	}
	public String getDbUser() {
		return dbUser;
	}
	
	public String getDbUrl() {
		return dbUrl;
	}
	
	public String getDbPassWd() {
		return dbPassWd;
	}	
	
	public String getDbDriver() {
		return dbDriver;
	}
	
	public String getDbPoolSource() {
		return dbPoolSource;
	}
	
	public String getDbEnv() {
		return dbEnv;
	}
	
	private void init(){
		String thisName = "PropManager.init()";
		try{
			ResourceBundle res;
			res = ResourceBundle.getBundle("config");
			dbDriver = res.getString("dbDriver");
			dbUser = res.getString("dbUser");
			dbPassWd = res.getString("dbPassWd");
			dbUrl= res.getString("dbUrl");
			dbEnv= res.getString("dbEnv");
			dbPoolSource= res.getString("dbPoolSource");
			
			flag = true;
		}catch(Exception e){
			log.error(thisName + " Exception:"+e.getMessage());
/*			dbDriver = "dm4.jdbc.driver.Dm4Driver";
			dbUrl = "jdbc:dm4://localhost/GDJRB";
			dbUser = "SYSDBA";
			dbPassWd = "SYSDBA";
			dbEnv = "java:comp/env";
			dbPoolSource = "jdbc/gdjrb";
*/         
			dbDriver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
			dbUrl = "jdbc:microsoft:sqlserver://localhost:1433; DatabaseName=gdbb";
			dbUser = "sa";
			dbPassWd = "sa";
			dbEnv = "java:comp/env";
			dbPoolSource = "jdbc/gdbb";
			flag = false;
			return;
		}
	}
}
