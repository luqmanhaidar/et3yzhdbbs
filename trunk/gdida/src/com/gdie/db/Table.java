/*
 * @(#)Table.java 1.0 2005-5-31
 *
 * Copyright 2005, Guangdong Information & Engineering Co. Ltd.
 * GDIE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gdie.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * <p>Title: ͨ�����ݿ������</p>
 * <p>Description: ��һЩ���õ����ݿ�����ɾ���ĵȲ�����ͨ����ơ�</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: www.gdie.com</p>
 * @author ycm@gdie.com & xjp@gdie.com
 * edit by lyx@gdie.com 20050530
 * <p>Description:����һ��catch���͸���һ��������PK���в�ѯ�ķ���
 * @version 2.0
 */
/*
 * ycm 2004-5-12 create
 */

public class Table {
	private static Logger log = Logger.getLogger(Table.class);
	/**
	 * ��һ�����еõ������
	 * @param sSql ��ѯ���
	 * @return �����
	 * @throws Exception ���������ѯ�쳣���׳�����
	 */
	public static ResultSet getRecordsBySql(String sSql) throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			DBPool dpool = new DBPool();
			conn = dpool.getConnection();
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									  ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(sSql);
			return rs;
		} catch (Exception e) {
			log.error(e.getMessage());
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
			throw e;
		}
	}

	/**
	 * ��һ�����еõ������
	 * @param sSql ��ѯ���
	 * @return �����
	 * @throws Exception ���������ѯ�쳣���׳�����
	 */
	public static ResultSet getRecordsBySql(String sSql, Connection conn) throws Exception {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									  ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(sSql);
			return rs;
		} catch (Exception e) {
			log.error(e.getMessage());
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			throw e;
		}
	}

	/**
	 * ��һ�����еõ������
	 * @param sSql ��ѯ���
	 * @param iFromRownum ��ʼ��¼��
	 * @param iToRownum ������¼��
	 * @return �����
	 * @throws Exception ���������ѯ�쳣���׳�����
	 * @version 2004-8-10 13:22
	 */
	public static ResultSet getRecordsBySql(String sSql, int iFromRownum,
											int iToRownum) throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			DBPool dpool = new DBPool();
			conn = dpool.getConnection();
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									  ResultSet.CONCUR_READ_ONLY);
			sSql = "SELECT T.*, ROWNUM AS NUM FROM (" + sSql + ") T ";
			sSql = "SELECT * FROM (" + sSql + ") WHERE NUM>=" + iFromRownum +
				   " AND NUM<=" + iToRownum;

			rs = st.executeQuery(sSql);
			return rs;
		} catch (Exception e) {
			log.error(e.getMessage());
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
			throw e;
		}
	}

	/**
	 * ����һ����¼����
	 * @param sTableName
	 * @param sColList
	 * @param sValueList
	 * @throws java.lang.Exception
	 */
	public static void insertRow(String sTableName, String sColList,
								 String sValueList) throws Exception {
		Connection conn = null;
		Statement st = null;
		try {
			DBPool dpool = new DBPool();
			String sSQL = "INSERT INTO " + sTableName + " (" + sColList + ")" +
						  " VALUES (" + sValueList + ")";
			conn = dpool.getConnection();
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									  ResultSet.CONCUR_UPDATABLE);
			st.executeUpdate(sSQL);
			st.close();
			conn.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
			//throw ExceptionFactory.getException(5);
		}
	}

	/**
	 * ����ĳ�����һ���ֶ�ֵ
	 * @param tableName ����
	 * @param colName ����
	 * @param value ֵ
	 * @param condition ����
	 * @throws Exception ��������������׳�����
	 */
	public static void setHit(String sTableName, String sColName,
								String sValue, String sCondition) throws
			Exception {
		Connection conn = null;
		Statement st = null;
		try {
			DBPool dpool = new DBPool();
			String strSql = "update " + sTableName + " set " + sColName + "=" +
							sValue   +
							(sCondition.equals("") ? "" :
							 " where " + sCondition);
			conn = dpool.getConnection();
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									  ResultSet.CONCUR_UPDATABLE);
			log.debug(strSql);
			st.executeUpdate(strSql);
			st.close();
			conn.close();

		} catch (Exception e) {
			log.error(e.getMessage());
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
			//throw ExceptionFactory.getException(6);
		}
	}

	
	
	/**
	 * ����ĳ�����һ���ֶ�ֵ
	 * @param tableName ����
	 * @param colName ����
	 * @param value ֵ
	 * @param condition ����
	 * @throws Exception ��������������׳�����
	 */
	public static void setValue(String sTableName, String sColName,
								String sValue, String sCondition) throws
			Exception {
		Connection conn = null;
		Statement st = null;
		try {
			DBPool dpool = new DBPool();
			String strSql = "update " + sTableName + " set " + sColName + "='" +
							sValue + "'" +
							(sCondition.equals("") ? "" :
							 " where " + sCondition);
			conn = dpool.getConnection();
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									  ResultSet.CONCUR_UPDATABLE);
			log.debug(strSql);
			st.executeUpdate(strSql);
			st.close();
			conn.close();

		} catch (Exception e) {
			log.error(e.getMessage());
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
			//throw ExceptionFactory.getException(6);
		}
	}

	/**
	 * �õ�ĳ���һ��ֵ
	 * @param tableName ����
	 * @param colName ����
	 * @param condition ����
	 * @param order ��������
	 * @return ��ֵ
	 * @throws Exception ��������������׳�
	 */
	public static String getValue(String sTableName, String sColName,
								  String sCondition, String sOrder) throws
			Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			String strSql = "select " + sColName + " from " + sTableName +
							(sCondition.equals("") ? "" :
							 " where " + sCondition) +
							(sOrder.equals("") ? "" : " order by " + sOrder);
			DBPool dpool = new DBPool();
			conn = dpool.getConnection();
		//	conn = dpool.getStaticConnection();
			st = conn.createStatement();
			rs = st.executeQuery(strSql);
			//System.out.println(strSql);
			if (!rs.next()) {
				//throw ExceptionFactory.getException();
			}
			String result = rs.getString(1);
			if (result == null) {
				result = "";
			}
			rs.close();
			st.close();
			conn.close();
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			if (rs != null)rs.close();
			if (st != null)st.close();
			if (conn != null)conn.close();
			return "";
		}
	}
	

	/**
	 * ��һ�����еõ������
	 * @param tableName ����
	 * @param colNameList ���б�
	 * @param condition ����
	 * @param order ��������
	 * @return �����
	 * @throws Exception ���������ѯ�쳣���׳�����
	 */
	public static ResultSet getRecords(String sTableName, String sColNameList,
									   String sCondition, String sOrder) throws
			Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			String strSql = "select " + sColNameList + " from " + sTableName +
							(sCondition.equals("") ? "" :
							 " where " + sCondition) +
							(sOrder.equals("") ? "" : " order by " + sOrder);
			DBPool dpool = new DBPool();
			conn = dpool.getConnection();
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									  ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(strSql);
		} catch (Exception e) {
			log.error(e.getMessage());
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
			//throw ExceptionFactory.getException(8);
		}
		return rs;
	}

	/**
	 * �õ����������ļ�¼��
	 * @param tableName ����
	 * @param condition ����
	 * @return ��¼��
	 */
	public static int getRecordCount(String sTableName, String sCondition) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			int count = 0;
			String strSql = "select count(*) from " + sTableName +
							(sCondition.equals("") ? "" :
							 " where " + sCondition);
			DBPool dpool = new DBPool();
			conn = dpool.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(strSql);
			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			st.close();
			conn.close();

			return count;
		} catch (Exception e) {
			log.error(e.getMessage());
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e1) {
				log.error(e1.getMessage());
			}
			return 0;
		}
	}

	/**
	 * �õ����������ļ�¼��
	 * @param sSql ��ѯ���
	 * @return ��¼��
	 * @version 2004-8-10 13:23
	 */
	public static int getRecordCount(String sSql) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			int count = 0;
			String strSql = "SELECT COUNT(*) FROM (" + sSql + ")";
			DBPool dpool = new DBPool();
			conn = dpool.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(strSql);
			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			st.close();
			conn.close();

			return count;
		} catch (Exception e) {
			log.error(e.getMessage());
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e1) {
				log.error(e1.getMessage());
			}
			return 0;
		}
	}

	/**
	 * �õ�һ������ļ�¼��
	 * @param sTableName
	 * @param sColNameList
	 * @param sCondition
	 * @param sGroupBy
	 * @param sHaving
	 * @param sOrder
	 * @return
	 * @throws java.lang.Exception
	 */
	public static ResultSet getRecordsGroup(String sTableName,
											String sColNameList,
											String sCondition, String sGroupBy,
											String sHaving, String sOrder) throws
			Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			String strSql = "select " + sColNameList + " from " + sTableName +
							(sCondition.equals("") ? "" :
							 " where " + sCondition) +
							(sGroupBy.equals("") ? "" : " group by " + sGroupBy) +
							(sHaving.equals("") ? "" : " having " + sHaving) +
							(sOrder.equals("") ? "" : " order by " + sOrder);
			DBPool dpool = new DBPool();
			conn = dpool.getConnection();
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									  ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(strSql);

		} catch (Exception e) {
			log.error(e.getMessage());
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
			//throw ExceptionFactory.getException(8);
		}
		return rs;
	}

	/**
	 * �õ����������ļ�¼��(����ʱʹ��)
	 * @param tableName ����
	 * @param condition ����
	 * @return ��¼��
	 */
	public static int getRecordCountGroup(String sTableName, String sDistinct,
										  String sCondition) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			int count = 0;
			String strSql = "select count(distinct(" + sDistinct + ")) from " +
							sTableName +
							(sCondition.equals("") ? "" :
							 " where " + sCondition);
			DBPool dpool = new DBPool();
			conn = dpool.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(strSql);
			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			st.close();
			conn.close();
			return count;
		} catch (Exception e) {
			log.error(e.getMessage());
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e1) {
				log.error(e1.getMessage());
			}
			return 0;
		}
	}

	/**
	 * ִ��һ���޷���ֵ��SQL���
	 * @param sSql SQL���
	 * @throws Exception
	 */
	public static void execSql(String sSql) throws Exception {
		Connection conn = null;
		Statement st = null;
		try {
			DBPool dpool = new DBPool();
			conn = dpool.getConnection();
			st = conn.createStatement();
			st.execute(sSql);
			st.close();
			conn.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
			throw new SQLException(e.getMessage());
		}
	}

	/**
	 *
	 * <p>ִ��һ���з���ֵ��SQL���
	 * <p>Author: lyx lyx@gdie.com
	 * <p>@param sSql
	 * <p>@throws Exception
	 * <p>�������ͣ�void
	 * <p>����ʱ��:2005-6-29
	 */
	public static boolean execSql2(String sSql) throws Exception {
		Connection conn = null;
		Statement st = null;
		boolean bRet = false;
		try {
			DBPool dpool = new DBPool();
			conn = dpool.getConnection();
			st = conn.createStatement();
			st.execute(sSql);
			bRet = true;
		} catch (Exception e) {
			bRet = false;
			log.error(e.getMessage());
		} finally {
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return bRet;
	}

	/**
	 * �رս����
	 * @param rs �����
	 */
	public static void close(ResultSet rs) {
		try {
			Statement st = rs.getStatement();
		//	System.out.println("aaaaaaaaaaaaaaaaa");
			Connection conn = st.getConnection();
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			System.out.println(e);
			log.error(e.getMessage());
		}
	}

	/**
	 *
	 * <P>:��������ֵ��ȡ������
	 * <P>:Create Date:2005-5-31
	 * <P>:Author:lyx lyx@gdie.com
	 * <P>:@param sTableName
	 * <P>:@param sPKName
	 * <P>:@param sPKValue
	 * <P>:@return
	 * <P>:@throws SQLException
	 */
	public static ResultSet getByPk(String sTableName, String sPKName,
									String sPKValue) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		query = "select * from " + sTableName + " " +
				" where " + sPKName + "=?";

		try {
			DBPool dbpool = new DBPool();
			con = dbpool.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, sPKValue);
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			rs = null;
			log.error(e.getMessage());
		}
		return rs;

	}

	/**
	 *
	 * <P>:	��������ֵ��ȡ������
	 * <P>:Create Date:2005-5-31
	 * <P>:Author:lyx lyx@gdie.com
	 * <P>:@param sTableName
	 * <P>:@param sPKName
	 * <P>:@param sPKValue
	 * <P>:@return
	 * <P>:@throws SQLException
	 */
	public static ResultSet getByPk(String sTableName, String sPKName,
									int sPKValue) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		query = "select * from " + sTableName + " " +
				" where " + sPKName + "=?";

		try {
			DBPool dbpool = new DBPool();
			con = dbpool.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, sPKValue);
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			rs = null;
			log.error(e.getMessage());
		}
		return rs;

	}

	/**
	 *
	 * <P>:	��������ֵ��ȡ������
	 * <P>:Create Date:2005-5-31
	 * <P>:Author:lyx lyx@gdie.com
	 * <P>:@param sTableName
	 * <P>:@param sPKName
	 * <P>:@param sPKValue
	 * <P>:@return
	 * <P>:@throws SQLException
	 */
	public static ResultSet getByPk(String sTableName, String sPKName,
									String sPKValue,
									String sPKName2, int iPKValue2) throws
			SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		query = "select * from " + sTableName + " " +
				" where " + sPKName + "=?  and " + sPKName2 + "=?";

		try {
			DBPool dbpool = new DBPool();
			con = dbpool.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, sPKValue);
			pstmt.setInt(2, iPKValue2);
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			rs = null;
			log.error(e.getMessage());
		}
		return rs;

	}

	/**
	 *
	 * <p>: ��ѯ�Ƿ������ͬ�ļ�¼
	 * <p>:Author: lyx lyx@gdie.com
	 * <p>:@param sTableName
	 * <p>:@param sPKName
	 * <p>:@param sPKValue
	 * <p>:@return boolean
	 * <p>:@throws SQLException boolean
	 * <p>:DEC:���ΪTrue���ʾ������ͬ��¼����False���ʾ����ͬ��¼
	 * <p>:2005-6-1
	 */
	public static boolean selectSameRec(String sTableName, String sPKName,
										String sPKValue) throws SQLException {

		boolean bRet = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		query = "select * from " + sTableName + "" +
				" where " + sPKName + "=?";
		//	System.out.println("dadf"+query);
		try {
			DBPool dbpool = new DBPool();
			con = dbpool.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, sPKValue);
			rs = pstmt.executeQuery();
			bRet = rs.next();
		} catch (Exception e) {
			bRet = true;
			log.error(e.getMessage());
		} finally {
			Table.close(rs);
		}
		return bRet;
	}


	/**
	 *
	 * <p>: ��ѯ�Ƿ������ͬ�ļ�¼
	 * <p>:Author: lyx lyx@gdie.com
	 * <p>:@param sTableName
	 * <p>:@param sPKName
	 * <p>:@param sPKValue
	 * <p>:@return boolean
	 * <p>:@throws SQLException boolean
	 * <p>:DEC:���ΪTrue���ʾ������ͬ��¼����False���ʾ����ͬ��¼
	 * <p>:2005-6-1
	 */
	public static boolean selectSameRec(String sTableName, String sPKName,
										String sPKValue, String sPKName2,
										String sPKValue2) throws SQLException {

		boolean bRet = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		query = "select * from " + sTableName + "" +
				" where " + sPKName + "=? and " + sPKName2 + "=?";

		try {
			DBPool dbpool = new DBPool();
			con = dbpool.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, sPKValue);
			pstmt.setString(2, sPKValue2);
			rs = pstmt.executeQuery();
			bRet = rs.next();
		} catch (Exception e) {
			bRet = true;
			log.error(e.getMessage());
		} finally {
			Table.close(rs);
		}
		return bRet;
	}

	/**
	 *
	 * <p>: ��ѯ�Ƿ������ͬ�ļ�¼
	 * <p>:Author: lyx lyx@gdie.com
	 * <p>:@param sTableName
	 * <p>:@param sPKName
	 * <p>:@param sPKValue
	 * <p>:@return boolean
	 * <p>:@throws SQLException boolean
	 * <p>:DEC:���ΪTrue���ʾ������ͬ��¼����False���ʾ����ͬ��¼
	 * <p>:2005-6-1
	 */
	public static boolean selectSameRec(String sTableName, String sPKName,
										int sPKValue, String sPKName2,
										String sPKValue2) throws SQLException {

		boolean bRet = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		query = "select * from " + sTableName + "" +
				" where " + sPKName + "=? and " + sPKName2 + "=?";

		try {
			DBPool dbpool = new DBPool();
			con = dbpool.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, sPKValue);
			pstmt.setString(2, sPKValue2);
			rs = pstmt.executeQuery();
			bRet = rs.next();
		} catch (Exception e) {
			bRet = true;
			log.error(e.getMessage());
		} finally {
			Table.close(rs);
		}
		return bRet;
	}

	/**
	 *
	 * <p>: ��ѯ�Ƿ������ͬ�ļ�¼
	 * <p>:Author: lyx lyx@gdie.com
	 * <p>:@param sTableName
	 * <p>:@param sPKName
	 * <p>:@param sPKValue
	 * <p>:@return boolean
	 * <p>:@throws SQLException boolean
	 * <p>:DEC:���ΪTrue���ʾ������ͬ��¼����False���ʾ����ͬ��¼
	 * <p>:2005-6-1
	 */
	public static boolean selectSameRec(String sTableName, String sPKName,
										String sPKValue, String sPKName2,
										String sPKValue2, int i) throws
			SQLException {

		boolean bRet = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		query = "SELECT * FROM " + sTableName + "" +
				" WHERE " + sPKName + "=? AND " + sPKName2 + "<>?";

		try {
			DBPool dbpool = new DBPool();
			con = dbpool.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, sPKValue);
			pstmt.setString(2, sPKValue2);
			rs = pstmt.executeQuery();
			bRet = rs.next();
		} catch (Exception e) {
			bRet = true;
			log.error(e.getMessage());
		} finally {
			Table.close(rs);
		}
		return bRet;
	}

	/**
	 *
	 * <p>:  ����PKֵɾ����¼
	 * <p>:Author: lyx lyx@gdie.com
	 * <p>:@param sTableName
	 * <p>:@param sPKName
	 * <p>:@param String sPKValue
	 * <p>:@return boolean
	 * <p>:@throws Exception boolean
	 * <p>:2005-6-2
	 */
	public static boolean delRecordByPK(String sTableName, String sPKName,
										String sPKValue) throws Exception {

		boolean bRet = false;
		String sSQL = "delete from " + sTableName + ""
					  + "  where " + sPKName + "=?";
		System.out.println(sSQL);
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			DBPool dbpool = new DBPool();
			con = dbpool.getConnection();
			pstmt = con.prepareStatement(sSQL);
			pstmt.setString(1, sPKValue);
			pstmt.executeQuery();
			bRet = true;
		} catch (Exception e) {
			bRet = false;
			log.error(e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return bRet;
	}

	/**
	 *
	 * <p>:  ����PKֵɾ����¼
	 * <p>:Author: lyx lyx@gdie.com
	 * <p>:@param sTableName
	 * <p>:@param sPKName
	 * <p>:@param Int sPKValue
	 * <p>:@return boolean
	 * <p>:@throws Exception boolean
	 * <p>:2005-6-2
	 */
	public static boolean delRecordByPK(String sTableName, String sPKName,
										int sPKValue) throws Exception {

		boolean bRet = false;
		String sSQL = "delete from " + sTableName + ""
					  + "  where " + sPKName + "=?";
		System.out.println(sSQL);
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			DBPool dbpool = new DBPool();
			con = dbpool.getConnection();
			pstmt = con.prepareStatement(sSQL);
			pstmt.setInt(1, sPKValue);
			pstmt.executeQuery();
			bRet = true;
		} catch (Exception e) {
			bRet = false;
			log.error(e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return bRet;
	}


	/**
	 *
	 * <p>:  ����PKֵɾ����¼
	 * <p>:Author: lyx lyx@gdie.com
	 * <p>:@param sTableName
	 * <p>:@param sPKName
	 * <p>:@param String sPKValue
	 * <p>:@param sPKName2
	 * <p>:@param String sPKValue2
	 * <p>:@return boolean
	 * <p>:@throws Exception boolean
	 * <p>:2005-6-2
	 */
	public static boolean delRecordByPK(String sTableName, String sPKName,
										String sPKValue,
										String sPKName2, String sPKValue2) throws
			Exception {

		boolean bRet = false;
		String sSQL = "delete from " + sTableName + ""
					  + "  where " + sPKName + "=? and " + sPKName2 + "=?";
		System.out.println(sSQL);
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			DBPool dbpool = new DBPool();
			con = dbpool.getConnection();
			pstmt = con.prepareStatement(sSQL);
			pstmt.setString(1, sPKValue);
			pstmt.setString(2, sPKValue2);
			pstmt.executeQuery();
			bRet = true;
		} catch (Exception e) {
			bRet = false;
			log.error(e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return bRet;
	}

	/**
	 *
	 * <p>:  ����PKֵɾ����¼
	 * <p>:Author: lyx lyx@gdie.com
	 * <p>:@param sTableName
	 * <p>:@param sPKName
	 * <p>:@param int sPKValue
	 * <p>:@param sPKName2
	 * <p>:@param String sPKValue2
	 * <p>:@return boolean
	 * <p>:@throws Exception boolean
	 * <p>:2005-6-2
	 */
	public static boolean delRecordByPK(String sTableName, String sPKName,
										int sPKValue,
										String sPKName2, String sPKValue2) throws
			Exception {

		boolean bRet = false;
		String sSQL = "delete from " + sTableName + ""
					  + "  where " + sPKName + "=? and " + sPKName2 + "=?";
		System.out.println(sSQL);
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			DBPool dbpool = new DBPool();
			con = dbpool.getConnection();
			pstmt = con.prepareStatement(sSQL);
			pstmt.setInt(1, sPKValue);
			pstmt.setString(2, sPKValue2);
			pstmt.executeQuery();
			bRet = true;
		} catch (Exception e) {
			bRet = false;
			log.error(e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return bRet;
	}

	/**
	 *
	 * <p>:  ����PKֵɾ����¼
	 * <p>:Author: lyx lyx@gdie.com
	 * <p>:@param sTableName
	 * <p>:@param sPKName
	 * <p>:@param int sPKValue
	 * <p>:@param sPKName2
	 * <p>:@param String sPKValue2
	 * <p>:@return boolean
	 * <p>:@throws Exception boolean
	 * <p>:2005-6-2
	 */
	public static boolean delRecordByPK(String sTableName, String sPKName,
										int sPKValue,
										String sPKName2, int sPKValue2) throws
			Exception {

		boolean bRet = false;
		String sSQL = "delete from " + sTableName + ""
					  + "  where " + sPKName + "=? and " + sPKName2 + "=?";
		System.out.println(sSQL);
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			DBPool dbpool = new DBPool();
			con = dbpool.getConnection();
			pstmt = con.prepareStatement(sSQL);
			pstmt.setInt(1, sPKValue);
			pstmt.setInt(2, sPKValue2);
			pstmt.executeQuery();
			bRet = true;
		} catch (Exception e) {
			bRet = false;
			log.error(e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return bRet;
	}

	/**
	 * ȡĳһ���е���һֵ
	 * @param sSequenceName String
	 * @return int
	 * @throws Exception
	 */
	public static int getSequence(String sSequenceName) throws Exception {
		int iResult = 0;
		String sSQL = "";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			DBPool dbpool = new DBPool();
			con = dbpool.getConnection();

			sSQL = "SELECT " + sSequenceName + ".NEXTVAL FROM DUAL";
			stmt = con.prepareStatement(sSQL);
			rs = stmt.executeQuery();
			if (rs.next()) {
				iResult = rs.getInt(1);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return iResult;
	}

} //class
