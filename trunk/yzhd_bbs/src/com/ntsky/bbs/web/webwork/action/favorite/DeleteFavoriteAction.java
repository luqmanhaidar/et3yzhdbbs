package com.ntsky.bbs.web.webwork.action.favorite;

import com.opensymphony.xwork.ModelDriven;

import com.ntsky.bbs.domain.Favorite;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 删除收藏夹信息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/24 07:10:57 $
 */
public class DeleteFavoriteAction extends FavoriteActionSupport {

	private Favorite favorite = new Favorite();
	
	/**
	 * 收藏夹编号
	 */
	private int favId;
	public void setFavId(int favId){
		this.favId = favId;
	}
	
	/**
	 * 删除单个收藏夹信息
	 */
	public String deleteOne() throws Exception {
		try{
			favoriteService.deleteFavorite(favId);
			favoriteTopicService.deleteFavorite(favId);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	private int[] ids;
	public void setId(int[] ids){
		this.ids = ids;
	}
	/**
	 * 删除多个收藏夹
	 * @return
	 * @throws Exception
	 */
	public String deleteMore() throws Exception {
		try{
			favoriteService.deleteMoreFavorite(ids);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}

	/**
	 * 清空用户收藏夹
	 * @return
	 * @throws Exception
	 */
	public String clearUserFavorite() throws Exception {
		try{
			favoriteService.clearUserFavorte(super.getSessionUser().getId().intValue());
			
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}

}
