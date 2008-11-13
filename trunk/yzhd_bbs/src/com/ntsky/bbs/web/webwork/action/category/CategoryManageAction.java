package com.ntsky.bbs.web.webwork.action.category;

import java.util.List;

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
 * @version $Revision: 1.2 $ $Date: 2008/11/04 16:27:21 $
 */
public class CategoryManageAction extends CategoryActionSupport {

	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	public int getForumId(){
		return this.forumId;
	}	
	
	/**
	 * 主题类别管理
	 */
	public String execute() throws Exception {
		try{
			categoryService.getCategories(forumId);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}

	private List forums;
	public List getForums(){
		return forums;
	}
	/**
	 * 类别目录（论坛列表）
	 * @return success
	 * @throws Exception
	 */
	public String doMenu() throws Exception {
		
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
		
		forums = ForumSingleton.getInstance().getForums();
		
		return "menu";
	}
	
	private int categoryId;
	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}
	/**
	 * 删除分类
	 * @return
	 * @throws Exception
	 */
	public String doDelete() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　删除主题类别
		if(!isPermisson("2_5")){
			setWarnMessage("您没有删除主题类别的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("删除主题类别");
		/* -------------------------------*/		
		
		try{
			categoryService.deleteCategory(categoryId);			
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return super.DELETE;
	}

	private Category category = null;
	public Category getCategory(){
		return this.category;
	}
	
	private Forum forum;
	public Forum getForum(){
		return this.forum;
	}		
	/**
	 * 修改类别(查找类别)
	 */
	public String doEdit() throws Exception {	
		
		/* ---------- 权限判断 ------------ */
		//　修改主题类别
		if(!isPermisson("2_5")){
			setWarnMessage("您没有修改主题类别的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("修改主题类别");
		/* -------------------------------*/				
		
		try{
			category = categoryService.getCategory(categoryId);
			// 论坛数据
			forum = ForumSingleton.getInstance().getForum(forumId);
			// 类别列表
			setCategories(categoryService.getCategories(-1));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return super.EDIT;
	}	
	
}
