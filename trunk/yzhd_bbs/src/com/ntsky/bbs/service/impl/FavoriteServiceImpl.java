package com.ntsky.bbs.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;

import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.dao.TopicDAO;
import com.ntsky.bbs.domain.Favorite;
import com.ntsky.bbs.dao.FavoriteDAO;
import com.ntsky.bbs.service.FavoriteService;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 管理员模块业务处理实现
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class FavoriteServiceImpl implements FavoriteService{
	
	public static Logger logger = Logger.getLogger(FavoriteServiceImpl.class); 
	
	private FavoriteDAO favoriteDAO;
	public void setFavoriteDAO(FavoriteDAO favoriteDAO){
		this.favoriteDAO = favoriteDAO;
	}
	
	private TopicDAO topicDAO;
	public void setTopicDAO(TopicDAO topicDAO){
		this.topicDAO = topicDAO;
	}
	
	/**
	 * 查找全部的收藏信息
	 * @param name 名称关键字
	 * @param userId 用户编号
	 * @param pagination 分页对象
	 * @return List 收藏集合
	 */
	public QueryResult getFavorites (final String name, int userId, final Pagination pagination) throws ServiceException {
		try{
			return favoriteDAO.findFavorites(name,userId,pagination);
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
	public void createFavorite(Favorite favorite) throws ServiceException {
		Topic topic = null;
		try{
			favorite.setDateCreated(new Date());
			favoriteDAO.save(favorite);
		}
		catch(DAOException daoException){
			throw new ServiceException("创建收藏夹[' "+topic.getTitle()+" ']发生错误.");
		}
	}
	
	/**
	 * 删除收藏夹
	 * 
	 * @param favId 收藏夹编号
	 */
	public void deleteFavorite(int favId) throws ServiceException {
		try{
			favoriteDAO.deleteFavorite(favId);
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
				favoriteDAO.deleteFavorite(ids[i]);
			}
		}
		catch(DAOException daoException){
			throw new DAOException("删除多个收藏内容发生发生错误");
		}
	}
	
	/**
	 * 清空用户收藏夹
	 * @param userId 用户编号
	 * @throws DAOException
	 */
	public void clearUserFavorte(int userId) throws ServiceException{
		try{
			favoriteDAO.clearUserFavorte(userId);
		}
		catch(DAOException de){
			throw new DAOException(de.getMessage());
		}
	}
}
