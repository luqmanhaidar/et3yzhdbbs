package com.ntsky.bbs.web.webwork.action.attachment;

import java.util.List;

import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.opensymphony.xwork.Preparable;

/**
 * 删除用户上传的附件
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */
public class DeleteAttachmentAction extends AttachmentActionSupport {

	private int attachmentId;
	public void setAttachmentId(int attachmentId){
		this.attachmentId = attachmentId;
	}
	
	/**
	 * 删除附件
	 * <pre>
	 * 	执行成功迁移到 attachments.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("删除附件...");
		}
		try{
			attachmentService.deleteAttachment(attachmentId);
		}
		catch(DAOException de){
			throw new ActionException(de);
		}
		return SUCCESS;
    }
	
	private int[] ids;
	public void setId(int[] ids){
		this.ids = ids;
	}	
	/**
	 * 删除多个附件
	 * @return
	 * @throws Exception
	 */
	public String deleteMore() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("删除多个附件...");
		}
		try{
			attachmentService.deleteMoreAttachment(ids);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}	
	
}
