/*
 * @(#)SimpleStatistic.java 1.0 2005-11-16 Administrator
 *
 * Copyright 2005, Guangdong Information & Engineering Co. Ltd.
 * GDIE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gdie.web;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.gdie.db.DBPool;

/**
 * 首页访问量简单统计<p>
 * 要在会话session范围内使用本类的实例<br/>
 * 如：&lt;jsp:useBean id="simplestatis" class="com.gdie.web.SimpleStatistic" scope="session" /&gt;
 * <p>
 * 要先记录访问，再读取访问次数<br/>
 * &lt;%
 * simplestatis.visit(request);
 * %>
 * 您是第&lt;%=simplestatis.getCounter() %>位访客
 * @author Samland
 * @version 1.0, 2005.11.16
 */
public class SimpleStatistic {
	private static Logger log = Logger.getLogger(SimpleStatistic.class);
	private HttpSession thisSession;
	private int iCount = 0;
	public SimpleStatistic(){
		
	}
	public void visit(HttpServletRequest request){
	//	System.out.println("1111111111111111111111111");
		HttpSession session = request.getSession();
		//当曾经记录过访问会话(session)，而且会话标识(getId)与当前会话标识相等，则认为是同一个访客
		if (thisSession!=null && thisSession.getId().equals(session.getId())) {
			return;
		}
		//否则，记录访问次数加1，
		try {
			Connection con = null;			
			try {
				DBPool dbpool = new DBPool();
				con = dbpool.getConnection();
				String sql = "UPDATE j_indexcounter SET N=N+1";
				//DEBUG.var("ss:"+sql);
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();
			}finally{
				if (con!=null) con.close();
			}
		}catch(Exception e){
        	log.error(" Exception:"+e.getMessage());
        }
		//并标记当前会话
		thisSession = request.getSession();
	}
	
	
	public int getCounter(){
		//如果当前会话曾经从数据库获得数字，则无需再次读取
	//	System.out.println("2222222222222222222222222");
		if (iCount>0) return iCount;
		//否则，读取数据库值
		try {
			Statement stmt = null;
			Connection con = null;
			try {
				DBPool dbpool = new DBPool();
				con = dbpool.getConnection();
				String sql = "SELECT N FROM j_indexcounter";
				//DEBUG.var("ss:"+sql);
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) this.iCount = rs.getInt(1);
				rs.close();
			}finally{
				if (stmt!=null) stmt.close();
				if (con!=null) con.close();
			}
		}catch(Exception e){
        	log.error(" Exception:"+e.getMessage());
        }
		return iCount;
	}
	
	public static void main(String[] args) {
		System.out.println("a");
	}
}
