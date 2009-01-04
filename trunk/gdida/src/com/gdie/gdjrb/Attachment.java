/*
 * @(#)Attachment.java 1.0 2006-9-6
 *
 * Copyright 2005, Guangdong Information & Engineering Co. Ltd.
 * GDIE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gdie.gdjrb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;

import com.gdie.db.DBPool;
import com.jspsmart.upload.SmartUpload;


/**
 * Title: ������
 * Description: ���ϵͳ��ͼƬ���ļ��ȸ���
 * Copyright: Copyright (c) 2006
 * Company: www.gdie.com
 */

public class Attachment {
	public String ss=new String("");
	public String ss2=new String("");
//	private static  String ss=new String("");
	private static Logger log = Logger.getLogger(Attachment.class);
	private String thisClassName = new String(this.getClass().getName());
	
	private int attmId;/* ������ʶ */
	private String attmTitle;/* ���� */
	private String attmType;/* �������� */
	private String fileName;/* �ļ�·�� */
	private int imageWidth;/* ͼƬ��� */
	private int imageHeight;/* ͼƬ�߶� */	

	private int dMaxFileSize = 10485760; // Ĭ�����10M
	
	public int getAttmId() {
		return attmId;
	}
	public void setAttmId(int attmId) {
		this.attmId = attmId;
	}
	public String getAttmTitle() {
		return attmTitle;
	}
	public void setAttmTitle(String attmTitle) {
		this.attmTitle = attmTitle;
	}
	public String getAttmType() {
		return attmType;
	}
	public void setAttmType(String attmType) {
		this.attmType = attmType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getimageHeight() {
		return imageHeight;
	}
	public void setimageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	public int getImageWidth() {
		return imageWidth;
	}
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}
	
