package com.ntsky.bbs.web.webwork.action.annoucement;

import java.util.Collection;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;
import com.ntsky.bbs.domain.Announcement;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.BeanUtil;

/**
 * 修改公告
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class EditAnnoucementAction extends AnnouncementActionSupport implements ModelDriven {

	private Announcement announcement = new Announcement();

	/**
	 * 更新公告
	 * 操作者 : 管理员
	 * <pre>
	 * 		执行成功迁移到 announcements.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　更新公告　
		if(!isPermisson("6_1")){
			setWarnMessage("您没有更新公告的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("更新公告");
		/* -------------------------------*/
		
		if(logger.isInfoEnabled()){
			logger.info("更新编号为[" + announcement.getId() +"]的公告信息");
		}
		try{
			// 更新操作
			super.announcementService.editAnnouncement((Announcement)BeanUtil.format(announcement));
	
			// 设置公告页面数据
			super.setAnnouncements(announcementService.getAnnouncements(-1,0));
			
			super.setForms(super.forumService.getForums());
		
			if(logger.isInfoEnabled()){
				logger.info("更新公告["+announcement.getTitle()+"]成功.");
			}
				
			setActionMessage("更新公告["+announcement.getTitle()+"]成功.");
			
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
    }
	
	public Object getModel() {
		return this.announcement;
	}
	
}
