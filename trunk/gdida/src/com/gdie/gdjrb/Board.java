/*
 * @(#)Board.java 1.0 2008-1-29
 *
 * Copyright 2005, Guangdong Information & Engineering Co. Ltd.
 * GDIE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gdie.gdjrb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;

import com.gdie.db.DBPool;

public class Board {
	private static Logger log = Logger.getLogger(Information.class);
	
	private int boardId;
	private String bip;
	private Date pubTime;
	private String writer;
	private String scontext;
	private String isok;
	/**
	 * @return 返回 boardId。
	 */
	public int getBoardId() {
		return boardId;
	}
	/**
	 * @return 返回 scontext。
	 */
	public String getContext() {
		return scontext;
	}
	/**
	 * @return 返回 ip。
	 */
	public String getBip() {
		return bip;
	}
	/**
	 * @return 返回 isok。
	 */
	public String getIsok() {
		return isok;
	}
	/**
	 * @return 返回 pubTime。
	 */
	public Date getPubTime() {
		return pubTime;
	}
	/**
	 * @return 返回 writer。
	 */
	public String getWriter() {
		return writer;
	}
	
	
	private void setRequestParameter(HttpServletRequest request) throws Exception{
    	request.setCharacterEncoding("GBK");
    	String sInfoId = request.getParameter("infoid");
    	
    	this.bip=request.getParameter("bip");
    	
    	this.writer=request.getParameter("writer");
    	this.scontext=request.getParameter("scontext");
    	this.pubTime=new Date(System.currentTimeMillis());
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
                String sql = "insert into J_BOARD (bip, writer, pubTime, scontext,isok) values (?,?,?,?,'N')";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, this.bip);
                if(this.writer!=null&&(!this.writer.equals(new String("").trim()))){
                	stmt.setString(2, this.writer);
                }else{
                	stmt.setString(2, "匿名");
                }
                stmt.setDate(3, this.pubTime);
                stmt.setString(4, this.scontext);
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
	
	public boolean del(String boardId) {
		String thisName = this.getClass().getName() + ".del()";
		boolean bResult = false;
		
		try {
			this.boardId = Integer.parseInt(boardId);
			Connection con = null;
			try {
                DBPool dbpool = new DBPool();
                con = dbpool.getConnection();
                con.setAutoCommit(false);
                String sql = "DELETE FROM J_BOARD WHERE boardid=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, this.boardId);
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
	
	public boolean updateBoard(String boardId) {
		String thisName = this.getClass().getName() + ".updateBoard()";
		boolean bResult = false;
		
		try {
			this.boardId = Integer.parseInt(boardId);
			Connection con = null;
			try {
                DBPool dbpool = new DBPool();
                con = dbpool.getConnection();
                con.setAutoCommit(false);
                String sql = "UPDATE  J_BOARD SET isok='Y' WHERE boardid=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, this.boardId);
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
	
	
	
	public boolean findRecord(int boardid) {
		String thisName = this.getClass().getName() + ".findRecord()";
		boolean bResult = false;
		
		try {
			Connection con = null;
			try {
                DBPool dbpool = new DBPool();
                con = dbpool.getConnection();
                String sql = "select * from J_BOARD where boardid=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, boardid);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                	this.boardId=rs.getInt("boardid");
                	this.bip=rs.getString("bip");
                	this.pubTime=rs.getDate("pubtime");
                	this.writer=rs.getString("writer");
                	this.scontext=rs.getString("scontext");
                	this.isok=rs.getString("isok");
        			bResult = true;
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


