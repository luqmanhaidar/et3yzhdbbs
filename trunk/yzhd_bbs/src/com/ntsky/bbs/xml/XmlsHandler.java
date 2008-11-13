/*
 * Copyright (c) 2001-2005 by www.ntsky.com All rights reserved.
 */
package com.ntsky.bbs.xml;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlsHandler extends DefaultHandler{

	private final static Logger logger = Logger.getLogger(XmlsHandler.class.getName());
    
	private Map xmlFileMap = null; 
	
	public void startDocument() throws SAXException {
        if(logger.isDebugEnabled())
            logger.debug("******开始解析WEB-INF/config/Xmls.xml文件*******");
        xmlFileMap = new HashMap();
	}
	
    public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException {
        // class节点
        if("xmls".equals(qName)){
            String name = null;
            String path = null;
            for (int att = 0; att < atts.getLength(); att++) {
	            String attName = atts.getQName(att);
	            if(("name").equals(attName)){
	                name = atts.getValue(attName);
	            }
	            if(("path").equals(attName)){
	            	path = atts.getValue(attName);
	            }
	        }
            xmlFileMap.put(name,path);
        }
    }
	
    public void endDocument() throws SAXException {
        if(logger.isDebugEnabled())
            logger.debug("******解析WEB-INF/config/Xmls.xml文件结束*******");        
    }
    
    /**
     * 取得xml路径集合
     * 
     * @return Map 
     */
    public Map getXmlFileMap(){
        return this.xmlFileMap;
    }
    
}
