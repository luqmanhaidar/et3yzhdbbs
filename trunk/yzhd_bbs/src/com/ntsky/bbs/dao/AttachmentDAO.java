package com.ntsky.bbs.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ntsky.bbs.domain.Attachment;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 帮助模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface AttachmentDAO extends BaseDAO{

	/**
	 * 根据附件编号查找的附件信息
	 * @param attachmentId 附件编号
	 * @return Attachment 附件信息
	 */	
	public Attachment findAttachment(int attachmentId) throws DAOException ;
	
	/**
	 * 根据编号删除附件信息
	 *
	 * @param attachmentId 附件编号
	 */
	public void deleteAttachment(int attachmentId) throws DAOException ;
	
	/**
	 * 取得制定用户的附件列表
	 * 
	 * @param userId 用户编号
	 * @param filetype 文件类型
	 * @param pagination 分页对象
	 * @return QueryResult 论坛贴子列表
	 * @throws DAOException
	 */
	public QueryResult findAttachments(int userId,String filetype,Pagination pagination) throws DAOException ;
	
	
	/**
	 * 更新下载次数
	 * @param attId 附件编号
	 * @throws ServiceException
	 */
	public void updateDownloadTimes(int attId) throws DAOException;
}
