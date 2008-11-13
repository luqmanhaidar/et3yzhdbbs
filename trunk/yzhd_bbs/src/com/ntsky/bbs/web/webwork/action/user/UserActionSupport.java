package com.ntsky.bbs.web.webwork.action.user;

import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.service.UserService;

/**
 * 用户基础Action
 * @author ntsky
 *
 */
public abstract class UserActionSupport extends BasicActionSupport{
	
	protected UserService userService;
	
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	// 设置Session
	public void setUserSession(Map session,User user){
		session.put(Symbols.SESSION_USER, user);
	}
	
	
}
