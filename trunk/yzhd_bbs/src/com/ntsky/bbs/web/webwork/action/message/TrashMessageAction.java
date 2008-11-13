package com.ntsky.bbs.web.webwork.action.message;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 更改消息状态
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:26 $
 */
public class TrashMessageAction extends MessageActionSupport {
	
	private int messageId;
	public void setMessageId(int messageId){
		this.messageId = messageId;
	}
	/**
	 * 将接收到的邮件丢到垃圾箱
	 * @return
	 * @throws Exception
	 */
	public String trashOne_receiver() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("将接收的短信息丢到垃圾箱...");
		}
		try{
			messageService.trashMessage(messageId,Symbols.MESSAGE_RECEIVER);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		if(logger.isInfoEnabled()){
			logger.info("成功设置单条接收的短消息为垃圾.");
		}
		return SUCCESS;
	}

	/**
	 * 将发送的邮件丢到垃圾箱
	 * @return
	 * @throws Exception
	 */
	public String trashOne_sender() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("将发送的短信息丢到垃圾箱...");
		}
		try{
			messageService.trashMessage(messageId,Symbols.MESSAGE_SENDER);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		if(logger.isInfoEnabled()){
			logger.info("成功设置单条发送的短消息垃圾.");
		}
		return SUCCESS;
	}	
	
	private Integer[] ids;
	public void setId(Integer[] ids){
		this.ids = ids;
	}
	/**
	 * 将接收到的多个邮件丢到垃圾箱
	 * @return
	 * @throws Exception
	 */
	public String trashMore_receiver() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("将接收的多个短消息丢到垃圾箱...");
		}
		try{
			messageService.trashMoreMessage(ids,Symbols.MESSAGE_RECEIVER);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}

	/**
	 * 将接收到的多个已发送邮件丢到垃圾箱
	 * @return
	 * @throws Exception
	 */
	public String trashMore_sender() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("将发送的多个短消息丢到垃圾箱...");
		}
		try{
			messageService.trashMoreMessage(ids,Symbols.MESSAGE_SENDER);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}	
	
	/**
	 * 设置全部已发送短消息为垃圾
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("设置全部已发送短消息为垃圾");
		}
		try{
			messageService.trashSendMessage(super.getSessionUser().getUsername());
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
}
