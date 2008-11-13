package com.ntsky.bbs.web.webwork.action.attachment;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.domain.Attachment;
import com.ntsky.bbs.service.AttachmentService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;
import com.opensymphony.webwork.interceptor.SessionAware;

/**
 * 论坛附件抽象类
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public abstract class AttachmentActionSupport extends BasicActionSupport {
	
	protected AttachmentService attachmentService;
	public void setAttachmentService(AttachmentService attachmentService){
		this.attachmentService = attachmentService;
	}
	
	private List attachments;
	public void setAttachments(List attachments){
		this.attachments = attachments;
	}
	public List getAttachments(){
		return this.attachments;
	}	
	
}
