package com.ntsky.bbs.dao;

import java.util.List;

import com.ntsky.bbs.domain.Post;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 帖子回复模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface PostDAO extends BaseDAO{
   
	/**
	 * 根据贴子编号查找贴子
	 * @param postId 贴子编号
	 * @return Post 贴子信息
	 */
	public Post findPost(long postId) throws DAOException;

	/**
	 * 列表全部的帖子
	 * 
	 * @param topicId 主题编号
	 * @param pagination 分页参数
	 * @return QueryResult 帖子集合
	 * @throws DAOException
	 */
	public QueryResult findPosts (final int topicId, final Pagination pagination) throws DAOException;	
	
	/**
	 * 取得贴子总数
	 * @return
	 * @throws DAOException
	 */
	public int countPost() throws DAOException;	
	
	/**
	 * 根据论坛编号取得贴子总数
	 * @param forums 论坛序列
	 * @return
	 * @throws DAOException
	 */
	public int countPost(List forums) throws DAOException;
	
	/**
	 * 根据论坛编号取得今日贴子总数
	 * @param forums 论坛列表
	 * @return
	 * @throws DAOException
	 */
	public int countTodayPost(List forums,String beginTime,String endTime) throws DAOException ;
	
	/**
	 * 根据主题批量删除帖子
	 * 
	 * @param topicId 主题编号
	 * @throws DAOException
	 */
	public void deletePosts(int topicId) throws DAOException ;	
	
	/**
	 * 根据主题查找指定数量的帖子信息
	 * @param topicId 主题编号
	 * @param dataNum 结果数
	 * @return Post 帖子对象
	 * @throws DAOException
	 */
	public List findPosts(int topicId,int dataNum) throws DAOException ;
	
	/**
	 * 移动帖子
	 *
	 * @param topicId 主题编号
	 * @param forumId 论坛编号
	 * @throws DAOException
	 */
	public void movePost(int topicId,int forumId) throws DAOException ;	
}
