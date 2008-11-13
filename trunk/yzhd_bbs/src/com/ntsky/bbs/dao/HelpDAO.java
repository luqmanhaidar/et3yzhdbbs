package com.ntsky.bbs.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ntsky.bbs.domain.Help;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 帮助模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface HelpDAO extends BaseDAO{

	/**
	 * 查找全部的帮助信息
	 * @param title 标题关键字
	 * @param pagination 分页对象
	 * @return List 帮助集合
	 */
	public QueryResult findHelps (final String title, final Pagination pagination) ;
	
	/**
	 * 根据帮助编号查找的帮助数据
	 * @param helpId 标题编号
	 * @return Help 帮助对象
	 */	
	public Help findHelp(int helpId) throws DAOException ;
	
	/**
	 * 根据帮助编号查找的帮助数据
	 * @param type 帮助类别
	 * @return List 帮助集合
	 */	
	public List findHelps(int type) throws DataAccessException ;
	
}
