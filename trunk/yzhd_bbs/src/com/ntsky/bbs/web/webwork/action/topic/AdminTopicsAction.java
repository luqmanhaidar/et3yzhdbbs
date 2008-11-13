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
 * 管理员列表主题
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:24 $
 */
public class AdminTopicsAction extends TopicActionSupport {

	// 参数
	private int forumId;
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
	
	/**
	 * 管理员列表主题
	 * <pre>
	 * 	执行成功迁移到 forum.ftl
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
			// 当前论坛数据
			forum = ForumSingleton.getInstance().getForum(forumId);
			// 全部论坛
			setForums(ForumSingleton.getInstance().getForums());
			
			// 主题数据
			Map orderMap = new TreeMap();
			orderMap.put("isTop","desc");
			if(super.order == null){
				orderMap.put("lastPostTime","desc");
			}
			else{
				orderMap.put(sort,order);
			}
			QueryResult queryResult = topicService.getTopics(forumId,orderMap,new Pagination(getPaginationStart(),SystemConfig.getInstance().getIntPropertyValue(Symbols.PAGINATION,Symbols.PAGINATION_TOPIC)));
			setTopics(queryResult.getItems());
			setPagination(queryResult.getPagination());
			
			// 分类列表
			categories = categoryService.getCategories(forumId);
			
			if(logger.isInfoEnabled()){
				logger.info("管理员列表主题成功,并返回到主题列表页面");
			}			
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}		
		return SUCCESS;
    }

	
}
