package com.ntsky.bbs.dao.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ntsky.bbs.domain.Announcement;
import com.ntsky.bbs.dao.AnnouncementDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ObjectExistException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 公告信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/26 14:01:10 $
 */
public class AnnouncementDAOHibernateImpl extends BaseDAOHibernateImpl implements AnnouncementDAO {
	
	/**
	 * 查找日志信息
	 * <ol>
	 * 	<li>forumId为-1时,取全部信息</li>
	 * 	<li>forumId为0时,首页信息</li>
	 * </ol>
	 * @param forumId 论坛编号
	 * @param pagination 分页对象
	 * @return QueryResult 公告集合
	 */
	public QueryResult findAnnouncements(final int forumId, final Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Announcement.class); 
			if(forumId!=-1){
				detachedCriteria.add(Restrictions.eq("forumId",String.valueOf(forumId)));
			}
			return super.findItemsByCriteria(detachedCriteria,null,pagination);
		}
		catch(DAOException daoException){
			throw new DAOException("列表公告发生错误.");
		}
	}
	
	/**
	 * 根据公告编号查找的公告数据
	 * @param amtId 公告编号
	 * @return Announcement 公告对象
	 */	
	public Announcement findAnnouncement(int announcementId) throws DAOException {
		try{
			return (Announcement)super.load(Announcement.class,new Long(announcementId));
		}
		catch(DAOException daoException){
			throw new DAOException("根据公告编号查找公告发生错误.");
		}
	}

	/**
	 * 根据论坛编号取得公告信息
	 * @param forumId 论坛编号
	 * @param num 公告数
	 * @return List 公告集合
	 */	
	public List findAnnouncements(int forumId,int num) throws DAOException {
		try{
		
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Announcement.class);
			if( forumId != -1 ){
				detachedCriteria.add(Restrictions.eq("forumId",new Integer(forumId)));
			}
			detachedCriteria.addOrder(Order.desc("dateCreated"));
			return super.find(detachedCriteria,num);		
		}
		catch(ObjectExistException objectExistException){
			throw new DAOException("取得 forumId(" +forumId+ ") 对应的公告信息发生错误.");
		}
    	catch(Exception exception){
    		throw new DAOException("取得 forumId(" +forumId+ ") 对应的公告信息发生错误");
    	}
	}

}