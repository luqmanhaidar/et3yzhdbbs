package com.ntsky.bbs.xml;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ntsky.bbs.NTskyTest;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.xml.SystemHandler;
import com.ntsky.bbs.exception.XMLException;
import com.ntsky.framework.util.xml.SAXHelper;

public class MemoryDataHandlerTest extends NTskyTest{

	public void testMemoryData(){
    	try{
	    	SystemHandler memoryDataHandler = new SystemHandler();
	       	System.out.println("xmlPath = " + Application.getInstance().getWebRealPath()+"WEB-INF/config/MemoryData.xml");
	       	memoryDataHandler.setParameter("register");
	    	SAXHelper.parseXML(Application.getInstance().getWebRealPath()+"WEB-INF/config/MemoryData.xml",memoryDataHandler);
	    	
	    	System.out.println("size=" + memoryDataHandler.getDatas().size());

            Set set = memoryDataHandler.getDatas().entrySet();
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
			throw new XMLException("解析MemoryData.xml发生错误!");
		}
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(MemoryDataHandlerTest.class);
	}	
	
}
