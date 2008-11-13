package com.ntsky.bbs.dao;

import com.ntsky.bbs.domain.ActLog;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 操作日志模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface ActLogDAO extends BaseDAO{

	/**
	 * 查找全部的操作日志信息
	 * @param name 操作名称
	 * @param pagination 分页对象
	 * @return QueryResult 日志集合
	 */
	public QueryResult findActLogs (final String page, final Pagination pagination) throws DAOException ;
	
	/**
	 * 根据日志编号查找的日志数据
	 * @param logId 日志编号
	 * @return Actlog 操作日志
	 */	
	public ActLog findActLog(long actLogId) throws DAOException ;

	/**
	 * 删除操作日志
	 * @param actLogId 操作日志编号 
	 */
	public void deleteActLog(long actLogId) throws DAOException ;
	
	/**
	 * 删除全部的日志
	 * @throws DAOException
	 */
	public void deleteAllActLog() throws DAOException;
	
}
