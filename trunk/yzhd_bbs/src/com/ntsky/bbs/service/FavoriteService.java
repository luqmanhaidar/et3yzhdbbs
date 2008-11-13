package com.ntsky.bbs.service;

import java.util.List;

import com.ntsky.bbs.domain.Favorite;
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
public interface FavoriteService extends BaseService {

	/**
	 * 创建收藏夹
	 * 
	 * @param favorite 收藏夹对象
	 */
	public void createFavorite(Favorite favorite) throws ServiceException ;
	
	/**
	 * 删除收藏夹
	 * 
	 * @param favId 收藏夹编号
	 */
	public void deleteFavorite(int favId) throws ServiceException ;
	
	/**
	 * 删除多个收藏内容
	 * @param ids 收藏内容编号数组 
	 * @throws ServiceException
	 */
	public void deleteMoreFavorite(int[] ids) throws ServiceException;

	/**
	 * 查找全部的收藏信息
	 * @param name 名称关键字
	 * @param userId 用户编号
	 * @param pagination 分页对象
	 * @return List 收藏集合
	 */
	public QueryResult getFavorites (final String name, int userId, final Pagination pagination) throws ServiceException;	
	
	/**
	 * 清空用户收藏夹
	 * @param userId 用户编号
	 * @throws DAOException
	 */
	public void clearUserFavorte(int userId) throws ServiceException;
	
}
