package com.ntsky.bbs.dao.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ntsky.bbs.domain.Help;
import com.ntsky.bbs.dao.HelpDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ObjectExistException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 帮助信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class HelpDAOHibernateImpl extends BaseDAOHibernateImpl implements HelpDAO {
	
	/**
	 * 查找全部的日志信息
	 * @param title 标题关键字
	 * @param pagination 分页对象
	 * @return QueryResult 帮助集合
	 */
	public QueryResult findHelps (final String title, final Pagination pagination) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Help.class); 
			detachedCriteria.add(Restrictions.like("title","%"+title+"%"));
			return super.findItemsByCriteria(detachedCriteria,null,pagination);
		}catch(Exception exception){
    		throw new DAOException("列表全部的帮助信息发生错误");
    	}
	}
	
	/**
	 * 根据帮助编号查找的帮助数据
	 * @param helpId 标题编号
	 * @return Help 帮助对象
	 */	
	public Help findHelp(int helpId) throws DAOException {
		try{
			return (Help)super.get(Help.class,new Long(helpId));
    	}
		catch(ObjectExistException objectExistException){
			throw new DAOException("取得 helpId(" +helpId+ ") 对应的帮助信息发生错误，该信息可能被删除.");
		}
    	catch(Exception exception){
    		throw new DAOException("取得 helpId(" +helpId+ ") 对应的帮助信息发生错误");
    	}
	}
	
	/**
	 * 根据帮助编号查找的帮助数据
	 * @param type 帮助类别
	 * @return List 帮助集合
	 */	
	public List findHelps(int type) throws DataAccessException {
		try{
			return super.find("from Help where type=? and isHand=0",new Integer(type));
		}
		catch(Exception exception){
    		throw new DAOException("根据类别列表帮助信息发生错误");
    	}
	}

}