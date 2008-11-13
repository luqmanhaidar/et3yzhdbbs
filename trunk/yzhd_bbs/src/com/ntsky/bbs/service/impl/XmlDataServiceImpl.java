package com.ntsky.bbs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ntsky.framework.util.FileUtil;
import com.ntsky.framework.util.xml.DOMData;
import com.ntsky.framework.util.xml.DOMHelper;
import com.ntsky.framework.util.xml.SAXHelper;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.config.EmailConfig;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.exception.XMLException;
import com.ntsky.bbs.service.XmlDataService;
import com.ntsky.bbs.xml.BadwordsHandler;
import com.ntsky.bbs.xml.EmailHandler;
import com.ntsky.bbs.xml.SystemHandler;
import com.ntsky.bbs.xml.bean.Badword;
import com.ntsky.bbs.xml.bean.Email;

public class XmlDataServiceImpl implements XmlDataService{

    private final static Logger logger = Logger.getLogger(XmlDataServiceImpl.class.getName());
    	
    
	/**
	 * 数据Map
	 * @param dataId 数据类型
	 * @return Map 指定数据类型的Map
	 */
	public Map select(String dataId) {
		return SystemConfig.getInstance().getPropertyMap(dataId);
	}

	/**
	 * 修改Xml数据
	 * @param dataId 数据标示
	 * @param valueMap 值数组
	 */
	public void editXml(String dataId,Map propertyMap) throws XMLException{

		if (logger.isDebugEnabled()) {
			logger.debug("更新的数据类型 : " + dataId);
		}
		DOMData domData = DOMData.getInstance(Application.getInstance().getFilePath(Symbols.XML_SYSTEM_CONFIG));

		this.updateNodeValue(domData,dataId,propertyMap);
		
		FileUtil.writeXMLByDOM(domData.getDocument(), Application.getInstance()
				.getFilePath(Symbols.XML_SYSTEM_CONFIG));

		// 刷新执行dataId对应的内存数据
		SystemHandler memoryDataHandler = new SystemHandler();
		memoryDataHandler.setParameter(dataId);
		SAXHelper.parseXML(Application.getInstance().getFilePath(Symbols.XML_SYSTEM_CONFIG),
				memoryDataHandler);
		SystemConfig.getInstance().setData(dataId, (Map) memoryDataHandler.getDatas().get(
				dataId));
	    	
	}

	/**
	 * 更新节点的值
	 * 
	 * @param domData dom对象
	 * @param dataId 节点值
	 * @param propertyMap 属性Map
	 */
	private void updateNodeValue(DOMData domData,String dataId,Map propertyMap){
		Node dataNode = domData.selectNodeByAttribute("data", "id", dataId);
		Set set = propertyMap.entrySet();
		Iterator iterator = set.iterator();
		Map.Entry entry = null;
		if(logger.isDebugEnabled()){
			logger.debug("被更新的节点信息如下 : ");
		}
		while (iterator.hasNext()) {
			entry = (Map.Entry) iterator.next();
			if(logger.isDebugEnabled()){
				logger.debug(" key : "+(String) entry.getKey() +" | value : " + ((String[]) entry.getValue())[0]);
			}
			domData.updateNodeValue(dataNode, "property", "key", (String) entry
					.getKey(), ((String[]) entry.getValue())[0]);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("更新后的xml节点信息 : " + dataNode);
		}		
	}
	
	/**
	 * 取得Email的信息
	 * 
	 * @return Email 邮件对象
	 */
	public Email getEmail() throws XMLException {
		return EmailConfig.getEmail();
	}
	
	/**
	 * 修改EMAIL
	 * @throws XMLException
	 */
	public void editEmail(Email email) throws XMLException{
		DOMData domData = DOMData.getInstance(Application.getInstance().getFilePath(Symbols.XML_EMAIL));
		if(logger.isDebugEnabled()){
			logger.debug("Email文件路径 : " + Application.getInstance().getFilePath(Symbols.XML_EMAIL));
		}
		logger.debug("root : " + domData.getRoot().toString());
		Node node = domData.getElements("smtp.host").item(0);
		// smtp
		domData.setNodeValue(node,email.getSmtpHost());
		// username
		node = domData.getElements("username").item(0);
		domData.setNodeValue(node,email.getUsername());
		// password
		node = domData.getElements("password").item(0);
		domData.setNodeValue(node,email.getPassword());
		// systemMail
		node = domData.getElements("systemMail").item(0);
		domData.setNodeValue(node,email.getSystemMail());
		
		// register
		this.updateEmailNodeValue(domData,"register",(Map)(email.getBodys().get("register")));
		// error 
		this.updateEmailNodeValue(domData,"error",(Map)(email.getBodys().get("error")));
		if(logger.isDebugEnabled()){
			logger.debug("Email xml 全部信息为 : \r\n" + domData.getDocument().toString());
		}
		FileUtil.writeXMLByString(domData.getDocument(), Application.getInstance()
				.getFilePath(Symbols.XML_EMAIL));

		// 刷新内存
		EmailHandler emailHandler = new EmailHandler();
    	SAXHelper.parseXML(Application.getInstance().getFilePath(Symbols.XML_EMAIL),emailHandler);
    	EmailConfig.setEmail(emailHandler.getEmail());
		
	}
	
