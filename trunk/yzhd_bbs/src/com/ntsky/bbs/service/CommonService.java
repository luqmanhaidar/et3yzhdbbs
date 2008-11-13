package com.ntsky.bbs.service;

import java.util.List;

import com.ntsky.bbs.domain.Common;
import com.ntsky.bbs.exception.ServiceException;

public interface CommonService extends BaseService{
	
	/**
	 * 查找头像类别
	 * @return List 头像信息
	 */
	public List getFaceTypes() throws ServiceException ;

	/**
	 * 查找帮助类别
	 */
	public List getHelpTypes() throws ServiceException ;
	
	/**
	 * 列表全部头像信息
	 * @return
	 * @throws ServiceException
	 */
	public List getAllFaces() throws ServiceException;
	
	/**
	 * 列表全部帮助信息
	 * @return
	 * @throws ServiceException
	 */
	public List getAllHelps() throws ServiceException;
	
}
