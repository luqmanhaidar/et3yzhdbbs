package com.ntsky.bbs.web.webwork.action.post;

import java.util.List;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.framework.util.HttpUtil;
import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.StringUtil;
import com.ntsky.framework.util.lumaqq.IPSeeker;

import com.ntsky.bbs.domain.Post;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.util.WebworkUtil;

/**
 * 删除贴子
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/11/08 13:10:22 $
 */
public class DeletePostAction extends PostActionSupport {

	private int postId; 
	public void setPostId(int postId){
		this.postId = postId;
	}
	
	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	
	/**
	 * 删除贴子
	 * 
	 * @return String 执行信息
	 */
	public String delete() throws Exception {
		if(logger.isInfoEnabled()){
			//logger.info("删除贴子['"+postId+"']....");
		}
		
		// ---------- 权限 ------------
		// 是否为论坛管理员
		if(super.getSessionUser().getUsername().equals(postService.getPost(postId).getUsername()))
		{
			if(super.isAccess("canDeleteOwnPost")==0){
				setWarnMessage("您没有删除自己帖子的权限.");
				return NO_PERMISSION;
			}
		// ---------------------------
		}
		else
		{
			if(!super.isForumManage(forumId)){
				setWarnMessage("您不是该论坛管理员,您没有删除帖子的权限.");
				return NO_PERMISSION;
			}		
			if(super.isAccess("canDelete")==0){
				setWarnMessage("您没有删除帖子的权限.");
				return NO_PERMISSION;
			}
		}
		try{
			postService.deletePost(postId);
			setForum(ForumSingleton.getInstance().getForum(forumId));
			if(logger.isInfoEnabled()){
				logger.info("论坛名称 : " + super.getForum().getName());
			}
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		if(logger.isInfoEnabled()){
			logger.info("删除贴子成功");
		}
		return SUCCESS;
    }
	
}
