package com.ntsky.bbs.web.webwork.action.message;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.webwork.interceptor.SessionAware;
import com.opensymphony.xwork.ModelDriven;

import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Message;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 删除消息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $
 */
public class DeleteMessageAction extends MessageActionSupport {

	/**
	 * 清空废件箱
	 */
	public String cleanTrash() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("清空废件箱");
		}
		try{
			messageService.cleanTrash(super.getSessionUser().getUsername());
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}

	private int messageId;
	public void setMessageId(int messageId){
		this.messageId = messageId;
	}
	
	/**
	 * 删除单个短消息
	 * @return
	 * @throws Exception
	 */
	public String deleteOne() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("删除单个短信息...");
		}
		try{
			messageService.deleteMessage(messageId);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		if(logger.isInfoEnabled()){
			logger.info("删除单个短信息成功.");
		}
		return SUCCESS;
	}
	
	/**
	 * 删除单个接收的短消息
	 * @return
	 * @throws Exception
	 */
	public String deleteOne_receiver() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("删除单个接收的短信息...");
		}
		try{
			messageService.deleteMessage(messageId,Symbols.MESSAGE_RECEIVER);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		if(logger.isInfoEnabled()){
			logger.info("删除单个接收的短信息成功.");
		}
		return SUCCESS;
	}
	
	/**
	 * 删除单个发送的短消息
	 * @return
	 * @throws Exception
	 */
	public String deleteOne_sender() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("删除单个发送的短信息...");
		}
		try{
			messageService.deleteMessage(messageId,Symbols.MESSAGE_SENDER);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		if(logger.isInfoEnabled()){
			logger.info("删除单个发送的短信息成功.");
		}
		return SUCCESS;
	}	
	
	private Integer[] ids;
	public void setId(Integer[] ids){
		this.ids = ids;
	}
	
	/**
	 * 删除多个短消息
	 * @return
	 * @throws Exception
	 */
	public String deleteMore() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("删除多个短信息...");
		}
		try{
			messageService.deleteMoreMessage(ids);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除多个发送的短消息
	 * @return
	 * @throws Exception
	 */
	public String deleteMore_sender() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("删除多个发送的短信息...");
		}
		try{
			messageService.deleteMoreMessage(ids,Symbols.MESSAGE_SENDER);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除多个接收的短消息
	 * @return
	 * @throws Exception
	 */
	public String deleteMore_receiver() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("删除多个接收的短信息...");
		}
		try{
			messageService.deleteMoreMessage(ids,Symbols.MESSAGE_RECEIVER);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	
}
