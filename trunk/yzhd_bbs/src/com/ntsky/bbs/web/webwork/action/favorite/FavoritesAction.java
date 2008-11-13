package com.ntsky.bbs.web.webwork.action.favorite;

import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 收藏夹列表
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/12 16:11:06 $
 */
public class FavoritesAction extends FavoriteActionSupport {

	private String name;
	public void setName(String name){
		if(name==null){
			name = "";
		}
		this.name = name;
	}
	
	/**
	 * 收藏夹列表
	 */
	public String execute() throws Exception {
		QueryResult queryResult = null;
		try{
			queryResult = favoriteService.getFavorites(name,super.getSessionUser().getId().intValue(),new Pagination(getPaginationStart()));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		setFavorites(queryResult.getItems());
		setPagination(queryResult.getPagination());
		return SUCCESS;
	}
}
