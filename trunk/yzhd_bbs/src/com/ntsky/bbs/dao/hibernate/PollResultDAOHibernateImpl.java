package com.ntsky.bbs.dao.hibernate;

import java.util.List;

import com.ntsky.bbs.dao.PollResultDAO;
import com.ntsky.bbs.exception.DAOException;

/**
 * 投票信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class PollResultDAOHibernateImpl extends BaseDAOHibernateImpl implements PollResultDAO {

	/**
	 * 根据投票编号列表投票选项
	 * @param pollId 投票编号
	 * @return List 投票选项列表
	 */	
	public List findPollResults(int pollId) throws DAOException {
		try{
			return super.find("from PollResult as pr where pr.pollId='"+pollId+"'");	
		}
		catch(DAOException daoException){
			throw new DAOException("根据投票编号列表投票选项发生错误.");
		}			
	}	
	
	/**
	 * 对选择的答案投票
	 * @param prId
	 * @throws DAOException
	 */
	public void vote(int prId) throws DAOException {
		try{
			super.executeHsql("update PollResult set votes=votes+1 where id="+prId);
		}
		catch(DAOException daoException){
			throw new DAOException("用户投票失败...");
		}			
	}
	
}
