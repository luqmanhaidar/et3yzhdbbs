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

/**
 * 网站内存数据
 * 
 * <p>内存数据处理</p>
 * 
 * <ol>
 * 	<li>载入全部Data节点数据</li>
 *  <li>参数设置时,从新载入对应dataId的节点数据入内存</li>
 * </ol>
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */

public class SystemHandler extends DefaultHandler{

	private final static Logger logger = Logger.getLogger(SystemHandler.class.getName());
    
	private Map memoryDataMap = null;
	private String key = null;
	private String element = null;
	private Map propertyMap = null;
	private String dataId = null;
	private String nodeValue = "";
	private String parameter = null;
	
	public void startDocument() throws SAXException {
        if(logger.isDebugEnabled())
            logger.debug("******开始解析WEB-INF/config/MemoryData.xml文件*******");
        memoryDataMap = new HashMap();
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
		if ("property".equals(element)) {
			nodeValue = nodeValue + new String(chars, start, length);
		}
    }    

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    	element = null;
    	if("property".equals(qName)){
			propertyMap.put(key,nodeValue);
			nodeValue = "";
        }
    	if ("data".equals(qName)) {
    		if(parameter!=null){
    			if(parameter.equals(dataId)){
    				// 设置指定dataId信息的Map
    				memoryDataMap.put(dataId,propertyMap);
    				return;
    			}
    		}
    		else{
    			// 设置全部的Map信息
    			memoryDataMap.put(dataId,propertyMap);
    		}
        }
    }    
    
    public void endDocument() throws SAXException {
        if(logger.isDebugEnabled())
            logger.debug("******解析WEB-INF/config/MemoryData.xml文件结束*******");        
    }
    
    /**
     * 取得数据Data
     * @return
     */
    public Map getDatas(){
    	return this.memoryDataMap;
    }

    public void setParameter(String parameter){
    	this.parameter = parameter;
    }
}
