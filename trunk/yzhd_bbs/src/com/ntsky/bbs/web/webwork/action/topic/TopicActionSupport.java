package com.ntsky.bbs.web.webwork.action.topic;

import java.util.List;

import com.ntsky.bbs.domain.Category;
import com.ntsky.bbs.service.CategoryService;
import com.ntsky.bbs.service.PollService;
import com.ntsky.bbs.service.TopicService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

/**
 * 主题操作顶层类
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.3 $ $Date: 2008/11/03 09:09:10 $
 */
public abstract class TopicActionSupport extends BasicActionSupport {
	
	protected TopicService topicService;
	protected Category category;
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public void setTopicService(TopicService topicService){
		this.topicService = topicService;
	}
	
	protected CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService){
		this.categoryService = categoryService;
	}
	
	protected PollService pollService;
	public void setPollService(PollService pollService){
		this.pollService = pollService;
	}
	
	protected List topics;
	public void setTopics(List topics){
		this.topics = topics;
	}
	public List getTopics(){
		return this.topics;
	}	
	
	protected List posts;
	public List getPosts(){
		return posts;
	}
	public CategoryService getCategoryService() {
		return categoryService;
	}
}
