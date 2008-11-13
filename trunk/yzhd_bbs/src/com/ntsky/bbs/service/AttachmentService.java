package com.ntsky.bbs.service;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.domain.Attachment;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 附件业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface AttachmentService extends BaseService{

	/**
	 * 根据附件编号取得附件详细信息
	 * @param attachmentId 附件编号
	 * @return Attachment 附件信息
	 */	
	public Attachment getAttachment(int attachmentId) throws ServiceException ;
	
	/**
	 * 删除附件信息
	 * 
	 * @param attachementId 附件编号
	 */
	public void deleteAttachment(int attachementId) throws ServiceException ;

	/**
	 * 删除多个附件信息
	 * 
	 * @param ids 附件编号集合
	 */
	public void deleteMoreAttachment(int[] ids) throws ServiceException ;
	
	
	/**
	 * 修改附件信息
	 * 
	 * @param attachment 附件信息
	 */
	public void editAttachment(Attachment attachment) throws ServiceException ;
	
	/**
	 * 创建附件信息
	 * 
	 * @param attachment 附件信息
	 */
	public void createAttachment(Attachment attachment) throws ServiceException ;
	
	/**
	 * 取得制定用户的附件列表
	 * 
	 * @param userId 用户编号
	 * @param filetype 文件类型
	 * @param pagination 分页对象
	 * @return QueryResult 论坛贴子列表
	 * @throws ServiceException
	 */
	public QueryResult getAttachments(int userId,String filetype,Pagination pagination) throws ServiceException ;	

	/**
	 * 更新下载次数
	 * @param attId 附件编号
	 * @throws ServiceException
	 */
	public void updateDownloadTimes(int attId) throws ServiceException;
}
