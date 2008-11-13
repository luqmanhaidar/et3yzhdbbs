package com.ntsky.bbs.web.webwork.action.topic;

import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork.ModelDriven;

import com.ntsky.framework.util.HttpUtil;
import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.StringUtil;
import com.ntsky.framework.util.lumaqq.IPSeeker;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.domain.Category;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 主题列表
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/23 03:20:47 $
 */
public class TopicListAction extends TopicActionSupport {

	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}	
	
	private Forum forum;
	public Forum getForum(){
		return this.forum;
	}
	
	private int status;
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
	
	// 论坛数据
	private List forums;
	public List getForums() {
		return forums;
	}
	
	private List categories;
	public List getCategories(){
		return this.categories;
	}	
	
	private int categoryId = -1;
	public int getCategoryId(){
		return this.categoryId;
	}
	
	private Category category;
	public Category getCategory(){
		return this.category;
	}
	
	/**
	 * 列表主题信息
	 * 
	 * @return String 执行信息
	 */
	public String execute() throws Exception {

		if(logger.isInfoEnabled()){
			if(status==1){
				logger.info("根据论坛编号列表精华贴");
			}
			else{
				logger.info("根据论坛编号列表主题");
			}
		}
		// 查看精华贴
		if(status==1){
			if(super.isAccess("canViewBestTopic")==0){
				return NO_PERMISSION;
			}
		}
		try{
			String categoryStr = HttpUtil.getParameter(getRequest(),"categoryId");
			if(!("".endsWith(categoryStr))){
				categoryId = Integer.parseInt(categoryStr);
			}
			// 设置排序信息
			Map orderMap = new TreeMap();
			orderMap.put("isTop","desc");
			if(super.order == null){
				orderMap.put("lastPostTime","desc");
			}
			else{
				orderMap.put(sort,order);
			}
			QueryResult queryResult = topicService.getTopics(forumId,categoryId,orderMap,status,new Pagination(getPaginationStart(),SystemConfig.getInstance().getIntPropertyValue(Symbols.PAGINATION,Symbols.PAGINATION_TOPIC)));
			setTopics(queryResult.getItems());
			setPagination(queryResult.getPagination());
			
			// 论坛信息
			forum = ForumSingleton.getInstance().getForum(forumId);
			forums = ForumSingleton.getInstance().getForums();
			
			// 分类列表
			categories = categoryService.getCategories(forumId);
			
			if(categoryId!=-1){
				category = categoryService.getCategory(categoryId);
			}
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
	    return SUCCESS;
    }
	
}
