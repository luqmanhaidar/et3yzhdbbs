package com.ntsky.bbs.web.webwork.action.message;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.webwork.interceptor.SessionAware;
import com.opensymphony.xwork.ModelDriven;

import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.domain.Message;

/**
 * 添加消息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:26 $
 */
public class EditMessageAction extends MessageActionSupport implements ModelDriven{

	private Message message = new Message();
	
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("添加的消息 ['"+message.getTitle()+"'] ");
		}
		messageService.editMessage(message);
		return SUCCESS;
	}
	
	public Object getModel() {
		return this.message;
	}

	// 返回数据
	public Message getMessage(){
		return this.message;
	}
	
	// 请求参数
	private int messageId;
	public void setMessageId(int messageId){
		this.messageId = messageId;
	}
	
	public String doReply() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("回复短消息");
		}
		message = messageService.getMessage(messageId);
		return SUCCESS;
	}
	
}
