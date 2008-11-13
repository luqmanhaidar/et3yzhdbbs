package com.ntsky.bbs.web.webwork.action.view; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ntsky.bbs.domain.Link;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.service.AnnouncementService;
import com.ntsky.bbs.service.LinkService;
import com.ntsky.bbs.service.TopicService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.util.memory.StarUserSingleton;

/**
 * 论坛首页
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.8 $ $Date: 2008/10/25 08:08:38 $
 */
public class IndexAction extends ViewActionSupport{
	
	private List forums;
	private List hotForums;
	private List announcements;
	private List dayTopics;
	private List weekTopics;
	private List forumsInIndex;
	public List getForumsInIndex() {
		return forumsInIndex;
	}

	public List getForums(){
		return forums;
	}
	
	private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private LinkService ls=(LinkService)bf.getBean("linkService");
	private TopicService topicService=(TopicService)bf.getBean("topicService");
	private AnnouncementService announcementService=(AnnouncementService)bf.getBean("announcementService");
	
	private Link ad;//顶部ad
	private Link ad2;
	private Link ad3;
	private Link ad4;
	
	// 首页数据
	private Map indexMap;
	public Map getIndexMap(){
		return indexMap;
	}
	
	// 最新注册的用户
	private User newlyUser;
	public User getNewlyUser(){
		return newlyUser;
	}
	
	//论坛之星
	private List stars;	

	public List getStars() {
		return stars;
	}

	/**
	 * 访问首页
	 * 
	 */
	public String execute() throws Exception {

		if(logger.isInfoEnabled()){
			logger.info("返回到首页...");
		}
		// 论坛数据
		forums = ForumSingleton.getInstance().getIndexForums();
		hotForums = forumService.getHotForums();
		announcements = announcementService.getAnnouncements(-1, 0);
		//dayTopics =topicService.getDayTopics(0, 10);
		//System.out.println("dayTopics:"+dayTopics.size());
		//weekTopics =topicService.getWeekTopics(0, 10);
		//System.out.println("weekTopics:"+weekTopics.size());
		forumsInIndex= ForumSingleton.getInstance().getForumsInIndex();
		stars=StarUserSingleton.getInstance().getStars();
		
		// 其它数据
		indexMap = new HashMap();
		indexMap.put("countUser",String.valueOf(userService.countUser()));
		indexMap.put("countTopic",String.valueOf(topicService.countTopic()));
		indexMap.put("countPost",String.valueOf(postService.countPost()));
		
		// 最新注册的用户
		newlyUser = userService.getNewlyUser();

		return SUCCESS;
	}

	public Link getAd() {
		return ls.findRandomLinkByType(1);
	}

	public void setAd(Link ad) {
		this.ad = ad;
	}

	public Link getAd2() {
		return ls.findRandomLinkByType(2);
	}

	public void setAd2(Link ad2) {
		this.ad2 = ad2;
	}

	public Link getAd3() {
		return ls.findRandomLinkByType(3);
	}

	public void setAd3(Link ad3) {
		this.ad3 = ad3;
	}

	public Link getAd4() {
		return ls.findRandomLinkByType(4);
	}

	public void setAd4(Link ad4) {
		this.ad4 = ad4;
	}

	public List getAnnouncements() {
		return announcements;
	}

	public List getDayTopics() {
		return dayTopics;
	}

	public void setDayTopics(List dayTopics) {
		this.dayTopics = dayTopics;
	}

	public List getWeekTopics() {
		return weekTopics;
	}

	public void setWeekTopics(List weekTopics) {
		this.weekTopics = weekTopics;
	}

	public List getHotForums() {
		return hotForums;
	}

	
}
