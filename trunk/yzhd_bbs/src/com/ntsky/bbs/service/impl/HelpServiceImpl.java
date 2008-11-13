package com.ntsky.bbs.service.impl;

import java.util.List;
import java.util.List;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.ntsky.framework.util.DateUtil;

import com.ntsky.bbs.dao.HelpDAO;
import com.ntsky.bbs.domain.Help;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.HelpService;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;


/**
 * 帮助信息数据事务处理实例
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:26 $
 */
public class HelpServiceImpl implements HelpService {

	private final static Logger logger = Logger.getLogger(HelpServiceImpl.class);
	
	private HelpDAO helpDAO;
	
	public void setHelpDAO(HelpDAO helpDAO){
		this.helpDAO = helpDAO;
	}
	
	/**
	 * 创建帮助信息
	 * 
	 * @param help 帮助信息
	 */
	public void createHelp(Help help) {
		help.setDateCreated(new Date());
		helpDAO.save(help);
	}

	/**
	 * 修改帮助信息
	 * 
	 * @param help 帮助
	 */
	public void editHelp(Help help) throws ServiceException {
		try {
			Help tempHelp = helpDAO.findHelp(help.getId().intValue());
			tempHelp.setTitle(help.getTitle());
			tempHelp.setType(help.getType());
			tempHelp.setIsHand(help.getIsHand());
			tempHelp.setContent(help.getContent());
			helpDAO.update(tempHelp);
		}
		catch(DAOException daoException){
			throw new ServiceException("更新帮助 ["+help.getTitle()+"] 发生错误.");
		}
	}
	
	/**
	 * 删除帮助信息
	 * 
	 * @param helpId 帮助编号
	 * @throws ServiceException
	 */
	public void deleteHelp(int helpId) throws ServiceException {
		try {
			helpDAO.delete(getHelp(helpId));
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}

	/**
	 * 取得帮助
	 * 
	 * @param helpId 帮助编号
	 * @return 编号对应的帮助信息
	 * @throws ServiceException 
	 */
	public Help getHelp(int helpId) throws ServiceException {
		Help help = null;
		try {
			help = helpDAO.findHelp(helpId);;
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
		return help;
	}

	/**
	 * 分页取得全部的帮助信息
	 * 
	 * @param title 标题
	 * @param pagination 分页bean
	 * @return
	 */
	public QueryResult getHelps (String title, Pagination pagination){
		return helpDAO.findHelps(title,pagination);
	}
	
	/**
	 * 根据帮助类型查找帮助
	 * @param type 帮助类型
	 * @return 帮助列表
	 * @throws ServiceException
	 */
	public List getHelps(int type) throws ServiceException{
		try{
			return helpDAO.findHelps(type);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}	
}
