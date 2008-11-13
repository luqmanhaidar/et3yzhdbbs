package com.ntsky.bbs.web.webwork.action.category;

import com.opensymphony.xwork.ModelDriven;

import com.ntsky.bbs.domain.Category;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.memory.ForumSingleton;

/**
 * 删除主题类别
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */
public class AdminDeleteCategoryAction extends CategoryActionSupport {

	private int categoryId;
	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}
	
	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	public int getForumId(){
		return this.forumId;
	}

	/**
	 * 用户删除类别
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if(!super.isForumManage(forumId)){
			setWarnMessage("您不是该论坛管理员,您没有删除论坛专题的权限.");
			return NO_PERMISSION;
		}		
		if(super.isAccess("canCategory")==0){
			setWarnMessage("您所属的角色没有删除论坛专题的权限.");
			return NO_PERMISSION;
		}
		// ---------------------------	
		
		try{
			categoryService.deleteCategory(categoryId);			
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}

}
