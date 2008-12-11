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
 * @version $Revision: 1.3 $ $Date: 2008/10/26 13:42:29 $
 */
public class SystemTopicsAction extends TopicActionSupport {

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
		if(type == null)
		{
			type="title";
		}
		if(sort == null)
		{
			sort="dateCreated";
		}
		if(order == null)
		{
			order="desc";
		}
		if(keyword == null)
		{
			keyword="";
		}
		System.out.println("time: "+time );
		System.out.println("way: "+way );
		/* ---------- 权限判断 ------------ */
		//　列表帖子
		if(!isPermisson("4_1")){
			setWarnMessage("您没有列表帖子的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("列表帖子");
		/* -------------------------------*/
		
		if(logger.isInfoEnabled()){
			logger.info("管理员列表全部主题信息...");
		}
		try{
			// 当前论坛数据
			forum = ForumSingleton.getInstance().getForum(forumId);
			// 全部论坛
			setForums(ForumSingleton.getInstance().getForums());
			
			// 主题数据
			Map orderMap = new TreeMap();
			orderMap.put(sort,order);
			QueryResult queryResult = topicService.searchTopics(forumId, type, keyword, time, way, orderMap, new Pagination(getPaginationStart(),SystemConfig.getInstance().getIntPropertyValue(Symbols.PAGINATION,Symbols.PAGINATION_TOPIC)),2,1);
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
