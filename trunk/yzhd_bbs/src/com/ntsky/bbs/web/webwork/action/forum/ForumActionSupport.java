package com.ntsky.bbs.web.webwork.action.forum;

import java.util.List;

import com.ntsky.bbs.service.CategoryService;
import com.ntsky.bbs.service.ForumService;
import com.ntsky.bbs.service.TopicService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

public abstract class ForumActionSupport extends BasicActionSupport{
	
	/**
	 * 论坛数据
	 */
	private List forums;
	public void setForums(List forums){
		this.forums = forums;
	}
	public List getForums() {
		return forums;
	}	
	
	protected ForumService forumService;
	public void setForumService(ForumService forumService){
		this.forumService = forumService;
	}
	
	protected TopicService topicService;
	public void setTopicService(TopicService topicService){
		this.topicService = topicService;
	}	
	
	protected CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService){
		this.categoryService = categoryService;
	}
	
}
