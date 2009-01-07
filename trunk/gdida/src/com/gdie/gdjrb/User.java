/*
 * @(#)User.java 1.0 2006-8-29
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
import com.gdie.util.Md5;

public class User {
	private static Logger log = Logger.getLogger(User.class);
	
	public static boolean verify(String sUsername, String sPassword) {
		String thisName = "User.verify()";
		boolean bResult = false;
		
		try {
			PreparedStatement pstmt = null;
			Connection con = DBPool.getStaticConnection();
			try {
				String sPwMd5 = Md5.encrypt(sPassword);

				
				String sql = "select * from j_user where SUSERNAME=? and SPASSWORD=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, sUsername);
				pstmt.setString(2, sPwMd5);
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
}
