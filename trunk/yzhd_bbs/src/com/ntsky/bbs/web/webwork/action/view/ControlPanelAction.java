package com.ntsky.bbs.web.webwork.action.view;

import java.util.List;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.util.memory.ForumSingleton;
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
public class ControlPanelAction extends ViewActionSupport{
	
	private List messages;
	public List getMessages(){
		return this.messages;
	}
	
	/**
	 * 控制面板页面
	 * 
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("打开个人控制面板页面");
		}
		messages = super.messageService.getMessages(super.getSessionUser().getUsername(),5);
		return SUCCESS;
	}
	
}
