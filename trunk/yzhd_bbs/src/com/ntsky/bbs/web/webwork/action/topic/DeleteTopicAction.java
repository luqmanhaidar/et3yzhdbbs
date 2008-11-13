package com.ntsky.bbs.web.webwork.action.topic;

import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 删除主题
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/11/08 13:10:22 $
 */
public class DeleteTopicAction extends TopicActionSupport implements Preparable{

	private int topicId;
	public void setTopicId(int topicId){
		this.topicId = topicId;
	}
	public int getTopicId(){
		return this.topicId;
	}

	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	public int getForumId(){
		return this.forumId;
	}
	
	/**
	 * 将主题丢到垃圾箱
	 * 
	 * @return String 执行信息
	 */
	public String trash() throws Exception {
		try{
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if(super.getSessionUser().getUsername().equals(topicService.getTopic(topicId).getUsername()))
		{
			topicService.trashTopic(topicId);
		}
		else
		{
			if(!super.isForumManage(forumId)){
				setWarnMessage("您不是该论坛管理员,您没有删除主题的权限.");
				return NO_PERMISSION;
			}		
			if(super.isAccess("canDelete")==0){
				setWarnMessage("您没有删除主题的权限.");
				return NO_PERMISSION;
			}
		// ---------------------------
		
			if(logger.isInfoEnabled()){
				logger.info("删除主题丢到垃圾箱");
			}
		}

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
	 * 将多个主题丢到垃圾箱
	 * 
	 * @return String 执行信息
	 */
	public String trashMore() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("删除多个主题丢到垃圾箱)");
		}
		
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if(!super.isForumManage(forumId)){
			setWarnMessage("您不是该论坛管理员,您没有删除主题的权限.");
			return NO_PERMISSION;
		}		
		if(super.isAccess("canDelete")==0){
			setWarnMessage("您没有删除主题的权限.");
			return NO_PERMISSION;
		}
		// ---------------------------
		
		try{
			for (int i = 0; i < ids.length; i++) {
				topicService.trashTopic(ids[i]);
			}	
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
	    return SUCCESS;
    }	
	
	// 论坛数据
	private Forum forum;
	public Forum getForum(){
		return this.forum;
	}
	public void prepare() throws Exception {
		forum = ForumSingleton.getInstance().getForum(super.getIntParameter(Symbols.PARA_FORUM_ID));
	}
	
}
