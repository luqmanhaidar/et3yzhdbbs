package com.ntsky.bbs.dao.hibernate;

import java.util.List;

import com.ntsky.bbs.dao.UserFaceDAO;
import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.exception.DAOException;

/**
 * 用户表情Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class UserFaceDAOHibernateImpl extends BaseDAOHibernateImpl implements UserFaceDAO {

	/**
	 * 查询用户头像类别集合
	 * @return List 用户头像集合
	 */
	public List findFaceTypes() throws DAOException {
		try{
			return super.find("from UserFace as f group by f.type");
		}
		catch(DAOException daoException){
			throw new DAOException("根据类别列表表情发生错误.");
		}	
	}

	/**
	 * 根据类别查询用户头像集合
	 * 
	 * @param type 头像类别
	 * @return List 用户头像集合
	 */
	public List findFaces(int type) throws DAOException {
		try{
			return super.find("from UserFace as f where f.type="+type+"");
		}
		catch(DAOException daoException){
			throw new DAOException("根据类别列表表情发生错误.");
		}
	}

	
}
