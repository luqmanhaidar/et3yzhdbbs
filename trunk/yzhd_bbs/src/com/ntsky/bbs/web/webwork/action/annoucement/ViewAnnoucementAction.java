package com.ntsky.bbs.web.webwork.action.annoucement;

import java.util.Collection;

import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.domain.Announcement;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 查看公告信息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class ViewAnnoucementAction extends AnnouncementActionSupport{

	private Announcement announcement = null;
	
	/**
	 * 公告信息
	 */
	public void setAnnouncement(Announcement announcement){
		this.announcement = announcement;
	}
	public Announcement getAnnouncement(){
		return this.announcement;
	}
	
	/**
	 * 查看公告信息
	 * <pre>
	 * 		执行成功迁移到 annoucement.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("删除编号为 ["+super.id+"] 的公告信息");
		}
		try{
			// 查看公告
			setAnnouncement(super.announcementService.getAnnouncement(id));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
    }
	
}
