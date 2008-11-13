package com.ntsky.bbs.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import org.hibernate.cache.EhCacheProvider;

import com.ntsky.bbs.domain.Common;
import com.ntsky.bbs.domain.Poll;
import com.ntsky.bbs.dao.CommonDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 公共类别Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:30 $
 */
public class CommonDAOHibernateImpl extends BaseDAOHibernateImpl implements CommonDAO {
	
	/**
	 * 查找全部头像类别
	 * @return List 头像信息
	 * @throws DAOException
	 */
	public List findFaceTypes() throws DAOException {
		try{
			//return super.find("from Common as c left outer join c.faces as face with face.type=c.id where c.type=1 group by c.id");
			return super.find("from Common as c where c.type=1 order by c.id asc");
		}
		catch(DAOException daoException){
			throw new DAOException("列表全部头像发生错误...");
		}			
	}
	
	/**
	 * 查找全部的帮助类别
	 * @return
	 * @throws DAOException
	 */
	public List findHelpTypes()	throws DAOException{
		try{
			//return super.find("from Common as c inner join fetch c.helps as help where c.type=2 and help.isHand=0 order by c.id asc");
			return super.find("from Common as c where c.type=2 order by c.id asc");
		}
		catch(DAOException daoException){
			throw new DAOException("列表全部帮助类别发生错误...");
		}		
	} 
	
}