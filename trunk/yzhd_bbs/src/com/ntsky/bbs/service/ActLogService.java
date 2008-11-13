package com.ntsky.bbs.service;

import java.util.List;

import com.ntsky.bbs.domain.ActLog;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.exception.ServiceException;

public interface ActLogService extends BaseService{

	/**
	 * 创建操作日志
	 * @param actLog 操作日志对象
	 */
	public void createActLog(ActLog actLog) throws ServiceException ;
		
	/**
	 * 删除操作日志
	 * @param actLogId 操作日志编号
	 */
	public void deleteActLog(long actLogId) throws ServiceException ;
	
	/**
	 * 删除多个操作日志
	 * @param actLogId 操作日志编号
	 */
	public void deleteActLogs(String[] actLogIds) throws ServiceException ;

	/**
	 * 删除全部操作日志
	 * @param actLogId 操作日志编号
	 */
	public void deleteAllActLog() throws ServiceException ;	
	
	/**
	 * 取得某条操作日志详细信息
	 * @param actLogId 操作日志编号
	 * @return ActLog 操作日志对象
	 */
	public ActLog getActLog(long actLogId) throws ServiceException ;
	
	/**
	 * 分页列表操作操作日志信息
	 * @param actName 操作名称
	 * @param pagination 分页对象
	 * @return QueryResult 操作日志列表
	 */
	public QueryResult getActLogs(String actName, Pagination pagination) throws ServiceException ;
	
}
