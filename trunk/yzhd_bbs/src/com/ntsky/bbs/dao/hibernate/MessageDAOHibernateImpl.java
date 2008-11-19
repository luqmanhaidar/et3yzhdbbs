package com.ntsky.bbs.dao.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Message;
import com.ntsky.bbs.dao.MessageDAO;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.exception.DAOException;

/**
 * 消息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/21 19:31:15 $
 */
public class MessageDAOHibernateImpl extends BaseDAOHibernateImpl implements MessageDAO {

	/**
	 * 根据msgId查找Message信息
	 * 
	 * @param msgId message编号
	 * @return Message 消息对象
	 */
	public Message findMessage(int msgId) throws DAOException {
		try{
			return (Message)super.get(Message.class,new Long(msgId));
		}
		catch(DAOException daoException){
			throw new DAOException("根据消息编号查找消息失败.");
		}
	}

	/**
	 * 根据消息状态列表消息
	 * 
	 * @param receiver 接收者
	 * @param pagination 分页参数
	 * @return QueryResult 消息集合
	 * @throws DAOException
	 */
	public QueryResult findMessages(String receiver,int isRead,Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class);
			detachedCriteria.add(Restrictions.eq("receiver",receiver));
			detachedCriteria.add(Restrictions.eq("status",new Integer(1)));
			detachedCriteria.add(Restrictions.eq("isDelReceiver",0));
			if(isRead!=2){
				detachedCriteria.add(Restrictions.eq("isRead",isRead));
			}
			return super.findItemsByCriteria(detachedCriteria,null,pagination);
		}
		catch(DAOException daoException){
			throw new DAOException("查找全部消息失败.");
			/*if(isRead == 0){
				throw new DAOException("查找未读消息失败.");
			}
			else{
				throw new DAOException("查找已读消息失败.");
			}*/
		}		
	} 
	
	/**
	 * 取得接受者未读消息数
	 * @param receiver 消息接收者
	 * @param isRead
	 * @return
	 */
	public int findMessageNumByIsNoRead(String receiver,int isRead) throws DAOException {
		try{
			return ((Integer)super.findByAggregate("select count(message.id) from Message as message where message.receiver='"+receiver+"' and message.isDelReceiver=0 and message.status=1 and message.isRead='"+isRead+"'")).intValue();
		}
		catch(DAOException de){
			throw new DAOException("统计未读消息数失败...");
		}
	}
	

	/**
	 * 更新消息是否已读
	 * @param messageId
	 * @param isRead
	 * @throws DAOException
	 */
	public void updateMessageIsRead(int messageId,int isRead) throws DAOException {
		try{
			super.executeHsql("update Message set isRead='"+isRead+"' where id='"+messageId+"'");
		}
		catch(DAOException de){
			throw new DAOException("更新消息是否已读状态失败.");
		}
	}
	
	/**
	 * 根据消息状态列表消息
	 * 
	 * @param status 消息状态
	 * @param sender 邮件发送者
	 * @param pagination 分页参数
	 * @return QueryResult 消息集合
	 * @throws DAOException
	 */
	public QueryResult findMessagesByStatus(int status,String sender,Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class); 
			detachedCriteria.add(Restrictions.eq("status",status));
			detachedCriteria.add(Restrictions.eq("sender",sender));
			// 状态0表示该信息还没有被删除
			detachedCriteria.add(Restrictions.eq("isDelSender",0));
			Map orderMap = new HashMap();
			orderMap.put("id","desc");
			return super.findItemsByCriteria(detachedCriteria,orderMap,pagination);
		}
		catch(DAOException daoException){
			throw new DAOException("根据消息状态查找消息失败.");
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
	public List findMessages(String receiver,int dataNum) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class); 
			detachedCriteria.add(Restrictions.eq("status",1));
			detachedCriteria.add(Restrictions.eq("receiver",receiver));
			// 状态0表示该信息还没有被删除
			detachedCriteria.add(Restrictions.eq("isDelReceiver",0));
			return super.find(detachedCriteria,dataNum);
		}
		catch(DAOException daoException){
			throw new DAOException("根据消息状态查找消息失败.");
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
	public QueryResult findMessagesByFlag(int flag,Pagination pagination) throws DAOException{
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class); 
			detachedCriteria.add(Restrictions.eq("flag",String.valueOf(flag)));
			return super.findItemsByCriteria(detachedCriteria,null,pagination);
		}
		catch(DAOException daoException){
			throw new DAOException("根据消息标志查找消息失败.");
		}		
	} 
		
	
	/**
	 * 更新消息状态
	 * 
	 * @param msgId 消息编号
	 * @param status 消息状态 
	 * 1、正常消息 2、草稿 3、已经删除消息 
	 * @throws DAOException
	 */
	public void updateMessageStatus(int msgId,int status) throws DAOException{
		try{
			super.executeHsql("update Message set stauts='"+status+"' where id='"+msgId+"'");
		}
		catch(DAOException de){
			throw new DAOException("更新消息状态失败");
		}
	}
	
	/**
	 * 删除邮件
	 * @param msgId 消息编号
	 */
	public void deleteMessage(int msgId) throws DAOException{
		try{
			super.executeHsql("delete from Message where id='"+msgId+"'");
		}
		catch(DAOException de){
			throw new DAOException("删除短消息失败");
		}
	}
	
	/**
	 * 删除全部的已发送邮件
	 * @param sender 发送者
	 */
	public void deleteReceiverMessage(String receiver) throws DAOException{
		try{
			super.executeHsql("delete from Message where receiver='"+receiver+"'");
		}
		catch(DAOException de){
			throw new DAOException("删除全部的短消息失败");
		}
	}
	
	/**
	 * 删除全部的已接收邮件
	 * @param sender 接收者
	 */
	public void deleteSendMessage(String sender) throws DAOException{
		try{
			super.executeHsql("delete from Message where sender='"+sender+"'");
		}
		catch(DAOException de){
			throw new DAOException("删除全部的短消息失败");
		}
	}
	
	/**
	 * 设置某条消息为垃圾
	 * 
	 * @param messageId 消息编号
	 * @param operator 操作者(sender、发送者|receiver、接收者)
	 */
	public void trashMessage(int messageId,String operator) throws DAOException {
		try{
			// 接收者删除短消息
			if(Symbols.MESSAGE_RECEIVER.equals(operator)){
				super.executeHsql("update Message set isDelReceiver=1 where id='"+messageId+"'");
			}
			else{
				// 发送者删除短消息
				super.executeHsql("update Message set isDelSender=1 where id='"+messageId+"'");
			}
		}
		catch(DAOException de){
			throw new DAOException("设置短消息为垃圾失败");
		}
	}
	
	/**
	 * 列表全部的垃圾消息
	 * @param username 发送或接收者名称
	 * @return 垃圾消息列表
	 * @throws DAOException
	 */
	public List findTrashMessages(String username) throws DAOException {
		try{
			return super.find("from Message as m where (m.sender='"+username+"' or m.receiver='"+username+"') and (m.isDelSender=1 or m.isDelSender=1)");
		}
		catch(DAOException daoException){
			throw new DAOException("列表全部的垃圾消息.");
		}				
	}
	
	/**
	 * 列表垃圾箱短消息
	 * @param username 发送或接收用户
	 * @throws DAOException
	 */
	public QueryResult findTrashMessages(String username,Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class); 
			// 条件1、 发件人或收件人为username的记录
			detachedCriteria.add(Restrictions.or(Restrictions.eq("sender",username),Restrictions.eq("receiver",username)));
			// 条件2、 被删除掉的发送的消息或接收的消息
			detachedCriteria.add(Restrictions.or(Restrictions.eq("isDelSender",new Integer(1)),Restrictions.eq("isDelReceiver",new Integer(1))));
			// 消息为3时，表示已被删除掉，不可见
			detachedCriteria.add(Restrictions.not(Restrictions.eq("status",new Integer(3))));
			Map orderMap = new HashMap();
			orderMap.put("id","desc");
			return super.findItemsByCriteria(detachedCriteria,orderMap,pagination);
		}
		catch(DAOException daoException){
			throw new DAOException("根据消息状态查找消息失败.");
		}		
	}
	
	
	/**
	 * 设置全部的已发送消息为垃圾
	 * @param sender 发送者
	 */	
	public void trashSendMessage(String sender) throws DAOException {
		try{
			super.executeHsql("update Message set status=3 where sender='"+sender+"'");
		}
		catch(DAOException de){
			throw new DAOException("设置全部已发送短消息为垃圾失败");
		}
	}		
	
}