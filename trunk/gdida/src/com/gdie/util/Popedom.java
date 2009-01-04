package com.gdie.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.gdie.db.DBPool;
import com.gdie.db.Table;
import com.gdie.gdjrb.Module;

public class Popedom {
	private static Logger log = Logger.getLogger(Popedom.class);
	
	/**
	 * <p>��֤�Ƿ����������Ȩ��
	 * @param uid
	 * @param pid
	 * @return
	 */
	public static boolean validate(String uid,String pop){
		String thisName = "Popedom.validate()";
		boolean bResult = false;
		
		try {
			PreparedStatement pstmt = null;
			Connection con = DBPool.getStaticConnection();
			try {	
				String sql = "select * from jview_u_p where uid=? and pid=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(uid));
				pstmt.setString(2, pop);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) bResult = true;
			} catch (Exception e) {
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (pstmt!=null) pstmt.close();				
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}
		
		return bResult;
	}
	/**
	 * ��֤�ܷ�������Ŀ 
	 * @param uid
	 * @param identify����Ŀ��
	 * @return
	 */
	public static boolean validateModule(String uid,String identify){
		String thisName = "Popedom.validate()";
		boolean bResult = false;		
		try {
			PreparedStatement pstmt = null;
			Connection con = DBPool.getStaticConnection();
			try {	
				String mid=Module.getMidByIdentify(identify);
				boolean hasSon=Module.hasSon(Integer.parseInt(mid));
				String sql;
				if(hasSon){
					sql="select * from jview_u_p where uid=? and identify in (select identify from j_module where IPARENTID ="+mid+")";
				}else{
					sql = "select * from jview_u_p where uid=? and identify=?";
				}
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(uid));
				if(!hasSon)
				pstmt.setString(2, identify);
				
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) bResult = true;
			} catch (Exception e) {
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (pstmt!=null) pstmt.close();				
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}
		
		return bResult;
	}
	
	/**
	 * 
	 * @param uid
	 * @param identify ��ģ���������ﲻ����Ŀ
	 * @return
	 */
	public static boolean validatePopedomType(String uid,String identify){
		String thisName = "Popedom.validatePopedomType()";
		boolean bResult = false;		
		try {
			PreparedStatement pstmt = null;
			Connection con = DBPool.getStaticConnection();
			try {	
				//String mid=Module.getMidByIdentify(identify);
				//boolean hasSon=Module.hasSon(Integer.parseInt(mid));
				String sql;
				
					sql = "select * from jview_u_p where uid=? and identify=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(uid));
				
				pstmt.setString(2, identify);
				
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) bResult = true;
			} catch (Exception e) {
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (pstmt!=null) pstmt.close();				
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}
		
		return bResult;
	}
	
	/**
	 * ��֤ĳ����Ŀ��ĳ�����ܵ��Ƿ���Ȩ��
	 * @param uid
	 * @param identify
	 * @param pop
	 * @return
	 */
	public static boolean validatePop(String uid,String identify,String pop){
		String thisName = "Popedom.validatePop()";
		boolean bResult = false;		
		try {
			PreparedStatement pstmt = null;
			Connection con = DBPool.getStaticConnection();
			try {	
				String mid=Module.getMidByIdentify(identify);
				boolean hasSon=Module.hasSon(Integer.parseInt(mid));
				String sql;
				if(hasSon){
					sql="select * from jview_u_p where uid=? and pop='"+pop+"' and identify in (select identify from j_module where IPARENTID ="+mid+")";
				}else{
					sql = "select * from jview_u_p where uid=? and identify='"+identify+"' and pop='"+pop+"'";
				}
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(uid));
				
				
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) bResult = true;
			} catch (Exception e) {
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (pstmt!=null) pstmt.close();				
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}
		
		return bResult;
	}
	
	/**
	 * Ȩ��ģ��
	 * @param uid
	 * @param identify
	 * @param pop
	 * @return
	 */
	public static boolean validatePopType(String uid,String identify,String pop){
		String thisName = "Popedom.validatePop()";
		boolean bResult = false;		
		try {
			PreparedStatement pstmt = null;
			Connection con = DBPool.getStaticConnection();
			try {	
				
				String sql;
				
				
					sql = "select * from jview_u_p where uid=? and identify='"+identify+"' and pop='"+pop+"'";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(uid));
				
				
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) bResult = true;
			} catch (Exception e) {
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (pstmt!=null) pstmt.close();				
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}
		
		return bResult;
	}
	
	public static void removeOnesPopedom(String uid){
		String thisName="Popedom.removeOnesPopedom";
		try{		
			String sql="delete from u_p where uid="+uid;
			Table.execSql(sql);		
		}catch(Exception eb){
			log.equals(thisName+"Exception b:"+eb.getMessage());
		}
	}
	
	public static void addOnesPopedom(String uid,String pid){
		try{
		Table.insertRow("u_p", "uid,pid", uid+","+pid);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>��֤�Ƿ��������Ȩ�ޣ�û�еĻ���תǰһҳ��
	 * @param uid
	 * @param pid
	 */
	public void validateRedirect(String uid,String pid){
		
	}
}
