package com.ntsky.bbs.web.webwork.action.topic;

import java.util.List;

import org.springframework.web.servlet.support.RequestContext;

import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.framework.upload.Request;
import com.opensymphony.webwork.ServletActionContext;
/**
 * 管理员列表主题
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/27 02:29:22 $
 */
public class UserTopicAction extends TopicActionSupport {

	// 参数
	private String username;

	// 应答数据
	private List topics;
	public void setTopics(List topics){
		this.topics = topics;
	}
	public List getTopics(){
		return this.topics;
	}		
	
	
	/******************/
	
	public String getUsername() {
		return username;
	}
	/**
	 * 管理员列表主题
	 * <pre>
	 * 	执行成功迁移到 forum.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {


		try{
			String username =new String(getParameter("username").getBytes("iso-8859-1"));
			QueryResult queryResult =topicService.findTopicsByUser(username, new Pagination(getIntParameter("start")));
			setTopics(queryResult.getItems());
			setPagination(queryResult.getPagination());

		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
	    return SUCCESS;
    }

	
}
