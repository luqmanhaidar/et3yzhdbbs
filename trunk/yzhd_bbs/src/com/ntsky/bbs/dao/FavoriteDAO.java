package com.ntsky.bbs.dao;

import java.util.List;

import com.ntsky.bbs.domain.Favorite;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 收藏夹模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface FavoriteDAO extends BaseDAO{

	/**
	 * 查找全部的收藏信息
	 * @param name 名称关键字
	 * @param userId 用户编号
	 * @param pagination 分页对象
	 * @return List 收藏集合
	 */
	public QueryResult findFavorites (final String name, int userId, final Pagination pagination) throws DAOException ;
	
	/**
	 * 根据收藏夹编号查找的帮助数据
	 * @param favId 标题编号
	 * @return Favorite 收藏夹对象
	 */	
	public Favorite findFavorite(int favId) throws DAOException ;
		
	/**
	 * 删除指定ID的收藏夹信息
	 * @param favId 收藏夹ID
	 * @throws DAOException
	 */
	public void deleteFavorite(int favId) throws DAOException ;
	
	/**
	 * 清空用户收藏夹
	 * @param userId 用户编号
	 * @throws DAOException
	 */
	public void clearUserFavorte(int userId) throws DAOException;
}
