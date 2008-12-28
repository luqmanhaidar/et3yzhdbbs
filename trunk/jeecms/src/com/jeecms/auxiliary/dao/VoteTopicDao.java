package com.jeecms.auxiliary.dao;

import com.jeecms.auxiliary.entity.VoteTopic;
import com.jeecms.core.JeeCoreDao;

public interface VoteTopicDao extends JeeCoreDao<VoteTopic> {
	/**
	 * 获得当前最新的投票
	 * 
	 * @param webId
	 * @return
	 */
	public VoteTopic getCurrentTopic(Long webId);
}