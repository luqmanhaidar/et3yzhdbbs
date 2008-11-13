package com.ntsky.bbs.dao;

import java.util.List;

import com.ntsky.bbs.domain.Announcement;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.exception.DAOException;
/**
 * 日志模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface AnnouncementDAO extends BaseDAO{

	/**
	 * 查找日志信息
	 * <ol>
	 * 	<li>forumId为-1时,取全部信息</li>
	 * 	<li>forumId为0时,首页信息</li>
	 * </ol>
	 * @param forumId 论坛编号
	 * @param pagination 分页对象
	 * @return List 公告集合
	 */
	public QueryResult findAnnouncements(final int forumId, final Pagination pagination) throws DAOException ;

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
	public List findAnnouncements(final int forumId,int num) throws DAOException ;
	
	
	/**
	 * 根据公告编号查找的公告数据
	 * @param announcementId 公告编号
	 * @return Announcement 公告对象
	 */	
	public Announcement findAnnouncement(int announcementId) throws DAOException ;
	
}
