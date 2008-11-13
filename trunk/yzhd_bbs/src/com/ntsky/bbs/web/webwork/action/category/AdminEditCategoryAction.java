package com.ntsky.bbs.web.webwork.action.category;

import com.opensymphony.xwork.ModelDriven;

import com.ntsky.bbs.domain.Category;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 管理员修改主题类别
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */
public class AdminEditCategoryAction extends CategoryActionSupport {

	private Category category = null;
	public Category getCategory(){
		return this.category;
	}
	
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
	
	private Forum forum;
	public Forum getForum(){
		return this.forum;
	}	
	
	/**
	 * 修改类别(查找类别)
	 */
	public String doEdit() throws Exception {
		// ---------- 权限 ------------
		if(!super.isForumManage(forumId)){
			setWarnMessage("您不是该论坛管理员,您没有修改论坛专题的权限.");
			return NO_PERMISSION;
		}				
		if(super.isAccess("canCategory")==0){
			setWarnMessage("您所属的角色没有修改论坛专题的权限.");
			return NO_PERMISSION;
		}
		// ---------------------------				
		try{
			category = categoryService.getCategory(categoryId);
			// 论坛数据
			forum = ForumSingleton.getInstance().getForum(forumId);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}	
	
	private String name;
	public void setName(String name){
		this.name = name;
	}

	private int displayOrder;
	public void setDisplayOrder(int displayOrder){
		this.displayOrder = displayOrder;
	}	
	
	/**
	 * 更新类别
	 */
	public String execute() throws Exception {
		// ---------- 权限 ------------
		if(!super.isForumManage(forumId)){
			setWarnMessage("您不是该论坛管理员,您没有更新论坛专题的权限.");
			return NO_PERMISSION;
		}				
		if(super.isAccess("canCategory")==0){
			setWarnMessage("您所属的角色没有更新论坛专题的权限.");
			return NO_PERMISSION;
		}
		// ---------------------------			
		try{
			category = new Category();
			category.setId(new Long(id));
			category.setName(name);
			category.setDisplayOrder(displayOrder);
			categoryService.editCategory(category);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}

	public Object getModel() {
		return category;
	}

}
