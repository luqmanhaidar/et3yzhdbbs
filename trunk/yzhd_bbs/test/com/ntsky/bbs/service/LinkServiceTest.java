package com.ntsky.bbs.service;

import java.util.Date;

import com.ntsky.bbs.domain.Link;
import com.ntsky.bbs.service.LinkService;

public class LinkServiceTest extends NTskyServiceTestCase{

	private LinkService linkService;
	private Link link = null;
	
    protected void setUp() throws Exception {
    	linkService = (LinkService)super.getBean("linkService");
    	
    	link = new Link();
    	link.setName("ntsky's web.");
    	link.setDescription("ntsky's.");
    	link.setUrl("http://www.ntsky.com");
    	link.setIsLogo((short)1);
    	link.setLogo("../1.gif");
    }
	
	public void testCreateLink(){
		linkService.createLink(link);
		//System.out.println(link.getId());
	}
		
	public void testDeleteLink(){
		linkService.deleteLink(2);
	}
	
	public void testGetLink(){
		assertNotNull(linkService.getLink(1));
	}
	
	public void testGetLinks(){
		linkService.getLinks(1);
	}
	
	public void testMakeLinkJsData(){
		//linkService.makeLinkJSData();
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(LinkServiceTest.class);
	}		
	
}
