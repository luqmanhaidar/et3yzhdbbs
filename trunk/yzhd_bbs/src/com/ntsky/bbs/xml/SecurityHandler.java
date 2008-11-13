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

public class SecurityHandler extends DefaultHandler {

    private final static Logger logger = Logger.getLogger(SecurityHandler.class.getName());

    private Map securityMap = null;

    private String tempQName = null;

    private String key = null;

    private String value = null;

    public void startDocument() throws SAXException {
        securityMap = new HashMap();
        if (logger.isDebugEnabled())
            logger.debug("******开始解析WEB-INF/config/Security.xml文件*******");
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if (logger.isDebugEnabled()) {
            logger.debug("qName = " + qName);
        }
        tempQName = qName;
        if (("security").equals(qName)) {
            for (int att = 0; att < atts.getLength(); att++) {
                String attName = atts.getQName(att);
                if (("key").equals(attName)) {
                    key = atts.getValue(attName);
                }
            }
        }
    }

    public void characters(char[] chars, int start, int length) throws SAXException {
        String nodeValue = new String(chars, start, length);
        if (("security").equals(tempQName)) {
            value = nodeValue;
        }
    }

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (("security").equals(tempQName)) {
            securityMap.put(key, value);
        }
        tempQName = null;
    }

    public void endDocument() throws SAXException {
        if (logger.isDebugEnabled())
            logger.debug("******解析WEB-INF/config/Security.xml文件结束*******");
    }

    public Map getSecruityMap() {
        return this.securityMap;
    }

}