package com.ntsky.bbs.service;

import java.util.List;

import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.domain.Poll;
import com.ntsky.bbs.exception.LoginException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.exception.UserException;

/**
 * 投票模块业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface PollService {

	/**
	 * 删除投票
	 * 
	 * @param pollId 投票编号
	 */
	public void deletePoll(int pollId) throws ServiceException ;

	/**
	 * 查找投票信息
	 * @param topicId
	 * @return
	 * @throws ServiceException
	 */
	public Poll findPoll(int topicId) throws ServiceException ;
	
}
