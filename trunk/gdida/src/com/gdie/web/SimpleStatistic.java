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
 * ��ҳ��������ͳ��<p>
 * Ҫ�ڻỰsession��Χ��ʹ�ñ����ʵ��<br/>
 * �磺&lt;jsp:useBean id="simplestatis" class="com.gdie.web.SimpleStatistic" scope="session" /&gt;
 * <p>
 * Ҫ�ȼ�¼���ʣ��ٶ�ȡ���ʴ���<br/>
 * &lt;%
 * simplestatis.visit(request);
 * %>
 * ���ǵ�&lt;%=simplestatis.getCounter() %>λ�ÿ�
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
		//��������¼�����ʻỰ(session)�����һỰ��ʶ(getId)�뵱ǰ�Ự��ʶ��ȣ�����Ϊ��ͬһ���ÿ�
		if (thisSession!=null && thisSession.getId().equals(session.getId())) {
			return;
		}
		//���򣬼�¼���ʴ�����1��
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
		//����ǵ�ǰ�Ự
		thisSession = request.getSession();
	}
	
	
	public int getCounter(){
		//�����ǰ�Ự���������ݿ������֣��������ٴζ�ȡ
	//	System.out.println("2222222222222222222222222");
		if (iCount>0) return iCount;
		//���򣬶�ȡ���ݿ�ֵ
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
