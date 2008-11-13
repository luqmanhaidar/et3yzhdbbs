package com.ntsky.bbs.dao.hibernate;

import java.util.List;

import com.ntsky.bbs.dao.PollVoterDAO;
import com.ntsky.bbs.domain.PollVoter;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ObjectExistException;

/**
 * 投票用户信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class PollVoterDAOHibernateImpl extends BaseDAOHibernateImpl implements PollVoterDAO {

	/**
	 * 投票用户列表
	 * @param pollId 投票编号
	 * @return 投票信息
	 */	
	public List findPollVoters(int pollId) throws DAOException {
		try{
			return super.find("from PollVoter as pv");
		}
		catch(DAOException daoException){
			throw new DAOException("取得投票用户列表失败...");
		}			
	}	
	
	/**
	 * 是否存在投票用户
	 * @param pollUser 用户名
	 * @param pollId 投票编号
	 * @return boolean 判断结果
	 * @throws DAOException
	 */
	public boolean isExistVoter(String pollUser,int pollId) throws DAOException {
		try{
			List list = super.find("from PollVoter as pv where pv.pollUser=? and pv.pollId='"+pollId+"'",new String[]{pollUser});
			if(list!=null&&list.size()>0){
				return true;
			}
			else{
				return false;
			}			
		}
		catch(DAOException daoException){
			throw new DAOException("判断投票用户是否存在发生错误.");
		}			
	}
	
}
