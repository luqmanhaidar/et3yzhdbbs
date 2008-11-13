package com.ntsky.bbs.web.webwork.action.user;

import java.util.Map;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.interceptor.SessionAware;

import com.ntsky.bbs.Symbols;
import com.ntsky.framework.util.HttpUtil;

/**
 * 退出登录
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:25 $
 */
public class LogoutAction extends UserActionSupport implements SessionAware{
	
	/**
	 * 退出登陆
	 * 
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("退出登录...");
		}
		session.remove(Symbols.SESSION_USER);
		HttpUtil.addCookie(ServletActionContext.getResponse(),Symbols.COOKIE_USER,"0",-1);
		if(logger.isInfoEnabled()){
			logger.info("成功退出...");
		}
		return SUCCESS;
	}
	
	private Map session;	
	public void setSession(Map session) {
		this.session = session;
	}
	
}
