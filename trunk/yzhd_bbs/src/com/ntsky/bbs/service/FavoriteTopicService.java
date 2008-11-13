package com.ntsky.bbs.service;

import com.ntsky.bbs.domain.FavoriteTopic;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 个人收藏夹模块业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface FavoriteTopicService extends BaseService {

	/**
	 * 创建收藏夹
	 * 
	 * @param favorite 收藏夹对象
	 */
	public void createFavoriteTopic(FavoriteTopic favoriteTopic) throws ServiceException ;
	
	/**
	 * 删除收藏夹
	 * 
	 * @param favId 收藏夹编号
	 */
	public void deleteFavorite(int favTopicId) throws ServiceException ;
	
	/**
	 * 删除多个收藏内容
	 * @param ids 收藏内容编号数组 
	 * @throws ServiceException
	 */
	public QueryResult getFavoriteTopics (int userId, final Pagination pagination) throws ServiceException;	
	
	/**
	 * 清空用户收藏夹
	 * @param userId 用户编号
	 * @throws DAOException
	 */
	public void clearUserFavorte(int userId) throws ServiceException;
	public FavoriteTopic getFavoriteTopicByUserTopic(int userId,int topicId) throws ServiceException;
	
}
