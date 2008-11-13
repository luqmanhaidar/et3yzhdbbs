package com.ntsky.bbs.service;

import java.util.List;

import com.ntsky.bbs.domain.Post;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.PostException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 贴子模块业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface PostService extends BaseService{

	/**
	 * 创建贴子信息
	 * 
	 * @param Post 贴子对象
	 */
	public Post createPost(Post post) throws PostException,ServiceException ;
	
	/**
	 * 删除贴子
	 * 
	 * @param postId 贴子编号 
	 */
	public void deletePost(int postId) throws ServiceException ;
	
	/**
	 * 根据主题批量删除帖子
	 * 
	 * @param topicId 主题编号
	 * @throws DAOException
	 */
	public void deletePosts(int topicId) throws ServiceException;	
	
	/**
	 * 修改贴子信息
	 * 
	 * @param post 贴子信息
	 */
	public void editPost(Post post) throws ServiceException ;

	/**
	 * 根据编号取得贴子
	 * @param postId 贴子编号
	 * @return Post 贴子信息
	 */
	public Post getPost(long postId) throws ServiceException ;	
	
	/**
	 * 列表全部的帖子
	 * 
	 * @param topicId 主题编号
	 * @param pagination 分页参数
	 * @return QueryResult 帖子集合
	 * @throws DAOException
	 */
	public QueryResult findPosts (final int topicId, final Pagination pagination) throws ServiceException ;	

	/**
	 * 取得贴子总数
	 * @return
	 * @throws DAOException
	 */
	public int countPost() throws ServiceException;
	
	public List findPosts(int topicId,int dataNum);
}
