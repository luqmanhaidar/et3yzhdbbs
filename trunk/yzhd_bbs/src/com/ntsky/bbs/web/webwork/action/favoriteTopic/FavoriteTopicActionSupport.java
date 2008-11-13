package com.ntsky.bbs.web.webwork.action.favoriteTopic;

import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.service.FavoriteTopicService;

/**
 * 用户基础Action
 * @author ntsky
 *
 */
public abstract class FavoriteTopicActionSupport extends BasicActionSupport{
	
	protected FavoriteTopicService favoriteTopicService;
	

	
	public FavoriteTopicService getFavoriteTopicService() {
		return favoriteTopicService;
	}



	public void setFavoriteTopicService(FavoriteTopicService favoriteTopicService) {
		this.favoriteTopicService = favoriteTopicService;
	}



	// 设置Session
	public void setUserSession(Map session,User user){
		session.put(Symbols.SESSION_USER, user);
	}
}
	
	