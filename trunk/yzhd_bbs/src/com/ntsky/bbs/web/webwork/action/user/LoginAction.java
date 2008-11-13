package com.ntsky.bbs.web.webwork.action.user;

import java.util.Map;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.interceptor.SessionAware;

import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.HttpUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.util.WebworkUtil;
import com.ntsky.bbs.exception.LoginException;

public class LoginAction extends UserActionSupport implements SessionAware{

	/**
	 * 登录数据
	 */
	private String username;
    private String password;
    private String vCode;
    private String cookie;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getVCode() {
		return vCode;
	}
	public void setVCode(String code) {
		vCode = code;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	/**
	 * 转向URL
	 */
	private String redirectURL;
	public String getRedirectURL(){
		return redirectURL;
	}
	
	/**
	 * 执行登陆
	 * 步骤 : 
	 * <ol>
	 * 	<li>验证登陆</li>
	 * 	<li>设置Session信息</li>
	 *  <li>写入Cookie</li>
	 * </ol>
	 * 
	 */
	public String execute() throws Exception {
		
		if(!"login".equals(super.action)){
			return super.LOGIN;
		}
		
		if(logger.isInfoEnabled()){
			logger.info("执行登陆: ");
			logger.info("\t用户名 : " + username);
			logger.info("\t密码 : " + password);
			logger.info("\t验证码 : " + vCode);
		}
		User user = null;
		try{
			// 验证验证码
			if(!HttpUtil.getAttribute(getRequest().getSession(),"vCode").equals(vCode)){
				logger.warn("错误验证码 : " + vCode);
				setWarnMessage("错误验证码["+vCode+"]");
				return INPUT;
			}
			// 验证用户名和密码			
			user = userService.authLogin(username,password);
			
			super.setUserSession(session, user);
			
			// 更新用户登陆信息
			userService.updateLoginInfo(user.getId().intValue(),DateUtil.getDate(),getRequest().getRemoteAddr());
			
			// 注册后就设置Fckeditor的cookie(原因 ： IE不同版本的情况下，Fckediotr中Session传值有问题)
			if(logger.isInfoEnabled())
				logger.info("Fckeditor's cookie value is : " + user.getId().toString());
			WebworkUtil.setFCKeditorCookie(user.getId().toString());
			
		}
		catch(LoginException loginException){
			// 登陆异常，返回到登陆页面
			logger.warn(loginException.getMessage());
			setWarnMessage(loginException.getMessage());
			return INPUT;
		}
		// 登陆成功，判断是否写入cookie信息
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
		HttpUtil.addCookie(ServletActionContext.getResponse(),Symbols.COOKIE_USER,String.valueOf(user.getId()),time);
		
		// 转向URL
		redirectURL = getParameter(Symbols.PARA_REDIRECT_URL);
		if(logger.isInfoEnabled()){
			logger.info("转向URL地址 ：" + redirectURL);
		}
		if("".equals(redirectURL)){
			return SUCCESS;
		}
		else{
			return "reLocation";
		}
	}
	
	private Map session;	
	public void setSession(Map session) {
		this.session = session;
	}
	
}
