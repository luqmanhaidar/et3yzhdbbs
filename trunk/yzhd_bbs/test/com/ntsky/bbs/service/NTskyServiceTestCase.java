package com.ntsky.bbs.service;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ntsky.bbs.util.Application;
import com.ntsky.framework.util.StringUtil;

import junit.framework.TestCase;

public class NTskyServiceTestCase extends TestCase{
		
	public static Logger __logger = Logger.getLogger(sun.reflect.Reflection.getCallerClass(1));

	
	public Object getBean(String servieName){
		DOMConfigurator.configure(StringUtil.applyRelativePath(System.getProperty("user.dir"),"Log4j.xml"));
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/com/ntsky/bbs/service/applicationContext-service.xml");
		
		//Application.appPath = "D:/cvs/project/forum/web/";
		Application.getInstance().setWebRealPath("E:/cvshome/project/forum/web/");
		return ctx.getBean(servieName);
	}
    
    protected void setDown() throws Exception{
    }
    
    //public final static String APP_PATH = "E:/cvshome/project/forum/web/";
    

    
}
