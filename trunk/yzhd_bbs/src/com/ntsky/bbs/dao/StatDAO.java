package com.ntsky.bbs.dao;

import com.ntsky.bbs.domain.Stat;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 日志模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface StatDAO extends BaseDAO{

	/**
	 * 查找全部的统计信息
	 * @param field 检索字段
	 * @param value 检索匹配的值
	 * @param pagination 分页对象
	 * @return QueryResult 日志集合
	 */
	public QueryResult findStats (String field, String value, Pagination pagination) throws DAOException ;
	
	/**
	 * 根据统计编号查找的统计数据
	 * @param statId 统计编号
	 * @return Stat 统计数据
	 */	
	public Stat findStat(long statId) throws DAOException ;
	
	/**
	 * 删除全部的统计
	 *
	 */
	public void deleteAllStat() throws DAOException ;
	
}
