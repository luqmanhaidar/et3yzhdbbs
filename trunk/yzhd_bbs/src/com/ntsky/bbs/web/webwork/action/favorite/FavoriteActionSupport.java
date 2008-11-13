package com.ntsky.bbs.web.webwork.action.favorite;

import java.util.List;

import com.ntsky.bbs.service.FavoriteService;
import com.ntsky.bbs.service.FavoriteTopicService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

/**
 * 个人收藏夹
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/24 07:10:57 $
 */
public abstract class FavoriteActionSupport extends BasicActionSupport{
	
	private List favorites = null;
	public List getFavorites() {
		return favorites;
	}
	public void setFavorites(List favorites) {
		this.favorites = favorites;
	}
	
	protected FavoriteService favoriteService;
	protected FavoriteTopicService favoriteTopicService;
	public void setFavoriteService(FavoriteService favoriteService){
		this.favoriteService = favoriteService;
	}
	public void setFavoriteTopicService(FavoriteTopicService favoriteTopicService) {
		this.favoriteTopicService = favoriteTopicService;
	}

}
