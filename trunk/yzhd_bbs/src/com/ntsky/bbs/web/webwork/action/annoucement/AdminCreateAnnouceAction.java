package com.ntsky.bbs.web.webwork.action.annoucement;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Announcement;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.util.BeanUtil;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 管理员添加公告
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class AdminCreateAnnouceAction extends AnnouncementActionSupport implements Preparable {

	private String title;
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private Forum forum;
	public Forum getForum(){
		return this.forum;
	}

	/**
	 * 添加公告
	 * <pre>
	 * 		执行成功迁移到 userAnnouces.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		forumId = super.getIntParameter("forumId");
		if(logger.isInfoEnabled()){
			logger.info("创建论坛 [' "+forumId+" '] 的公告...");
		}
		
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if(!super.isForumManage(forumId)){
			setWarnMessage("您不是该论坛管理员,您没有创建公告的权限.");
			return NO_PERMISSION;
		}		
		if(super.isAccess("canAnnounce")==0){
			return NO_PERMISSION;
		}
		// ---------------------------
		
		Announcement announcement = null;
		try{
			announcement = new Announcement();
			announcement.setTitle(title);
			announcement.setContent(content);
			announcement.setForumId(forumId);
			
			// 添加操作
			announcement.setAuthor(super.getSessionUser().getUsername());
			announcementService.createAnnouncement((Announcement)BeanUtil.format(announcement));
			// 设置公告页面数据
			//setAnnouncements(announcementService.getAnnouncements(forumId));
			// 设置论坛信息
			/*forum = ForumMemory.getForum(forumId);
			if(logger.isInfoEnabled()){
				logger.info("设置论坛信息 [' "+forum.getName()+" '] ...");
			}*/			
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		setActionMessage("添加公告[ '"+announcement.getTitle()+"' ]成功");
		return SUCCESS;
    }
	
	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}	
	public void getForumId(){
		this.forumId = forumId;
	}
	
	public String doDefault() throws Exception{
		if(logger.isInfoEnabled()){
			logger.info("准备创建公告....");
		}
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if(!super.isForumManage(forumId)){
			setWarnMessage("您不是该论坛管理员,您没有创建公告的权限.");
			return NO_PERMISSION;
		}			
		if(super.isAccess("canAnnounce")==0){
			setWarnMessage("您所属的角色没有创建公告的权限");
			return NO_PERMISSION;
		}
		// ---------------------------
		return SUCCESS;
	}

	public void prepare() throws Exception {
		
		forum = ForumSingleton.getInstance().getForum(getIntParameter(Symbols.PARA_FORUM_ID));
		if(logger.isInfoEnabled()){
			logger.info("设置论坛信息 [' "+forum.getName()+" '] ...");
		}	
	}

}
