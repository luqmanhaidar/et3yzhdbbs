package com.ntsky.bbs.service;

import java.util.List;

import com.ntsky.bbs.domain.Link;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 友情链接模块业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface LinkService {

	/**
	 * 创建友情链接
	 * 
	 * @param link 友情链接
	 */
	public void createLink(Link link) throws ServiceException;
	
	/**
	 * 删除友情链接
	 * 
	 * @param linkId 友情链接编号
	 */
	public void deleteLink(int linkId) throws ServiceException ;
	
	/**
	 * 修改友情链接信息
	 * 
	 * @param link 友情链接信息
	 */
	public void editLink(Link link) throws ServiceException ;
	
	/**
	 * 取得友情链接
	 * 
	 * @param linkId 友情链接编号
	 */
	public Link getLink(int linkId) throws ServiceException ;
	
	/**
	 * 取得全部的友情链接列表
	 * 
	 * @return List 友情链接列表
	 */
	public List getLinks() throws ServiceException ;
	
	/**
	 * 取得指定类型的友情链接列表
	 * @param isLogo　是否为友情链接
	 * @return List 友情链接列表
	 */
	public List getLinks(int isLogo) throws ServiceException ;

	/**
	 * 作成链接JS数据
	 *
	 */
	public void makeLinkJSData() ;
	
	public Link findRandomLinkByType(int at) throws DAOException;
	
}
