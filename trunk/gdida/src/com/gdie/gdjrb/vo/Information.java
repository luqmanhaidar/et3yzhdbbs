/*
 * @(#)Information.java 1.0 2006-8-30
 *
 * Copyright 2005, Guangdong Information & Engineering Co. Ltd.
 * GDIE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gdie.gdjrb.vo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;

import com.gdie.db.DBPool;
import com.gdie.db.Table;

public class Information {
	private static Logger log = Logger.getLogger(Information.class);
	
	/**
	 * 表元素
	 */
	private int iInfoId;
	private int iModuleId;
	private String sInfoTitle;
	private Date dPubDate;
	private int iPubUserId;
	private String sModuleName;
	private String sPubUserName;
	private String[] cinfo; //内容,存时用
    private Vector vInfo = new Vector(3, 1); //内容,取时用
    private String sIfPic;
    private String sAttm;

    public int getInfoId() {
    	return this.iInfoId;
    }
    public int getModuleId() {
    	return this.iModuleId;
    }
    public String getInfoTitle() {
    	return this.sInfoTitle;
    }
    public Date getPubDate() {
    	return this.dPubDate;
    }
    public int getPubUserId() {
    	return this.iPubUserId;
    }
    public String getModuleName() {
    	return this.sModuleName;
    }
    public String getPubUserName() {
    	return this.sPubUserName;
    }
    public Vector getVInfo() {
        return this.vInfo;
    }
    public String getIfPic() {
    	return this.sIfPic;
    }
    public String getAttm() {
    	return this.sAttm;
    }
    
    private void setRequestParameter(HttpServletRequest request) throws Exception{
    	request.setCharacterEncoding("GBK");
    	String sInfoId = request.getParameter("infoid");
    	if (sInfoId!=null && !sInfoId.trim().equals(""))
    		this.iInfoId = Integer.parseInt(sInfoId);
    	this.iModuleId = Integer.parseInt(request.getParameter("moduleid"));
    	this.sInfoTitle = request.getParameter("stitle");
    	this.iPubUserId = Integer.parseInt((String)request.getSession().getAttribute("USERID"));
    	this.cinfo = request.getParameterValues("cinfo");
    	this.sIfPic = request.getParameter("ifPic");
    	if (!"Y".equals(this.sIfPic)) this.sIfPic = "N";
    	this.sAttm = request.getParameter("sattm");
    }
    
	public boolean add(PageContext pageContext) {
		String thisName = this.getClass().getName() + ".add()";
		boolean bResult = false;
		
		try {
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
            this.setRequestParameter(request);
            
			Connection con = null;
			try {
                DBPool dbpool = new DBPool();
                con = dbpool.getConnection();
                con.setAutoCommit(false);
                String sql = "insert into j_information (IMODULEID, SINFOTITLE, IPUBUSERID," +
                		" SIFPIC, SATTM, ISOK) values (?,?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, this.iModuleId);
                stmt.setString(2, this.sInfoTitle);
                stmt.setInt(3, this.iPubUserId);
                stmt.setString(4, this.sIfPic); 
                stmt.setString(6, "N");
                if (this.sAttm==null || this.sAttm.trim().equals("")) {
                	stmt.setNull(5, Types.VARCHAR);
                } else {
                	stmt.setString(5, this.sAttm);
                }
                
                stmt.executeUpdate();
                stmt.close();
                
    			sql = "SELECT max(IINFOID) FROM j_information";
                stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()){
                	this.iInfoId = rs.getInt(1);
                }
                stmt.close();

                int iSInfoLength = this.cinfo==null?0:this.cinfo.length;
                for (int i = 0; i < iSInfoLength; i++) {
                    sql = "INSERT INTO j_information_pages(IINFOID, IPAGEID, CINFO) VALUES (?,?,?)";
                    stmt = con.prepareStatement(sql);
                    stmt.setInt(1, this.iInfoId);
                    stmt.setInt(2, i + 1);
                    stmt.setString(3, this.cinfo[i]);
                    stmt.executeUpdate();
                    stmt.close();
                }
                stmt.close();
    			con.commit();
    			bResult = true;
			} catch (Exception e) {
    			con.rollback();
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
                if (con != null) con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}
		
		return bResult;
	}

	public boolean edit(PageContext pageContext) {
		String thisName = this.getClass().getName() + ".edit()";
		boolean bResult = false;
		
		try {
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
            this.setRequestParameter(request);
            
			Connection con = null;
			try {
                DBPool dbpool = new DBPool();
                con = dbpool.getConnection();
                con.setAutoCommit(false);
                String sql = "update j_information set IMODULEID=?, SINFOTITLE=?," +
                		" SIFPIC=?, SATTM=?, ISOK='N' where IINFOID=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, this.iModuleId);
                stmt.setString(2, this.sInfoTitle);
                stmt.setString(3, this.sIfPic);
                if (this.sAttm==null || this.sAttm.trim().equals("")) {
                	stmt.setNull(4, Types.VARCHAR);
                } else {
                	stmt.setString(4, this.sAttm);
                }
                stmt.setInt(5, this.iInfoId);
                stmt.executeUpdate();
                stmt.close();

                int iSInfoLength = this.cinfo==null?0:this.cinfo.length;
                for (int i = 0; i < iSInfoLength; i++) {
    				if (Table.getRecordCount("j_information_pages",
    						"IINFOID=" + this.iInfoId + " AND IPAGEID=" + (i+1)) < 1) {
    					sql = "INSERT INTO j_information_pages (CINFO, IINFOID, IPAGEID) VALUES (?,?,?)";
    				} else {
    					sql = "UPDATE j_information_pages SET CINFO=?"
    						+ " WHERE IINFOID=? AND IPAGEID=?";
    				}
    				stmt = con.prepareStatement(sql);
    				stmt.setString(1, this.cinfo[i]);
    				stmt.setInt(2, this.iInfoId);
    				stmt.setInt(3, i+1);
    				stmt.executeUpdate();
                    stmt.close();
    			}
    			con.commit();
    			bResult = true;
			} catch (Exception e) {
    			con.rollback();
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
                if (con != null) con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}
		
		return bResult;
	}

	public boolean del(String sInfoId) {
		String thisName = this.getClass().getName() + ".del()";
		boolean bResult = false;
		
		try {
			this.iInfoId = Integer.parseInt(sInfoId);
			Connection con = null;
			try {
                DBPool dbpool = new DBPool();
                con = dbpool.getConnection();
                con.setAutoCommit(false);
                String sql = "DELETE FROM j_information WHERE IINFOID=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, this.iInfoId);
                stmt.executeUpdate();
                stmt.close();

                sql = "DELETE FROM j_information_pages WHERE IINFOID=?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, this.iInfoId);
                stmt.executeUpdate();
                stmt.close();

                con.commit();
                bResult = true;
			} catch (Exception e) {
    			con.rollback();
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
                if (con != null) con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}
		
		return bResult;
	}
	
	public boolean updateBoard(String sInfoId) {
		String thisName = this.getClass().getName() + ".updateBoard()";
		boolean bResult = false;
		
		try {
			this.iInfoId = Integer.parseInt(sInfoId);
			Connection con = null;
			try {
                DBPool dbpool = new DBPool();
                con = dbpool.getConnection();
                con.setAutoCommit(false);
                String sql = "UPDATE  j_information SET ISOK='Y' WHERE IINFOID=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, this.iInfoId);
                stmt.executeUpdate();
                stmt.close();               
                con.commit();
                bResult = true;
			} catch (Exception e) {
    			con.rollback();
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
                if (con != null) con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}
		
		return bResult;
	}
	
	
	
	public boolean findRecord(int iInfoId) {
		String thisName = this.getClass().getName() + ".findRecord()";
		boolean bResult = false;
		
		try {
			Connection con = null;
			try {
                DBPool dbpool = new DBPool();
                con = dbpool.getConnection();
                String sql = "select * from JVIEW_INFORMATION where IINFOID=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, iInfoId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                	this.iInfoId = rs.getInt("IINFOID");
                	this.iModuleId = rs.getInt("IMODULEID");
                	this.sInfoTitle = rs.getString("SINFOTITLE");
                	this.dPubDate = rs.getDate("DPUBDATE");
                	this.iPubUserId = rs.getInt("IPUBUSERID");
                	this.sModuleName = rs.getString("SMODULENAME");
                	this.sPubUserName = rs.getString("SPUBUSERNAME");
                	this.sIfPic = rs.getString("SIFPIC");
                	this.sAttm = rs.getString("SATTM");
        			bResult = true;
                }
                rs.close();
                sql = "select * from j_information_pages where IINFOID=? order by IPAGEID";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, this.iInfoId);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    this.vInfo.add(rs.getString("CINFO"));
                }
                rs.close();
                stmt.close();
			} catch (Exception e) {
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
                if (con != null) con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}
		
		return bResult;
	}
}
