package com.ntsky.bbs.web.webwork.action.favorite;

import com.opensymphony.xwork.ModelDriven;

import com.ntsky.bbs.domain.Favorite;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 添加收藏夹信息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/24 06:44:56 $
 */
public class CreateFavoriteAction extends FavoriteActionSupport implements ModelDriven {

	Favorite favorite = new Favorite();
	
	/**
	 * 添加收藏夹信息
	 */
	public String execute() throws Exception {
		try{
			favorite.setUserId(super.getSessionUser().getId().intValue());
			favoriteService.createFavorite(favorite);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		if(logger.isInfoEnabled()){
			logger.info("创建收藏夹[ "+favorite.getName()+" ]");
		}
		return NONE;
	}


	public Object getModel() {
		return favorite;
	}
	
}
