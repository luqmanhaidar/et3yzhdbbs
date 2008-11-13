package com.ntsky.bbs.service.impl;

import org.springframework.dao.DataAccessException;

import com.ntsky.bbs.dao.StatDAO;
import com.ntsky.bbs.domain.Stat;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.StatService;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 统计业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class StatServiceImpl implements StatService{

	private StatDAO statDAO;
	
	public void setStatDAO(StatDAO statDAO){
		this.statDAO = statDAO;
	}
	
	/**
	 * 查找全部的统计信息
	 * @param field 检索字段
	 * @param value 检索匹配的值
	 * @param pagination 分页对象
	 * @return QueryResult 日志集合
	 */
	public QueryResult getStats(String field, String value, Pagination pagination) throws ServiceException {
		try{
			return statDAO.findStats(field,value,pagination);
		}
		catch(DAOException de){
			throw new DAOException(de.getMessage());
		}
	}

	
	/**
	 * 根据统计编号查找的统计数据
	 * @param statId 统计编号
	 * @return Stat 统计对象
	 */	
	public Stat getStat(long statId) throws ServiceException {
		try{
			return statDAO.findStat(statId);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}		
	}

	/**
	 * 创建统计
	 * @param stat 公告对象
	 */
	public void createStat(Stat stat) throws ServiceException  {
		try{
			statDAO.save(stat);
		}
		catch(DAOException de){
			throw new ServiceException("创建统计信息发生错误");
		}	
	}

	/**
	 * 删除日志
	 * @param logId 日志编号
	 */
	public void deleteStat(long statId) throws ServiceException {
		statDAO.delete(statDAO.load(Stat.class,new Long(statId)));
	}

	/**
	 * 删除多个统计信息
	 * @param statIds 统计信息列表
	 */
	public void deleteStats(String[] statIds) throws ServiceException {
		for (int i = 0; i < statIds.length; i++) {
			statDAO.delete(statDAO.load(Stat.class,new Long(statIds[i])));
		}
	}

	/**
	 * 删除全部的统计
	 */
	public void deleteAllStat() throws ServiceException {
		statDAO.deleteAllStat();
	}
}
