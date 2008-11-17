package com.ntsky.bbs.web.webwork.action.view;

import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

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
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.web.webwork.action.view.ViewActionSupport;

/**
 * 检索信息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/26 13:42:30 $
 */
public class SearchAction extends ViewActionSupport {
	
	// 检索条件
	private String keyword;
	public void setKeyword(String keyword){
		this.keyword = keyword;
	}
	private String type;
	public void setType(String type){
		this.type = type;
	}
	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	private int time;
	public void setTime(int time){
		this.time = time;
	}
	private String way;
	public void setWay(String way){
		this.way = way;
	}
	private int status;
	// 应答数据
	private List topics;
	public List getTopics(){
		return this.topics;
	}	
	/**
	 * 检索信息
	 * 
	 * @return String 执行信息
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("执行检索......");
		}
		// 排序
		Map orderMap = new TreeMap();
		orderMap.put(sort,order);
		try{
			QueryResult queryResult = topicService.searchTopics(forumId, type, keyword, time, way, orderMap, new Pagination(getPaginationStart(),SystemConfig.getInstance().getIntPropertyValue(Symbols.PAGINATION,Symbols.PAGINATION_TOPIC)),status);
			topics = queryResult.getItems();
			setPagination(queryResult.getPagination());
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		// 全部论坛
		forums = ForumSingleton.getInstance().getForums();
		return SUCCESS;
    }
	
	/**
	 * 论坛列表
	 */
	private List forums;
	public List getForums() {
		return forums;
	}	
	
	/**
	 * 检索初始化
	 * @return
	 * @throws Exception
	 */
	public String doDefault() throws Exception{
		if(logger.isInfoEnabled()){
			logger.info("检索初始化......");
		}
		forums = ForumSingleton.getInstance().getForums();
		return SUCCESS;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getForumId() {
		return forumId;
	}
	public String getKeyword() {
		return keyword;
	}
	public int getStatus() {
		return status;
	}
	public int getTime() {
		return time;
	}
	public String getType() {
		return type;
	}
	public String getWay() {
		return way;
	}
	public void setForums(List forums) {
		this.forums = forums;
	}
	public void setTopics(List topics) {
		this.topics = topics;
	}
	
}
