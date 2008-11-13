package com.ntsky.bbs.service.impl;

import java.util.List;

import com.ntsky.bbs.dao.HelpDAO;
import com.ntsky.bbs.dao.CommonDAO;
import com.ntsky.bbs.dao.UserFaceDAO;
import com.ntsky.bbs.domain.Common;
import com.ntsky.bbs.service.CommonService;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 公共类别业务处理实现
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class CommonServiceImpl implements CommonService{

	private CommonDAO commonDAO;
	
	public void setCommonDAO(CommonDAO commonDAO){
		this.commonDAO = commonDAO;
	}
	
	private HelpDAO helpDAO;
	public void setHelpDAO(HelpDAO helpDAO){
		this.helpDAO = helpDAO;
	}
	
	private UserFaceDAO userFaceDAO;
	public void setUserFaceDAO(UserFaceDAO userFaceDAO){
		this.userFaceDAO = userFaceDAO;
	}

	/**
	 * 查找头像类别
	 * @return List 头像信息
	 * @throws ServiceException
	 */
	public List getFaceTypes() throws ServiceException {
		try{
			return commonDAO.findFaceTypes();
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
	/**
	 * 查找帮助类别
	 * @return List 头像信息
	 * @throws ServiceException
	 */
	public List getHelpTypes() throws ServiceException {
		try{
			return commonDAO.findHelpTypes();
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}

	/**
	 * 列表全部帮助信息
	 * @return
	 * @throws ServiceException
	 */
	public List getAllHelps() throws ServiceException {
		try{
			List helpTypes = getHelpTypes();
			Object[] objArray = helpTypes.toArray();
			Common common = null;
			for (int i = 0; i < objArray.length; i++) {
				common = (Common)objArray[i];
				common.setHelps(helpDAO.findHelps(common.getId().intValue()));
			}
			return helpTypes;
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
	/**
	 * 列表全部头像信息
	 * @return
	 * @throws ServiceException
	 */
	public List getAllFaces() throws ServiceException {
		try{
			List faceTypes = getFaceTypes();
			Object[] objArray = faceTypes.toArray();
			Common common = null;
			for (int i = 0; i < objArray.length; i++) {
				common = (Common)objArray[i];
				common.setFaces(userFaceDAO.findFaces(common.getId().intValue()));
			}
			return faceTypes;
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
}
