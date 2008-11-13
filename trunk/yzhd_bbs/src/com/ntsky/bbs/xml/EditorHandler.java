/*
 * Copyright (c) 2001-2005 by www.ntsky.com All rights reserved.
 */
package com.ntsky.bbs.xml;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class EditorHandler extends DefaultHandler{

	private final static Logger logger = Logger.getLogger(EditorHandler.class.getName());
    
	private Map cdataMap = null; 
	
	public void startDocument() throws SAXException {
        if(logger.isDebugEnabled())
            logger.debug("******开始解析WEB-INF/config/Editor.xml文件*******");
        cdataMap = new HashMap();
	}
	
    public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException {
        // class节点
        if("class".equals(qName)){
            String name = "";
            TreeSet fieldSet = null;
            for (int att = 0; att < atts.getLength(); att++) {
	            String attName = atts.getQName(att);
	            if(("name").equals(attName)){
	                name = atts.getValue(attName);
	            }
	            if(("fields").equals(attName)){
	                fieldSet = getFieldSet(atts.getValue(attName));
	            }
	        }
            cdataMap.put(name,fieldSet);
        }
    }
	
    public void endDocument() throws SAXException {
        if(logger.isDebugEnabled())
            logger.debug("******解析WEB-INF/config/Editor.xml文件结束*******");        
    }
    
    /**
     * 取得特殊字符字段Map
     * 
     * @return Map 
     */
    public Map getEditorMap(){
        return this.cdataMap;
    }
    
    /**
     * 取得Field的TreeSet集合 
     * 
     * @param fields
     * @return TreeSet
     */
    public static TreeSet getFieldSet(String fields){
        StringTokenizer stringTokenizer = new StringTokenizer(fields,",");
        Set set = new HashSet();
        while(stringTokenizer.hasMoreTokens()){
            set.add(stringTokenizer.nextToken());
        }
        return new TreeSet(set);
    }
}
