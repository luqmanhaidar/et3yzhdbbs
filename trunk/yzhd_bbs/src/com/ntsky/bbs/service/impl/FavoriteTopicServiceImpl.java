package com.ntsky.bbs.service.impl;

import org.apache.log4j.Logger;

import com.ntsky.bbs.dao.FavoriteTopicDAO;
import com.ntsky.bbs.domain.FavoriteTopic;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.FavoriteTopicService;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 管理员模块业务处理实现
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class FavoriteTopicServiceImpl implements FavoriteTopicService{
	
	public static Logger logger = Logger.getLogger(FavoriteTopicServiceImpl.class); 
	
	private FavoriteTopicDAO favoriteTopicDAO;
	
	/**
	 * 查找全部的收藏信息
	 * @param name 名称关键字
	 * @param userId 用户编号
	 * @param pagination 分页对象
	 * @return List 收藏集合
	 */
	public QueryResult getFavoriteTopics (int userId, final Pagination pagination) throws ServiceException {
		try{
			return favoriteTopicDAO.findFavoriteTopic(userId,pagination);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}
	
	/**
	 * 创建收藏夹
	 * 
	 * @param favorite 收藏夹对象
	 */
	
	/**
	 * 删除收藏夹
	 * 
	 * @param favId 收藏夹编号
	 */
	public void deleteFavorite(int favTopicId) throws ServiceException {
		try{
			favoriteTopicDAO.deleteFavoriteTopic(favTopicId);
		}
		catch(DAOException daoException){
			throw new DAOException(daoException.getMessage());
		}
	}
	
	/**
	 * 查找某条收藏夹
	 * 
	 * @param favorite 收藏夹对象
	 */
	
	/**
	 * 删除收藏夹
	 * 
	 * @param favId 收藏夹编号
	 */
	public FavoriteTopic getFavoriteTopicByUserTopic(int userId,int topicId) throws ServiceException {
		try{
			return favoriteTopicDAO.getFavoriteTopicByUserTopic(userId, topicId);
		}
		catch(DAOException daoException){
			throw new DAOException(daoException.getMessage());
		}
	}
	
		
	

	public FavoriteTopicDAO getFavoriteTopicDAO() {
		return favoriteTopicDAO;
	}

	public void setFavoriteTopicDAO(FavoriteTopicDAO favoriteTopicDAO) {
		this.favoriteTopicDAO = favoriteTopicDAO;
	}

	public void createFavoriteTopic(FavoriteTopic favoriteTopic) throws ServiceException {
		try{
			favoriteTopicDAO.save(favoriteTopic);
		}
		catch(DAOException daoException){
			throw new ServiceException("创建收藏夹[' "+favoriteTopic.getTopic().getId()+" ']发生错误.");
		}
		
	}


	public void clearUserFavorte(int userId) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

}
