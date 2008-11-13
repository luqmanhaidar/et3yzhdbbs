package com.ntsky.bbs.service;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 论坛模块业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface ForumService extends BaseService{

	/**
	 * 创建论坛
	 * @param forum 论坛数据
	 */
	public void createForum(Forum forum) throws ServiceException;
	
	/**
	 * 修改论坛
	 * @param forum 论坛数据
	 */
	public void editForum(Forum forum) throws ServiceException;
	
	/**
	 * 删除论坛
	 * @param forumId 论坛编号
	 */
	public void deleteForum(int forumId) throws ServiceException;
	
	/**
	 * 取得全部的论坛信息
	 * @return List 论坛信息集合
	 */
	public List getForums() throws ServiceException ;
	
	/**
	 * 根据论坛编号取得论坛某条全部信息
	 * @param forumId 论坛标示
	 * @return Forum 论坛数据
	 */	
	public Forum getForum(int forumId) throws ServiceException;
	
	/**
	 * 合并论坛
	 * <ol>
	 * 	<li>将主题源信息中论坛编号更新成目标论坛的编号</li>
	 *  <li>将Category信息中论坛编号更新成目标论坛的编号</li>
	 * 	<li>删除源论坛数据</li>
	 * </ol>
	 * 
	 * @param sourceForum 源论坛编号
	 * @param toForum　目标论坛编号
	 * @return
	 */
	public void uniteForum(int sourceForum, int toForum) throws ServiceException ;
	
	/**
	 * 根据父类编号取得论坛列表
	 * @param parentId 父类编号
	 * @return 论坛列表
	 * @throws ServiceException
	 */
	public List getForums(int parentId) throws ServiceException;
	
	
	/**
	 * 更新论坛版块序号
	 * 
	 * @param oldBranchId 旧分支编号
	 * @param newbranchId 新分支编号
	 * @throws DAOException
	 */
	public boolean updateForumBranch(int oldBranchId,int newbranchId) throws ServiceException;
	
	/**
	 * for Test
	 * @return
	 */
	public void doIsAdmin(int forumId,int isAdmin) throws ServiceException;
	
	public void doIsMasters(int forumId,int isMasters) throws ServiceException;
	
	public void doIsTop(int forumId,int isTop) throws ServiceException;
	//public int getMaxBranchId();
	public List findForumsIsTop() throws DAOException ;
	
	/**
	 * 根据热门论坛
	 * @return 论坛列表
	 * @throws ServiceException
	 */
	public List getHotForums() throws ServiceException;
}
