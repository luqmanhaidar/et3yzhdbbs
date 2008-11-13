package com.ntsky.bbs.web.webwork.interceptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.AroundInterceptor;
import com.opensymphony.xwork.interceptor.Interceptor;
import com.opensymphony.xwork.util.OgnlValueStack;

import com.ntsky.framework.util.HttpUtil;

import com.ntsky.bbs.web.listener.OnlineInfo;
import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.service.UserService;
import com.ntsky.bbs.service.MessageService;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.util.WebworkUtil;
import com.ntsky.bbs.util.memory.RoleSingleton;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.memory.OnlineUserSingleton;

/**
 * 网站是否关闭拦截器
 * @author ntsky
 *
 */
public class GlobalInterceptor implements Interceptor {

    private final static Logger logger = Logger.getLogger(GlobalInterceptor.class.getName());
	public static String GLOBAL_WEBCLOSED = "global_webclosed";
	public static String GLOBAL_LAWLESS = "global_lawless";

	/*protected void before(ActionInvocation invocation) throws Exception {    	
    }

    protected void after(ActionInvocation invocation, String result) throws Exception {
    }*/

	/**
     * 如果用户没有登陆就跳转到登陆画面
     * 
     * <ol>
     *  <li>
     *  	<ul>防止用户本地提交
     *  		<li>检测服务器器名称和用户浏览器头(header)中host的信息是否一致进行判断</li>
     *  	</ul>
     *  </li>
     *  <li>
     *  	<pre>session判断
     *  		<ul>
     *  			<li>判断用户Session是否存在</li>
     *  			<li>如果Session不存在，从cookie中取用户信息</li>	
     *  		</ul>
     *  	</pre>
     * 	</li>
     *  <li>
     *  	判断用户的角色是否存在，如果不存在就要求用户重新登录.
     *  	由于管理员从后台维护角色信息,当删除角色时,此时内存在不存在该角色，所以强制要求用户重新登录获取新的角色
     *  </li>
     *  <li>
     *  	取得邮件中未读消息数
     *  	<ul>
     *  		<li>用户登录的情况</li>
     *  		<li>取得未读消息数存放在堆栈中</li>
     *  	</ul>
     *  </li>
     * 	<li>判断网站是否关闭</li>
     * 	<li>判断全局权限</li>
     * </ol>
     */
    public String intercept(ActionInvocation invocation) throws Exception {

    	// 防止用户本地提交(安全隐患)
    	HttpServletRequest request = WebworkUtil.getRequest();
    	
    	if(!(request.getServerName().equals(request.getHeader("host").split(":")[0]))){
    		if(logger.isInfoEnabled()){
    			logger.info("serverName : " + request.getServerName() + " | host : " + request.getHeader("host"));
    		}
    		return GLOBAL_LAWLESS;
    	}
    	
    	boolean isLogin = false;
    	User user = null;
    	// 用户session判断
    	ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();
        Object object = session.get(Symbols.SESSION_USER);
        if (object == null) {
        	String userId = HttpUtil.getCookieValue(ServletActionContext.getRequest(),Symbols.COOKIE_USER);
        	if(userId != null){
      			if(logger.isInfoEnabled()){
      				logger.info("cookie中找到编号为['"+userId+"']的用户");
     			}        		
        		 // cookie存在的情况下判断用户是否已被删除
        		 UserService userService = (UserService)BeanFactory.getInstance(ServletActionContext.getServletContext()).getBean("userService");
        		 try{
        			 user = userService.getUser(new Long(userId));
		   		 }
		   		 catch(Exception e){
		   			 user = null;
		         }
        		 if(user != null && user.getIsLock()==0){
        			 if(logger.isInfoEnabled())
        				 logger.info("根据cookie用户['"+user.getUsername()+"']登陆成功");
    				 // 登陆成功
    				 session.put(Symbols.SESSION_USER,user);
    				 
  					// 登陆后设置Fckeditor的cookie(原因 ： IE不同版本的情况下，Fckediotr中Session传值有问题)
  					HttpUtil.addCookie(ServletActionContext.getResponse(),Symbols.COOKIE_FCKEDITOR,user.getId().toString(),-1);

    				 isLogin = true;
        		 }
        	 }
        }
        else{
        	isLogin = true;
        	user = (User)object;
        }
        
        //用户角色判断，防止空角色出现
        if( user!=null ){
        	if( RoleSingleton.getInstance().getRole(String.valueOf(user.getRoles()))==null ){
        		if(logger.isDebugEnabled()){
        			logger.warn("目前用户所属角色["+user.getRoles()+"]已经被管理员删除,请重新登录获取角色");
        		}
        		return Action.LOGIN;
        	}
        }
        
        // 判断未读邮件数
        if(isLogin){
        	MessageService messageService = (MessageService)BeanFactory.getInstance(ServletActionContext.getServletContext()).getBean("messageService");
        	int noReadMessageNum = messageService.getMessageNumByIsNoRead(user.getUsername(), 0);
        	if(logger.isDebugEnabled()){
        		logger.debug("["+user.getUsername()+"]的未读邮件数("+noReadMessageNum+")");
        	}
        	OgnlValueStack ovStack = (OgnlValueStack)WebworkUtil.getRequest().getAttribute("webwork.valueStack");
			ovStack.setValue("noReadMessageNum",new Integer(noReadMessageNum));
        }
    	
		// 网站是否关闭  true关闭　 false打开
		if((Symbols.TRUE).equals(SystemConfig.getInstance().getPropertyValue(Symbols.BASIC,Symbols.BASIC_IS_CLOSE))){
			// 网站关闭页
			return GLOBAL_WEBCLOSED;
		}    
		
		// 计算网站统计信息
		int forumId = HttpUtil.getIntParameter(request,Symbols.PARA_FORUM_ID);
		Object tempObject = OnlineUserSingleton.getInstance().getOnlineUserMap().get(request.getRemoteAddr());
		OnlineInfo onlineInfo = null;
		// 如果用户第一次访问
		if(tempObject == null){
			onlineInfo = new OnlineInfo();
			onlineInfo.setIpAddress(request.getRemoteAddr());
			if( user == null ) {
		    	onlineInfo.setUsername(Symbols.GUEST);
		    	onlineInfo.setRoles("0");
		    	onlineInfo.setForumId(forumId);
		    }
			else{
		    	// 用于计算用户在线时长(用户第一次登录时，记录登录时间)
		    	if(logger.isDebugEnabled()){
		    		logger.info("用户"+ user.getUsername() + "开始记时...");
		    	}
		    	onlineInfo.setDate(new Date());
		    	onlineInfo.setUsername(user.getUsername());
		    	onlineInfo.setRoles(user.getRoles());
		    	onlineInfo.setForumId(forumId);
		    }
		}
		else{
			// 用户再次访问时候
			onlineInfo = (OnlineInfo)tempObject;
			if(user!=null){
				onlineInfo.setUsername(user.getUsername());
		    	onlineInfo.setRoles(user.getRoles());
		    	// 用户登录到网站，则进行时间记录
		    	if(Symbols.GUEST.equals(onlineInfo.getUsername())){
			    	if(logger.isDebugEnabled()){
			    		logger.info("用户"+ user.getUsername() + "开始记时...");
			    	}
			    	onlineInfo.setDate(new Date());
		    	}
			}
			onlineInfo.setForumId(forumId);
		}
	    
	    WebworkUtil.getRequest().getSession().setAttribute(Symbols.SESSION_ONLINE,onlineInfo);
		
		String result = invocation.invoke();
        return result;
		
    }
    
    public void destroy() {
    }

	public void init() {
		// TODO Auto-generated method stub
	}
   
}

