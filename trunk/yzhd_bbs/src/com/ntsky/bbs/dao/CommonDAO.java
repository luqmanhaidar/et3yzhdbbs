package com.ntsky.bbs.dao;

import java.util.List;

import com.ntsky.bbs.domain.ActLog;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 公共类别模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface CommonDAO extends BaseDAO{

	/**
	 * 查找全部头像
	 * @return List 头像信息
	 * @throws DAOException
	 */
	public List findFaceTypes() throws DAOException ;
	
	/**
	 * 查找全部帮助类别
	 * @return List 帮助类别
	 * @throws DAOException
	 */
	public List findHelpTypes() throws DAOException ;
	
}
