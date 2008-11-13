package com.ntsky.bbs.dao;

import java.util.List;

import com.ntsky.bbs.exception.DAOException;

/**
 * 用户表情数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:26 $ 
 */
public interface UserFaceDAO extends BaseDAO{

	/**
	 * 查询用户头像类别集合
	 * @return List 用户头像集合
	 */
	public List findFaceTypes () throws DAOException ;
	

	/**
	 * 根据类别查询用户头像集合
	 * 
	 * @param type 头像类别
	 * @return List 用户头像集合
	 */
	public List findFaces (int type) throws DAOException ;	
}
