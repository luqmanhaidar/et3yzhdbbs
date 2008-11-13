package com.ntsky.bbs.web.webwork.action.message;

import java.util.List;

import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

import com.ntsky.bbs.service.MessageService;

/**
 * 消息抽象类
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $
 */
public abstract class MessageActionSupport extends BasicActionSupport{
	
	private List messages = null;
	public List getMessages() {
		return messages;
	}
	public void setMessages(List messages) {
		this.messages = messages;
	}
	
	protected MessageService messageService;
	
	public void setMessageService(MessageService messageService){
		this.messageService = messageService;
	}

}
