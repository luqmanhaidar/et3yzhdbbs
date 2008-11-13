package com.ntsky.bbs.web.webwork.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.AroundInterceptor;

import com.ntsky.framework.util.HttpUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.service.PostService;
import com.ntsky.bbs.service.UserService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.util.WebworkUtil;

/**
 * 用户信息拦截器
 * @author ntsky
 *
 */
public class UserInterceptor extends AroundInterceptor {

	private final static Logger logger = Logger.getLogger(UserInterceptor.class.getName());

	/**
	 * 检验用户是否登陆
	 * 
	 * <ol>
	 * 	<li>判断session</li>
	 *  <li>session为空的情况判断cookie</li>
	 *  <li>cookie存在的情况下判断是否被锁定</li>
	 * </ol>
	 */
	protected void before(ActionInvocation invocation) throws Exception {
	}

	protected void after(ActionInvocation invocation, String result) throws Exception {
	}
	private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private UserService userService=(UserService)bf.getBean("userService");
	private List errs;
	public List getErrs() {
		return errs;
	}

	/**
	 * 如果用户没有登陆就跳转到登陆画面
	 */
	public String intercept(ActionInvocation invocation) throws Exception {

		before(invocation);

		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		Object object = session.get(Symbols.SESSION_USER);
		if (object == null) {
			if (logger.isInfoEnabled())
				logger.info("session不存在,请先登录...");
			return Symbols.NO_LOGIN;
		}
		else{
			User olduser = (User)object;
			User user =userService.getUser(olduser.getUsername());
			if(user == null)
			{
				//if (logger.isInfoEnabled())
					//logger.info("用户[ '" + user.getUsername() + "' ]已被删除");
				session.remove(Symbols.SESSION_USER);
				HttpUtil.addCookie(ServletActionContext.getResponse(),Symbols.COOKIE_USER,"0",-1);
				return Symbols.NO_LOGIN;
			}
			else
			{
				if(user.getIsLock()==1){
					if (logger.isInfoEnabled())
						logger.info("用户[ '" + user.getUsername() + "' ]已被锁定");
					return Symbols.NO_LOGIN;
				}
				else{
					String result = invocation.invoke();
					after(invocation, result);
					return result;  
				}
			}
		}

		/*errs = new ArrayList();
		if(logger.isInfoEnabled()){
			logger.info("用户是否已登陆 : " + isLogin);
		}        
        if(isLogin){
    		String result = invocation.invoke();
            after(invocation, result);
            return result;  
        }
    	else{
            if (isNotExist) {
				if (logger.isInfoEnabled())
					logger.info("用户[ '" + userId + "' ]不存在");
				errs.add("编号为[ '" + userId + "' ]的用户不存在");
				return Symbols.NO_LOGIN; // 用户被删除
			} 
            else {
				if (isLock) {
					if (logger.isInfoEnabled())
						logger.info("用户[ '" + userId + "' ]已被锁定");
					errs.add("用户[ '" + userId + "' ]已被锁定");
					return Symbols.NO_LOGIN; // 锁定
				} 
	            return Symbols.NO_LOGIN; 
			}       		     		
    	}*/
	}

}

