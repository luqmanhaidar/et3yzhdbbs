package com.ntsky.bbs.web.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ntsky.framework.util.HttpUtil;

import com.ntsky.bbs.domain.Attachment;
import com.ntsky.bbs.domain.Role;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.service.AttachmentService;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.util.memory.RoleSingleton;
import com.ntsky.bbs.Symbols;

public class DownloadServlet extends HttpServlet {

	private final static Logger logger = Logger.getLogger(DownloadServlet.class);
	
	/**
	 * Constructor of the object.
	 */
	public DownloadServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       
		// ---------- 权限 ------------
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Symbols.SESSION_USER);
		Role role = null;
		int opt = 0;
		if( user==null ){
			role = RoleSingleton.getInstance().getRole("0");
			Object object = role.getPermissionMap().get("canGetAttachment");
    		if(object != null){
    			opt = Integer.parseInt(String.valueOf(object));
    		}
		}
		else{
			role = RoleSingleton.getInstance().getRole(user.getRoles());
			Object object = role.getPermissionMap().get("canGetAttachment");
    		if(object != null){
    			opt = Integer.parseInt(String.valueOf(object));
    		}
		}
		
		if(opt==0){
			if(logger.isDebugEnabled()){
				logger.debug("用户没有下载文件的权限.");
			}
			response.sendRedirect("login.action");
		}
		
		AttachmentService attachmentService = (AttachmentService)BeanFactory.getInstance(getServletContext()).getBean("attachmentService");
		int attId = HttpUtil.getIntParameter(request,"attId");
		Attachment attachment = attachmentService.getAttachment(attId);
		
		//String filename = HttpUtil.getParameter(request,"filename");
		String filename = attachment.getFolder() + attachment.getFilename();
		
        String characterEncoding = HttpUtil.getParameter(request,"characterEncoding");
 		if(characterEncoding==null){
			characterEncoding = "utf-8";
		}
 		//logger.info()
		//filename = new String(filename.getBytes(characterEncoding), "ISO8859_1"); //处理文件中含中文的问题,不是万能的
	    String realPath = this.getServletContext().getRealPath(filename);
	    if(logger.isInfoEnabled()){
	    	logger.info("取得文件的文件名 : " + filename + "; 文件的真实路径 : " + realPath);
	    }
        File file = new File(realPath);
		if (!file.exists()) {
			response.sendError(404, "File not found!");
			return;
		}
		boolean isOnLine = false;
		response.reset(); //非常重要
		if (isOnLine) { //在线打开方式
		     URL u = new URL("file:///" + realPath);
		     response.setContentType(u.openConnection().getContentType());
		     response.setHeader("Content-Disposition",
		                               "inline; filename=" + file.getName());
		} else { //纯下载方式
			response.setContentType("application/x-msdownload");
		    response.setHeader("Content-Disposition","attachment; filename=" + file.getName());
			if (request.getHeader("User-Agent").indexOf("MSIE 5.5") != -1) {
				// MS IE5.5 有要作特別處理
				response.setHeader("Content-Disposition","filename=" + file.getName());
			}
			else {
				// 非 IE5.5 的 Header 設定方式
				response.addHeader( "Content-Disposition", "attachment;filename=" + file.getName());
			}		
		}
		byte[] buf = new byte[1024];
		int len = 0;
		BufferedInputStream br = null;
		OutputStream out = null;
		try {
			br = new BufferedInputStream(new FileInputStream(file));
		    out = response.getOutputStream();
		    while ((len = br.read(buf)) > 0) {
		    	out.write(buf, 0, len);
		    }
		    out.flush();
		    if(logger.isInfoEnabled()){
		    	logger.info("下载文件成功...");
		    }		 
		    // 更新下载次数
		    attachmentService.updateDownloadTimes(attId);
		}catch (Exception e) {
		   e.printStackTrace();
		} finally {
			if (br != null) {
		    	br.close();
		    	br = null;
			}
		 	if (out != null) {
		 		out.close();
		    	out = null;
			}
		}
		response.setStatus( response.SC_OK );
		response.flushBuffer();
	}		

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
