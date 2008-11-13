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

import com.ntsky.bbs.domain.Stat;
import com.ntsky.bbs.dao.StatDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 统计信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class StatDAOHibernateImpl extends BaseDAOHibernateImpl implements StatDAO {
	
	/**
	 * 查找全部的统计信息
	 * @param field 检索字段
	 * @param value 检索匹配的值
	 * @param pagination 分页对象
	 * @return QueryResult 日志集合
	 */
	public QueryResult findStats (String field, String value, Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Stat.class); 
			detachedCriteria.add(Restrictions.like(field,"%"+value+"%"));
			return super.findItemsByCriteria(detachedCriteria,null,pagination);
		}
		catch(DAOException daoException){
			throw new DAOException("分页列表统计信息失败.");
		}
	}
	
	/**
	 * 根据日志编号查找的日志数据
	 * @param logId 日志编号
	 * @return Log 日志集合
	 */	
	public Stat findStat(long statId) throws DAOException {
		try{
			return (Stat)super.get(Stat.class,new Long(statId));
		}
		catch(DAOException daoException){
			throw new DAOException("根据统计编号查找统计信息失败.");
		}
	}

	/**
	 * 删除全部的统计信息
	 */
	public void deleteAllStat() throws DAOException {
		try{
			super.executeHsql("delete from Stat");
		}
		catch(DAOException daoException){
			throw new DAOException("删除全部的统计信息发生错误.");
		}
	}

	/**
	 * 删除指定的统计信息
	 * @param id
	 * @throws DAOException
	 */
	public void deleteStat(int id) throws DAOException {
		try{
			super.executeHsql("delete from Stat where id='"+id+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("根据统计编号删除统计信息失败.");
		}
	}
	
}