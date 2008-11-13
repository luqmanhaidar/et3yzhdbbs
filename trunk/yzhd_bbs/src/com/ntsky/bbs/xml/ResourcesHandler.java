package com.ntsky.bbs.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ntsky.bbs.xml.bean.Resource;

/**
 * 读取Resouce（权限资源）XML信息
 * 
 * <ol>
 *  <li>邮件登陆信息的配置</li>
 * 	<li>载入全部Data节点数据</li>
 *  <li>参数设置时,从新载入对应dataId的节点数据入内存</li>
 * </ol>
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */

public class ResourcesHandler extends DefaultHandler{

	private final static Logger logger = Logger.getLogger(ResourcesHandler.class.getName());
    
	private String element = null;

	// 资源集合
	private List resources = null;
	
	// 资源对象
	private Resource resource = null; 
	
	// 权限集合
	private Map permissionMap = null;
	
	// permission 主键
	private String point = null;
	
	private String value = null;
	
	public void startDocument() throws SAXException {
        if(logger.isDebugEnabled())
            logger.debug("******开始解析WEB-INF/config/Resources.xml文件*******");
        resources = new ArrayList();
	}
	
    public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException {
    	element = qName; 	
    	
    	// resource节点
    	if("resource".equals(qName)){
    		resource = new Resource();
    		permissionMap = new HashMap();
    		for (int att = 0; att < atts.getLength(); att++) {
    			String attName = atts.getQName(att);
    			if ("id".equals(attName)) {
    				resource.setId(Integer.parseInt(atts.getValue(attName)));
    			}
    			if("name".equals(attName)){
    				resource.setName(atts.getValue(attName));
    			}
    		}    
    	}
    	
    	
    	//全部permission值
	   	if("permission".equals(qName)){
            for (int att = 0; att < atts.getLength(); att++) {
            	String attName = atts.getQName(att);
		        if(("point").equals(attName)){
		        	point = atts.getValue(attName);
		        }
		        if(("value").equals(attName)){
		        	value = atts.getValue(attName);
		        }
		    }
	    }    		
    }
	
    public void characters(char[] chars, int start, int length) throws SAXException {
		
    }    

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    	element = null;
    	
    	// 设置属性的key和value
    	if("permission".equals(qName)){
    		permissionMap.put(point,value);
        }
    	if ("resource".equals(qName)) {
    		// 设置全部的资源信息
    		resource.setPermissionMap(permissionMap);
    		resources.add(resource);
        }
    }    
    
    public void endDocument() throws SAXException {
        if(logger.isDebugEnabled())
            logger.debug("******解析WEB-INF/config/Resources.xml文件结束*******");        
    }
    
    public List getResources(){
    	return this.resources;
    }

}
