package com.ntsky.bbs.dao;

import java.util.List;

import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 论坛模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface ForumDAO extends BaseDAO{

	/**
	 * 查找全部的论坛信息
	 * @return List 帮助集合
	 */
	public List findForums () throws DAOException ;
	
	/**
	 * 根据父类编号取得论坛列表
	 * @param parentId 父类编号
	 * @return 论坛版块列表
	 * @throws ServiceException
	 */
	public List findForums(int parentId) throws DAOException;	
	
	/**
	 * 根据论坛编号查找论坛数据
	 * @param forumId 论坛标示
	 * @return Forum 论坛数据
	 */	
	public Forum findForum(int forumId) throws DAOException ;
		
	/**
	 * 取得最大的分支编号
	 * @return 最大分支编号
	 */
	public int findMaxBranchId() throws DAOException ;
	
	/**
	 * 取得子层中最大的分支编号
	 * 
	 * @param parentId 父节点编号
	 */
	public int findChildMaxOrder(int parentId) throws DAOException;
	
	/**
	 * 批量更新显示次序
	 * @param branchId 分支编号
	 * @param displayOrder 显示次序
	 * @throws DAOException 数据处理异常
	 */
	public void batchUpdateDisplayOrder(int branchId,int displayOrder) throws DAOException;

	/**
	 * 更新论坛版块序号
	 * 
	 * @param oldBranchId 旧分支编号
	 * @param newbranchId 新分支编号
	 * @throws DAOException
	 */
	public void updateForumBranch(int oldBranchId,int newbranchId) throws DAOException;
	
	/**
	 * 查找分支是否存在
	 * @param branchId 分支编号
	 * @throws DAOException
	 */
	public boolean isExistBranch(int branchId) throws DAOException;

	
	/**
	 * 根据父类集合取得子论坛列表
	 * @return 子论坛集合
	 * @throws DAOException
	 */
	public List findForumsByParentEnum(String parentEnum) throws DAOException;
	
	/**
	 * 更新是否首页显示
	 * @throws DAOException
	 */
	public void doIsTop(int forumId,int isTop) throws DAOException ;
	
	/**
	 * 更新是否版主进入
	 * @throws DAOException
	 */
	public void doIsMasters(int forumId,int isMasters) throws DAOException ;
	
	
	/**
	 * 更新是否管理员发帖
	 * @throws DAOException
	 */
	public void doIsAdmin(int forumId,int isAdmin) throws DAOException ;
	
	
	/**
	 * 取得放在首页的论坛列表
	 * @return 论坛列表
	 * @throws DAOException
	 */
	public List findForumsIsTop() throws DAOException;
	public List getHotForums() throws DAOException;
	
	
}
