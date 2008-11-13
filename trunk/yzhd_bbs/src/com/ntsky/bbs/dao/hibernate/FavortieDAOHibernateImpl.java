package com.ntsky.bbs.dao.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ntsky.bbs.domain.Favorite;
import com.ntsky.bbs.domain.Help;
import com.ntsky.bbs.dao.FavoriteDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ObjectExistException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 帮助信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/23 15:43:50 $
 */
public class FavortieDAOHibernateImpl extends BaseDAOHibernateImpl implements FavoriteDAO {
	/**
	 * 查找全部的收藏信息
	 * @param name 名称关键字
	 * @param userId 用户编号
	 * @param pagination 分页对象
	 * @return List 收藏集合
	 */
	public QueryResult findFavorites (final String name, int userId, final Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Favorite.class); 
			//detachedCriteria.add(Restrictions.like("name","%"+name+"%"));
			detachedCriteria.add(Restrictions.eq("userId",userId));
			return super.findItemsByCriteria(detachedCriteria,null,pagination);
		}
		catch(DAOException de){
			throw new DAOException("列表全部收藏夹发生错误");
		}
	}
	
	/**
	 * 根据收藏夹编号查找的帮助数据
	 * @param favId 标题编号
	 * @return Favorite 收藏夹对象
	 */	
	public Favorite findFavorite(int favId) throws DAOException {
		try{
			return  (Favorite)super.get(Favorite.class,new Long(favId));
    	}
		catch(ObjectExistException objectExistException){
			throw new DAOException("取得['"+favId+"']对应的收藏信息发生错误，该信息可能被删除.");
		}
    	catch(Exception exception){
    		throw new DAOException("取得['"+favId+"']对应的帮助信息发生错误");
    	}
	}
	
	/**
	 * 删除指定ID的收藏夹信息
	 * @param favId 收藏夹ID
	 * @throws DAOException
	 */
	public void deleteFavorite(int favId) throws DAOException {
		try{
			super.executeHsql("delete from Favorite where id='"+favId+"'");
		}
		catch(DAOException de){
			throw new DAOException("删除指定的收藏夹信息发生错误");
		}
	}
	
	/**
	 * 清空用户收藏夹
	 * @param userId 用户编号
	 * @throws DAOException
	 */
	public void clearUserFavorte(int userId) throws DAOException{
		try{
			super.executeHsql("delete from Favorite where userId='"+userId+"'");
		}
		catch(DAOException de){
			throw new DAOException("清空用户[userId]的收藏夹发生错误");
		}
	}
	
}