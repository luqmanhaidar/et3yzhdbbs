package com.ntsky.bbs.service.impl;

import java.util.Date;
import java.util.List;

import com.ntsky.bbs.dao.AnnouncementDAO;
import com.ntsky.bbs.domain.Announcement;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.AnnouncementService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.framework.util.FileUtil;

/**
 * 公告业务处理实现
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class AnnouncementServiceImpl extends BaseServiceImpl implements AnnouncementService{

	AnnouncementDAO announcementDAO;
	
	public void setAnnouncementDAO(AnnouncementDAO announcementDAO){
		this.announcementDAO = announcementDAO;
	}
	
	/**
	 * 查找公告信息
	 * <ol>
	 * 	<li>forumId为-1时,取全部信息</li>
	 * 	<li>forumId为0时,首页信息</li>
	 * </ol>
	 * @param forumId 论坛编号
	 * @param pagination 分页对象
	 * @return QueryResult 公告集合
	 */
	public QueryResult getAnnouncements(final int forumId, final Pagination pagination) throws ServiceException {
		try{
			return announcementDAO.findAnnouncements(forumId,pagination);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
	/**
	 * 根据公告编号查找的公告数据
	 * @param amtId 公告编号
	 * @return Announcement 公告对象
	 */	
	public Announcement getAnnouncement(int amtId)  throws ServiceException{
		try{
			return announcementDAO.findAnnouncement(amtId);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}

	/**
	 * 创建公告
	 * @param announcement 公告对象
	 */
	public void createAnnouncement(Announcement announcement) throws ServiceException {
		try{
			announcement.setDateCreated(new Date());
			announcementDAO.save(announcement);
		}
		catch(DAOException daoException){
			throw new ServiceException("创建公告\"" + announcement.getTitle() +"\"发生错误.");
		}
		// 生成公告JS数据
		this.makeAnnouncementJSData(announcement.getForumId());
	}
	
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
	public List getAnnouncements(final int forumId,int num) throws ServiceException {
		try{	
			return announcementDAO.findAnnouncements(forumId,num);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}

	/**
	 * 删除公告
	 * @param annoucementId　公告编号
	 */
	public void deleteAnnouncement(int annoucementId) throws ServiceException {
		int forumId = 0;
		try{
			forumId = announcementDAO.findAnnouncement(annoucementId).getForumId();

			announcementDAO.delete(getAnnouncement(annoucementId));
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
		// js数据生成
		this.makeAnnouncementJSData(forumId);
	}
	
	/**
	 * 修改公告信息	
	 * @param announcement 公告信息 
	 */
	public void editAnnouncement(Announcement announcement) throws ServiceException{
		try{
			Announcement tempAnnounce = announcementDAO.findAnnouncement(announcement.getId().intValue());
			tempAnnounce.setTitle(announcement.getTitle());
			tempAnnounce.setLink(announcement.getLink());
			//tempAnnounce.setContent(announcement.getContent());
			tempAnnounce.setForumId(announcement.getForumId());
			announcementDAO.update(tempAnnounce);
		}
		catch(DAOException daoException){
			throw new ServiceException("更新公告 ["+announcement.getTitle()+"] 成功. ");
		}
		// js数据生成
		this.makeAnnouncementJSData(announcement.getForumId());

	}	
	
	/**
	 * 生成公告JS数据
	 * @param forumId
	 */
	public void makeAnnouncementJSData(int forumId){
		/*if(logger.isDebugEnabled()){
			logger.debug("生成["+forumId+"]对应的公告JS");
		}*/
		int num = forumId==0? 3 : 5 ;
		try{
			Object[] announcements = getAnnouncements(forumId,num).toArray();
			StringBuffer announcementBuffer = new StringBuffer();
			Announcement announcement = null;
			String[] announcementTypeArray = {"title","dateCreated"}; 
			for (int i = 0; i < announcements.length; i++) {
				announcement = (Announcement) announcements[i];
				super.makeJsData(announcement,announcementBuffer,"announcementClass.add",announcementTypeArray);
			}
			/*if(logger.isDebugEnabled()){
				logger.debug("公告内容 ： " + announcementBuffer.toString());
			}*/
			FileUtil.writeFile(Application.getInstance().getJsDirectory()+"announcement_"+forumId+".js",announcementBuffer.toString());
		}
		catch(Exception ex){
			logger.error("生成JS发生错误",ex);
		}
	}
}
