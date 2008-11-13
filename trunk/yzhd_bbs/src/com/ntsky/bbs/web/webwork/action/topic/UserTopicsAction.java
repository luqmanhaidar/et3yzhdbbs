package com.ntsky.bbs.web.webwork.action.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Announcement;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Help;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.memory.ForumSingleton;
/**
 * 用户发表的主题列表
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/20 18:38:19 $
 */
public class UserTopicsAction extends TopicActionSupport {
	
	// 应答数据
	private List topics;
	public void setTopics(List topics){
		this.topics = topics;
	}
	public List getTopics(){
		return this.topics;
	}		
	
	private List forums;
	public void setForums(List forums){
		this.forums = forums;
	}
	public List getForums() {
		return forums;
	}
	
	private int forumId;
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	
	/**
	 * 用户发表的主题列表
	 * <pre>
	 * 	执行成功迁移到 userTopics.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("管理员列表全部主题信息...");
		}
		
		if(super.isAccess("canViewForum")==0){
			return NO_PERMISSION;
		}
		
		try{		
			// 主题数据
			Map orderMap = new TreeMap();
			orderMap.put("dateCreated","desc");
			QueryResult queryResult = topicService.getUserTopics(super.getSessionUser().getUsername(),forumId,super.getWd(),orderMap,new Pagination(getPaginationStart(),SystemConfig.getInstance().getIntPropertyValue(Symbols.PAGINATION,Symbols.PAGINATION_TOPIC)));
			setTopics(queryResult.getItems());
			setPagination(queryResult.getPagination());
			System.out.println("fdafadsfdsafads  "+topics.size());
			// 全部论坛
			setForums(ForumSingleton.getInstance().getForums());
			
			if(logger.isInfoEnabled()){
				logger.info("用户列表自己发表的主题成功,返回到主题列表页面");
			}			
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}		
		return SUCCESS;
    }

	
}
