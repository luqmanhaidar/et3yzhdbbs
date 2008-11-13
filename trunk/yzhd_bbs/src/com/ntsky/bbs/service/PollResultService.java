package com.ntsky.bbs.service;

import com.ntsky.bbs.domain.PollVoter;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 投票结果业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface PollResultService {

	/**
	 * 执行投票
	 * @param pollVoter 投票者
	 * @throws ServiceException
	 */
	public void vote(PollVoter pollVoter) throws ServiceException ;
	
}
