package com.ntsky.bbs.web.webwork.action.topic;

import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.framework.util.HttpUtil;
import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.StringUtil;
import com.ntsky.framework.util.lumaqq.IPSeeker;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 管理员管理主题
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.3 $ $Date: 2008/10/23 09:28:56 $
 */
public class SystemManageTopicAction extends TopicActionSupport {

	private int topicId;
	public void setTopicId(int topicId){
		this.topicId = topicId;
	}	
	public int getTopicId(){
		return this.topicId;
	}
	
	// 是/否
	private int is;
	public void setIs(int is){
		this.is = is;
	}

	private int[] ids;
	public void setId(int[] ids){
		this.ids = ids;
	}
	/**
	 * 主题是否置顶
	 * 
	 * @return String 执行信息
	 */
	public String isTopMore() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　置顶帖子
		if(!isPermisson("4_1")){
			if(is==1){
				setWarnMessage("您没有设置多个贴子置顶的权限.");
			}
			else{
				setWarnMessage("您没有取消多个贴子置顶的权限.");
			}
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		if(is==1){
			recordActLog("设置多个贴子置顶");
		}
		else{
			recordActLog("取消多个贴子置顶");;
		}
		/* -------------------------------*/		
		
		if(logger.isInfoEnabled()){
			if(is==1){
				logger.info("设置多个贴子置顶");
			}
			else{
				logger.info("取消多个贴子置顶");
			}
		}
		
		try{
			for (int i = 0; i < ids.length; i++) {
				super.topicService.updateTopicIsTop(ids[i],is);
			}
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}	    
	    return SUCCESS;
    }	
	
	private int status;
	public void setStatus(int status){
		this.status = status;
	}
	
	// 新论坛编号
	private int newForumId;
	public void setNewForumId(int newForumId){
		this.newForumId = newForumId;
	}
	
	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	public int getForumId(){
		return this.forumId;
	}
	
	/**
	 * 移动主题
	 * 
	 * @return String 执行信息
	 */
	public String move() throws Exception {
		
		if(logger.isInfoEnabled()){
			logger.info("移动帖子...");
		}

		try{
			topicService.moveTopic(ids,forumId,is);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}	    
	    return SUCCESS;
    }
	
	
	/**
	 * 主题是否置顶
	 * 
	 * @return String 执行信息
	 */
	public String statusMore() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		if(!isPermisson("4_1")){
			if( status==1 ){
				setWarnMessage("您没有设置精华帖的权限.");
			}
			else{
				setWarnMessage("您没有锁定帖子的权限.");
			}
			return NO_PERMISSION;
		}
		/* ------------------------------ */
		/* ------------ 记录日志 -----------*/
		if(status==1){
			recordActLog("设置精华帖");
		}
		else{
			recordActLog("锁定帖子");;
		}
		/* -------------------------------*/	
		
		if(logger.isInfoEnabled()){
			if(status==1){
				logger.info("设置精华帖...");
			}
			else{
				if(status==2){
					logger.info("锁定帖子...");
				}	
			}
		}

		try{
			for (int i = 0; i < ids.length; i++) {
				topicService.updateTopicStatus(ids[i],status);
			}
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}	    
	    return SUCCESS;
    }
	
}
