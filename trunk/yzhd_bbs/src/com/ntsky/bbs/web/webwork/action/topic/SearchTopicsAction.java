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

import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 检索主题信息
 * <ul>
 * 	<li>edit -- 检索帮助</li>
 * </ul>
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:24 $
 */
public class SearchTopicsAction extends TopicActionSupport {

	// 论坛编号
	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	
	// 状态 1，精华贴 2，锁定贴
	private int status;
	public void setStatus(int status){
		this.status = status;
	}
	
	/**
	 * 修改主题(更新主题数据)
	 * 
	 * @return String 执行信息
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("列表主题信息");
		}
		try{
			// 设置排序信息
			Map orderMap = new TreeMap();
			orderMap.put("isTop","desc");
			if(super.order == null){
				orderMap.put("lastPostTime","desc");
			}
			else{
				orderMap.put(sort,order);
			}
			QueryResult queryResult = topicService.getTopics(forumId,orderMap,new Pagination(getIntParameter("start")));
			setTopics(queryResult.getItems());
			setPagination(queryResult.getPagination());
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
	    
	    return SUCCESS;
    }
	
}
