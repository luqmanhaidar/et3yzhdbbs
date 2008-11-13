package com.ntsky.bbs.web.webwork.action.message;

import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 列表消息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $
 */
public class MessageListAction extends MessageActionSupport {

	/*private int isRead ;
	public void setIsRead(int isRead){
		this.isRead = isRead;
	}*/
	
	/**
	 * 列表消息
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("列表全部消息");
		}
		QueryResult queryResult = null;
		try{
			queryResult = messageService.getMessages(super.getSessionUser().getUsername(),2,new Pagination(getPaginationStart()));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		setMessages(queryResult.getItems());
		setPagination(queryResult.getPagination());
		return SUCCESS;
	}
	
	/**
	 * 垃圾箱消息
	 * @return
	 * @throws Exception
	 */
	public String trash() throws Exception{
		if(logger.isInfoEnabled()){
			logger.info("列表已删除的短消息");
		}
		QueryResult queryResult = null;
		try{
			queryResult = messageService.getTrashMessages(super.getSessionUser().getUsername(),new Pagination(getPaginationStart()));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		super.setMessages(queryResult.getItems());
		super.setPagination(queryResult.getPagination());
		return SUCCESS;
	}
	
	/**
	 * 草稿箱信息
	 */
	public String draft() throws Exception{
		if(logger.isInfoEnabled()){
			logger.info("列表草稿箱中的短消息");
		}
		QueryResult queryResult = null;
		try{
			queryResult = messageService.getMessagesByStatus(2,super.getSessionUser().getUsername(),new Pagination(getPaginationStart()));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		super.setMessages(queryResult.getItems());
		super.setPagination(queryResult.getPagination());
		return SUCCESS;	
	}
	
	/**
	 * 发件箱信息
	 */
	public String sended() throws Exception{
		if(logger.isInfoEnabled()){
			logger.info("列表发件箱中的短消息");
		}
		QueryResult queryResult = null;
		try{
			queryResult = messageService.getMessagesByStatus(1,super.getSessionUser().getUsername(),new Pagination(getPaginationStart()));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		super.setMessages(queryResult.getItems());
		super.setPagination(queryResult.getPagination());
		return SUCCESS;	
	}
}
