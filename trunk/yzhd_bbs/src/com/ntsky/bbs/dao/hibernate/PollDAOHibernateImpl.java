package com.ntsky.bbs.dao.hibernate;

import java.util.List;

import com.ntsky.bbs.dao.PollDAO;
import com.ntsky.bbs.domain.Poll;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ObjectExistException;

/**
 * 投票信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:30 $
 */
public class PollDAOHibernateImpl extends BaseDAOHibernateImpl implements PollDAO {

	/**
	 * 根据主题编号查找投票信息
	 * @param topicId 主题编号
	 * @return Poll 投票信息
	 */	
	public Poll findPoll(int topicId) throws DAOException {
		try{
			return (Poll)super.find("from Poll as p left outer join fetch p.pollResults where p.topicId="+topicId).get(0);
		}
		catch(DAOException daoException){
			throw new DAOException("根据主题编号查找投票信息发生错误.");
		}			
	}	
	
}
