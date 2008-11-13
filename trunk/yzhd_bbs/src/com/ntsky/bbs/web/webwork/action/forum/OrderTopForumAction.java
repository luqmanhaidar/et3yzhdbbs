package com.ntsky.bbs.web.webwork.action.forum;

import java.util.List;

import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 论坛信息Action
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:30 $
 */
public class OrderTopForumAction extends ForumActionSupport {

	/**
	 * 旧分支编号
	 */
	private int oldBranchId;
	public int getOldBranchId() {
		return oldBranchId;
	}
	public void setOldBranchId(int oldBranchId) {
		this.oldBranchId = oldBranchId;
	}	
	
	/**
	 * 分支编号
	 */
	private int branchId;
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	/**
	 * 更新顶层次序
	 * @throws Exception
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　顶层类别排序
		if(!isPermisson("2_3")){
			setWarnMessage("您没有顶层类别排序的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("顶层类别排序");
		/* -------------------------------*/
		
		if(logger.isInfoEnabled()){
			logger.info("更新旧的分支编号["+oldBranchId+"]成新分支编号["+branchId+"]");
		}
		// 初始化论坛列表
		try{
			if(!super.forumService.updateForumBranch(oldBranchId,branchId)){
				setWarnMessage("分支ID : ["+branchId+"]已存在,请使用其它ID");
			}
			setForums(forumService.getForums(0));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		// 初始化论坛列表
		init();
		return SUCCESS;
	}
	
	/**
	 * 创建论坛版块信息
	 * 
	 * @return String 执行信息
	 */
	public String init() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　顶层类别排序
		if(!isPermisson("2_3")){
			setWarnMessage("您没有顶层类别排序的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("顶层类别排序");
		/* -------------------------------*/
		
		if(logger.isInfoEnabled()){
			logger.info("初始论坛的顶层树");
		}		
		try{			
			setForums(forumService.getForums(0));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}

	
}
