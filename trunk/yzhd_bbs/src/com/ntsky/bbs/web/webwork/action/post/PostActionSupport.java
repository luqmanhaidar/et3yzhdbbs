package com.ntsky.bbs.web.webwork.action.post;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Post;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.service.CategoryService;
import com.ntsky.bbs.service.PostService;
import com.ntsky.bbs.service.TopicService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;
import com.opensymphony.webwork.interceptor.SessionAware;

/**
 * 贴子操作抽象类
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:34 $
 */
public abstract class PostActionSupport extends BasicActionSupport{
	
	protected PostService postService;
	
	public void setPostService(PostService postService){
		this.postService = postService;
	}
	
	protected TopicService topicService;
	
	public void setTopicService(TopicService topicService){
		this.topicService = topicService;
	}


	/**
	 * 贴子列表
	 */
	private List posts;
	public void setPosts(List posts){
		this.posts = posts;
	}
	public List getPosts(){
		return this.posts;
	}

	// 论坛数据
	private Forum forum;
	public void setForum(Forum forum){
		this.forum = forum;
	}
	public Forum getForum(){
		return this.forum;
	}
	
	private int topicId;
	public int getTopicId(){
		return super.getIntParameter(Symbols.PARA_TOPIC_ID);
	}
	
}
