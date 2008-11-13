package com.ntsky.bbs.dao.hibernate;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import org.hibernate.cache.EhCacheProvider;

import com.ntsky.bbs.domain.ActLog;
import com.ntsky.bbs.dao.ActLogDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 日志信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class ActLogDAOHibernateImpl extends BaseDAOHibernateImpl implements ActLogDAO {
	
	/**
	 * 查找全部的操作日志信息
	 * @param name 操作名称关键字
	 * @param pagination 分页对象
	 * @return List 操作日志集合
	 */
	public QueryResult findActLogs (final String name, final Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ActLog.class); 
			detachedCriteria.add(Restrictions.like("name","%"+name+"%"));
			return super.findItemsByCriteria(detachedCriteria,null,pagination);
		}
		catch(DAOException daoException){
			throw new DAOException("分页列表操作日志发生错误.");
		}
	}
	
	/**
	 * 根据日志编号查找的日志数据
	 * @param logId 日志编号
	 * @return Log 日志集合
	 */	
	public ActLog findActLog(long logId) throws DAOException {
		try{
			return (ActLog)super.load(ActLog.class,new Long(logId));
		}
		catch(DAOException daoException){
			throw new DAOException("根据操作日志编号查找操作日志发生错误.");
		}
	}
	
	/**
	 * 删除操作日志
	 * @param actLogId 操作日志编号 
	 */
	public void deleteActLog(long actLogId) throws DAOException {
		try{
			super.executeHsql("delete from ActLog where id="+actLogId+"");
		}
		catch(DAOException daoException){
			throw new DAOException("根据操作日志编号删除操作日志发生错误.");
		}
	}	

	/**
	 * 删除全部的日志
	 * @throws DAOException
	 */
	public void deleteAllActLog() throws DAOException{
		try{
			super.executeHsql("delete from ActLog");
		}
		catch(DAOException daoException){
			throw new DAOException("清空操作日志发生错误.");
		}		
	}
}