package com.ntsky.bbs.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.ntsky.framework.util.security.MD5;

import com.ntsky.bbs.domain.Poll;
import com.ntsky.bbs.dao.PollDAO;
import com.ntsky.bbs.service.PollService;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.LoginException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.exception.UserException;

/**
 * 管理员模块业务处理实现
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class PollServiceImpl implements PollService{
	
	public static Logger logger = Logger.getLogger(PollServiceImpl.class); 
	
	private PollDAO pollDAO;
	
	public void setPollDAO(PollDAO pollDAO){
		this.pollDAO = pollDAO;
	}

	/**
	 * 删除投票
	 * 
	 * @param pollId 投票编号
	 */
	public void deletePoll(int pollId) throws ServiceException {
		try{
			pollDAO.delete(pollDAO.load(Poll.class,pollId));
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}
	
	/**
	 * 查找投票信息
	 * @param topicId
	 * @return
	 * @throws ServiceException
	 */
	public Poll findPoll(int topicId) throws ServiceException {
		try{
			return pollDAO.findPoll(topicId);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}
	
}
