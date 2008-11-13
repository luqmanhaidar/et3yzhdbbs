package com.ntsky.bbs.dao.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ntsky.bbs.domain.Attachment;
import com.ntsky.bbs.dao.AttachmentDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 帮助信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/26 14:01:10 $
 */
public class AttachmentDAOHibernateImpl extends BaseDAOHibernateImpl implements AttachmentDAO {

	/**
	 * 根据附件编号查找的附件信息
	 * @param attachmentId 附件编号
	 * @return Attachment 附件信息
	 */	
	public Attachment findAttachment(int attachmentId) {
		try {
			return  (Attachment)super.load(Attachment.class,new Long(attachmentId));
		}
		catch(DAOException de){
			throw new DAOException("查找附件信息发生错误...");
		}
	}
	
	/**
	 * 根据编号删除附件信息
	 *
	 * @param attachmentId 附件编号
	 */
	public void deleteAttachment(int attachmentId){
		try{
			super.executeHsql("delete from Attachment where id='"+ attachmentId +"'");
		}
		catch(DAOException de){
			throw new DAOException("删除附件信息发生错误...");
		}
	}
	
	/**
	 * 取得制定用户的附件列表
	 * 
	 * @param userId 用户编号
	 * @param filetype 文件类型
	 * @param pagination 分页对象
	 * @return QueryResult 论坛贴子列表
	 * @throws DAOException
	 */
	public QueryResult findAttachments(int userId,String filetype,Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Attachment.class); 
			detachedCriteria.add(Restrictions.eq("userId",new Integer(userId)));
			if( filetype != null ){
				
				detachedCriteria.add(Restrictions.eq("filetype",filetype));
			}
			return super.findItemsByCriteria(detachedCriteria,null,pagination);
		}
		catch(DAOException de){
			throw new DAOException("删除附件信息发生错误...");
		}
	}
	
	/**
	 * 更新下载次数
	 * @param attId 附件编号
	 * @throws ServiceException
	 */
	public void updateDownloadTimes(int attId) throws DAOException{
		try{
			super.executeHsql("update Attachment set downloadTimes=downloadTimes+1 where id="+attId);
		}
		catch(DAOException de){
			throw new DAOException("删除附件信息发生错误...");
		}		
	}
	
	
}