package com.ntsky.bbs.web.webwork.action.annoucement;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.util.BeanUtil;
import com.ntsky.bbs.domain.Announcement;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 添加公告
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class CreateAnnoucementAction extends AnnouncementActionSupport implements ModelDriven {

	private Announcement announcement = new Announcement();
	
	public Announcement getAnnouncement(){
		return this.announcement;
	}
	
	/**
	 * 添加公告
	 * 操作者 : 管理员
	 * <pre>
	 * 		执行成功迁移到 annoucements.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　添加公告　
		if(!isPermisson("6_1")){
			setWarnMessage("您没有添加公告的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("添加公告");
		/* -------------------------------*/		
		
		if(logger.isInfoEnabled()){
			logger.info("管理员添加公告信息");
		}
		try{
			// 添加操作
			announcement.setAuthor(super.getSessionAdmin().getUsername());
			super.announcementService.createAnnouncement((Announcement)BeanUtil.format(announcement));
			// 设置公告页面数据
			super.setAnnouncements(announcementService.getAnnouncements(-1,0));
			// 论坛信息
			super.setForms(super.forumService.getForums());
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		super.setActionMessage("添加公告[ '"+announcement.getTitle()+"' ]成功");
				
		return SUCCESS;
    }
	
	public Object getModel() {
		return this.announcement;
	}

}
