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
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:25 $ 
 */
public interface PollDAO extends BaseDAO{

	/**
	 * 根据主题编号查找投票信息
	 * @param topicId 主题编号
	 * @return Poll 投票信息
	 */	
	public Poll findPoll(int topicId) throws DAOException ;

}
