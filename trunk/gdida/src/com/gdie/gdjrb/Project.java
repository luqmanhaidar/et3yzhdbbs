/*
 * @(#)Project.java 1.0 2006-8-30
 *
 * Copyright 2005, Guangdong Information & Engineering Co. Ltd.
 * GDIE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gdie.gdjrb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;

import com.gdie.common.Page;
import com.gdie.db.DBPool;

public class Project {
	private static Logger log = Logger.getLogger(Project.class);

	/**
	 * 表元素
	 */
	//private ResultSet rss;
	
	private int projectId;

	private int iModuleId;

	private String projectTitle;

	private Date dPubDate;

	private int isOk;

	private String iPubUserName;
	
	private int ipubUserId;

	private String cinfo; // 内容,存时用

	private Vector vInfo = new Vector(3, 1); // 内容,取时用

	private int editUserId;

	private int auditUserId;

	private String sModuleName;
	
	private String projectMode;

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public int getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(int auditUserId) {
		this.auditUserId = auditUserId;
	}


	public Date getDPubDate() {
		return dPubDate;
	}

	public void setDPubDate(Date pubDate) {
		dPubDate = pubDate;
	}

	public int getEditUserId() {
		return editUserId;
	}

	public void setEditUserId(int editUserId) {
		this.editUserId = editUserId;
	}

	public int getIModuleId() {
		return iModuleId;
	}

	public void setIModuleId(int moduleId) {
		iModuleId = moduleId;
	}

	public int getIsOk() {
		return isOk;
	}

	public void setIsOk(int isOk) {
		this.isOk = isOk;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}



	private void setRequestParameter(HttpServletRequest request)
			throws Exception {
			this.projectId = Integer.parseInt(request.getParameter("projectId").trim());
		//this.iModuleId = Integer.parseInt(request.getParameter("moduleid"));
		this.projectTitle = request.getParameter("projectTitle");
		this.ipubUserId = Integer.parseInt((String) request.getSession()
				.getAttribute("userid"));
		this.cinfo = request.getParameter("cinfo");
		this.projectMode=request.getParameter("projectMode");

	}

	public boolean addParent(PageContext pageContext) {
		String thisName = this.getClass().getName() + ".addParent()";
		boolean bResult = false;

		try {
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			//this.setRequestParameter(request);
			request.setCharacterEncoding("GBK");
			Connection con = null;
			try {
				DBPool dbpool = new DBPool();
				con = dbpool.getConnection();
				con.setAutoCommit(false);
				String sql = "insert into j_project (projectTitle ,dpubdate, IPUBUSERID,"
					+ "ISOK, EDITUSERID, LASTEDITDATE,IMODULEID,PROJECT_MODE,CINFO) values (?,now(),?,?,?,now(),18,?,?)";
				PreparedStatement stmt = con.prepareStatement(sql);
				//System.out.println( request.getParameter("projectTitle")+","+Integer.parseInt((String) request.getSession().getAttribute("userid"))+","+request.getParameter("projectMode"));
				stmt.setString(1, request.getParameter("projectTitle"));
				stmt.setInt(2, Integer.parseInt((String) request.getSession()
						.getAttribute("userid")));
				stmt.setString(3,"N");
				stmt.setInt(4, Integer.parseInt((String) request.getSession()
						.getAttribute("userid")));
				stmt.setString(5,request.getParameter("projectMode"));
				stmt.setString(6,Page.text2html(request.getParameter("bodytextsave")));
				//System.out.println("bodytextsave    	"+Page.text2html(request.getParameter("bodytextsave")));
				stmt.executeUpdate();
				stmt.close();
				con.commit();
				con.close();
				bResult = true;
				System.out.println("xiaoxiaoxiao");
				
			} catch (Exception e) {
				System.out.println("111111111111");
				con.rollback();
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				System.out.println("222222222222");
				if (con != null)
					con.close();
			}
			System.out.println("chenchenchen");
			//return bResult;
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
			System.out.println(eb);
		}
		System.out.println("chenchenchen");
		return bResult;
	}

	public boolean addChildren(PageContext pageContext) {
		String thisName = this.getClass().getName() + ".add()";
		boolean bResult = false;

		try {
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			//this.setRequestParameter(request);
			Connection con = null;
			try {
				DBPool dbpool = new DBPool();
				con = dbpool.getConnection();
				con.setAutoCommit(false);
				String sql = "insert into j_project (PARENTID, projectTitle ,dpubdate, IPUBUSERID,"
						+ "ISOK, EDITUSERID, LASTEDITDATE,IMODULEID,PROJECT_MODE,CINFO,CONTACTS,TEL) values (?,?,now(),?,?,?,now(),19,?,?,?,?)";
				PreparedStatement stmt = con.prepareStatement(sql);
				System.out.println("projectMode"+request.getParameter("projectMode"));
				stmt.setString(1, request.getParameter("parentid"));
				stmt.setString(2, request.getParameter("projectTitle"));
				stmt.setInt(3, Integer.parseInt((String) request.getSession()
						.getAttribute("userid")));
				stmt.setString(4,"N");
				stmt.setInt(5, Integer.parseInt((String) request.getSession()
						.getAttribute("userid")));
				stmt.setString(6,request.getParameter("projectMode"));
				stmt.setString(7,Page.text2html(request.getParameter("bodytextsave")));
				stmt.setString(8, request.getParameter("contacts"));
				stmt.setString(9, request.getParameter("tel"));
				stmt.executeUpdate();
				stmt.close();
				con.commit();
				con.close();
				bResult = true;
			} catch (Exception e) {
				con.rollback();
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (con != null)
					con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}

		return bResult;
	}
	
	public boolean findRecord(int projectId) {
		String thisName = this.getClass().getName() + ".findRecord()";
		boolean bResult = false;

		try {
			Connection con = null;
			try {
				DBPool dbpool = new DBPool();
				con = dbpool.getConnection();
				String sql = "select * from j_project where projectId=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, projectId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					this.projectId = rs.getInt("projectId");
					this.iModuleId = rs.getInt("iModuleId");
					this.projectTitle = rs.getString("projectTitle");
					this.dPubDate = rs.getDate("dPubDate");
					this.ipubUserId = rs.getInt("ipubUserId");
					//this.sModuleName = rs.getString("sModuleName");
					//this.iPubUserName = rs.getString("iPubUserName");
					this.vInfo.add(rs.getString("cinfo"));
					this.cinfo=rs.getString("cinfo");
					bResult = true;
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (con != null)
					con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}

		return bResult;
	}
	public boolean updateProject(String prjectid) {
		String thisName = this.getClass().getName() + ".updateProject()";
		boolean bResult = false;

		try {
			this.projectId = Integer.parseInt(prjectid);
			Connection con = null;
			try {
				DBPool dbpool = new DBPool();
				con = dbpool.getConnection();
				con.setAutoCommit(false);
				String sql = "UPDATE  j_project SET ISOK='Y' WHERE PROJECTID=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, this.projectId);
				stmt.executeUpdate();
				stmt.close();
				con.commit();
				bResult = true;
			} catch (Exception e) {
				con.rollback();
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (con != null)
					con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}

		return bResult;
	}
	
	public boolean deleteProject(String prjectid) {
		String thisName = this.getClass().getName() + ".deleteProject()";
		boolean cResult = false;

		try {
			this.projectId = Integer.parseInt(prjectid);
			Connection con = null;
			try {
				DBPool dbpool = new DBPool();
				con = dbpool.getConnection();
				con.setAutoCommit(false);
				String sql = "delete from j_project WHERE PROJECTID=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, this.projectId);
				int flag_int=stmt.executeUpdate();
				stmt.close();
				con.commit();
				if(flag_int==0){cResult=false;}
				else{cResult = true;}
			} catch (Exception e) {
				con.rollback();
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (con != null)
					con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}

		return cResult;
	}
	
	public boolean deleteProject_all(String prjectid) {
		String thisName = this.getClass().getName() + ".deleteProject()";
		boolean dResult = false;

		try {
			this.projectId = Integer.parseInt(prjectid);
			Connection con = null;
			try {
				DBPool dbpool = new DBPool();
				con = dbpool.getConnection();
				con.setAutoCommit(false);
				String sql = "delete from j_project WHERE PROJECTID=? or PARENTID=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, this.projectId);
				stmt.setInt(2, this.projectId);
				int flag_int=stmt.executeUpdate();
				stmt.close();
				con.commit();
				System.out.println(this.projectId);
				if(flag_int==0){dResult=false;}
				else{dResult = true;}
			} catch (Exception e) {
				con.rollback();
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (con != null)
					con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}

		return dResult;
	}
	
	public boolean selectProject(String prjectid) throws SQLException {
		String thisName = this.getClass().getName() + ".selectProject()";
		boolean sResult = false;
		try {
			this.projectId = Integer.parseInt(prjectid);
			Connection con = null;
			try {
				DBPool dbpool = new DBPool();
				con = dbpool.getConnection();
				String sql = "select PARENTID from  j_project WHERE PROJECTID=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, this.projectId);
				ResultSet rss=stmt.executeQuery();
				if(rss.next())	
				{
					if(!(rss.getString("PARENTID")==null))
					{
					//	System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBbb"+rss.getString("PARENTID"));
						sResult=true;	
					}
					else if(rss.getString("PARENTID")==null){sResult=false;}
				}
				else{sResult=false;}
				stmt.close();
			} catch (Exception e) {
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (con != null)
					con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}
		return sResult;
	}
	public int getIpubUserId() {
		return ipubUserId;
	}

	public void setIpubUserId(int ipubUserId) {
		this.ipubUserId = ipubUserId;
	}

	public String getIPubUserName() {
		return iPubUserName;
	}

	public void setIPubUserName(String pubUserName) {
		iPubUserName = pubUserName;
	}

	public String getSModuleName() {
		return sModuleName;
	}

	public void setSModuleName(String moduleName) {
		sModuleName = moduleName;
	}

	public Vector getVInfo() {
		return vInfo;
	}

	public void setVInfo(Vector info) {
		vInfo = info;
	}

	public String getCinfo() {
		return cinfo;
	}

	public void setCinfo(String cinfo) {
		this.cinfo = cinfo;
	}
}
