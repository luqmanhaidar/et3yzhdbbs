package com.ntsky.bbs.dao;

import java.util.List;

import com.ntsky.bbs.domain.Message;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 消息模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface MessageDAO extends BaseDAO{
	
	/**
	 * 根据消息编号查找消息信息
	 * @param msgId 消息编号
	 * @return Message 消息信息
	 */	
	public Message findMessage(int msgId) throws DAOException ;
	
	/**
	 * 根据消息状态列表消息
	 * 
	 * @param isRead 是否已读 (1 是, 0 否, 2 全部)
	 * @param pagination 分页参数
	 * @param receiver 接收者
	 * @return QueryResult 消息集合
	 * @throws DAOException
	 */
	public QueryResult findMessages(String receiver,int isRead,Pagination pagination) throws DAOException ; 
	
	/**
	 * 取得接受者未读消息数
	 * @param receiver 消息接收者
	 * @param isRead
	 * @return
	 */
	public int findMessageNumByIsNoRead(String receiver,int isRead) throws DAOException ;
	
	
	/**
	 * 列出收件箱信息
	 * 
	 * @param receiver 消息接收者
	 * @param dataNum 每次检索的消息数
	 * @return List 消息集合
	 * @throws DAOException
	 */
	public List findMessages(String receiver,int dataNum) throws DAOException;	
	
	/**
	 * 根据消息状态列表消息
	 * 
	 * @param status 消息状态
	 * @param sender 邮件发送者
	 * @param pagination 分页参数
	 * @return QueryResult 消息集合
	 * @throws DAOException
	 */
	public QueryResult findMessagesByStatus(int status,String sender,Pagination pagination) throws DAOException; 
	
	/**
	 * 根据消息标志列表消息
	 * 
	 * @param flag 消息标志
	 * @param pagination 分页参数
	 * @return QueryResult 消息集合
	 * @throws DAOException
	 */
	public QueryResult findMessagesByFlag(int flag,Pagination pagination) throws DAOException ; 
	
	/**
	 * 列表全部的垃圾消息
	 * @param username 发送或接收者名称
	 * @return 垃圾消息列表
	 * @throws DAOException
	 */
	public List findTrashMessages(String username) throws DAOException ;	
	
	/**
	 * 列表垃圾箱短消息
	 * @param username 发送或接收用户
	 * @throws DAOException
	 */
	public QueryResult findTrashMessages(String username,Pagination pagination) throws DAOException;	
	
	/**
	 * 更新消息状态
	 * 
	 * @param msgId 消息编号
	 * @param status 消息状态 
	 * 1、正常消息 2、草稿 3、已经删除消息 
	 * @throws DAOException
	 */
	public void updateMessageStatus(int msgId,int status) throws DAOException;
	
	/**
	 * 删除邮件
	 * @param msgId 消息编号
	 */
	public void deleteMessage(int msgId) throws DAOException;
	
	/**
	 * 删除全部的已发送邮件
	 * @param sender 发送者
	 */
	public void deleteSendMessage(String sender) throws DAOException;
	
	/**
	 * 删除全部的已接收邮件
	 * @param sender 接收者
	 */
	public void deleteReceiverMessage(String receiver) throws DAOException;
	/**
	 * 设置某条消息为垃圾
	 * 
	 * @param messageId 消息编号
	 * @param operator 操作者(sender、发送者|receiver、接收者)
	 */
	public void trashMessage(int messageId,String operator) throws DAOException ;
	
	/**
	 * 设置全部的已发送消息为垃圾
	 * @param sender 发送者
	 */	
	public void trashSendMessage(String sender) throws DAOException;	
	
	/**
	 * 更新消息是否已读
	 * @param messageId
	 * @param isRead
	 * @throws DAOException
	 */
	public void updateMessageIsRead(int messageId,int isRead) throws DAOException;
}
