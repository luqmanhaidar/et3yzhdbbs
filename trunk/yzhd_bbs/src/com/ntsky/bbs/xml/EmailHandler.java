package com.ntsky.bbs.xml;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ntsky.bbs.xml.bean.Email;

/**
 * 邮件内存数据
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

public class EmailHandler extends DefaultHandler{

	private final static Logger logger = Logger.getLogger(EmailHandler.class.getName());
    
	private String element = null;
	// 数据Map
	private Map dataMap = null;
	// 属性集合
	private Map propertyMap = null;
	// 属性集合编号
	private String dataId = null;
	// property 主键
	private String key = null;
	// 节点值
	private String nodeValue = "";
	// 参数信息
	private String parameter = null;
	
	private Email email = null;
	
	public void startDocument() throws SAXException {
        if(logger.isDebugEnabled())
            logger.debug("******开始解析WEB-INF/config/Email.xml文件*******");
        email = new Email();
        dataMap = new HashMap();
	}
	
    public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException {
    	element = qName; 	
    	
    	// data节点
    	if("data".equals(qName)){
    		propertyMap = new HashMap();
    		for (int att = 0; att < atts.getLength(); att++) {
    			String attName = atts.getQName(att);
    			if ("id".equals(attName)) {
    				dataId = atts.getValue(attName);
    			}
    		}    
    	}
    	
    	// 参数不为空
    	if(parameter!=null){
    		// 参数值和dataId值相等时取nodeId下的property的值
    		if(parameter.equals(dataId)){
		    	// property节点
		    	if("property".equals(qName)){
		            for (int att = 0; att < atts.getLength(); att++) {
			            String attName = atts.getQName(att);
			            if(("key").equals(attName)){
			            	key = atts.getValue(attName);
			            }
			        }
		        }
    		}
    	}
    	else{
    		//全部property值
	    	if("property".equals(qName)){
	            //TreeSet fieldSet = null;
	            for (int att = 0; att < atts.getLength(); att++) {
		            String attName = atts.getQName(att);
		            if(("key").equals(attName)){
		            	key = atts.getValue(attName);
		            }
		        }
	        }    		
    	}
    }
	
    public void characters(char[] chars, int start, int length) throws SAXException {
		
		if(parameter!=null){
			if(parameter.equals(dataId)){
				if ("property".equals(element)) {
					nodeValue = nodeValue + new String(chars, start, length);
				}
			}
		}
		else{
	    	if ("property".equals(element)) {
				nodeValue = nodeValue + new String(chars, start, length);
			}
			// 邮件主属性
			if("smtp.host".equals(element)){
				nodeValue = nodeValue + new String(chars, start, length);
			}
			if("username".equals(element)){
				nodeValue = nodeValue + new String(chars, start, length);
			}
			if("password".equals(element)){
				nodeValue = nodeValue + new String(chars, start, length);
			}
			if("systemMail".equals(element)){
				nodeValue = nodeValue + new String(chars, start, length);
			}
		}
    }    

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    	element = null;
    	
    	// 设置邮件主属性
    	if("smtp.host".equals(qName)){
    		email.setSmtpHost(nodeValue);
			nodeValue = "";
    	}
    	if("username".equals(qName)){
    		email.setUsername(nodeValue);
			nodeValue = "";
    	}
    	if("password".equals(qName)){
    		email.setPassword(nodeValue);
			nodeValue = "";
    	}
    	if("systemMail".equals(qName)){
    		email.setSystemMail(nodeValue);
			nodeValue = "";
    	}
    	// 设置属性的key和value
    	if("property".equals(qName)){
			propertyMap.put(key,nodeValue);
			nodeValue = "";
        }
    	if ("data".equals(qName)) {
    		if(parameter!=null){
    			if(parameter.equals(dataId)){
    				// 设置指定dataId信息的Map
    				dataMap.put(dataId,propertyMap);
    				return;
    			}
    		}
    		else{
    			// 设置全部的Map信息
    			dataMap.put(dataId,propertyMap);
    		}
        }
    	
    	if("Email".equals(qName)){
    		email.setBodys(dataMap);
    	}
    }    
    
    public void endDocument() throws SAXException {
        if(logger.isDebugEnabled())
            logger.debug("******解析WEB-INF/config/Email.xml文件结束*******");        
    }
    
    /**
     * 取得EMAIL数据
     * @return
     */
    public Email getEmail(){
    	return this.email;
    }

    /**
     * 设置属性
     * @param parameter
     */
    public void setParameter(String parameter){
    	this.parameter = parameter;
    }
}
