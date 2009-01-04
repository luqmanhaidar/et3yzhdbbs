/*
 * @(#)Module.java 1.0 2006-8-29
 *
 * Copyright 2005, Guangdong Information & Engineering Co. Ltd.
 * GDIE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gdie.gdjrb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.gdie.db.DBPool;
import com.gdie.db.Table;

public class Module {
	private static Logger log = Logger.getLogger(Module.class);

	public static boolean hasSon(int iModuleid) {
		String thisName = "Module.hasSon()";
		boolean bResult = false;

		try {
			PreparedStatement pstmt = null;
			Connection con = DBPool.getStaticConnection();
			try {
				
				String sql = "select * from j_module where IPARENTID=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, iModuleid);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) bResult = true;
			} catch (Exception e) {
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				//if (pstmt!=null) pstmt.close();
				//if(con!=null) con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}

		return bResult;
	}
	
	public static String getMidByIdentify(String identify){
		String mid="0";
		try{
		mid=Table.getValue("j_module", "IMODULEID", "IDENTIFY='"+identify+"'", "");
		}catch(Exception e){
			
		}
		return mid;
	}
	
	public static String getPath(int mid){
		String str=null;
		try{
			str=" > <a href='info_list.jsp?identify="+Table.getValue("j_module", "identify", "IMODULEID="+mid, "")+"'>"
			+Table.getValue("j_module", "SMODULENAME", "IMODULEID="+mid, "")+"</a>";
			
			String pid=Table.getValue("j_module", "IPARENTID", "IMODULEID="+mid, "");
			if(pid==null || pid.trim().equals("")){
				str="<a href='index.jsp'>广东省产业发展研究院首页 </a>"+str;
			}else{
				str=getPath(Integer.parseInt(pid))+str;
			}
			
			return str;
			
		}catch(Exception e){
			
		}
		return str;
	}	
	
	public static String getPathAdmin(int mid){
		String str=null;
		try{
			str=" >> <u>"+Table.getValue("j_module", "SMODULENAME", "IMODULEID="+mid, "")+"</u>";			
			String pid=Table.getValue("j_module", "IPARENTID", "IMODULEID="+mid, "");
			if(pid==null || pid.trim().equals("")){	
				str="<u>信息管理</u>"+str;
			}else{
				str=getPathAdmin(Integer.parseInt(pid))+str;
			}
			
			return str;
			
		}catch(Exception e){
			
		}
		return str;
	}	
	
	public static boolean hasSonByIdentify(String identify) {
		String thisName = "Module.hasSon()";
		boolean bResult = false;

		try {
			PreparedStatement pstmt = null;
			Connection con = DBPool.getStaticConnection();
			try {
				
				String sql = "select * from j_module where identify=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, identify);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) bResult = true;
			} catch (Exception e) {
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				//if (pstmt!=null) pstmt.close();
				//if(con!=null) con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}

		return bResult;
	}
	
	public static ResultSet getSon(int iModuleid)throws Exception{
		PreparedStatement pstmt=null;
		Connection con=DBPool.getStaticConnection();
		try{
			
			String sql="select * from j_module where where IPARENTID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, iModuleid);
			ResultSet rs=pstmt.executeQuery();
			con.close();
			pstmt.close();
			return rs;
			
		}catch(Exception e){
			if(con!=null) con.close();
			if(pstmt!=null) pstmt.close();
			return null;
		}
		
	}
	
	public static ResultSet getRoot()throws Exception{
		PreparedStatement pstmt=null;
		Connection con=DBPool.getStaticConnection();
		try{
			
			String sql="select * from j_module where IPARENTID is null";
			pstmt=con.prepareStatement(sql);
			
			ResultSet rs=pstmt.executeQuery();
			con.close();
			pstmt.close();
			return rs;
			
		}catch(Exception e){
			if(con!=null) con.close();
			if(pstmt!=null) pstmt.close();
			return null;
		}
	}
	
	public static int getNextOrder(String sParentid) {
		String thisName = "Module.getNextOrder()";
		int iResult = 0;

		try {
			PreparedStatement pstmt = null;
			try {
				Connection con = DBPool.getStaticConnection();
				String sql = null;
				if (sParentid==null) {
					sql = "select max(IORDER) from j_module where IPARENTID is null";
				} else {
					sql = "select max(IORDER) from j_module where IPARENTID=?";
				}
				pstmt = con.prepareStatement(sql);
				if (sParentid!=null)	pstmt.setInt(1, Integer.parseInt(sParentid));
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) iResult = rs.getInt(1);
				iResult++;
			} catch (Exception e) {
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (pstmt!=null) pstmt.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}

		return iResult;
	}
	
	
}
