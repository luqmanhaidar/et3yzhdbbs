package com.ntsky.bbs.web.webwork.action.topic;

import java.util.List;

import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
/**
 * 管理员列表主题
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/19 17:42:00 $
 */
public class NewlyTopicsAction extends TopicActionSupport {

	// 参数
	private int forumId;
	private int isNewLy;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}	
	
	// 应答数据
	private List topics;
	public void setTopics(List topics){
		this.topics = topics;
	}
	public List getTopics(){
		return this.topics;
	}		
	
	private Forum forum;
	public Forum getForum(){
		return this.forum;
	}
	
	private List categories;
	public List getCategories(){
		return this.categories;
	}	

	private List forums;
	public void setForums(List forums){
		this.forums = forums;
	}
	public List getForums() {
		return forums;
	}	
	
	/******************/
	// 检索条件
	private String keyword;
	public void setKeyword(String keyword){
		this.keyword = keyword;
	}
	private String type;
	public void setType(String type){
		this.type = type;
	}
	private int time;
	public void setTime(int time){
		this.time = time;
	}
	private String way;
	public void setWay(String way){
		this.way = way;
	}
	/******************/
	
	/**
	 * 管理员列表主题
	 * <pre>
	 * 	执行成功迁移到 forum.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {


		try{
			if(isNewLy == 1)
			{
				topics =topicService.getNewlyTopics(0, 20);
			}
			if(isNewLy == 0)
			{
				topics = topicService.getLastPostTopics(0, 20);
			}
		
			
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
	    return SUCCESS;
    }
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public int getIsNewLy() {
		return isNewLy;
	}
	public void setIsNewLy(int isNewLy) {
		this.isNewLy = isNewLy;
	}

	
}
