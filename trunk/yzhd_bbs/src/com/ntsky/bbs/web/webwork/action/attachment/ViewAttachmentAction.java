package com.ntsky.bbs.web.webwork.action.attachment;

import java.util.List;

import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.domain.Attachment; 
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 查看用户上传的附件
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */
public class ViewAttachmentAction extends AttachmentActionSupport {

	private int attId;
	public void setAttId(int attId){
		this.attId = attId;
	}
	
	private Attachment attachment;
	public Attachment getAttachment(){
		return attachment;
	}
	
	/**
	 * 查看附件
	 * <pre>
	 * 	执行成功迁移到 attachment.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("查看附件...");
		}
		try{
			attachment = attachmentService.getAttachment(attId);
		}
		catch(DAOException de){
			throw new ActionException(de);
		}
		if(logger.isInfoEnabled()){
			logger.info("取得附件"+attachment.getFilename()+"的信息并返回到显示页面...");
		}		
		return SUCCESS;
    }
		
}
