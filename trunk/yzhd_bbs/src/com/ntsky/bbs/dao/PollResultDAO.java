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
public interface PollResultDAO extends BaseDAO{

	/**
	 * 根据投票编号列表投票选项
	 * @param pollId 投票编号
	 * @return List 投票选项列表
	 */	
	public List findPollResults(int pollId) throws DAOException ;
	
	/**
	 * 对选择的答案投票
	 * @param prId
	 * @throws DAOException
	 */
	public void vote(int prId) throws DAOException;

}
