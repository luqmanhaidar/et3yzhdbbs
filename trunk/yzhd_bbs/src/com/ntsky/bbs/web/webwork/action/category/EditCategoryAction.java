package com.ntsky.bbs.web.webwork.action.category;

import java.util.List;

import com.ntsky.bbs.domain.Category;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.opensymphony.xwork.ModelDriven;

/**
 * 管理员修改主题类别
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.3 $ $Date: 2008/11/05 16:54:39 $
 */
public class EditCategoryAction extends CategoryActionSupport implements ModelDriven {

	private Category category = new Category();
	
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
	 * 更新类别
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　更新主题类别
		if(!isPermisson("2_5")){
			setWarnMessage("您没有更新主题类别的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("更新主题类别");
		/* -------------------------------*/			
		
		if(logger.isInfoEnabled()){
			//logger.info("创建类别:"+category.getName());
		}
		try{
			Category tempCategory =categoryService.findCategoryByIdAndName(category.getId().intValue(), category.getName());
			if(tempCategory==null)
			{
				categoryService.editCategory(category);
			}
			else
			{
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
