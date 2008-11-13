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
import com.opensymphony.xwork.interceptor.Interceptor;

import com.ntsky.framework.util.HttpUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.service.UserService;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.util.memory.RoleSingleton;
import com.ntsky.bbs.util.config.SystemConfig;

/**
 * 权限处理拦截器
 * @author ntsky
 *
 */
public class PermissionInterceptor implements Interceptor {

    private final static Logger logger = Logger.getLogger(PermissionInterceptor.class.getName());
    
	/**
     * 判断GUEST用户是否有操作权限
     * 
     * <ul>
     * 	<li>判断全局权限</li>
     * </ul>
     */
    public String intercept(ActionInvocation invocation) throws Exception {   

		
		String result = invocation.invoke();
        return result;
		
    }

    
    public void destroy() {

    }


	public void init() {
		// TODO Auto-generated method stub
		
	}
   
}

