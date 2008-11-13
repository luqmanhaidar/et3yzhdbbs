package com.ntsky.bbs.util.memory;

import java.util.List;

import com.ntsky.bbs.service.ForumService;
import com.ntsky.bbs.service.UserService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;

public class StarUserSingleton {
	private static StarUserSingleton starUserSingleton=null;
	
	public synchronized static StarUserSingleton getInstance(){
		if(starUserSingleton==null){
			starUserSingleton = new StarUserSingleton();
		}
		return starUserSingleton;
	}
	private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private UserService userService=(UserService)bf.getBean("userService");
	
	//论坛之星
	private List stars;

	public List getStars() {
		return userService.findStarUser(2);
	}

	public void setStars(List stars) {
		this.stars = stars;
	}

}
