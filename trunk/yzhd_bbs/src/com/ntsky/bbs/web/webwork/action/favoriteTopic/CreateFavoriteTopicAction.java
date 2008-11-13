package com.ntsky.bbs.web.webwork.action.favoriteTopic;

import com.ntsky.bbs.domain.FavoriteTopic;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.FavoriteTopicException;
import com.ntsky.bbs.exception.PollException;
import com.ntsky.bbs.exception.ServiceException;
import com.opensymphony.xwork.ModelDriven;

/**
 * 添加收藏夹信息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.5 $ $Date: 2008/10/26 07:33:22 $
 */
public class CreateFavoriteTopicAction extends FavoriteTopicActionSupport implements ModelDriven {

	FavoriteTopic favoriteTopic = new FavoriteTopic();
    private int favId;
	private int topicId;
	public int getTopicId() {
		return topicId;
	}


	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}


	/**
	 * 添加收藏夹信息
	 */
	public String execute() throws FavoriteTopicException,Exception {
		try{
			FavoriteTopic tempFavoriteTopic =favoriteTopicService.getFavoriteTopicByUserTopic(super.getSessionUser().getId().intValue(), favId);
			if(tempFavoriteTopic==null)
			{
				favoriteTopic.setUserId(super.getSessionUser().getId().intValue());
				favoriteTopic.setTopicId(favId);
				favoriteTopicService.createFavoriteTopic(favoriteTopic);
				super.setActionMessage("收藏成功!");
			}
			else
			{	
				super.setWarnMessage("该主题已经在收藏夹里面了!");
			}
		}
		catch(FavoriteTopicException pe){
			throw new FavoriteTopicException(pe.getMessage());
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
	
		return SUCCESS;
	}


	public Object getModel() {
		return favoriteTopic;
	}


	public void setFavId(int favId) {
		this.favId = favId;
	}


	public int getFavId() {
		return favId;
	}
	
}
