package com.ntsky.bbs.dao.hibernate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Post;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.dao.PostDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 论坛信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/26 06:36:41 $
 */
public class PostDAOHibernateImpl extends BaseDAOHibernateImpl implements PostDAO {

	/**
	 * 根据贴子编号查找贴子
	 * @param postId 贴子编号
	 * @return Post 贴子信息
	 */
	public Post findPost(long postId) throws DAOException {
		try{
			return (Post)super.get(Post.class,new Long(postId));
		}
		catch(DAOException daoException){
			throw new DAOException("取得贴子信息失败");
		}
	}	
	
	/**
	 * 列表全部的帖子
	 * 
	 * @param topicId 主题编号
	 * @param pagination 分页参数
	 * @return QueryResult 帖子集合
	 * @throws DAOException
	 */
	public QueryResult findPosts (final int topicId, final Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Post.class);
			detachedCriteria.add(Restrictions.eq("topicId",topicId));
			Map orderMap = new HashMap();
			orderMap.put("id","asc");
			return super.findItemsByCriteria(detachedCriteria,orderMap,pagination);
		}
		catch(DAOException daoException){
			throw new DAOException("列表贴子信息发生错误.");
		}
	}
	
	/**
	 * 取得贴子总数
	 * @return
	 * @throws DAOException
	 */
	public int countPost() throws DAOException {
		try{
			return (Integer)super.findByAggregate("select count(p.id) from Post as p, Topic as t where p.topicId=t.id and t.isDelete=0");
		}
 		catch(DAOException daoException){
			throw new DAOException("统计贴子总数失败.");
		}
	}	

	/**
	 * 根据论坛编号取得贴子总数
	 * @param forums 
	 * @return
	 * @throws DAOException
	 */
	public int countPost(List forums) throws DAOException {
		Forum forum = null;
		String forumEnum = "";
		try{
			for (int i = 0; i < forums.size(); i++) {
				forum = (Forum)forums.get(i);
				if(forums.size()-1==i){
					forumEnum = forumEnum + "p.forumId='"+forum.getId()+"'";
				}
				else{
					forumEnum = forumEnum + "p.forumId='"+forum.getId()+"' or ";
				}
			}
			return (Integer)super.findByAggregate("select count(p.id) from Post as p , Topic as t where p.topicId=t.id and t.isDelete=0 and ("+forumEnum+")");
		}
 		catch(DAOException daoException){
			throw new DAOException("统计贴子总数失败.");
		}
	}

	/**
	 * 根据论坛编号取得今日贴子总数
	 * @param forums 论坛列表
	 * @return
	 * @throws DAOException
	 */
	public int countTodayPost(List forums,String beginTime,String endTime) throws DAOException {
		Forum forum = null;
		String forumEnum = "";
		try{
			for (int i = 0; i < forums.size(); i++) {
				forum = (Forum)forums.get(i);
				if(forums.size()-1==i){
					forumEnum = forumEnum + "p.forumId='"+forum.getId()+"'";
				}
				else{
					forumEnum = forumEnum + "p.forumId='"+forum.getId()+"' or ";
				}
			}
			if(logger.isDebugEnabled()){
				logger.debug("select count(p.id) from Post as p where ("+forumEnum+") and p.dateCreated between '"+beginTime+"' and '"+endTime+"'");
			}
			return (Integer)super.findByAggregate("select count(p.id) from Post as p , Topic as t where p.topicId=t.id and t.isDelete=0 and ("+forumEnum+") and p.dateCreated between '"+beginTime+"' and '"+endTime+"'");
		}
 		catch(DAOException daoException){
			throw new DAOException("统计贴子总数失败.");
		}
	}
	
	/**
	 * 根据主题查找指定数量的帖子信息
	 * @param topicId 主题编号
	 * @param dataNum 结果数
	 * @return Post 帖子对象
	 * @throws DAOException
	 */
	public List findPosts(int topicId,int dataNum) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Post.class); 
			detachedCriteria.add(Restrictions.eq("topicId",topicId));
			detachedCriteria.addOrder(Order.asc("id"));
			return find(detachedCriteria,dataNum);
		}
		catch(DAOException daoException){
			throw new DAOException("根据主题查找指定数量的帖子失败...");
		}
	}
	
	/**
	 * 根据主题批量删除帖子
	 * 
	 * @param topicId 主题编号
	 * @throws DAOException
	 */
	public void deletePosts(int topicId) throws DAOException {
		try{
			super.executeHsql("delete Post where topicId='"+topicId+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("根据主题删除帖子发生错误");
		}
	}
	
	/**
	 * 移动帖子
	 *
	 * @param topicId 主题编号
	 * @param forumId 论坛编号
	 * @throws DAOException
	 */
	public void movePost(int topicId,int forumId) throws DAOException {
		try{
			super.executeHsql("update Post set forumId='"+forumId+"' where topicId="+topicId);
		}
		catch(DAOException daoException){
			throw new DAOException("移动帖子到指定论坛失败");
		}
	}	
	
}