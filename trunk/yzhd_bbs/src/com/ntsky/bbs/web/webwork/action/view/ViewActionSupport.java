package com.ntsky.bbs.web.webwork.action.view;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.service.UserService;
import com.ntsky.bbs.service.PostService;
import com.ntsky.bbs.service.ForumService;
import com.ntsky.bbs.service.TopicService;
import com.ntsky.bbs.service.MessageService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;
import com.opensymphony.webwork.interceptor.SessionAware;

/**
 * 主体操作顶层类
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */
public abstract class ViewActionSupport extends BasicActionSupport {
	
	// 论坛业务对象
	protected ForumService forumService;
	public void setForumService(ForumService forumService){
		this.forumService = forumService;
	}	

	// 用户业务对象
	protected UserService userService;
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	// 主题业务对象
	protected TopicService topicService;
	public void setTopicService(TopicService topicService){
		this.topicService = topicService;
	}

	// 贴子业务对象
	protected PostService postService;
	public void setPostService(PostService postService){
		this.postService = postService;
	}

	// 消息对象
	protected MessageService messageService;
	public void setMessageService(MessageService messageService){
		this.messageService = messageService;
	}
	
}
