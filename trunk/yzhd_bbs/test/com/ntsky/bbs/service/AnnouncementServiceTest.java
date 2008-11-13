package com.ntsky.bbs.service;

import java.util.Date;

import com.ntsky.bbs.domain.Announcement;
import com.ntsky.bbs.service.AnnouncementService;

public class AnnouncementServiceTest extends NTskyServiceTestCase{

	private AnnouncementService announcementService;
	private Announcement announcement = null;
	
    protected void setUp() throws Exception {
    	announcementService = (AnnouncementService)super.getBean("announcementService");
    	
    	announcement = new Announcement();
    	announcement.setTitle("网络天空论坛系统发布");
    	announcement.setForumId(1);
    	announcement.setAuthor("ntsky");
    	announcement.setContent("网络天空论坛系统发布!");
    	announcement.setDateCreated(new Date());
    }
	
	public void testCreateAnnouncement(){
		announcementService.createAnnouncement(announcement);
	}
		
	public void testDeleteAnnouncement(){
		announcementService.deleteAnnouncement(2);
	}
	
	public void testGetAnnouncement(){
		assertNotNull(announcementService.getAnnouncement(1));
	}
	
	public void testGetAnnouncements(){
		announcementService.getAnnouncements(1,0);
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(AnnouncementServiceTest.class);
	}		
	
}
