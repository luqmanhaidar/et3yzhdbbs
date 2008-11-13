package com.ntsky.bbs.service.impl;

import java.util.Date;
import java.util.List;

import com.ntsky.framework.util.DateUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Post;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.dao.PostDAO;
import com.ntsky.bbs.dao.UserDAO;
import com.ntsky.bbs.dao.TopicDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.PostException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.PostService;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.memory.BadwordSingleton;
import com.ntsky.bbs.util.memory.ForumSingleton;
/**
 * 贴子模块业务处理实现
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class PostServiceImpl extends BaseServiceImpl implements PostService {

	private PostDAO postDAO;
	public void setPostDAO(PostDAO postDAO){
		this.postDAO = postDAO;
	}
	
	private TopicDAO topicDAO;
	public void setTopicDAO(TopicDAO topicDAO){
		this.topicDAO = topicDAO;
	}

	/**
	 * 创建贴子信息
	 * 
	 * <ol>
	 * 	<li>添加贴子</li>
	 *  <li>更新主题</li>
	 *  <li>更新用户中主题和贴子总数</li>
	 *  <li>重置内存数据</li>
	 * </ol>
	 * 
	 * @param Post 贴子对象
	 */
	public Post createPost(Post post) throws PostException,ServiceException {
		try{
			Topic tempTopic = topicDAO.findTopic(post.getTopicId());
			if(tempTopic.getStatus()==2){
				throw new PostException("帖子["+tempTopic.getTitle()+"]被锁定,您不能进行回复。");
			}
			
			Date date = new Date();
			// 添加贴子
			String dateCreated = DateUtil.getDate();
			post.setDateCreated(date);
			post.setTitle(BadwordSingleton.getInstance().replaceString(post.getTitle()));
			post.setContent(BadwordSingleton.getInstance().replaceString(post.getContent()));
			postDAO.save(post);
			
			// 更新主题
			tempTopic.setReplies(tempTopic.getReplies()+1);
			tempTopic.setLastPostUser(post.getUsername());
			tempTopic.setLastPostTime(date);
			topicDAO.update(tempTopic);
			
			// 如果是guest用户就不需要如下操作
			if(!(Symbols.GUEST.equals(post.getUsername()))){
				// 发表主题增加金钱数
				userDAO.updateMoney(post.getUsername(),SystemConfig.getInstance().getIntPropertyValue(Symbols.MONEY,Symbols.MONEY_ADD_POST));			
				
				// 更新用户信息(用户发表贴子数+1)
				userDAO.updateAboutPost(post.getUsername(),Symbols.POST,Symbols.INCREASE);
				
				// 重置内存数据(增加贴子信息)
				ForumSingleton.getInstance().setLastPostTopic(tempTopic.getForumId(),tempTopic);
				ForumSingleton.getInstance().setPostCount(post.getForumId(),Symbols.INCREASE);
				ForumSingleton.getInstance().setTodayPostCount(post.getForumId(),Symbols.INCREASE);
			}
			return post;
		}
		catch(PostException pe){
			throw new PostException(pe.getMessage());
		}
		catch(DAOException de){
			throw new ServiceException("发表贴子['" +post.getTitle()+ "']发生错误.");
		}
		catch(Exception ex){
			logger.error("发表贴子['" +post.getTitle()+ "']发生错误.");
			throw new ServiceException("发表贴子['" +post.getTitle()+ "']发生错误.");
		}
	}
	
	/**
	 * 删除贴子
	 * 
	 * @param postId 贴子编号 
	 */
	public void deletePost(int postId) throws ServiceException {
		try{
			Post post = postDAO.findPost(postId);
			
			// 更新主题
			Topic tempTopic = topicDAO.findTopic(post.getTopicId());
			tempTopic.setReplies(tempTopic.getReplies()-1);
			topicDAO.update(tempTopic);
			
			// 删除帖子
			postDAO.delete(post);
			
			// 如果是guest用户就不需要如下操作
			if(!(Symbols.GUEST.equals(post.getUsername()))){
				// 删除帖子减少金钱数
				userDAO.updateMoney(post.getUsername(),SystemConfig.getInstance().getIntPropertyValue(Symbols.MONEY,Symbols.MONEY_DELETE_POST));				
				
				// 用户帖子数-1
				userDAO.updateAboutPost(post.getUsername(),Symbols.POST,Symbols.DECREASE);
				
				// 更新内存数据
				ForumSingleton.getInstance().setPostCount(post.getForumId(),Symbols.DECREASE);
				ForumSingleton.getInstance().setTodayPostCount(post.getForumId(),Symbols.DECREASE);
			}
		}
		catch(DAOException de){
			throw new ServiceException("删除贴子发生错误.");
		}
	}
	
	/**
	 * 修改帖子信息
	 * 
	 * @param post 帖子信息
	 */
	public void editPost(Post post) throws ServiceException {
		try{
			Post tempPost = postDAO.findPost(post.getId());
			// 修改人是否为原帖发表者
			tempPost.setTitle(BadwordSingleton.getInstance().replaceString(post.getTitle()));
			tempPost.setContent(BadwordSingleton.getInstance().replaceString(post.getContent()));
			tempPost.setEditCount(tempPost.getEditCount()+1);
			tempPost.setDateEdited(new Date());
			postDAO.update(tempPost);
		}
		catch(DAOException de){
			throw new ServiceException("更新贴子失败.");
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
	public QueryResult findPosts (final int topicId, final Pagination pagination) throws ServiceException {
		try{
			return postDAO.findPosts(topicId,pagination);
		}
		catch(DAOException de){
			throw new ServiceException("取得贴子列表失败.");
		}
	} 

	/**
	 * 根据主题批量删除帖子
	 * 
	 * @param topicId 主题编号
	 * @throws DAOException
	 */
	public void deletePosts(int topicId) throws ServiceException {
		try{
			postDAO.deletePosts(topicId);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
	/**
	 * 根据编号取得贴子
	 * 
	 * @param postId 贴子编号
	 * @return Post 贴子信息
	 */
	public Post getPost(long postId) throws ServiceException {
		try{
			return postDAO.findPost(postId);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
	/**
	 * 取得贴子总数
	 * @return
	 * @throws DAOException
	 */
	public int countPost() throws ServiceException {
		try{
			return postDAO.countPost();
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}

	public List findPosts(int topicId, int dataNum) {
		return postDAO.findPosts(topicId, dataNum);
	}
}
