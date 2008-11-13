package com.ntsky.bbs.web.webwork.action.message;

import com.ntsky.bbs.domain.Message;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 查看消息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $
 */
public class ViewMessageAction extends MessageActionSupport {

	//　消息编号
	private int messageId;
	public void setMessageId(int messageId){
		this.messageId = messageId;
	}
	
	// 消息对象
	private Message message;
	public Message getMessage(){
		return this.message;
	}
	
	/**
	 * 查看消息
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("查看消息");
		}
		try{
			message = messageService.viewMessage(messageId);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
}
