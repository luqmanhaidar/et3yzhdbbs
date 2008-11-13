package com.ntsky.bbs.web.webwork.interceptor;

import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.AroundInterceptor;

import com.ntsky.framework.util.HttpUtil;
import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.service.AdminService;
import com.ntsky.bbs.util.WebworkUtil;

/**
 * 管理员拦截器
 * 
 * @author ntsky
 * 
 */
public class AdminInterceptor extends AroundInterceptor {

	private final static Logger logger = Logger
			.getLogger(AdminInterceptor.class.getName());

	private final static String NO_PERMISSION = "no_permission";

	private boolean isLogin = false;

	private Admin admin = null;

	private void initData() {
		isLogin = false;
		admin = null;
	}

	/**
	 * 检验管理员是否登陆
	 * 
	 * <ol>
	 * <li>判断session</li>
	 * <li>session为空的情况判断cookie</li>
	 * </ol>
	 */
	protected void before(ActionInvocation invocation) throws Exception {

		initData();

		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		Object object = session.get(Symbols.SESSION_ADMIN);
		if (object == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Admin not logged in. next step is get cookie.");
			}
			String username = HttpUtil.getCookieValue(ServletActionContext
					.getRequest(), Symbols.COOKIE_ADMIN);
			if(logger.isDebugEnabled()){
				logger.debug("cookie value is " + username);
			}
			if (username == null) {
				isLogin = false;
			} else {
				// cookie存在的情况下判断用户是否已被删除
				AdminService adminService = (AdminService) BeanFactory
						.getInstance(ServletActionContext.getServletContext())
						.getBean("adminService");
				Admin admin = adminService.getAdmin(username);
				if (admin != null) {
					session.put(Symbols.SESSION_ADMIN, admin);
					if(logger.isDebugEnabled()){
						logger.debug("set session " + admin.getUsername());
					}					
					isLogin = true;
				}
			}
		} else {
			logger.info("User is Logged in System");
			isLogin = true;
		}
	}

	protected void after(ActionInvocation invocation, String result)
			throws Exception {
	}

	/**
	 * 如果用户没有登陆就跳转到登陆画面
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		// String point = WebworkUtil.getParameter("point");
		before(invocation);
		if(logger.isDebugEnabled()){
			logger.debug("user is login in : " + isLogin);
		}
		if (isLogin) {
			/*
			 * 用户登录后在Validity中进行权限校验 // 超级管理员,有全部的权限 if
			 * (!"0".equals(admin.getPermissions())) { // 如果管理员包含权限
			 * if(!StringUtil.isArrayContainString(StringUtil.splitStringToArray(admin.getPermissions(),","),point)){
			 * if(logger.isDebugEnabled()){ logger.debug("权限点为 : " + point +" --
			 * 该管理员没有操作的权限"); } return NO_PERMISSION; } }
			 */
			String result = invocation.invoke();
			after(invocation, result);
			return result;
		} else {
			return Action.LOGIN;
		}
	}
}
