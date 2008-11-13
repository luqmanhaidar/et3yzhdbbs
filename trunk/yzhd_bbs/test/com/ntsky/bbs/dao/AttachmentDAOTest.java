package com.ntsky.bbs.dao;

import java.util.Date;

import com.ntsky.bbs.domain.Attachment;

/**
 * 管理模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class AttachmentDAOTest extends BaseDAOCase{

    private AttachmentDAO attachmentDAO = null;
    private Attachment attachment = null;
    
    public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
        this.attachmentDAO = attachmentDAO;
    }
    
    public void testSaveAttachment(){
    	attachment = new Attachment();
    	attachment.setTopicId(1);
    	attachment.setFilename("11.gif");
    	attachment.setFilesize(11);
    	attachment.setFiletype("gif");
    	attachment.setComments("1234");
    	attachment.setDownloadTimes(1);
    	attachment.setDateCreated(new Date());
    	attachment.setUserId(134);
    	attachmentDAO.save(attachment);
    }
    
    public void testFindAttachment(){
    	//assertNull(adminDAO.findAdmin(100));
    	Attachment tempAdmin = attachmentDAO.findAttachment(1);
    	System.out.println(tempAdmin);
    }

}
