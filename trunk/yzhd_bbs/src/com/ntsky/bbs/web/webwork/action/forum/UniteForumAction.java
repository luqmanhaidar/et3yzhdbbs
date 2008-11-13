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

/**
 * 合并论坛
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:30 $
 */
public class UniteForumAction extends ForumActionSupport implements Preparable {

	/**
	 * 源论坛
	 */
	private int sourceForum;
	public int getSourceForum() {
		return sourceForum;
	}
	public void setSourceForum(int sourceForum) {
		this.sourceForum = sourceForum;
	}

	/**
	 * 目标论坛
	 */
	private int toForum;
	public int getToForum() {
		return toForum;
	}
	public void setToForum(int toForum) {
		this.toForum = toForum;
	}
	/**
	 * 初始化论坛列表信息
	 * @throws Exception
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　合并论坛
		if(!isPermisson("2_4")){
			setWarnMessage("您没有合并论坛的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("合并论坛");
		/* -------------------------------*/
		
		if(logger.isDebugEnabled()){
			logger.debug("合并论坛 ：源论坛["+sourceForum+"],目标论坛["+toForum+"]");
		}
		logger.warn("合并论坛 ：源论坛["+sourceForum+"],目标论坛["+toForum+"]");
		forumService.uniteForum(sourceForum, toForum);
		
		super.setActionMessage("合并论坛成功");
		return SUCCESS;
	}
	
	/**
	 * 合并版块
	 * 
	 * @return String 执行信息
	 */
	public String doInit() throws Exception {
	
		/* ---------- 权限判断 ------------ */
		//　合并论坛
		if(!isPermisson("2_4")){
			setWarnMessage("您没有合并论坛的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("合并论坛");
		/* -------------------------------*/		
		
		if(logger.isInfoEnabled()){
			logger.info("合并版块信息");
		}
		
		return SUCCESS;
	}
	
	public void prepare() throws Exception {
		setForums(super.forumService.getForums());
	}
	
}

