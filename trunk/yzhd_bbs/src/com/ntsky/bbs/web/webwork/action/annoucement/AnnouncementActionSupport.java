package com.ntsky.bbs.web.webwork.action.annoucement;

import java.util.List;

import com.ntsky.bbs.service.ForumService;
import com.ntsky.bbs.service.AnnouncementService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

public abstract class AnnouncementActionSupport extends BasicActionSupport{
	
	protected AnnouncementService announcementService;
	public void setAnnouncementService(AnnouncementService announcementService){
		this.announcementService = announcementService;
	}
	
	protected ForumService forumService;
	public void setForumService(ForumService forumService){
		this.forumService = forumService;
	}
	
	// 论坛集合
	private List forums = null;
	public void setForms(List forums){
		this.forums = forums;
	}
	public List getForums(){
		return this.forums;
	}
	
	// 公告集合
	private List announcements = null;	
	
	public List getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(List announcements) {
		this.announcements = announcements;
	}
	
	
}
