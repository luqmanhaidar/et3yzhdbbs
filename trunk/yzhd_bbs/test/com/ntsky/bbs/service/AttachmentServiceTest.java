package com.ntsky.bbs.service;

import java.util.Date;

import com.ntsky.bbs.domain.Attachment;
import com.ntsky.bbs.service.AttachmentService;


public class AttachmentServiceTest extends NTskyServiceTestCase{

	private AttachmentService attachmentService;
	private Attachment attachment = null;
	
    protected void setUp() throws Exception {
    	attachmentService = (AttachmentService)super.getBean("attachmentService");
    	
    	attachment = new Attachment();
    	attachment.setTopicId(1);
    	attachment.setFilename("11.gif");
    	attachment.setFilesize(11);
    	attachment.setFiletype("gif");
    	attachment.setComments("1234");
    	attachment.setDownloadTimes(2);
    	attachment.setDateCreated(new Date());
    	attachment.setUserId(134);
    }
	
	public void testCreateAttachment(){
		attachmentService.createAttachment(attachment);
	}
	
	public void testGetAttachment(){
		assertNotNull(attachmentService.getAttachment(1));
	}

	public void testDeleteAttachment(){
		attachmentService.deleteAttachment(1);
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(AttachmentServiceTest.class);
	}		
	
}
