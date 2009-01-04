package com.gdie.db;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class JdbcConn {
	private static Logger log = Logger.getLogger(JdbcConn.class);
	private String dbDriver="";
	private String dbUrl="";
	private String dbUser;
	private String dbPassWd;
	
	private void init(){
		ResourceBundle res;
		res = ResourceBundle.getBundle("conn");
		dbDriver = res.getString("dbDriver");
		dbUser = res.getString("dbUser");
		dbPassWd = res.getString("dbPassWd");
		dbUrl= res.getString("dbUrl");		
	}
	
	public void main (String[] args){
		JdbcConn jc=new JdbcConn();
		jc.init();
		System.out.println(jc.getDbDriver());
	}
	
	public String getDbDriver() {
		return dbDriver;
	}
	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}
	public String getDbPassWd() {
		return dbPassWd;
	}
	public void setDbPassWd(String dbPassWd) {
		this.dbPassWd = dbPassWd;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public String getDbUser() {
		return dbUser;
	}
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
	
}
