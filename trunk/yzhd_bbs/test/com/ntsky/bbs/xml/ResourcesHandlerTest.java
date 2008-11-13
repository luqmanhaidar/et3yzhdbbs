package com.ntsky.bbs.xml;

import java.util.List;

import com.ntsky.framework.util.xml.SAXHelper;

import com.ntsky.bbs.NTskyTest;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.xml.ResourcesHandler;
import com.ntsky.bbs.exception.XMLException;
import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.xml.bean.Resource;

public class ResourcesHandlerTest extends NTskyTest{

	public void testResourceHandler(){
    	try{
    		ResourcesHandler resourcesHandler = new ResourcesHandler();
	       
    		//emailHandler.setParameter("register");
    		if(logger.isDebugEnabled()){
    			logger.debug("app's path : " + Application.getInstance().getFilePath(Symbols.XML_RESOURCE));
    		}
	    	SAXHelper.parseXML(Application.getInstance().getFilePath(Symbols.XML_RESOURCE),resourcesHandler);
	    	List resources = resourcesHandler.getResources();
	    	System.out.println("资源信息长度 : " + resources.size());
	    	Object[] objectArray = resources.toArray();
	    	Resource resource = null;
	    	for (int i = 0; i < objectArray.length; i++) {
	    		resource = (Resource)objectArray[i];
	    		System.out.println("name = " + resource.getName());
	    		System.out.println("size = " + (resource.getPermissionMap().size()));
			}
            /*Set set = email.getMimeMessages().entrySet();
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
           }*/
			
		} catch (XMLException exception) {
			throw new XMLException("解析Email.xml发生错误!");
		}
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(ResourcesHandlerTest.class);
	}	
	
}
