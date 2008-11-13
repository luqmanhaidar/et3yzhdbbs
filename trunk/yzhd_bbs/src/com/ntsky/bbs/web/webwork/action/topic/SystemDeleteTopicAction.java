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
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:24 $
 */
public class SystemDeleteTopicAction extends TopicActionSupport {

	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	public int getForumId(){
		return this.forumId;
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
		
		/* ---------- 权限判断 ------------ */
		//　删除多个主题
		if(!isPermisson("4_1")){
			setWarnMessage("您没有删除多个主题的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("删除多个主题");
		/* -------------------------------*/
		
		if(logger.isInfoEnabled()){
			logger.info("删除多个主题丢到垃圾箱)");
		}
		
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
	
}
