package com.ntsky.bbs.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ntsky.bbs.domain.Link;
import com.ntsky.bbs.exception.DAOException;

/**
 * 友情链接数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/16 17:25:53 $
 */
public interface LinkDAO extends BaseDAO{

	/**
	 * 查找全部的友情链接
	 * @param isLogo 是否为友情链接
	 * @return List 友情链接列表
	 */
	public List findLinks (int isLogo) throws DAOException ;

	/**
	 * 根据ID查找链接数据
	 * @param linkId 链接编号
	 * @return Link 友情链接对象
	 */		
	public Link findLink(int linkId) throws DAOException ;
	
	/**
	 * 查找全部的友情链接信息
	 * @return List 友情链接列表
	 * @throws DataAccessException
	 */
	public List findLinks() throws DAOException ;
	
	public Link findRandomLinkByType(int at) throws DAOException;
	
}
