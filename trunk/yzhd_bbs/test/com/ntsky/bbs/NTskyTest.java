package com.ntsky.bbs;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.util.Application;

import com.ntsky.bbs.Symbols;

import junit.framework.TestCase;

public abstract class NTskyTest extends TestCase{

	public static Logger logger = Logger.getLogger(sun.reflect.Reflection.getCallerClass(1));
	
	public NTskyTest(){
		DOMConfigurator.configure(StringUtil.applyRelativePath(System.getProperty("user.dir"),"Log4j.xml"));
	}
	
	protected void setUp() throws Exception{
		Application.getInstance().setWebRealPath("D:/cvs/project/ntsky_bbs/web/");
		//Application.appPath = "E:/cvshome/project/forum/web/";
		Application.getInstance().setFilePathMap(Symbols.XML_SYSTEM_CONFIG,Application.getInstance().getWebRealPath()+"WEB-INF/config/MemoryData.xml");
		Application.getInstance().setFilePathMap(Symbols.XML_EMAIL,Application.getInstance().getWebRealPath()+"WEB-INF/config/Email.xml");
		Application.getInstance().setFilePathMap(Symbols.XML_RESOURCE,Application.getInstance().getWebRealPath()+"WEB-INF/config/Resources.xml");
		Application.getInstance().setFilePathMap(Symbols.XML_BADWORDS,Application.getInstance().getWebRealPath()+"WEB-INF/config/Badwords.xml");
		Application.getInstance().setFilePathMap(Symbols.XML_EDITOR,Application.getInstance().getWebRealPath()+"WEB-INF/config/Editor.xml");
	}
	
}
