package com.ntsky.bbs.service;

import java.util.Date;

import org.apache.log4j.Logger;

import com.ntsky.bbs.domain.ActLog;
import com.ntsky.bbs.domain.Message;
import com.ntsky.bbs.service.ActLogService;

public class MessageServiceTest extends NTskyServiceTestCase{

	public static Logger __logger = Logger.getLogger(MessageServiceTest.class);
	
	private MessageService messageService;
	private Message message = null;
	
    protected void setUp() throws Exception {
    	messageService = (MessageService)super.getBean("messageService");
    	if(__logger.isDebugEnabled()){
    		__logger.debug("messageService为空吗 : " + (messageService!=null));
    	}
    	message = new Message();
    	message.setSender("ntsky");
    	message.setReceiver("delevermaster");
    	message.setFlag(1);
    	message.setTitle("您好!");
    	message.setContent("12345");
    	message.setIsRead(0);
    	message.setSendTime(new Date());
    }
    
    public void testMessageService(){
    	assertNotNull(messageService!=null);
    }
	
	public void testCreateMessage(){
		messageService.createMessage(message);
	}
		
	public void testDeleteMessage(){
		messageService.deleteMessage(2,"sender");
	}
	
	public void testGetMessage(){
		//assertNotNull(messageService.getMessage("ntsky",1));
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(MessageServiceTest.class);
	}		
	
}
