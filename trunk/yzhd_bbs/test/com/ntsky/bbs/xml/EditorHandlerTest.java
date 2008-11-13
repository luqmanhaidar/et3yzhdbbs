package com.ntsky.bbs.xml;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ntsky.framework.util.xml.SAXHelper;

import com.ntsky.bbs.NTskyTest;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.config.EmailConfig;
import com.ntsky.bbs.xml.EmailHandler;
import com.ntsky.bbs.exception.XMLException;
import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.xml.bean.Email;

public class EditorHandlerTest extends NTskyTest{

	public void testEditorHandler(){
    	try{
    		EmailHandler emailHandler = new EmailHandler();
	       
    		//emailHandler.setParameter("register");
	    	SAXHelper.parseXML(Application.getInstance().getFilePath(Symbols.XML_EMAIL),emailHandler);
	    	Email email = emailHandler.getEmail();
	    	
	    	assertNotNull(email);
	    	logger.debug("data map 's size : " + email.getBodys().size());
	    	
	    	assertEquals(email.getSmtpHost(),"smtp.163.com");
	    	assertEquals(email.getUsername(),"ntsky");
	    	//assertEquals(email.getPassword(),"ntskyqaz");
	    	
	    	//__logger.debug("smtp host : " + );
	    	EmailConfig.setEmail(email);
	    	
	    	//__logger.debug("register-title : " + EmailConfig.getPropertyValue("register","content"));
	    	
            Set set = email.getBodys().entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry keyEntry = (Map.Entry) iterator.next();
                System.out.println(keyEntry.getKey() + "\r\n");
                
                Map dataMap = (Map)keyEntry.getValue();
                Iterator propertyIterator = dataMap.entrySet().iterator();
                while (propertyIterator.hasNext()) {
                	Map.Entry propertyEntry = (Map.Entry) propertyIterator.next();
                	System.out.println("\t" + propertyEntry.getKey() + " -- " + propertyEntry.getValue() + "\r\n");
                }
           }
			
		} catch (XMLException exception) {
			throw new XMLException("解析Email.xml发生错误!");
		}
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(EditorHandlerTest.class);
	}	
	
}
