package com.ntsky.bbs.web.webwork.action.annoucement;

import java.util.Collection;

import com.opensymphony.xwork.Preparable;
import com.ntsky.bbs.domain.Announcement;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.memory.ForumSingleton;

/**
 * 公告管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class AnnoucementManageAction extends AnnouncementActionSupport {
	
	/**
	 * 公告信息
	 */
	private Announcement announcement = null;
	public void setAnnouncement(Announcement announcement){
		this.announcement = announcement;
	}
	public Announcement getAnnouncement(){
		return this.announcement;
	}
	
	/**
	 * 查找公告(根据公告编号查找公告信息)
	 * 操作者 ：管理员
	 * @return 公告信息
	 * @throws Exception
	 */
	public String doEdit() throws Exception{
		
		/* ---------- 权限判断 ------------ */
		//　修改公告
		if(!isPermisson("6_1")){
			setWarnMessage("您没有修改公告的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("修改公告");
		/* -------------------------------*/		
		
		if(logger.isInfoEnabled()){
			logger.info("查找公告.查找编号为[ " + id +" ]的公告信息.");
		}
		try{
			this.announcement = (super.announcementService.getAnnouncement(id));
			if(logger.isInfoEnabled()){
				logger.info("成功取得公告,公告标题为 : " + announcement.getTitle());
			}
			super.setForms(super.forumService.getForums());
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return EDIT;
	}	
	
	
	/**
	 * 删除公告
	 * 操作者 ：管理员
	 * <pre>
	 * 		执行成功迁移到 annoucementManage.ftl
	 * </pre>
	 * @return String success 
	 */
	public String doDelete() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　删除公告
		if(!isPermisson("6_1")){
			setWarnMessage("您没有删除公告的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("删除公告");
		/* -------------------------------*/		
		
		if(logger.isInfoEnabled()){
			logger.info("删除编号为 ["+id+"] 的公告信息");
		}		
		try{
			// 删除公告
			super.announcementService.deleteAnnouncement(id);
			// 设置公告页面数据
			super.setAnnouncements(announcementService.getAnnouncements(-1,0));
			// 论坛信息
			super.setForms(super.forumService.getForums());
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		setActionMessage("成功删除公告");
		return DELETE;
    }
	
}
