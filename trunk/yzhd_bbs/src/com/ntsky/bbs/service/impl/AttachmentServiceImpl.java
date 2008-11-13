package com.ntsky.bbs.service.impl;

import java.util.List;
import java.util.Date;

import org.apache.log4j.Logger;

import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.FileUtil;
import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.dao.AttachmentDAO;
import com.ntsky.bbs.domain.Attachment;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.AttachmentService;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;


/**
 * 附件信息数据事务处理实例
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:26 $
 */
public class AttachmentServiceImpl implements AttachmentService {

	private final static Logger logger = Logger.getLogger(AttachmentServiceImpl.class);
	
	private AttachmentDAO attachmentDAO;
	
	public void setAttachmentDAO(AttachmentDAO attachmentDAO){
		this.attachmentDAO = attachmentDAO;
	}
	
	/**
	 * 创建附件信息
	 * 
	 * @param attachment 附件信息
	 */
	public void createAttachment(Attachment attachment) throws ServiceException {
		try{
			attachmentDAO.save(attachment);
		}
		catch(DAOException de){
			logger.error("创建附件失败",de);
			throw new ServiceException("创建附件失败");
		}
	}
	
	/**
	 * 修改附件信息
	 * 
	 * @param attachment 附件信息
	 */
	public void editAttachment(Attachment attachment) throws ServiceException {
		/*Help tempHelp = helpDAO.findHelp(help.getId());
		tempHelp.setTitle(help.getTitle());
		tempHelp.setType(help.getType());
		tempHelp.setContent(help.getContent());*/
		attachmentDAO.update(attachment);
	}
	
	/**
	 * 删除附件信息
	 * 
	 * @param attachementId 附件编号
	 */
	public void deleteAttachment(int attachementId) throws ServiceException {
		Attachment attachment = null;
		try{
			attachment = attachmentDAO.findAttachment(attachementId);
			attachmentDAO.delete(attachment);
			String attPath = StringUtil.applyRelativePath(attachment.getFolder(),attachment.getFilename());
			if(logger.isDebugEnabled()){
				logger.debug("附件路径为 : " + attPath);
			}
			// 删除附件对应的文件
			FileUtil.deleteFile(attPath);
		}
		catch(DAOException de){
			throw new ServiceException("删除附件失败...");
		}
	}
	
	/**
	 * 删除多个附件信息
	 * 
	 * @param ids 附件编号集合
	 */
	public void deleteMoreAttachment(int[] ids) throws ServiceException {
		try{
			for (int i = 0; i < ids.length; i++) {
				this.deleteAttachment(ids[i]);
			}
		}
		catch(ServiceException de){
			throw new ServiceException("删除多个附件失败...");
		}		
	}	
	
	/**
	 * 根据附件编号取得附件
	 * 
	 * @param attachementId 附件编号
	 * @return Attachment 附件信息
	 */
	public Attachment getAttachment(int attachementId) throws ServiceException {
		try {
			return attachmentDAO.findAttachment(attachementId);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
	/**
	 * 取得制定用户的附件列表
	 * 
	 * @param userId 用户编号
	 * @param filetype 文件类型
	 * @param pagination 分页对象
	 * @return QueryResult 论坛贴子列表
	 * @throws ServiceException
	 */
	public QueryResult getAttachments(int userId,String filetype,Pagination pagination) throws ServiceException {
		try {
			return attachmentDAO.findAttachments(userId,filetype,pagination);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
	/**
	 * 更新下载次数
	 * @param attId 附件编号
	 * @throws ServiceException
	 */
	public void updateDownloadTimes(int attId) throws ServiceException{
		try{
			attachmentDAO.updateDownloadTimes(attId);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}	
	}
	
}
