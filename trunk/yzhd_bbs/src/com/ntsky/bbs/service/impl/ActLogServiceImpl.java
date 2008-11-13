package com.ntsky.bbs.service.impl;

import com.ntsky.bbs.dao.ActLogDAO;
import com.ntsky.bbs.domain.ActLog;
import com.ntsky.bbs.service.ActLogService;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 操作日志业务处理实现
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class ActLogServiceImpl implements ActLogService{

	private ActLogDAO actLogDAO;
	
	public void setActLogDAO(ActLogDAO actLogDAO){
		this.actLogDAO = actLogDAO;
	}
	
	/**
	 * 创建操作日志
	 * @param actLog 操作日志对象
	 */
	public void createActLog(ActLog actLog) throws ServiceException {
		try{
			actLogDAO.save(actLog);
		}
		catch(DAOException de){
			throw new ServiceException("创建操作日志发生错误");
		}
	}
		
	/**
	 * 删除操作日志
	 * @param actLogId 操作日志编号
	 */
	public void deleteActLog(long actLogId) throws ServiceException {
		try{
			actLogDAO.deleteActLog(actLogId);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
	/**
	 * 查看某条操作日志详细信息
	 * @param actLogId 操作日志编号
	 * @return ActLog 操作日志对象
	 */
	public ActLog getActLog(long actLogId) throws ServiceException {
		try{
			return actLogDAO.findActLog(actLogId);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
	/**
	 * 分页列表操作日志信息
	 * @param place 对象
	 * @param pagination 分页对象
	 * @return QueryResult 操作日志列表
	 */
	public QueryResult getActLogs(String place, Pagination pagination) throws ServiceException {
		try{
			return actLogDAO.findActLogs(place,pagination);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}

	/**
	 * 删除多个操作日志
	 * @param actLogId 操作日志编号
	 */
	public void deleteActLogs(String[] actLogIds) throws ServiceException {
		try{
			long actLogId = 0;
			for (int i = 0; i < actLogIds.length; i++) {
				actLogId = new Long(actLogIds[i]);
				actLogDAO.deleteActLog(actLogId);
			}
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}

	/**
	 * 删除全部操作日志
	 * @param actLogId 操作日志编号
	 */
	public void deleteAllActLog() throws ServiceException {
		try{
			actLogDAO.deleteAllActLog();
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}

}
