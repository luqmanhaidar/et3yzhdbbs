package com.ntsky.bbs.web.webwork.action.annoucement;

import java.util.Collection;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;
import com.ntsky.bbs.domain.Announcement;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.BeanUtil;
import com.ntsky.bbs.util.memory.ForumSingleton;

/**
 * 修改公告
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class AdminEditAnnouceAction extends AnnouncementActionSupport {

	private Announcement announcement = null ;
	
	// 公告编号
	private int announcementId;
	public void setAnnouncementId(int announcementId){
		this.announcementId = announcementId;
	}
	
	/**
	 * 公告信息
	 */
	public Announcement getAnnouncement(){
		return this.announcement;
	}
	
	
	private Forum forum;
	public Forum getForum(){
		return this.forum;
	}
	
	// 论坛编号
	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	/**
	 * 修改公告(查找制定公告编号的公告信息)
	 * @return 公告信息
	 * @throws Exception
	 */
	public String doEdit() throws Exception{
		if(logger.isInfoEnabled()){
			logger.info("修改公告.查找编号为[ " + announcementId +" ]的公告信息.");
		}
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if(!super.isForumManage(forumId)){
			setWarnMessage("您不是该论坛管理员,您没有修改公告的权限.");
			return NO_PERMISSION;
		}			
		if(super.isAccess("canAnnounce")==0){
			setWarnMessage("您所属的角色没有修改公告的权限.");
			return NO_PERMISSION;
		}
		// ---------------------------		
		try{
			this.announcement = (super.announcementService.getAnnouncement(announcementId));
			if(logger.isInfoEnabled()){
				logger.info("成功取得公告,公告标题为 : " + announcement.getTitle());
			}
			forum = ForumSingleton.getInstance().getForum(forumId);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	
	private Long id;
	private String title;
	private String content;

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 更新公告
	 * <pre>
	 * 		执行成功迁移到 editAnnoucement.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if(!super.isForumManage(forumId)){
			setWarnMessage("您不是该论坛管理员,您没有更新公告的权限.");
			return NO_PERMISSION;
		}			
		if(super.isAccess("canAnnounce")==0){
			setWarnMessage("您所属的角色没有更新公告的权限.");
			return NO_PERMISSION;
		}
		// ---------------------------			
		Announcement announcement = new Announcement();
		announcement.setId(id);
		announcement.setForumId(forumId);
		announcement.setTitle(title);
		announcement.setContent(content);
		if(logger.isInfoEnabled()){
			logger.info("更新编号为[" + announcement.getId() +"]的公告信息");
		}
		try{
			// 更新操作
			super.announcementService.editAnnouncement((Announcement)BeanUtil.format(announcement));
	
			// 设置公告页面数据
			forum = ForumSingleton.getInstance().getForum(forumId);
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
	
}
