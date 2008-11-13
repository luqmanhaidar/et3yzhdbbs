package com.ntsky.bbs.web.webwork.action.admin;

import java.util.Map;

import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.HttpUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.exception.LoginException;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.interceptor.SessionAware;

/**
 * 管理登陆
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $
 */
public class LoginAction extends AdminActionSupport implements SessionAware{
	
	/**
	 * 页面Form数据
	 */
	private String username;
	private String password;
	private String vCode;
	private String cookie;
	
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setVCode(String code) {
		vCode = code;
	}

	/**
	 * 设置Session
	 */
    private Map session;
    public void setSession(Map session) {
        this.session = session;
    }
   
	/**
	 * 管理登陆
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("登陆用户名 : " + username);
		}
		try{
			// 验证验证码
			if(!vCode.equals(HttpUtil.getAttribute(getRequest().getSession(),"vCode"))){
				logger.warn("验证码错误");
				setWarnMessage("验证码错误");
				return INPUT;
			}
			// 验证用户名和密码			
			Admin admin = adminService.authLogin(username,password);
			session.put(Symbols.SESSION_ADMIN, admin);
			
			// 更新用户登陆信息
			adminService.updateLoginInfo(admin.getId().intValue(),DateUtil.getDate(),getRequest().getRemoteAddr());
			
		}
		catch(LoginException loginException){
			// 登陆异常，返回到登陆页面
			logger.error(loginException.getMessage());
			setWarnMessage(loginException.getMessage());
			return INPUT;
		}
		// 写入cookie信息
		// cookie 时间
		int time = 0;
		switch (Integer.parseInt(cookie)) {
		case 0:
			break;
		case 1:
			// one day
			time = 1;
			break;
		case 2:
			// one month
			time = 30;
			break;
		case 3:
			// one year
			time = 365;
			break;
		}
		if(logger.isInfoEnabled()){
			logger.info("cookie保留时间 : " + time);
		}
		HttpUtil.addCookie(ServletActionContext.getResponse(),Symbols.COOKIE_ADMIN,username,time);
		
		return SUCCESS;
	}

}
