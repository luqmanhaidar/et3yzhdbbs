package com.ntsky.bbs.web.webwork.action.annoucement;

import java.util.Collection;

import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 删除公告
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class DeleteAnnoucementAction extends AnnouncementActionSupport implements Preparable{

	// 公告编号
	private int announcementId;
	public void setAnnouncementId(int announcementId){
		this.announcementId = announcementId;
	}
	/**
	 * 删除公告
	 * <pre>
	 * 		执行成功迁移到 annoucementManage.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		
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
			logger.info("删除编号为 ["+announcementId+"] 的公告信息");
		}

		// ---------- 权限 ------------
		if(super.isAccess("canAnnounce")==0){
			setWarnMessage("您所属的角色没有删除公告的权限.");
			return NO_PERMISSION;
		}
		// ---------------------------
		
		try{
			// 删除公告
			super.announcementService.deleteAnnouncement(announcementId);
			// 设置公告页面数据
			super.setAnnouncements(announcementService.getAnnouncements(-1,0));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}

		setActionMessage("成功删除公告");
		return SUCCESS;
    }
	
	/**
	 * 执行此ManageAction的准备信息
	 * <ul>
	 *  <li>初始化论坛类别信息</li>
	 * </ul>
	 */
	public void prepare() throws Exception {
		// 论坛信息
		super.setForms(super.forumService.getForums());
		
		if(logger.isInfoEnabled()){
			logger.info("初始化论坛版块数据信息");		
		}
	}
	
}
