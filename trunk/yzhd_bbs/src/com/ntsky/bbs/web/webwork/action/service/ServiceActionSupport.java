package com.ntsky.bbs.web.webwork.action.service;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.service.PollService;
import com.ntsky.bbs.service.TopicService;
import com.ntsky.bbs.service.CategoryService;
import com.ntsky.bbs.service.UserService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

/**
 * 外部服务抽象类
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:24 $
 */
public abstract class ServiceActionSupport extends BasicActionSupport {
	
	protected TopicService topicService;
	public void setTopicService(TopicService topicService){
		this.topicService = topicService;
	}
	
	protected List topics;
	public void setTopics(List topics){
		this.topics = topics;
	}
	public List getTopics(){
		return this.topics;
	}	
	
	protected UserService userService;
	
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	// 设置Session
	public void setUserSession(Map session,User user){
		session.put(Symbols.SESSION_USER, user);
	}
}
