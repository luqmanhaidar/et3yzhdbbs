package com.ntsky.bbs.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.ntsky.framework.util.security.MD5;
import com.ntsky.framework.util.TimeWatcher;

import com.ntsky.bbs.domain.UserFace;
import com.ntsky.bbs.dao.UserFaceDAO;
import com.ntsky.bbs.service.UserFaceService;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 用户表情数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:26 $ 
 */
public class UserFaceServiceImpl implements UserFaceService{
	
	public static Logger logger = Logger.getLogger(UserFaceServiceImpl.class); 
	
	private UserFaceDAO FaceDAO;
	
	public void setUserFaceDAO(UserFaceDAO FaceDAO){
		this.FaceDAO = FaceDAO;
	}

	/**
	 * 查询用户头像类别集合
	 * @return List 用户头像集合
	 */
	private List getFaceTypes () throws ServiceException {
		try{
			return FaceDAO.findFaceTypes();
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	

	/**
	 * 根据类别查询用户头像集合
	 * 
	 * @param type 头像类别
	 * @return List 用户头像集合
	 */
	private List getFaces (int type) throws ServiceException {
		try{
			return FaceDAO.findFaces(type);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}		
	}	
	
	/**
	 * 取得用户头像集合
	 * 
	 * <p>
	 * list中存储是的是一组Faces
	 * </p>
	 * 
	 * @return List 用户头像集合
	 */
	public List getFaces () throws ServiceException {
		TimeWatcher watcher = new TimeWatcher();
		watcher.start();
		List allFaces = null;
		try{
			allFaces = FaceDAO.findFaceTypes();
			Object[] typesArray = allFaces.toArray();
			/*if(logger.isDebugEnabled()){
				logger.debug("检测到的头像类别数 : " + typesArray.length);
			}*/
			UserFace face = null;
			for (int i = 0; i < typesArray.length; i++) {
				face = (UserFace)typesArray[i];
				face.setUserFaces(FaceDAO.findFaces(face.getType()));
			}
			watcher.stop();
			System.out.println(watcher);				
			return allFaces;		
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}	
	}
	
}
