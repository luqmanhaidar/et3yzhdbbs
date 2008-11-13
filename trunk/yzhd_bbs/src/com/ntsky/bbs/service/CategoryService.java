package com.ntsky.bbs.service;

import java.util.List;

import com.ntsky.bbs.domain.Category;
import com.ntsky.bbs.domain.Favorite;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 主题类别模块业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface CategoryService extends BaseService {

	/**
	 * 创建主题类别
	 * 
	 * @param topicId 主题编号
	 */
	public void createCategory(Category category) throws ServiceException ;
	
	/**
	 * 删除主题类别
	 * 
	 * @param catId 类别编号
	 */
	public void deleteCategory(int catId) throws ServiceException ;
	
	/**
	 * 根据类别编号取得类别对象
	 * @param catId
	 * @throws ServiceException
	 */
	public Category findCategoryByIdAndName(int catId,String name) throws ServiceException;
	public Category getCategory(int catId) throws ServiceException;
	
	public Category getCategoryByName(String name) throws ServiceException;
	
	/**
	 * 修改主题类别
	 * 
	 * @param category 主题类别
	 */
	public void editCategory(Category category) throws ServiceException ;	
	
	/**
	 * 删除多个主题类别
	 * @param ids 主题类别编号数组 
	 * @throws ServiceException
	 */
	//public void deleteMoreCategory(int[] ids) throws ServiceException;

	/**
	 * 根据论坛编号列表类别
	 * 
	 * @param forumId 论坛编号
	 * @return List 类别集合
	 */
	public List getCategories (int forumId) throws ServiceException;
	
	public List getAllCategories () throws ServiceException;
		
}
