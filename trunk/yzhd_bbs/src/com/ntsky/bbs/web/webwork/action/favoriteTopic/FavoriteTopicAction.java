package com.ntsky.bbs.web.webwork.action.favoriteTopic;
import java.util.List;

import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.domain.*;
import com.ntsky.bbs.util.page.QueryResult;
/**
 * 管理员列表主题
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/24 02:47:42 $
 */
public class FavoriteTopicAction extends FavoriteTopicActionSupport{

	// 参数
	private int userId;

	
	// 应答数据
	private List favoriteTopic;

	
	private Topic topics;

    /******************/
	
	public List getFavoriteTopic() {
		return favoriteTopic;
	}




	public void setFavoriteTopic(List favoriteTopic) {
		this.favoriteTopic = favoriteTopic;
	}




	public int getUserId() {
		return userId;
	}




	public void setUserId(int userId) {
		this.userId = userId;
	}




	/**
	 * 管理员列表主题
	 * <pre>
	 * 	执行成功迁移到 forum.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {


		try{

			QueryResult queryResult = favoriteTopicService.getFavoriteTopics(super.getSessionUser().getId().intValue(), new Pagination(getIntParameter("start")));
			setFavoriteTopic(queryResult.getItems());
			setPagination(queryResult.getPagination());	
			
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
	    return SUCCESS;
    }




	public Topic getTopics() {
		return topics;
	}




	public void setTopics(Topic topics) {
		this.topics = topics;
	}




	






	
}
