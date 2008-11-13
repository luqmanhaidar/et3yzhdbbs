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
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:24 $
 */
public class AdminManageTopicAction extends TopicActionSupport implements Preparable {

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
	
	/**
	 * 主题是否置顶
	 * 
	 * @return String 执行信息
	 */
	public String isTop() throws Exception {
		
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if(!super.isForumManage(super.getIntParameter(Symbols.PARA_FORUM_ID))){
			if(is==1){
				setWarnMessage("您不是该论坛管理员,您没有置顶帖子的权限.");
			}
			else{
				setWarnMessage("您不是该论坛管理员,您没有取消置顶的权限.");
			}
			return NO_PERMISSION;
		}		
		if(super.isAccess("canTopTopic")==0){
			if(is==1){
				setWarnMessage("您没有置顶帖子的权限.");
			}
			else{
				setWarnMessage("您不是该论坛管理员,您没有取消置顶的权限.");
			}			
			return NO_PERMISSION;
		}
		// ---------------------------		
		
		if(logger.isInfoEnabled()){
			if(is==1){
				logger.info("设置贴子置顶");
			}
			else{
				logger.info("取消贴子置顶");
			}
		}
		try{
			super.topicService.updateTopicIsTop(topicId,is);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}	    
	    return SUCCESS;
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
		if(logger.isInfoEnabled()){
			if(is==1){
				logger.info("设置多个贴子置顶");
			}
			else{
				logger.info("取消多个贴子置顶");
			}
		}
		
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if(!super.isForumManage(super.getIntParameter(Symbols.PARA_FORUM_ID))){
			if(is==1){
				setWarnMessage("您不是该论坛管理员,您没有置顶多个帖子的权限.");
			}
			else{
				setWarnMessage("您不是该论坛管理员,您没有取消多个帖子置顶的权限.");
			}
			return NO_PERMISSION;
		}		
		if(super.isAccess("canTopTopic")==0){
			if(is==1){
				setWarnMessage("您没有置顶多个帖子的权限.");
			}
			else{
				setWarnMessage("您不是该论坛管理员,您没有取消多个帖子置顶的权限.");
			}			
			return NO_PERMISSION;
		}
		// ---------------------------
		
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
	
	/**
	 * 主题是否置顶
	 * 
	 * @return String 执行信息
	 */
	public String status() throws Exception {
		
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if( status==1 ) {
			if(!super.isForumManage(getIntParameter(Symbols.PARA_FORUM_ID))){
				setWarnMessage("您不是该论坛管理员,您没有设置精华帖的权限.");
				return NO_PERMISSION;
			}		
			if(super.isAccess("canBestTopic")==0){
				setWarnMessage("您没有设置精华帖的权限.");
			}
		}
		else{
			if(!super.isForumManage(getIntParameter(Symbols.PARA_FORUM_ID))){
				setWarnMessage("您不是该论坛管理员,您没有锁定帖子的权限.");
				return NO_PERMISSION;
			}		
			if(super.isAccess("canBestTopic")==0){
				setWarnMessage("您没有锁定帖子的权限.");
			}
		}
		// ---------------------------				
		
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
			topicService.updateTopicStatus(topicId,status);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}	    
	    return SUCCESS;
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
		
		// ---------- 权限 ------------
		// 是否为论坛管理员
		/*if( status==1 ) {
			if(!super.isForumManage(getIntParameter(Symbols.PARA_FORUM_ID))){
				setWarnMessage("您不是该论坛管理员,您没有设置精华帖的权限.");
				return NO_PERMISSION;
			}		
			if(super.isAccess("canBestTopic")==0){
				setWarnMessage("您没有设置精华帖的权限.");
			}
		}
		else{
			if(!super.isForumManage(getIntParameter(Symbols.PARA_FORUM_ID))){
				setWarnMessage("您不是该论坛管理员,您没有锁定帖子的权限.");
				return NO_PERMISSION;
			}		
			if(super.isAccess("canBestTopic")==0){
				setWarnMessage("您没有锁定帖子的权限.");
			}
		}*/
		// ---------------------------		

		try{
			topicService.moveTopic(ids,forumId,newForumId);
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
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if( status==1 ) {
			if(!super.isForumManage(getIntParameter(Symbols.PARA_FORUM_ID))){
				setWarnMessage("您不是该论坛管理员,您没有设置精华帖的权限.");
				return NO_PERMISSION;
			}		
			if(super.isAccess("canBestTopic")==0){
				setWarnMessage("您没有设置精华帖的权限.");
			}
		}
		else{
			if(!super.isForumManage(getIntParameter(Symbols.PARA_FORUM_ID))){
				setWarnMessage("您不是该论坛管理员,您没有锁定帖子的权限.");
				return NO_PERMISSION;
			}		
			if(super.isAccess("canBestTopic")==0){
				setWarnMessage("您没有锁定帖子的权限.");
			}
		}
		// ---------------------------		
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
	
	private Forum forum;
	public Forum getForum(){
		return this.forum;
	}	
	public void prepare() throws Exception {
		forum = ForumSingleton.getInstance().getForum(super.getIntParameter(Symbols.PARA_FORUM_ID));
	}
}
