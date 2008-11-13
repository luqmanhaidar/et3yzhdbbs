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
import com.ntsky.bbs.exception.PostException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.BeanUtil;
import com.ntsky.bbs.util.memory.ForumSingleton;

/**
 * 创建主题信息Action
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:34 $
 */
public class CreatePostAction extends PostActionSupport implements ModelDriven,Preparable {

	private Post post = new Post();
	
	/**
	 * 创建统计信息
	 * 
	 * @return String 执行信息
	 */
	public String execute() throws Exception {
		// ---------- 权限 ------------
		if(super.isAccess("canReplyPost")==0){
			return NO_PERMISSION;
		}		
		// ---------------------------
		
		if(logger.isInfoEnabled()){
			logger.info("创建回复信息");
		}
		try{
			post.setIp(HttpUtil.getRemoteAddr(super.getRequest()));
			post.setUserId(super.getSessionUser().getId().intValue());
			post.setUsername(super.getSessionUser().getUsername());
			postService.createPost((Post)BeanUtil.format(post));
		}
		catch(PostException pe){
			super.setWarnMessage(pe.getMessage());
			return super.WARN;
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}

		if(logger.isInfoEnabled()){
			logger.info("发表回复['"+post.getTitle()+"']成功");
		}
		return SUCCESS;
    }

	/**
	 * 主题数据
	 */
	private Topic topic;
	public Topic getTopicObj(){
		return this.topic;
	}
	
	public Post getPost(){
		return this.post;
	}
	
	/**
	 * 回复主题初始化
	 * @return
	 * @throws Exception
	 */
	public String doDefault() throws Exception{
		if(logger.isInfoEnabled()){
			logger.info("回复主题初始化...");
		}
		// ---------- 权限 ------------
		if(super.isAccess("canReplyPost")==0){
			return NO_PERMISSION;
		}		
		// ---------------------------
		int qutoeId = getIntParameter("qutoeId");
		if( qutoeId != 0 ){
			post = postService.getPost(new Long(qutoeId));
		}
		topic  = topicService.getTopic(getIntParameter(Symbols.PARA_TOPIC_ID));
		if(logger.isInfoEnabled()){
			logger.info("主题 : " + topic.getTitle());
		}
		return SUCCESS;
	}
	
	public void prepare() throws Exception {
		setForum(ForumSingleton.getInstance().getForum(getIntParameter(Symbols.PARA_FORUM_ID)));
	}
	
	public Object getModel() {
		return this.post;
	}
	
}
