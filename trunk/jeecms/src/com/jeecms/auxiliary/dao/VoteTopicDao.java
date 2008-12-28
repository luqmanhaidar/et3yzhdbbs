package com.jeecms.auxiliary.dao;

import com.jeecms.auxiliary.entity.VoteTopic;
import com.jeecms.core.JeeCoreDao;

public interface VoteTopicDao extends JeeCoreDao<VoteTopic> {
	/**
	 * ��õ�ǰ���µ�ͶƱ
	 * 
	 * @param webId
	 * @return
	 */
	public VoteTopic getCurrentTopic(Long webId);
}