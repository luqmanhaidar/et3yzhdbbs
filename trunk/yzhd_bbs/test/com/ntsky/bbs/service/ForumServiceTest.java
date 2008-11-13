package com.ntsky.bbs.service;

import java.util.Date;

import com.ntsky.bbs.domain.Forum;

public class ForumServiceTest extends NTskyServiceTestCase{

	private ForumService forumService;
	private Forum forum = null;
	
    protected void setUp() throws Exception {
    	
    	forumService = (ForumService)super.getBean("forumService");
    	
    	forum = new Forum();
    	forum.setName("open source");
    	forum.setParentId(0);
    	forum.setParentEnum("0");
    	forum.setDepth(1);
    	forum.setDisplayOrder(2);
    	forum.setBranchId(1);
    	forum.setMasters("ntsky");
    	forum.setDescription("Open source");
    	forum.setDateCreated(new Date());
    
    }
	
	public void testCreateForum(){
		forumService.createForum(forum);
	}
	
	public void testGetForms(){
		forumService.getForums();
	}
	
	public void testGetMaxBranchId(){
		//forumService.getMaxBranchId();
	}
	
	public void testGetForum(){
		assertNotNull(forumService.getForum(1));
	}

	public void testDeleteForum(){
		//forumService.deleteForum(2);
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(ForumServiceTest.class);
	}		
	
}
