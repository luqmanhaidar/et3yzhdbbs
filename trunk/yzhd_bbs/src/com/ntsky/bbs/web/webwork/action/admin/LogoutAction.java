package com.ntsky.bbs.web.webwork.action.admin;

import java.util.Map;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.interceptor.SessionAware;

import com.ntsky.bbs.Symbols;
import com.ntsky.framework.util.HttpUtil;

/**
 * 管理用户退出登录
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/17 17:21:30 $
 */
public class LogoutAction extends AdminActionSupport implements SessionAware{
	
	/**
	 * 管理用户退出登陆
	 * 
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("管理用户退出登录");
		}
		session.remove(Symbols.SESSION_ADMIN);
		HttpUtil.addCookie(ServletActionContext.getResponse(),Symbols.COOKIE_ADMIN,"0",-1);
		if(logger.isInfoEnabled()){
			logger.info("成功退出");
		}
		return SUCCESS;
	}
	
	private Map session;	
	public void setSession(Map session) {
		this.session = session;
	}
	
}
