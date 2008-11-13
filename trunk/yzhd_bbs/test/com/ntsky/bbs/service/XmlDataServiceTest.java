package com.ntsky.bbs.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;

import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.util.Application;
import com.ntsky.framework.util.StringUtil;

public class XmlDataServiceTest extends NTskyServiceTestCase{

	private XmlDataService xmlDataService;
	
    protected void setUp() throws Exception {
		DOMConfigurator.configure(StringUtil.applyRelativePath(System.getProperty("user.dir"),"Log4j.xml"));
    	xmlDataService = (XmlDataService)super.getBean("xmlDataService");
		Application.getInstance().setWebRealPath("D:/cvs/project/forum/WebRoot/");
		Application.getInstance().setFilePathMap("memoryData",Application.getInstance().getWebRealPath()+"WEB-INF/config/MemoryData.xml");
    }
	
    public void testEditXml(){
    	Map map = new HashMap();
    	map.put("t1","22");
    	xmlDataService.editXml("test",map);
    }
    
    public static void main(String[] args) {
		junit.textui.TestRunner.run(XmlDataServiceTest.class);
	}		
	
}
