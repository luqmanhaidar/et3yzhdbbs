package com.ntsky.bbs.web.webwork.action.forum;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Link;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.AnnouncementService;
import com.ntsky.bbs.service.CategoryService;
import com.ntsky.bbs.service.LinkService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.util.memory.OnlineUserSingleton;
import com.ntsky.bbs.util.memory.RoleSingleton;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
/**
 * 显示论坛
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.10 $ $Date: 2008/11/09 08:14:02 $
 */
public class ViewForumAction extends ForumActionSupport {

	// 参数
	private int forumId;

	private List announcements;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}

	private Link ad;//顶部ad
	private Link ad2;
	private Link ad3;
	private Link ad4;
	private CategoryService categoryService;

	// 应答数据
	private List topics;
	public void setTopics(List topics){
		this.topics = topics;
	}
	public List getTopics(){
		return this.topics;
	}		

	private Forum forum;
	public Forum getForum(){
		return this.forum;
	}

	private List childForums;
	public List getChildForums(){
		return this.childForums;
	}

	private List categories;
	public List getCategories(){
		return this.categories;
	}	

	/**
	 * 显示论坛数据
	 * <pre>
	 * 	执行成功迁移到 forum.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			//logger.info("列表全部主题信息...");
		}

		if(super.isAccess("canViewForum")==0){
			return NO_PERMISSION;
		}

		forum = ForumSingleton.getInstance().getForum(forumId);
		if(forum.getIsMasters()==1){
			if(getSessionUser()==null || getSessionUser().getUsername().equals("guest"))
			{
				setWarnMessage("该帖子需要版主以上级别才能访问");
				return NO_PERMISSION;
			}
			else
			{
				String roles=RoleSingleton.getInstance().getRoleIdByName(super.getSessionUser().getUsername());
				if(roles.equals("1")||roles.equals("2")||roles.equals("3")){

				}else{
					return NO_PERMISSION;
				}
			}
		}
		try{
			// 当前论坛数据

			// 全部论坛
			setForums(ForumSingleton.getInstance().getForums());
			// 子论坛列表
			childForums = ForumSingleton.getInstance().getChildForums(forumId);

			announcements = announcementService.getAnnouncements(forumId, 0);

			//本论坛人数

			int forumUsers=OnlineUserSingleton.getInstance().getOnlineUser(forumId).size();
			int forumGuests=OnlineUserSingleton.getInstance().getOnlineGuest(forumId).size();

			OnlineUserSingleton.getInstance().setForumUsers(forumUsers);
			OnlineUserSingleton.getInstance().setForumGuests(forumGuests);
			// 主题数据
			Map orderMap = new TreeMap();
			orderMap.put("isTop","desc");
			if(super.order == null){
				orderMap.put("lastPostTime","desc");
			}
			else{
				orderMap.put(sort,order);
			}
			QueryResult queryResult = topicService.getTopics(forumId,orderMap,new Pagination(getPaginationStart(),SystemConfig.getInstance().getIntPropertyValue(Symbols.PAGINATION,Symbols.PAGINATION_TOPIC)));
			setTopics(queryResult.getItems());
			setPagination(queryResult.getPagination());


			// 分类列表
			categories = categoryService.getCategories(forumId);

			if(logger.isInfoEnabled()){
				logger.info("列表主题成功,并返回到主题列表页面");
			}			
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}		
		return SUCCESS;
	}

	private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private LinkService ls=(LinkService)bf.getBean("linkService");
	private AnnouncementService announcementService=(AnnouncementService)bf.getBean("announcementService");

	public List getAnnouncements() {
		return announcements;
	}
	public Link getAd() {
		return ls.findRandomLinkByType(1);
	}
	public Link getAd2() {
		return ls.findRandomLinkByType(2);
	}
	public Link getAd3() {
		return ls.findRandomLinkByType(3);
	}
	public Link getAd4() {
		return ls.findRandomLinkByType(4);
	}
	public CategoryService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


}
