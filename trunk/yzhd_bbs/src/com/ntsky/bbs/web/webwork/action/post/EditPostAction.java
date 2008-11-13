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

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Post;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.BeanUtil;
import com.ntsky.bbs.util.WebworkUtil;
import com.ntsky.bbs.util.memory.ForumSingleton;

/**
 * 修改贴子
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/11/09 09:32:02 $
 */
public class EditPostAction extends PostActionSupport implements ModelDriven {

	private Post post = new Post();
	
	public Object getModel() {
		return post;
	}
	
	public Post getPost() {
		return post;
	}
	
	/**
	 * 修改贴子(更新贴子信息)
	 * 
	 * @return String 执行信息
	 */
	public String doUpdate() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("更新贴子['"+post.getTitle()+"']....");
		}
		// ---------- 权限 ------------
		if(super.isAccess("canEditPost")==0){
			return NO_PERMISSION;
		}	
		else{
			if(logger.isInfoEnabled()){
				logger.info("判断帖子是否该用户发表");
			}
			// 如果该帖子不是原用户发表
			System.out.println(post.getUserId());
			System.out.println(super.getSessionUser().getId().intValue());
			if(post.getUserId()!=super.getSessionUser().getId().intValue()){
				if(logger.isInfoEnabled()){
					logger.info("如果不是该用户发表的帖子,判断是否是有权限用户编辑帖子");
				}
				if(super.isAccess("canEditOther")==0){
					setWarnMessage("您没有修改帖子[' "+post.getTitle()+" ']的权限.");
					return NO_PERMISSION;
				}
			}
		}
		// ---------------------------
		try{
			postService.editPost((Post)BeanUtil.format(post));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		
		// 论坛数据
		forum = ForumSingleton.getInstance().getForum(post.getForumId());
		
		if(logger.isInfoEnabled()){
			logger.info("更新贴子成功...");
		}
		return SUCCESS;
    }
	

	private int postId; 
	public void setPostId(int postId){
		this.postId = postId;
	}
	
	private Forum forum = null;
	public Forum getForum(){
		return this.forum;
	}
	
	private Topic topic = null;
	public Topic getTopicObj(){
		return this.topic;
	}
	
	/**
	 * 修改(根据编号查询)
	 * @return
	 * @throws Exception
	 */
	public String doEdit() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("修改帖子...");
		}		

		try{
			post = postService.getPost(postId);
			
			// ---------- 权限 ------------
			if(super.isAccess("canEditPost")==0){
				return NO_PERMISSION;
			}	
			else{
				if(logger.isInfoEnabled()){
					logger.info("判断帖子是否该用户发表");
				}
				// 如果该帖子不是原用户发表
				if(post.getUserId()!=super.getSessionUser().getId().intValue()){
					if(logger.isInfoEnabled()){
						logger.info("如果不是该用户发表的帖子,判断是否是有权限用户编辑帖子");
					}
					if(super.isAccess("canEditOther")==0){
						setWarnMessage("您没有修改帖子[' "+post.getTitle()+" ']的权限.");
						return NO_PERMISSION;
					}
				}
			}
			
			// 制定论坛数据
			forum = ForumSingleton.getInstance().getForum(post.getForumId());
			// 主题信息
			topic = super.topicService.getTopic(post.getTopicId());
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	

}
