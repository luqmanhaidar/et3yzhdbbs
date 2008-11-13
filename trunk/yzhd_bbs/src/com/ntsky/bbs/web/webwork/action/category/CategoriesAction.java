package com.ntsky.bbs.web.webwork.action.category;

import java.util.List;

import com.opensymphony.xwork.ModelDriven;

import com.ntsky.bbs.domain.Category;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.memory.ForumSingleton;

/**
 * 主题类别管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.3 $ $Date: 2008/11/03 16:33:25 $
 */
public class CategoriesAction extends CategoryActionSupport {

	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	
	private Forum forum;
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	/**
	 * 主题类别管理
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　列表主题类别
		if(!isPermisson("2_5")){
			setWarnMessage("您没有列表主题类别的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("列表主题类别");
		/* -------------------------------*/		
		
		try{
			//if(forumId==0){
				//return SUCCESS;
			//}
			//setForum(ForumSingleton.getInstance().getForum(forumId));
			setCategories(categoryService.getCategories(-1));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}

}
