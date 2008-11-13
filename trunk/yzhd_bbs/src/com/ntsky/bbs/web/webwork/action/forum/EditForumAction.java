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
 * @version $Revision: 1.2 $ $Date: 2008/11/05 16:54:39 $
 */
public class EditForumAction extends ForumActionSupport implements ModelDriven {

	private Forum forum = new Forum();
	private int branchId;
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	/**
	 * 更新论坛版块数据信息
	 * 
	 * @throws Exception
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　更新论坛
		if(!isPermisson("2_1")){
			setWarnMessage("您没有更新论坛的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("更新论坛");
		/* -------------------------------*/
		
		// 更新论坛版块信息
		if(logger.isInfoEnabled()){
			//logger.info("更新论坛["+forum.getName()+"]的信息.");
		}
		try{
			forumService.editForum(forum);
			setActionMessage("成功更新论坛['"+forum.getName()+"']的信息");
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		initForumTree();
		if(logger.isInfoEnabled()){
			//logger.info("论坛版块更新成功.....");
		}
		return SUCCESS;
	}
	
	public Object getModel() {
		return this.forum;
	}

	/**
	 * 初始论坛版块列表数据
	 */ 
	private void initForumTree() throws Exception {
		try{
			setForums(forumService.getForums());
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
	}	
	
}
