package com.ntsky.bbs.dao;

import com.ntsky.bbs.domain.FavoriteTopic;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

public interface FavoriteTopicDAO extends BaseDAO{
	/**
	 * 根据主题编号查找投票信息
	 * @param topicId 主题编号
	 * @return Poll 投票信息
	 */	
	public QueryResult findFavoriteTopic(int userId, final Pagination pagination) throws DAOException;
	
	public void deleteFavoriteTopic(int favTopicId) throws DAOException;
	
	public FavoriteTopic getFavoriteTopicByUserTopic(int userId,int topicId) throws DAOException;
}
