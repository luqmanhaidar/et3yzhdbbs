package com.ntsky.bbs.web.webwork.action.forum;

import java.util.List;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.memory.ForumSingleton;

/**
 * 论坛信息Action
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:30 $
 */
public class CreateForumAction extends ForumActionSupport implements ModelDriven{

	private Forum modelForum = new Forum();
	
	/**
	 * 初始化论坛列表信息
	 * @throws Exception
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　创建论坛
		if(!isPermisson("2_1")){
			setWarnMessage("您没有创建论坛的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("创建论坛");
		/* -------------------------------*/
		
		if(logger.isInfoEnabled()){
			logger.info("创建版块['"+modelForum.getName()+"']信息");
		}		
		try{
			// 创建论坛
			forumService.createForum(modelForum);
			// 初始化论坛列表
			initForumTree();
			setActionMessage("添加论坛版块 ['"+modelForum.getName()+"'] 成功");
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	/**
	 * 初始论坛版块列表数据
	 */ 
	private void initForumTree() throws Exception {
		try{
			// 刷新内存
			super.setForums(forumService.getForums());
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
	}
	
	public Object getModel() {
		return this.modelForum;
	}
	
}
