package com.ntsky.bbs.web.webwork.action.view;

import java.util.List;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.config.SystemConfig;

/**
 * 控制面板页面
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */
public class MasterPanelAction extends ViewActionSupport{
	
	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	
	private Forum forum;
	public Forum getForum(){
		return this.forum;
	}	
	
	/**
	 * 管理员控制面板
	 * 
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("管理员控制面板");
		}
		
		forum = ForumSingleton.getInstance().getForum(forumId);
		
		return SUCCESS;
	}
	
}
