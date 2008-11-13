package com.ntsky.bbs.service;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ntsky.bbs.domain.Announcement;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 公告业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface AnnouncementService extends BaseService{

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
	public QueryResult getAnnouncements(final int forumId, final Pagination pagination) ;
	
	/**
	 * 查找日志信息
	 * <ol>
	 * 	<li>forumId为-1时,取全部信息</li>
	 * 	<li>forumId为0时,首页信息</li>
	 * </ol>
	 * @param forumId 论坛编号
	 * @param num 公告数
	 * @return List 公告集合
	 */
	public List getAnnouncements(final int forumId,int num) throws ServiceException ;
	
	/**
	 * 根据公告编号查找的公告数据
	 * @param amtId 公告编号
	 * @return Announcement 公告对象
	 */	
	public Announcement getAnnouncement(int amtId) throws ServiceException ;
	
	/**
	 * 创建公告
	 * @param announcement 公告对象
	 */
	public void createAnnouncement(Announcement announcement) throws ServiceException;
	
	/**
	 * 删除公告
	 * @param annoucementId　公告编号
	 */
	public void deleteAnnouncement(int annoucementId) throws ServiceException;
	
	/**
	 * 修改公告信息	
	 * @param announcement 公告信息 
	 */
	public void editAnnouncement(Announcement announcement) throws ServiceException;

	/**
	 * 生成公告JS数据
	 * @param forumId
	 */
	public void makeAnnouncementJSData(int forumId);
}
