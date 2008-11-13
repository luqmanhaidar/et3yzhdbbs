package com.ntsky.bbs.util.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ntsky.framework.util.xml.SAXHelper;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.web.servlet.SystemINIT;
import com.ntsky.bbs.xml.ResourcesHandler;


/**
 * 资源(权限点配置)配置
 * 
 * @author ntsky
 * @link http://www.ntsky.com
 */
public class ResourceConfig {
	
	private final static Logger logger = Logger.getLogger(SystemINIT.class);
	
	public static Map resourcesMap;
	
	/**
	 * 
	 * @return
	 */
	public static Map getResourcesMap(){
		return resourcesMap;
	}
	
	/**
	 * 取得集合列表
	 * @param type
	 * @return
	 */
	public static List getResources(String type){
		return (List)resourcesMap.get(type);
	}
	
	/**
	 * 刷新内存
	 *
	 */
	public static void init(){
		try{
			resourcesMap = new HashMap();
			ResourcesHandler resourceHandler = new ResourcesHandler();
			/*SAXHelper.parseXML(Application.getFilePath(Symbols.XML_RESOURCE_USER),resourceHandler);
			resourcesMap.put("user",resourceHandler.getResources());*/
			SAXHelper.parseXML(Application.getInstance().getFilePath(Symbols.XML_RESOURCE),resourceHandler);
			resourcesMap.put("admin",resourceHandler.getResources());
		}
		catch(Exception e){
			logger.error("解析资源xml发生错误 " ,e);
		}
	}
	
}
