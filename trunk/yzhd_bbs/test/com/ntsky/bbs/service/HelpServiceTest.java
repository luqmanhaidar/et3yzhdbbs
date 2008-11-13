package com.ntsky.bbs.service;

import java.util.Date;

import com.ntsky.bbs.domain.Help;
import com.ntsky.bbs.util.page.Pagination;

public class HelpServiceTest extends NTskyServiceTestCase{

	private HelpService helpService;
	private Help help = null;
	
    protected void setUp() throws Exception {
    	helpService = (HelpService)super.getBean("helpService");
    	
    	help = new Help();
    	help.setTitle("create topic");
    	help.setContent("crete topic info more.");
    	help.setType(1);
    	help.setDateCreated(new Date());
    }
	
	public void testCreateHelp(){
		helpService.createHelp(help);
	}
	
	public void testHelpException(){
		helpService.deleteHelp(1);
	}
		
	public void testDeleteHelp(){
		try {
			helpService.deleteHelp(2);
			/*Help help = (Help)helpService.getHelp(2);
			assertNull(help);*/		
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			// TODO: handle exception
		}

	}
	
	public void testGetHelp(){
		try {
			helpService.getHelp(2);
			System.out.println("getHelp");
		} catch (Exception e) {
			System.out.println("sasa");
		}
		
	}
	
	public void testGetHelps(){
		helpService.getHelps("",new Pagination(0));
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(HelpServiceTest.class);
	}		
	
}
