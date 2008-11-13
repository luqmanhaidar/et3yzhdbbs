package com.ntsky.bbs.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ntsky.bbs.domain.Category;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.dao.TopicDAO;
import com.ntsky.bbs.domain.Favorite;
import com.ntsky.bbs.dao.CategoryDAO;
import com.ntsky.bbs.service.CategoryService;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 主题模块业务处理实现
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class CategoryServiceImpl implements CategoryService{
	
	private CategoryDAO categoryDAO;
	public void setCategoryDAO(CategoryDAO categoryDAO){
		this.categoryDAO = categoryDAO;
	}
	
	private TopicDAO topicDAO;
	public void setTopicDAO(TopicDAO topicDAO){
		this.topicDAO = topicDAO;
	}
	
	/**
	 * 根据论坛编号列表类别
	 * 
	 * @param forumId 论坛编号
	 * @return List 类别集合
	 */
	public List getCategories (int forumId) throws ServiceException {
		try{
			return categoryDAO.findCategories(forumId);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}
	
	public List getAllCategories () throws ServiceException {
		try{
			return categoryDAO.findAllCategories();
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}
	
	/**
	 * 根据类别编号取得类别对象
	 * @param catId
	 * @throws ServiceException
	 */
	public Category getCategory(int catId) throws ServiceException{
		try{
			return categoryDAO.findCategory(catId);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}	
	
	/**
	 * 根据类别名称取得类别对象
	 * @param catId
	 * @throws ServiceException
	 */
	public Category getCategoryByName(String name) throws ServiceException{
		try{
			return categoryDAO.findCategoryByName(name);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}	
	
	/**
	 * 创建主题类别
	 * 
	 * @param topicId 主题编号
	 */
	public void createCategory(Category category) throws ServiceException {
		try{
			categoryDAO.save(category);
		}
		catch(DAOException daoException){
			throw new ServiceException("创建主题类别[' "+category.getName()+" ']发生错误.");
		}
	}
	
	/**
	 * 删除主题类别
	 * 
	 * @param catId 类别编号
	 */
	public void deleteCategory(int catId) throws ServiceException {
		try{
			categoryDAO.deleteCategory(catId);
		}
		catch(DAOException daoException){
			throw new DAOException(daoException.getMessage());
		}
	}
	
	/**
	 * 修改主题类别
	 * 
	 * @param category 主题类别
	 */
	public void editCategory(Category category) throws ServiceException {
		try{
			Category tempCategory = categoryDAO.findCategory(category.getId().intValue());
			tempCategory.setName(category.getName());
			tempCategory.setDisplayOrder(category.getDisplayOrder());
			categoryDAO.update(tempCategory);
		}
		catch(DAOException daoException){
			throw new DAOException(daoException.getMessage());
		}
	}
	
	/**
	 * 删除多个收藏内容
	 * @param ids 收藏内容编号数组 
	 * @throws ServiceException
	 */
	public void deleteMoreFavorite(int[] ids) throws ServiceException{
		try{
			for (int i = 0; i < ids.length; i++) {
				
			}
		}
		catch(DAOException daoException){
			throw new DAOException("删除多个收藏内容发生发生错误");
		}
	}
	
	public Category findCategoryByIdAndName(int catId,String name) throws ServiceException{
		try{

			return categoryDAO.findCategoryByIdAndName(catId, name);
		}
		catch(DAOException daoException){
			throw new DAOException(daoException.getMessage());
		}
	}
	
}
