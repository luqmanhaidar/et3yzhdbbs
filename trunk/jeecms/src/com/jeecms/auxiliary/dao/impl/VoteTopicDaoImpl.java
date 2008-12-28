package com.jeecms.auxiliary.dao.impl;

import org.springframework.stereotype.Repository;

import com.jeecms.auxiliary.entity.VoteTopic;
import com.jeecms.auxiliary.dao.VoteTopicDao;
import com.jeecms.core.JeeCoreDaoImpl;

@Repository
public class VoteTopicDaoImpl extends JeeCoreDaoImpl<VoteTopic> implements
		VoteTopicDao {
	public VoteTopic getCurrentTopic(Long webId) {
		String hql = "from VoteTopic v where v.website.id=? and v.current=true and v.disabled=false order by v.id desc";
		return (VoteTopic) findUnique(hql, webId);
	}
}