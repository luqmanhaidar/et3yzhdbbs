package com.ntsky.bbs.dao;

import java.util.List;

import com.ntsky.bbs.domain.Poll;
import com.ntsky.bbs.exception.DAOException;

/**
 * 投票模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:26 $ 
 */
public interface PollVoterDAO extends BaseDAO{

	/**
	 * 投票用户列表
	 * @param pollId 投票编号
	 * @return 投票信息
	 */	
	public List findPollVoters(int pollId) throws DAOException ;

	/**
	 * 是否存在投票用户
	 * @param pollUser 用户名
	 * @param pollId 投票编号
	 * @return boolean 判断结果
	 * @throws DAOException
	 */
	public boolean isExistVoter(String pollUser,int pollId) throws DAOException;
	
}
