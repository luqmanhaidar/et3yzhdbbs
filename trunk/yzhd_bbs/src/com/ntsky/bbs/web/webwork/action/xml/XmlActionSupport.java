package com.ntsky.bbs.web.webwork.action.xml;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.service.XmlDataService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

public abstract class XmlActionSupport extends BasicActionSupport{
	
	protected XmlDataService xmlDataService;
	
	public void setXmlDataService(XmlDataService xmlDataService){
		this.xmlDataService = xmlDataService;
	}
	
	private Map propertyMap;
	public void setPropertyMap(Map propertyMap){
		this.propertyMap = propertyMap;
	}
	public Map getPropertyMap(){
		return propertyMap;
	}
}