	/**
	 * �ж����ݿ����Ƿ����ָ���ĸ���
	 * @return boolean true������ false��������
	 */
	public boolean hasExist(int iAttmid) {
		String thisName = new String(thisClassName + ".hasExist()");
		boolean bResult = false;
		try {
			Connection con = null;
			try {
				DBPool dbpool = new DBPool();
				con = dbpool.getConnection();
				String sql = "SELECT * FROM j_attachment WHERE IATTMID=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, iAttmid);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) bResult = true;
				rs.close();
				stmt.close();
			} catch (Exception e) {
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (con != null)	con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}
		return bResult;
	}

	/**
	 * �ϴ������������¼
	 * @param pageContext PageContext
	 * @return boolean
	 */
	public boolean upload(PageContext pageContext) {
		String thisName = new String(thisClassName + ".upload(PageContext)");
		boolean bResult = false;

		try {
			ServletContext application = pageContext.getServletContext();
			// ʵ��������bean
			SmartUpload mySmartUpload = new SmartUpload();
			// ��ʼ��
			mySmartUpload.initialize(pageContext);
			mySmartUpload.setMaxFileSize(Math.round(this.dMaxFileSize));
			//mySmartUpload.setAllowedFilesList("gif,jpg,bmp,swf");
			mySmartUpload.setDeniedFilesList("exe,jsp");
			// �����ļ�
			mySmartUpload.upload();
			// �ļ�����
		
			String sAttmTitle =mySmartUpload.getRequest().getParameter("sAttmTitle");
			ss2=toUtf8String(sAttmTitle);
			sAttmTitle=toUtf8String(sAttmTitle);
			sAttmTitle=new String(sAttmTitle.getBytes(),"GBK");
			log.debug("ss2"+ss2+"\n");
			log.debug("�������������"+java.net.URLEncoder.encode(sAttmTitle, "GBK"));
			log.debug("�������������"+sAttmTitle+" SBBBBBBB");
			 ss=ss+"����1"+   new   String(sAttmTitle.getBytes("iso8859_1"),"utf-8");			
			 ss=ss+"����2"+  new   String(sAttmTitle.getBytes("gbk"),"utf-8");		  
			 ss=ss+"����3"+  new   String(sAttmTitle.getBytes("utf-8"),"iso8859_1");	
			 ss=ss+"����4"+  new   String(sAttmTitle.getBytes("gbk"),"iso8859_1");	
			 ss=ss+"����5" + new   String(sAttmTitle.getBytes("iso8859_1"),"gbk");
			 ss=ss+"����6" +  new   String(sAttmTitle.getBytes("utf-8"),"gbk");			
			 ss=ss+"����7"+   new   String(sAttmTitle.getBytes("utf-8"),"gb2312");
			 ss=ss+"����8"+ new   String(sAttmTitle.getBytes("iso8859_1"),"gb2312");
			 ss=ss+"����9"+ new   String(sAttmTitle.getBytes("gbk"),"gb2312");
			 ss=ss+"����10"+ new   String(sAttmTitle.getBytes("gb2312"),"utf-8");
		     ss=ss+"����11"+ new   String(sAttmTitle.getBytes("gb2312"),"iso8859_1");
		     ss=ss+"����12"+  new   String(sAttmTitle.getBytes("gb2312"),"gbk");
			 ss=ss+"����1"+   new   String(sAttmTitle.getBytes(),"utf-8");
			 ss=ss+"����1"+   new   String(sAttmTitle.getBytes(),"iso8859_1");			
			 ss=ss+"����2"+  new   String(sAttmTitle.getBytes(),"gbk");
			log.debug(ss);


	 
			// ȡ�����ص��ļ�
			com.jspsmart.upload.File myFile = mySmartUpload.getFiles().getFile(0);
			int iWidth = 0;
			int iHeight = 0;
			String sType = "";
			String myFileName = "";
			if (!myFile.isMissing()) {
				// ȡ�ÿ���
				iWidth =Integer.parseInt(mySmartUpload.getRequest().getParameter("hi_width"));
				iHeight =Integer.parseInt(mySmartUpload.getRequest().getParameter("hi_height"));
				sType=myFile.getFileExt();
				// �ļ�������
				Random random = new Random();
				log.debug("����������������!!!!\n"+mySmartUpload.getFiles().getFile(0).getFileName());
				myFileName=(new java.util.Date()).getTime()+Math.abs(random.nextInt())%100+"."+myFile.getFileExt();
				
				// ����·���ļ���
				String sPathFile = application.getRealPath("/admin/attachment/upload/"+myFileName);
				// ���ļ������ڷ�������
				myFile.saveAs(sPathFile, SmartUpload.SAVE_PHYSICAL);
			}
			if (sAttmTitle != null && !"".equals(sAttmTitle)
					&& myFileName != null && !"".equals(myFileName)) {
				Connection con = null;
				try {
					DBPool dbpool = new DBPool();
					con = dbpool.getConnection();
					String sql="INSERT INTO j_attachment(SATTMTITLE,SATTMTYPE,SFILENAME,IMAGEWIDTH,IMAGEHEIGHT) VALUES(?,?,?,?,?)";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1,sAttmTitle);
					stmt.setString(2,sType);
					stmt.setString(3,myFileName);					
					stmt.setInt(4,iWidth);
					stmt.setInt(5,iHeight);
					stmt.executeUpdate();
					stmt.close();
					bResult = true;
				} catch (Exception e) {
					log.error(thisName + " Exception:" + e.getMessage());
				} finally {
					if (con!=null)	con.close();
				}
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}
		return bResult;
	}
	
	
	
	 public static String toUtf8String(String s) {
		 StringBuffer sb = new StringBuffer();
		 for (int i=0;i<s.length();i++) {
		     char c = s.charAt(i);
		     if (c >= 0 && c <= 255) {
		 sb.append(c);
		     } else {
		 byte[] b;
		 try {
		     b = Character.toString(c).getBytes("utf-8");
		 } catch (Exception ex) {
		     System.out.println(ex);
		     b = new byte[0];
		 }
		 for (int j = 0; j < b.length; j++) {
		     int k = b[j];
		     if (k < 0) k += 256;
		     sb.append("%" + Integer.toHexString(k).
		     toUpperCase());
		 }
		     }
		 }
		 return sb.toString();
		     }
	
}
