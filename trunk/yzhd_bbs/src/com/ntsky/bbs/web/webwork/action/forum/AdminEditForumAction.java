package com.ntsky.bbs.web.webwork.action.forum;

import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.framework.util.HttpUtil;
import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.StringUtil;
import com.ntsky.framework.util.lumaqq.IPSeeker;

import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.domain.Stat;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 修改和更新论坛信息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/26 16:39:02 $
 */
public class AdminEditForumAction extends ForumActionSupport implements ModelDriven {

	private Forum forum = new Forum();

	public Forum getForum(){
		return this.forum;
	}
	
	// 论坛编号
	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	
	/**
	 * 更新论坛版块数据信息
	 * 
	 * @throws Exception
	 */
	public String execute() throws Exception {
		
		// 更新论坛版块信息
		/*if(logger.isInfoEnabled()){
			logger.info("更新论坛["+forum.getName()+"]的信息.论坛编号 : " + forumId);
		}*/
		
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if(!super.isForumManage(forum.getId().intValue())){
			setWarnMessage("您不是该论坛管理员,您没有修改论坛信息的权限.");
			return NO_PERMISSION;
		}
		if(super.isAccess("canEditForum")==0){
			setWarnMessage("您所属的角色没有修改论坛信息的权限.");
			return NO_PERMISSION;
		}
		// ---------------------------		
		
		try{
			forumService.editForum(forum);
			setActionMessage("成功更新论坛['"+forum.getName()+"']的信息");
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
		
	public Object getModel() {
		return this.forum;
	}
	
}
