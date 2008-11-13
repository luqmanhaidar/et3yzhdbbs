package com.ntsky.bbs.util;

import java.util.ArrayList;
import java.util.List;

import com.ntsky.bbs.service.AnnouncementService;
import com.ntsky.bbs.service.ForumService;
import com.ntsky.bbs.util.memory.ForumSingleton;

public class FrontTopUtil {
	private List forums;
	private List announcements;
    private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private AnnouncementService announcementService=(AnnouncementService)bf.getBean("announcementService");
	private ForumService forumService=(ForumService)bf.getBean("forumService");
	public List getAnnouncements() {
		announcements = announcementService.getAnnouncements(0, 0);
		if(announcements==null){
			announcements= new ArrayList();
		}
		return announcements;
	}

	public List getForums() {
		List forums=forumService.getForums();
		if(forums==null){
			forums= new ArrayList();
		}
		return forums;
	}

	
	
}
