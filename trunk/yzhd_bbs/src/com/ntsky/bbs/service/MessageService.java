package com.ntsky.bbs.service;

import java.util.List;

import com.ntsky.bbs.domain.Message;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 消息服务业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface MessageService extends BaseService{

	/**
	 * 创建消息
	 * 
	 * @param message
	 */
	public void createMessage(Message message) throws ServiceException ;
	
	/**
	 * 更新消息状态
	 * 
	 * @param msgId 消息编号
	 * @param status 消息状态 
	 * 1、正常消息 2、草稿 3、已经删除消息
	 * @throws ServiceException 业务异常
	 */
	public void editMessageStatus(int msgId,int status) throws ServiceException;
	
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
	public void deleteMessage(int messageId,String operator) throws ServiceException ;
	
	/**
	 * 删除单个短消息
	 * @param messageId
	 */
	public void deleteMessage(int messageId) throws ServiceException ;
	
	/**
	 * 删除多条短消息
	 * @param ids
	 * 
	 * @throws ServiceException
	 */
	public void deleteMoreMessage(Integer[] ids) throws ServiceException;
	
	/**
	 * 删除多条短消息
	 * @param ids
	 * @param operator 操作者(sender、发送者|receiver、接收者)
	 * 
	 * @throws ServiceException
	 */
	public void deleteMoreMessage(Integer[] ids,String operator) throws ServiceException ;
	
	/**
	 * 删除全部的已发送邮件
	 * @param sender 发送者
	 */	
	public void deleteSendMessage(String sender) throws ServiceException ;
	
	public void deleteReceiverMessage(String receiver) throws ServiceException;
	
	/**
	 * 清除垃圾箱
	 * @param username 发送或接收者名称
	 */
	public void cleanTrash(String username) throws ServiceException;	
	
	/**
	 * 设置某条消息为垃圾
	 * 
	 * @param messageId 消息编号
	 * @param operator 操作者(sender、发送者|receiver、接收者)
	 */
	public void trashMessage(int messageId,String operator) throws ServiceException ;
	
	/**
	 * 设置多条短消息为垃圾
	 * @param ids
	 * @throws ServiceException
	 */
	public void trashMoreMessage(Integer[] ids,String operator) throws ServiceException;
	
	/**
	 * 设置全部的已发送消息为垃圾
	 * @param sender 发送者
	 */	
	public void trashSendMessage(String sender) throws ServiceException;
	
	/**
	 * 修改消息信息
	 * 
	 * @param message 消息信息
	 */
	public void editMessage(Message message) throws ServiceException ;
	
	/**
	 * 取得某条消息详细信息
	 * 
	 * @param messageId 消息编号
	 */
	public Message getMessage(int messageId) throws ServiceException ;	
	
	/**
	 * 取得某条消息详细信息
	 * 
	 * @param messageId 消息编号
	 */
	public Message viewMessage(int messageId) throws ServiceException ;
	
	/**
	 * 根据消息状态列表消息
	 * 
	 * @param isRead 是否已读 (1 是, 0 否, 2 全部)
	 * @param pagination 分页参数
	 * @param sender 邮件发送者
	 * @return QueryResult 消息集合
	 * @throws DAOException
	 */
	public QueryResult getMessages(String sender,int isRead,Pagination pagination) throws ServiceException ;
	
	/**
	 * 列出收件箱信息
	 * 
	 * @param receiver 消息接收者
	 * @param dataNum 每次检索的消息数
	 * @return List 消息集合
	 * @throws DAOException
	 */
	public List getMessages(String receiver,int dataNum) throws ServiceException;	
	
	/**
	 * 取得接受者未读消息数
	 * @param receiver 消息接收者
	 * @param isRead
	 * @return
	 */
	public int getMessageNumByIsNoRead(String receiver,int isRead) throws ServiceException ;
	
	
	/**
	 * 根据消息状态列表消息
	 * 
	 * @param status 消息状态
	 * @param pagination 分页参数
	 * @return QueryResult 消息集合
	 * @throws DAOException
	 */
	public QueryResult getMessagesByStatus(int status,String receiver,Pagination pagination) throws ServiceException ;
	
	/**
	 * 列表垃圾箱短消息
	 * @param username 发送或接收用户
	 * @throws DAOException
	 */
	public QueryResult getTrashMessages(String username,Pagination pagination) throws ServiceException;
	
	/**
	 * 根据消息标志列表消息
	 * 
	 * @param flag 消息标志
	 * @param pagination 分页参数
	 * @return QueryResult 消息集合
	 * @throws DAOException
	 */
	public QueryResult getMessagesByFlag(int flag,Pagination pagination) throws DAOException ;

}
