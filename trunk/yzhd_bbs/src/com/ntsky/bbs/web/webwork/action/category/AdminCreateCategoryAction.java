package com.ntsky.bbs.web.webwork.action.category;

import com.opensymphony.xwork.ModelDriven;

import com.ntsky.bbs.domain.Category;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 管理员添加主题类别
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:33 $
 */
public class AdminCreateCategoryAction extends CategoryActionSupport implements ModelDriven {

	private Category category = new Category();
	
	// 论坛
	private Forum forum;
	private Forum getForum(){
		return this.forum;
	}
	
	/**
	 * 管理员添加主题类别
	 */
	public String execute() throws Exception {

		// ---------- 权限 ------------
		if(!super.isForumManage(category.getForumId())){
			setWarnMessage("您不是该论坛管理员,您没有创建论坛专题的权限.");
			return NO_PERMISSION;
		}				
		if(super.isAccess("canCategory")==0){
			setWarnMessage("您所属的角色没有创建论坛专题的权限.");
			return NO_PERMISSION;
		}
		// ---------------------------		
		try{
			categoryService.createCategory(category);
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
