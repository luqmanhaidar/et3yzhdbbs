package com.ntsky.bbs.service.impl;

import java.util.Date;
import java.util.List;

import com.ntsky.bbs.domain.Message;
import com.ntsky.bbs.domain.Stat;
import com.ntsky.bbs.dao.MessageDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.MessageService;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 消息服务业务处理实现
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class MessageServiceImpl implements MessageService{

	private MessageDAO messageDAO;
	
	public void setMessageDAO(MessageDAO messageDAO){
		this.messageDAO = messageDAO;
	}
	
	/**
	 * 创建消息
	 * 
	 * @param message
	 */
	public void createMessage(Message message) throws ServiceException {
		try{
			message.setSendTime(new Date());
			messageDAO.save(message);
		}
		catch(DAOException de){
			throw new ServiceException("添加消息['"+message.getTitle()+"']失败.");
		}
	}
	
	/**
	 * 更新消息状态
	 * 
	 * @param msgId 消息编号
	 * @param status 消息状态 
	 * 1、正常消息 2、草稿 3、已经删除消息
	 * @throws ServiceException 业务异常
	 */
	public void editMessageStatus(int msgId,int status) throws ServiceException{
		try{
			messageDAO.updateMessageStatus(msgId,status);
		}
		catch(DAOException de){
			throw new ServiceException("更新消息状态错误");
		}
	}
	
	/**
	 * 删除某条消息
	 * 
	 * <pre>执行步骤</pre>
	 * <ol>
	 * 		<li>已经被发送者删除&&接收者也删除&&状态为3（删除不可见）就执行物理删除</li>
	 * 		<li>更新状态为3，消息不可见</li>
	 * </ol>
	 * 
	 * @param messageId 消息编号
	 */
	/*public void deleteMessage(int messageId) throws ServiceException {
		try{
			Message message = messageDAO.findMessage(messageId);
			if(message.getIsDelReceiver()==1&&message.getIsDelSender()==1&&message.getStatus()==3)
				messageDAO.deleteMessage(messageId);
			else
				messageDAO.updateMessageStatus(messageId,3);
		}
		catch(DAOException de){
			throw new ServiceException("删除消息发生错误.");
		}
	}*/
	/**
	 * 删除某条消息
	 * 
	 * <pre>执行步骤</pre>
	 * <ol>
	 * 		<li>已经被发送者删除&&接收者也删除 就执行物理删除</li>
	 * 		<li>更新状态为3，消息不可见</li>
	 * </ol>
	 * 
	 * @param messageId 消息编号
	 * @param operator 操作者(sender、发送者|receiver、接收者)
	 */	
	public void deleteMessage(int messageId,String operator) throws ServiceException {
		try{
			Message message = messageDAO.findMessage(messageId);
			if(message.getIsDelReceiver()==1&&message.getIsDelSender()==1)
				messageDAO.deleteMessage(messageId);
			else
				messageDAO.trashMessage(messageId,operator);
		}
		catch(DAOException de){
			throw new ServiceException("删除消息发生错误.");
		}
	}
	
	/**
	 * 删除单个短消息
	 * @param messageId
	 */
	public void deleteMessage(int messageId) throws ServiceException{
		try{
			messageDAO.deleteMessage(messageId);
		}
		catch(DAOException de){
			throw new ServiceException("删除消息发生错误.");
		}
	}
	
	/**
	 * 设置某条消息为垃圾
	 * 
	 * @param messageId 消息编号
	 * @param operator 操作者(sender、发送者|receiver、接收者)
	 */
	public void trashMessage(int messageId,String operator) throws ServiceException {
		try{
			messageDAO.trashMessage(messageId,operator);
		}
		catch(DAOException de){
			throw new ServiceException("设置短消息为垃圾失败.");
		}
	}
	
	/**
	 * 设置多条短消息为垃圾
	 * @param ids
	 * @param operator 操作者(sender、发送者|receiver、接收者)
	 * @throws ServiceException
	 */
	public void trashMoreMessage(Integer[] ids,String operator) throws ServiceException {
		try{
			for (int i = 0; i < ids.length; i++) {
				messageDAO.trashMessage(((Integer)ids[i]).intValue(),operator);
			}
		}
		catch(DAOException de){
			throw new ServiceException("设置多条短消息为垃圾发生错误");
		}
	}
	
	/**
	 * 设置全部的已发送消息为垃圾
	 * @param sender 发送者
	 */	
	public void trashSendMessage(String sender) throws ServiceException{
		try{
			messageDAO.trashSendMessage(sender);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}	
	
	/**
	 * 修改消息信息
	 * 
	 * @param message 消息信息
	 */
	public void editMessage(Message message) throws ServiceException {
		try{
			Message tempMsg = messageDAO.findMessage(message.getId().intValue());
			tempMsg.setTitle(message.getTitle());
			tempMsg.setContent(message.getContent());
			tempMsg.setReceiver(message.getReceiver());
			messageDAO.update(tempMsg);
		}	
		catch(DAOException de){
			throw new ServiceException("更新消息发生错误.");
		}
	}
	
	/**
	 * 取得某条消息详细信息
	 * 
	 * @param messageId 消息编号
	 */
	public Message viewMessage(int messageId) throws ServiceException {
		try{
			// 更新贴子状态为已读
			messageDAO.updateMessageIsRead(messageId,1);
			// 查看消息
			return messageDAO.findMessage(messageId);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
	/**
	 * 取得某条消息详细信息
	 * 
	 * @param messageId 消息编号
	 */
	public Message getMessage(int messageId) throws ServiceException {
		try{
			return messageDAO.findMessage(messageId);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
	/**
	 * 列出收件箱信息
	 * 
	 * @param receiver 消息接收者
	 * @param dataNum 每次检索的消息数
	 * @return List 消息集合
	 * @throws DAOException
	 */
	public List getMessages(String receiver,int dataNum) throws ServiceException {
		try{
			return messageDAO.findMessages(receiver,dataNum);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}		
	}
	
	/**
	 * 根据消息状态列表消息
	 * 
	 * @param isRead 是否已读 (1 是, 0 否, 2 全部)
	 * @param pagination 分页参数
	 * @param receiver 接收者
	 * @return QueryResult 消息集合
	 * @throws DAOException
	 */
	public QueryResult getMessages(String receiver,int isRead,Pagination pagination) throws ServiceException{
		try{
			return messageDAO.findMessages(receiver,isRead,pagination);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	} 
	
	/**
	 * 根据消息状态列表消息
	 * 
	 * @param status 消息状态
	 * @param sender 邮件发送者
	 * @param pagination 分页参数
	 * @return QueryResult 消息集合
	 * @throws ServiceException
	 */
	public QueryResult getMessagesByStatus(int status,String sender,Pagination pagination) throws ServiceException {
		try{
			return messageDAO.findMessagesByStatus(status,sender,pagination);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	} 
	
	/**
	 * 列表垃圾箱短消息
	 * @param username 发送或接收用户
	 * @throws DAOException
	 */
	public QueryResult getTrashMessages(String username,Pagination pagination) throws ServiceException{
		try{
			return messageDAO.findTrashMessages(username,pagination);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
	/**
	 * 根据消息标志列表消息
	 * 
	 * @param flag 消息标志
	 * @param pagination 分页参数
	 * @return QueryResult 消息集合
	 * @throws DAOException
	 */
	public QueryResult getMessagesByFlag(int flag,Pagination pagination) throws DAOException {
		try{
			return messageDAO.findMessagesByFlag(flag,pagination);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}		
	} 
	
	/**
	 * 删除全部的已发送邮件
	 * @param sender 发送者
	 */	
	public void deleteSendMessage(String sender) throws ServiceException{
		try{
			messageDAO.deleteSendMessage(sender);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}

	
	/**
	 * 清除垃圾箱
	 * @param username 发送或接收者名称
	 */
	public void cleanTrash(String username) throws ServiceException {
		try{
			Object[] messageArray = messageDAO.findTrashMessages(username).toArray();
			int messageId = 0;
			Message message = null;
			for (int i = 0; i < messageArray.length; i++) {
				message = messageDAO.findMessage(messageId);
				if(message.getIsDelReceiver()==1&&message.getIsDelSender()==1)
					messageDAO.deleteMessage(messageId);
				else
					messageDAO.updateMessageStatus(messageId,3);
			}
		}
		catch(DAOException de){
			throw new ServiceException("清空垃圾箱发生错误!");
		}
	}
	
	/**
	 * 删除多条短消息
	 * @param ids
	 * @param operator 操作者(sender、发送者|receiver、接收者)
	 * 
	 * @throws ServiceException
	 */
	public void deleteMoreMessage(Integer[] ids,String operator) throws ServiceException{
		try{
			int messageId = 0;
			for (int i = 0; i < ids.length; i++) {
				messageId = ((Integer)ids[i]).intValue();
				Message message = messageDAO.findMessage(messageId);
				if(message.getIsDelReceiver()==1&&message.getIsDelSender()==1)
					messageDAO.deleteMessage(messageId);
				else
					messageDAO.trashMessage(messageId,operator);
			}
		}
		catch(DAOException de){
			throw new ServiceException("删除多条Message信息发送错误");
		}
	}
	
	/**
	 * 取得接受者未读消息数
	 * @param receiver 消息接收者
	 * @param isRead
	 * @return
	 */
	public int getMessageNumByIsNoRead(String receiver,int isRead) throws ServiceException {
		try{
			return messageDAO.findMessageNumByIsNoRead(receiver, isRead);
		}
		catch(DAOException de){
			throw new ServiceException("统计未读消息数失败...");
		}
	}
	
	/**
	 * 删除多条短消息
	 * @param ids
	 * 
	 * @throws ServiceException
	 */
	public void deleteMoreMessage(Integer[] ids) throws ServiceException{
		try{
			int messageId = 0;
			for (int i = 0; i < ids.length; i++) {
				messageId = ((Integer)ids[i]).intValue();
				messageDAO.deleteMessage(messageId);
			}
		}
		catch(DAOException de){
			throw new ServiceException("删除多条Message信息发送错误");
		}
	}	
	
}
