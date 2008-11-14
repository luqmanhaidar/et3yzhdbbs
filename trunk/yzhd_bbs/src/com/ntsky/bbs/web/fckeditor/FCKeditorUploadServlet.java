/*
 * FCKeditor - The text editor for internet
 * Copyright (C) 2003-2005 Frederico Caldeira Knabben
 * 
 * Licensed under the terms of the GNU Lesser General Public License:
 * 		http://www.opensource.org/licenses/lgpl-license.php
 * 
 * For further information visit:
 * 		http://www.fckeditor.net/
 * 
 * File Name: SimpleUploaderServlet.java
 * 	Java File Uploader class.
 * 
 * Version:  2.3
 * Modified: 2005-08-11 16:29:00
 * 
 * File Authors:
 * 		Simone Chiaretta (simo@users.sourceforge.net)
 */ 

package com.ntsky.bbs.web.fckeditor;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


import org.apache.commons.fileupload.*;
import org.apache.log4j.Logger;


import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult; 

import com.ntsky.framework.util.FileUtil;
import com.ntsky.framework.util.HttpUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Role;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.domain.Attachment;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.web.servlet.SystemINIT;
import com.ntsky.bbs.service.UserService;
import com.ntsky.bbs.service.AttachmentService;
import com.ntsky.bbs.service.UserFaceService;
import com.ntsky.bbs.util.memory.RoleSingleton;

/**
 * Servlet to upload files.<br>
 *
 * This servlet accepts just file uploads, eventually with a parameter specifying file type
 *
 * @author Simone Chiaretta (simo@users.sourceforge.net)
 */

public class FCKeditorUploadServlet extends HttpServlet {

	private final static Logger logger = Logger.getLogger(FCKeditorUploadServlet.class);

	private static String baseDir;
	private static boolean debug=false;
	private static boolean enabled=false;
	private static Hashtable allowedExtensions;
	private static Hashtable deniedExtensions;

	/**
	 * Initialize the servlet.<br>
	 * Retrieve from the servlet configuration the "baseDir" which is the root of the file repository:<br>
	 * If not specified the value of "/UserFiles/" will be used.<br>
	 * Also it retrieve all allowed and denied extensions to be handled.
	 *
	 */
	public void init() throws ServletException {

		debug=(new Boolean(getInitParameter("debug"))).booleanValue();

		baseDir=getInitParameter("baseDir");
		enabled=(new Boolean(getInitParameter("enabled"))).booleanValue();
		if(baseDir==null)
			baseDir="/UserFiles/";
		String realBaseDir=getServletContext().getRealPath(baseDir);
		File baseFile=new File(realBaseDir);
		if(!baseFile.exists()){
			baseFile.mkdir();
		}

		allowedExtensions = new Hashtable(3);
		deniedExtensions = new Hashtable(3);

		allowedExtensions.put("File",FckeditorUtil.stringToArrayList(getInitParameter("AllowedExtensionsFile")));
		deniedExtensions.put("File",FckeditorUtil.stringToArrayList(getInitParameter("DeniedExtensionsFile")));

		allowedExtensions.put("Image",FckeditorUtil.stringToArrayList(getInitParameter("AllowedExtensionsImage")));
		deniedExtensions.put("Image",FckeditorUtil.stringToArrayList(getInitParameter("DeniedExtensionsImage")));

		allowedExtensions.put("Flash",FckeditorUtil.stringToArrayList(getInitParameter("AllowedExtensionsFlash")));
		deniedExtensions.put("Flash",FckeditorUtil.stringToArrayList(getInitParameter("DeniedExtensionsFlash")));

	}

	/**
	 * Manage the Upload requests.<br>
	 *
	 * The servlet accepts commands sent in the following format:<br>
	 * simpleUploader?Type=ResourceType<br><br>
	 * It store the file (renaming it in case a file with the same name exists) and then return an HTML file
	 * with a javascript command in it.
	 *
	 */	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (debug) System.out.println("--- BEGIN DOPOST ---");

		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");

		PrintWriter out = response.getWriter();
		// edit check user uploader file size
		int contextLength = request.getContentLength();
		int fileSize = (int)(((float)contextLength)/((float)(1024)));

		String retVal="0";
		String newName="";
		String fileUrl="";
		String errorMessage="";

		// 检测角色允许上传的文件大小
		User user = (User) HttpUtil.getAttribute(request.getSession(),
				Symbols.SESSION_USER);
		Role role = null;
		String userId = "0";
		if (user == null) {
			// 用户未登录（可能是Guest用户,也可能是由于浏览器原因）
			userId = HttpUtil.getCookieValue(request, Symbols.COOKIE_FCKEDITOR);
			if(userId==null){
				if(logger.isDebugEnabled()){
					logger.debug("Guest组用户进行上传操作...");
				}
				role = RoleSingleton.getInstance().getRole("0");
			}
			else{
				UserService userService = (UserService)BeanFactory.getInstance(getServletContext()).getBean("userService");
				user = userService.getUser(userId);
				role = RoleSingleton.getInstance().getRole(user.getRoles());
			}
		} else {
			userId = user.getId().toString();
			role = RoleSingleton.getInstance().getRole(RoleSingleton.getInstance().getRoleIdByName(user.getUsername()));
		}

