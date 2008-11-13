package com.ntsky.bbs.dao;

import java.util.List;

import com.ntsky.bbs.domain.Category;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 主题类别模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface CategoryDAO extends BaseDAO{

	/**
	 * 根据论坛编号查找主题类别
	 * 
	 * @param forumId 论坛编号
	 * @return List 主题类别列表
	 */
	public List findCategories ( int forumId ) throws DAOException ;
	
	public List findAllCategories () throws DAOException;
	
	/**
	 * 根据类别编号查找类别数据
	 * @param catId 类别编号
	 * @return Category 类别对象
	 */	
	public Category findCategory( int catId ) throws DAOException ;
	
	/**
	 * 根据文章类型名查找文章类型信息
	 * 
	 * @param name 名称
	 * @return Category 文章类型对象(查找的文章类型不存在时,返回null)
	 */
	public Category findCategoryByName(String name) throws DAOException;
		
	/**
	 * 删除类别
	 * @param catId 类别ID
	 * @throws DAOException
	 */
	public void deleteCategory(int catId) throws DAOException ;
	
	/**
	 * 更改类别所属论坛
	 * 
	 * @param oldForumId 旧的论坛编号
	 * @param newForumId 新的论坛编号
	 * @throws DAOException
	 */
	public void updateCategoryForum( int oldForumId, int newForumId ) throws DAOException ;
	public Category findCategoryByIdAndName( int catId,String name ) throws DAOException;
	
}
