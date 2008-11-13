package com.ntsky.bbs.web.webwork.action.annoucement;

import java.util.Collection;

import com.opensymphony.xwork.Preparable;
import com.ntsky.bbs.domain.Announcement;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.util.memory.ForumSingleton;

/**
 * 公告管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class AdminAnnoucesAction extends AnnouncementActionSupport {

	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	
	private Forum forum;
	public Forum getForum(){
		return this.forum;
	}	
	
	/**
	 * 公告管理
	 * <pre>
	 * 		执行成功迁移到 userAnnouces.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("列表论坛对应的公告信息");
		}
		
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if(!super.isForumManage(forumId)){
			setWarnMessage("您不是该论坛管理员,您没有查看公告的权限.");
			return NO_PERMISSION;
		}			
		if(super.isAccess("canAnnounce")==0){
			setWarnMessage("您所属的角色没有查看公告的权限.");
			return NO_PERMISSION;
		}
		// ---------------------------
		
		// 公告信息
		super.setAnnouncements(announcementService.getAnnouncements(forumId,0));

		forum = ForumSingleton.getInstance().getForum(forumId);
		
		return SUCCESS;
    }
}