		if (debug)
			System.out.println("get user id from cookie and userId value is : "
					+ userId);		

		if(logger.isDebugEnabled()){
			logger.debug("用户上传的文件大小"+fileSize+"，用户允许上传的文件大小 : " + role.getPermissionMap().get("uploadAttachmentSize"));
		}
		logger.debug(role.getPermissionMap().get("canUploadAttachment"));
		if(role.getPermissionMap().get("canUploadAttachment").equals("1")){
			if(fileSize<Integer.parseInt((String)role.getPermissionMap().get("uploadAttachmentSize"))){
				//logger.debug("用户上传的文件大小"+fileSize+"，用户允许上传的文件大小 : " + role.getPermissionMap().get("uploadAttachmentSize"));
				String typeStr=request.getParameter("Type");

				// edit begin +++++++++++++++++
				//String currentPath=baseDir + userId + "/" + typeStr;
				String currentPath=baseDir;
				String currentDirPath=getServletContext().getRealPath(currentPath);
				currentPath=request.getContextPath()+currentPath;
				// edit end ++++++++++++++++	
				if (debug) System.out.println(currentDirPath);
				if(enabled) {		
					DiskFileUpload upload = new DiskFileUpload();
					upload.setHeaderEncoding("utf-8");
					try {
						List items = upload.parseRequest(request);

						Map fields=new HashMap();

						Iterator iter = items.iterator();
						while (iter.hasNext()) {
							FileItem item = (FileItem) iter.next();
							if (item.isFormField())
								fields.put(item.getFieldName(),item.getString());
							else
								fields.put(item.getFieldName(),item);
						}
						FileItem uplFile=(FileItem)fields.get("NewFile");
						String fileNameLong=uplFile.getName();
						fileNameLong=fileNameLong.replace('\\','/');
						String[] pathParts=fileNameLong.split("/");
						String fileNameOld=pathParts[pathParts.length-1];

						String ext=FckeditorUtil.getExtension(fileNameOld);
						//取随机名称
						Random random = new Random();
						String fileName =(new java.util.Date()).getTime()+Math.abs(random.nextInt())%100+"."+ext;
						String nameWithoutExt=FckeditorUtil.getNameWithoutExtension(fileNameOld);
						File pathToSave=new File(currentDirPath,fileName);
						fileUrl=currentPath+"/"+fileName;
						if(FckeditorUtil.extIsAllowed(allowedExtensions,deniedExtensions,typeStr,ext)) {
							int counter=1;
							while(pathToSave.exists()){
								newName=nameWithoutExt+"("+counter+")"+"."+ext;
								fileUrl=currentPath+"/"+newName;
								retVal="201";
								pathToSave=new File(currentDirPath,newName);
								counter++;
							}
							logger.debug(fileName);
							uplFile.write(pathToSave);
							//logger.debug("Y6");
							// 记录上传的信息
							if(logger.isInfoEnabled()){
								logger.info("记录用户上传的文件...");
							}
							AttachmentService attachmentService = (AttachmentService)BeanFactory.getInstance(getServletContext()).getBean("attachmentService");
							try{
								Attachment attachment = new Attachment();
								attachment.setOriginFilename(fileName);
								attachment.setFilename(fileName);
								attachment.setFilesize(fileSize);
								attachment.setFiletype(typeStr);
								attachment.setFolder(currentPath);
								attachment.setComments(" ");
								attachment.setDateCreated(new Date());
								attachment.setUserId(Integer.parseInt(userId));
								attachmentService.createAttachment(attachment);
							}
							catch(ServiceException se){
								logger.error("添加附件到库失败...");
								retVal="203";
							}
						}
						else {
							retVal="202";
							errorMessage="";
							if (debug) System.out.println("Invalid file type: " + ext);	
						}
					}catch (Exception ex) {
						logger.debug(ex);
						if (debug) ex.printStackTrace();
						retVal="203";
					}
				}
				else {
					retVal="1";
					errorMessage="This file uploader is disabled. Please check the WEB-INF/web.xml file";
				}

			}
			else{
				retVal = "204";
				errorMessage="上传附件不能超过."+(String)role.getPermissionMap().get("uploadAttachmentSize")+"KB.";
			}
		}
		else
		{
			retVal="203";
		}
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.OnUploadCompleted("+retVal+",'"+fileUrl+"','"+newName+"','"+errorMessage+"');");
		out.println("</script>");
		out.flush();
		out.close();

		if (debug) System.out.println("--- END DOPOST ---");	

	}






}