	/**
	 * 更新EMAIL中的data节点信息
	 * 
	 * @param domData
	 * @param dataId
	 * @param propertyMap
	 */
	private void updateEmailNodeValue(DOMData domData,String dataId,Map propertyMap){
		Node dataNode = domData.selectNodeByAttribute("data", "id", dataId);
		Set set = propertyMap.entrySet();
		Iterator iterator = set.iterator();
		Map.Entry entry = null;
		if(logger.isDebugEnabled()){
			logger.debug("被更新的节点信息如下 : ");
		}
		while (iterator.hasNext()) {
			entry = (Map.Entry) iterator.next();
			if(logger.isDebugEnabled()){
				logger.debug(" key : "+(String) entry.getKey() +" | value : " + (entry.getValue()));
			}
			domData.updateNodeValue(dataNode, "property", "key", (String) entry
					.getKey(), (String)entry.getValue());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("更新后的xml节点信息 : " + dataNode);
		}		
	}
	
	/**
	 * 取得脏话列表
	 * @return
	 * @throws XMLException
	 */
	public List getBadwords() throws XMLException {
		try{
			BadwordsHandler badwordsHandler = new BadwordsHandler();
			SAXHelper.parseXML(Application.getInstance().getFilePath(Symbols.XML_BADWORDS),badwordsHandler);
			return badwordsHandler.getBadwords();
		} catch (XMLException exception) {
			throw new XMLException("解析badwords.xml发生错误!");
		}
	}
	
	/**
	 * 创建过滤的文字
	 * 
	 * @param badword 脏话对象
	 * @throws XMLException
	 */
	public void createBadword(Badword badword) throws XMLException {
		try{
			String badwordPath = Application.getInstance().getFilePath(Symbols.XML_BADWORDS);
			DOMData domData = DOMData.getInstance(badwordPath);
			Element element = domData.createElement(domData.getRoot(),"badword");
			element.setAttribute("id", String.valueOf(domData.getMaxAttributeKey("badword","id")+1));
			Map map = new HashMap();
			map.put("oldStr", badword.getOldStr());
			map.put("replaceStr", badword.getReplaceStr());
			domData.createElement(element, map);
			FileUtil.writeXMLByDOM(domData.getDocument(), Application.getInstance().getFilePath(Symbols.XML_BADWORDS));
		}
		catch (XMLException exception){
			throw new XMLException("在badwords.xml中创建过滤信息发生错误!");
		}
	}
	
	/**
	 * 根据ID查找过滤的文字的信息
	 * @param id 索引
	 * @return 查找到的过滤的文字对象
	 * @throws XMLException
	 */
	public Badword getBadword(int id) throws XMLException {
		Badword badword = new Badword();
		try{
			String badwordPath = Application.getInstance().getFilePath(Symbols.XML_BADWORDS);
			DOMData domData = DOMData.getInstance(badwordPath);
			Node node = domData.getSelElement("badword", 1);
			if(logger.isDebugEnabled())
				logger.debug("选择的badword节点为 : " + node.toString());
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				node = nodeList.item(i);
				if("oldStr".equals(node.getNodeName())){
					badword.setOldStr(node.getFirstChild().getNodeValue());
					if(logger.isDebugEnabled())
						logger.debug("oldStr = " + node.getFirstChild().getNodeValue());
				}
				if("replaceStr".equals(node.getNodeName())){
					badword.setReplaceStr(node.getFirstChild().getNodeValue());
					if(logger.isDebugEnabled())
						logger.debug("replaceStr = " + node.getFirstChild().getNodeValue());
				}
			}
		}
		catch(Exception ex){
			logger.error(ex);
			throw new XMLException("根据ID从badwords.xml中取得过滤信息发生错误!");
		}		
		return badword;
	}
	
	/**
	 * 更新xml节点
	 * @throws XMLException
	 */
	public void editBadword(Badword badword) throws XMLException {
		try{
			String badwordPath = Application.getInstance().getFilePath(Symbols.XML_BADWORDS);
			DOMData domData = DOMData.getInstance(badwordPath);
			Node node = domData.getSelElement("badword", badword.getId());
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				node = nodeList.item(i);
				if("oldStr".equals(node.getNodeName())){
					domData.setNodeValue(node, badword.getOldStr());
					logger.debug("oldStr = " + node.getFirstChild().getNodeValue());
				}
				if("replaceStr".equals(node.getNodeName())){
					domData.setNodeValue(node, badword.getReplaceStr());
					logger.debug("replaceStr = " + node.getFirstChild().getNodeValue());
				}
			}
			FileUtil.writeXMLByDOM(domData.getDocument(), badwordPath);
		}
		catch(Exception ex){
			logger.error("更新badword.xml发生错误",ex);
			throw new XMLException(ex);
		}		
	}
	
	public void replaceBadword(String Cotent) throws XMLException{
		try{
			List badwords = this.getBadwords();
		}
		catch(Exception ex){
			logger.error("替换脏话发生错误",ex);
			throw new XMLException(ex);
		}	
	}
	
}
