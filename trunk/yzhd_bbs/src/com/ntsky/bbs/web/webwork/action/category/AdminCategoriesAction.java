package com.ntsky.bbs.web.webwork.action.category;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.domain.Category;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 主题类别管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */
public class AdminCategoriesAction extends CategoryActionSupport{

	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	
	private Forum forum;
	public Forum getForum(){
		return this.forum;
	}
	
	/**
	 * 主题类别管理
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("类别对应的论坛编号 [ '"+forumId+"' ] ");
		}

		// ---------- 权限 ------------
		// 是否为论坛管理员
		if(!super.isForumManage(forumId)){
			setWarnMessage("您不是该论坛管理员,您没有列表论坛分类的权限.");
			return NO_PERMISSION;
		}
		if(super.isAccess("canCategory")==0){
			setWarnMessage("您所属的角色没有列表论坛专题的权限.");
			return NO_PERMISSION;
		}
		// ---------------------------				
		
		try{
			setCategories(categoryService.getCategories(forumId));
	
			// 论坛
			forum = ForumSingleton.getInstance().getForum(forumId);	
			if(logger.isInfoEnabled()){
				logger.info("类别对应的论坛为 : " + forum.getName());
			}		
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	public String doDefault() throws Exception {
		
		// ---------- 权限 ------------
		if(super.isAccess("canCategory")==0){
			setWarnMessage("您所属的角色没有创建论坛专题的权限.");
			return NO_PERMISSION;
		}
		// ---------------------------		
		
		// 论坛
		forum = ForumSingleton.getInstance().getForum(forumId);	
		return SUCCESS;
	}
	
	
}
