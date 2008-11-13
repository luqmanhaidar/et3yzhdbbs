package com.ntsky.bbs.web.webwork.action.post;

import java.util.List;
import java.util.Date;
import java.util.Map;

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
import com.ntsky.bbs.util.memory.ForumSingleton;

/**
 * 创建主题信息Action
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:34 $
 */
public class SearchPostAction extends PostActionSupport {
	
	/**
	 * 创建统计信息
	 * 
	 * @return String 执行信息
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("创建回复信息");
		}
		try{

		}
		catch(ServiceException se){
			throw new ActionException(se);
		}


		// 设置主题信息
		//topic  = topicService.getTopic(post.getTopicId());
		if(logger.isInfoEnabled()){
			logger.info("取得主题['']的信息");
		}	
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
			logger.info("检索初始化..");
		}
		forums = ForumSingleton.getInstance().getForums();
		return SUCCESS;
	}
	
}
