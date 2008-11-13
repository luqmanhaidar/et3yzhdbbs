package com.ntsky.bbs.web.webwork.action.service;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.service.PollService;
import com.ntsky.bbs.service.TopicService;
import com.ntsky.bbs.service.CategoryService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

/**
 * 主题服务
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:24 $
 */
public class TopicServiceAction extends ServiceActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int num;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public int forumId;
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	/**
	 * 列表最新的主题
	 * @return 
	 * @throws Exception
	 */ 
	public String doNewly() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("列表最新的主题...");
		}
		super.setTopics(super.topicService.getNewlyTopics(forumId,num));
		return SUCCESS;
	}
	
}
