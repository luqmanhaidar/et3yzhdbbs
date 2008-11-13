package com.ntsky.bbs.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.Symbols;

/**
 * Application信息
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:26 $
 */
public class Application {
	
	public String webRealPath;
	
	private Map filePathMap = new HashMap();
	
	private static Application application = null;
	
	private Application(){}
	
	public static Application getInstance(){
		if(application == null){
			application = new Application();
		}
		return application;
	}
	
	/**
	 * 设置文件路径Map信息
	 * @param name 文件名称
	 * @param filePath 文件路径
	 */
	public void setFilePathMap(String name,String filePath){
		Object object = filePathMap.get(name);
		if( object == null ){
			filePathMap.put(name,filePath);
		}
	}
	
	/**
	 * 根据文件标示取得文件路径
	 * @param name Map标示
	 * @return String xml文件路径
	 */
	public String getFilePath(String name){
		Object object = filePathMap.get(name);
		return (object == null) ? "" : (String)object;
	}
	
	/**
	 * app路径 
	 * @param appPath 
	 */
	public void setWebRealPath(String webRealPath){
		this.webRealPath = webRealPath;
	}
	
	private ServletContext servletContext;
	public ServletContext getServletContext() {
		return servletContext;
	}
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}	
	
	/**
	 * 取得App路径
	 * @return
	 */
	public String getWebRealPath(){
		return this.webRealPath;
	}
	
	/**
	 * 取得JS目录
	 * @return
	 */
	public String getJsDirectory(){
		return StringUtil.applyRelativeDirectory(webRealPath,Symbols.SCRIPT_DIR);
	}
	
	// 加密信息集合
	private Map securityMap;
	public Map getSecurityMap() {
		return securityMap;
	}
	public void setSecurityMap(Map securityMap) {
		this.securityMap = securityMap;
	}
	public String getSecurity(String key){
		return (String)securityMap.get(key);
	}
	
}
