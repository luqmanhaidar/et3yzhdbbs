package com.ntsky.bbs.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ntsky.bbs.NTskyTest;
import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.exception.XMLException;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.xml.bean.Badword;
import com.ntsky.framework.util.FileUtil;
import com.ntsky.framework.util.xml.SAXHelper;
import com.ntsky.framework.util.xml.DOMData; 

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
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:46 $
 */

public class BadwordsHandlerTest extends NTskyTest{

	public void testBadwordsHandler(){
    	try{
    		BadwordsHandler badwordsHandler = new BadwordsHandler();
    		SAXHelper.parseXML(Application.getInstance().getFilePath(Symbols.XML_BADWORDS),badwordsHandler);
    		List list = badwordsHandler.getBadwords();
    		System.out.println("过滤字符个数 : "+list.size());
    		Badword badword = null;
    		for (int i = 0; i < list.size(); i++) {
    			badword = (Badword)list.get(i);
    			logger.debug(badword.getId() + ". " + badword.getReplaceStr());
			}
		} catch (XMLException exception) {
			throw new XMLException("解析badwords.xml发生错误!");
		}
	}
	
	
	public void testCreateBadword(){
		try{
			String badwordPath = Application.getInstance().getFilePath(Symbols.XML_BADWORDS);
			DOMData domData = DOMData.getInstance(badwordPath);
			Element element = domData.createElement(domData.getRoot(),"badword");
			element.setAttribute("id", String.valueOf(domData.getMaxAttributeKey("badword","id")+1));
			Map map = new HashMap();
			map.put("oldStr", "test");
			map.put("replaceStr", "***");
			domData.createElement(element, map);
			logger.debug("document : " + domData.getDocument());
			FileUtil.writeXMLByDOM(domData.getDocument(), "c:/11.xml");
		}
		catch(Exception ex){
			ex.printStackTrace();
			logger.error(ex);
		}
	}
	
	public void testGetBadword(){
		try{
			String badwordPath = Application.getInstance().getFilePath(Symbols.XML_BADWORDS);
			DOMData domData = DOMData.getInstance(badwordPath);
			Node node = domData.getSelElement("badword", 1);
			logger.debug(node.toString());
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				node = nodeList.item(i);
				//logger.debug("nodeName = " + node.getNodeName());
				if("oldStr".equals(node.getNodeName()))
					logger.debug("oldStr = " + node.getFirstChild().getNodeValue());
				if("replaceStr".equals(node.getNodeName()))
					logger.debug("replaceStr = " + node.getFirstChild().getNodeValue());
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			logger.error(ex);
		}
	}
	
	public void testUpdateBadword(){
		try{
			String badwordPath = Application.getInstance().getFilePath(Symbols.XML_BADWORDS);
			DOMData domData = DOMData.getInstance(badwordPath);
			Node node = domData.getSelElement("badword", 1);
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				node = nodeList.item(i);
				if("oldStr".equals(node.getNodeName())){
					domData.setNodeValue(node, "123");
					logger.debug("oldStr = " + node.getFirstChild().getNodeValue());
				}
				if("replaceStr".equals(node.getNodeName())){
					domData.setNodeValue(node, "234");
					logger.debug("replaceStr = " + node.getFirstChild().getNodeValue());
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			logger.error("更新badword.xml发生错误");
		}
	}
	
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(BadwordsHandlerTest.class);
	}	

}
