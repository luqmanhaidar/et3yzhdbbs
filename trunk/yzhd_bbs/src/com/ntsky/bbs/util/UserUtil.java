package com.ntsky.bbs.util;

import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.service.UserService;

public class UserUtil {
	private static BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private static UserService userService=(UserService)bf.getBean("userService");
	
	public static String getAliasByUsername(String username){		
		User user=userService.getUser(username);
		if(user!=null){
			return user.getAlias();
		}else{
			return username;
		}
	}
}
