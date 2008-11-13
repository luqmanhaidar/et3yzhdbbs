package com.ntsky.bbs.web.webwork.action.attachment;

import java.util.List;

import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.opensymphony.xwork.Preparable;

/**
 * 用户附件管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */
public class UserAttachmentManageAction extends AttachmentActionSupport {

	private String filetype;
	public void setFiletype( String filetype ){
		this.filetype = filetype;
	}
	
	/**
	 * 附件管理
	 * <pre>
	 * 	执行成功迁移到 attachments.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("列表论坛全部附件...");
		}
		try{
			QueryResult queryResult = attachmentService.getAttachments(super.getSessionUser().getId().intValue(),filetype,new Pagination(getPaginationStart()));
			setAttachments(queryResult.getItems());
			setPagination(queryResult.getPagination());
		}
		catch(DAOException de){
			throw new ActionException(de);
		}
		return SUCCESS;
    }
	
}
