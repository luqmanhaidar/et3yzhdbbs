package com.ntsky.bbs.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ntsky.bbs.domain.Stat;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 公告业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface StatService extends BaseService{

	/**
	 * 查找全部的统计信息
	 * @param field 检索字段
	 * @param value 检索匹配的值
	 * @param pagination 分页对象
	 * @return QueryResult 日志集合
	 */
	public QueryResult getStats(String field, String value, Pagination pagination) throws ServiceException ;

	
	/**
	 * 根据统计编号查找的统计数据
	 * @param statId 统计编号
	 * @return Stat 统计对象
	 */	
	public Stat getStat(long statId) throws ServiceException ;

	/**
	 * 创建统计
	 * @param stat 统计对象
	 */
	public void createStat(Stat stat) throws ServiceException ;

	/**
	 * 删除统计
	 * @param statId 统计编号
	 */
	public void deleteStat(long statId) throws ServiceException ;
	
	/**
	 * 删除指定数组的统计信息
	 * @param statIds 统计信息列表
	 */
	public void deleteStats(String[] statIds) throws ServiceException ;
	
	/**
	 * 删除全部的统计
	 *
	 */
	public void deleteAllStat() throws ServiceException ;
	
}
