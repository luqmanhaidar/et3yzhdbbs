package com.jeecms.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Install {
	public static void dbXml(String fileName, String dbHost, String dbPort,
			String dbName, String dbUser, String dbPassword) throws Exception {
		String s = FileUtils.readFileToString(new File(fileName));
		s = s.replaceFirst("DB_HOST", dbHost);
		s = s.replaceFirst("DB_PORT", dbPort);
		s = s.replaceFirst("DB_NAME", dbName);
		s = s.replaceFirst("DB_USER", dbUser);
		s = s.replaceFirst("DB_PASSWORD", dbPassword);
		FileUtils.writeStringToFile(new File(fileName), s);
	}

	public static Connection getConn(String dbHost, String dbPort,
			String dbName, String dbUser, String dbPassword) throws Exception {
		Class.forName("org.gjt.mm.mysql.Driver");
		String connStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName
				+ "?user=" + dbUser + "&password=" + dbPassword
				+ "&characterEncoding=GBK";
		Connection conn = DriverManager.getConnection(connStr);
		return conn;
	}

	public static void webXml(String fromFile, String toFile) throws Exception {
		FileUtils.copyFile(new File(fromFile), new File(toFile));
	}

	/**
	 * 创建数据库
	 * 
	 * @param dbHost
	 * @param dbName
	 * @param dbPort
	 * @param dbUser
	 * @param dbPassword
	 * @throws Exception
	 */
	public static void createDb(String dbHost, String dbPort, String dbName,
			String dbUser, String dbPassword) throws Exception {
		Class.forName("org.gjt.mm.mysql.Driver");
		String connStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "?user="
				+ dbUser + "&password=" + dbPassword + "&characterEncoding=GBK";
		Connection conn = DriverManager.getConnection(connStr);
		Statement stat = conn.createStatement();
		String sql = "drop database if exists " + dbName;
		stat.execute(sql);
		sql = "create database " + dbName
				+ " CHARACTER SET gbk COLLATE gbk_chinese_ci";
		stat.execute(sql);
		stat.close();
		conn.close();
	}

	/**
	 * 创建表
	 * 
	 * @param dbHost
	 * @param dbName
	 * @param dbPort
	 * @param dbUser
	 * @param dbPassword
	 * @param sqlList
	 * @throws Exception
	 */
	public static void createTable(String dbHost, String dbPort, String dbName,
			String dbUser, String dbPassword, List<String> sqlList)
			throws Exception {
		Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
		Statement stat = conn.createStatement();
		for (String dllsql : sqlList) {
			stat.addBatch(dllsql);
		}
		stat.executeBatch();
		stat.close();
		conn.close();
	}

	/**
	 * 更新配置
	 * 
	 * @param dbHost
	 * @param dbName
	 * @param dbPort
	 * @param dbUser
	 * @param dbPassword
	 * @param domain
	 * @param cxtPath
	 * @param port
	 * @param resDomain
	 * @throws Exception
	 */
	public static void updateConfig(String dbHost, String dbPort,
			String dbName, String dbUser, String dbPassword, String domain,
			String cxtPath, String port, String resDomain) throws Exception {
		Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
		Statement stat = conn.createStatement();
		String sql = "update CORE_WEBSITE set DOMAIN='" + domain
				+ "',CONTEXT_PATH='" + cxtPath + "',PORT=" + port
				+ ",RES_DOMAIN='" + resDomain + "'";
		stat.executeUpdate(sql);
		stat.close();
		conn.close();
	}

	/**
	 * 读取sql语句。“/*”开头为注释，“;”为sql结束。
	 * 
	 * @param fileName
	 *            sql文件地址
	 * @return list of sql
	 * @throws Exception
	 */
	public static List<String> readSql(String fileName) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		List<String> sqlList = new ArrayList<String>();
		StringBuilder sqlSb = new StringBuilder();
		String s = null;
		while ((s = br.readLine()) != null) {
			if (s.startsWith("/*")) {
				continue;
			}
			if (s.endsWith(";")) {
				sqlSb.append(s);
				sqlSb.setLength(sqlSb.length() - 1);
				sqlList.add(sqlSb.toString());
				sqlSb.setLength(0);
			} else {
				sqlSb.append(s);
			}
		}
		br.close();
		return sqlList;
	}
}
