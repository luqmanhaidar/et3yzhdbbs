package com.ntsky.bbs.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ntsky.bbs.xml.bean.Badword;

/**
 * 读取过滤字符(Badwords)XML信息
 * 
 * <ol>
 * 	<li>读取Badwords将过滤字符添加到列表</li>
 * </ol>
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */

public class BadwordsHandler extends DefaultHandler{

	private final static Logger logger = Logger.getLogger(BadwordsHandler.class.getName());
    
	private String element = null;

	// 过滤字符集合
	private List badwords = null;
	
	// 过滤字符的对象
	private Badword badword = null; 

	public void startDocument() throws SAXException {
        if(logger.isDebugEnabled())
            logger.debug("******开始解析WEB-INF/config/Badwords.xml文件*******");
        badwords = new ArrayList();
	}
	
    public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException {
    	element = qName; 	
    	
    	// resource节点
    	if("badword".equals(qName)){
    		badword = new Badword();
    		for (int att = 0; att < atts.getLength(); att++) {
    			String attName = atts.getQName(att);
    			if ("id".equals(attName)) {
    				badword.setId(Integer.parseInt(atts.getValue(attName)));
    			}
    		}    
    	}
    }
	
    public void characters(char[] chars, int start, int length) throws SAXException {
    	if ("oldStr".equals(element)) {
    		badword.setOldStr(new String(chars, start, length));
		}
    	if ("replaceStr".equals(element)){
    		badword.setReplaceStr(new String(chars, start, length));
    	}
    }    

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    	element = null;
    	
    	// 设置属性的key和value
    	if ("badword".equals(qName)) {
    		badwords.add(badword);
        }
    }    
    
    public void endDocument() throws SAXException {
        if(logger.isDebugEnabled())
            logger.debug("******解析WEB-INF/config/Badwords.xml文件结束*******");        
    }
    
    public List getBadwords(){
    	return this.badwords;
    }

}
