package com.ntsky.bbs.web.webwork.action.annoucement;

import java.util.Collection;

import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.util.memory.ForumSingleton;
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
public class AdminDeleteAnnouceAction extends AnnouncementActionSupport {

	// 公告编号
	private int announcementId;
	public void setAnnouncementId(int announcementId){
		this.announcementId = announcementId;
	}
	
	// 论坛编号
	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	
	private Forum forum;
	public Forum getForum(){
		return this.forum;
	}
	
	/**
	 * 删除公告
	 * <pre>
	 * 		执行成功迁移到 annoucementManage.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("删除编号为 ["+announcementId+"] 的公告信息");
		}
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if(!super.isForumManage(forumId)){
			setWarnMessage("您不是该论坛管理员,您没有删除公告的权限.");
			return NO_PERMISSION;
		}			
		if(super.isAccess("canAnnounce")==0){
			setWarnMessage("您所属的角色没有删除公告的权限.");
			return NO_PERMISSION;
		}
		// ---------------------------
		try{
			// 删除公告
			super.announcementService.deleteAnnouncement(announcementId);
			// 设置公告页面数据
			super.setAnnouncements(announcementService.getAnnouncements(forumId,0));
		
			forum = ForumSingleton.getInstance().getForum(forumId);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}

		setActionMessage("成功删除公告");
		return SUCCESS;
    }
	
}
