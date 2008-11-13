package com.ntsky.bbs.web.webwork.action.category;

import com.ntsky.bbs.domain.Category;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.opensymphony.xwork.ModelDriven;

/**
 * 添加主题类别
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.4 $ $Date: 2008/11/05 16:54:39 $
 */
public class CreateCategoryAction extends CategoryActionSupport implements ModelDriven {

	private Category category = new Category();
	
	private int forumId;
	public int getForumId(){
		return forumId;
	}
	/**
	 * 添加主题类别
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　创建主题类别
		if(!isPermisson("2_5")){
			setWarnMessage("您没有创建主题类别的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("创建主题类别");
		/* -------------------------------*/		
		
		try{
			// 添加主题类别
			if(categoryService.getCategoryByName(category.getName())==null)
			{
				categoryService.createCategory(category);
			}
			else{
				
				setActionMessage("主题类别["+category.getName()+"]已存在,请选择其它主题类别");
				return INPUT;
			}
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
