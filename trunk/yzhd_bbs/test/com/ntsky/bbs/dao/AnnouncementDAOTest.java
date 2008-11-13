package com.ntsky.bbs.dao;

import java.util.Collection;
import java.util.Date;

import com.ntsky.bbs.domain.Announcement;

public class AnnouncementDAOTest extends BaseDAOCase{
	
    private AnnouncementDAO announcementDAO = null;
    private Announcement announcement = null;
    
    
    public void setAnnouncementDAO(AnnouncementDAO dao) {
        this.announcementDAO = dao;
    }

    public void testSaveAnnouncement(){
    	announcement = new Announcement();
    	announcement.setTitle("title");
    	announcement.setForumId(1);
    	announcement.setContent("content....");
    	announcement.setAuthor("ntsky....");
    	announcement.setDateCreated(new Date());
    	announcementDAO.save(announcement);
    }
    
    public void testFindAnnouncement(){
    	announcement = announcementDAO.findAnnouncement(1);
    	if(__logger.isDebugEnabled()){
    		__logger.debug("title : " + announcement.getTitle());
    	}
    	assertEquals(announcement.getAuthor(),"ntsky");
    }
    
    public void testFindAnnouncements(){
    	Collection collection = announcementDAO.findAnnouncements(-1,1);
    	System.out.println("collection = " + collection);
    	//assertEquals(collection.size(),1);
    }
}
