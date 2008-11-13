package com.ntsky.bbs.service.impl;

import java.util.List;

import com.ntsky.bbs.dao.PollVoterDAO;
import com.ntsky.bbs.domain.PollVoter;
import com.ntsky.bbs.dao.PollResultDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.PollException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.PollResultService;
/**
 * 投票结果业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class PollResultServiceImpl implements PollResultService{

	// 投票用户
	private PollVoterDAO pollVoterDAO;
	public void setPollVoterDAO(PollVoterDAO pollVoterDAO){
		this.pollVoterDAO = pollVoterDAO;
	}
	
	// 投票结果 
	private PollResultDAO pollResultDAO;
	public void setPollResultDAO(PollResultDAO pollResultDAO){
		this.pollResultDAO = pollResultDAO;
	}	
	
	/**
	 * 执行投票
	 * @param pollVoter 投票者
	 * @throws ServiceException
	 */
	public void vote(PollVoter pollVoter) throws PollException,ServiceException {
		try{
			if(pollVoterDAO.isExistVoter(pollVoter.getPollUser(),pollVoter.getPollId())){
				throw new PollException("用户 "+pollVoter.getPollUser()+" 已投过票,不能重复投票");
			}
			else{
				// 添加投票用户
				pollVoterDAO.save(pollVoter);
				// 更新投票数
				pollResultDAO.vote(pollVoter.getPollResultId());
			}
		}
		catch(PollException pe){
			throw new PollException(pe.getMessage());
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
}
