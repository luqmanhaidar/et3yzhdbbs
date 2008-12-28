package com.jeecms.cms.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.CmsChannelDao;
import com.jeecms.cms.entity.CmsChannel;
import com.jeecms.core.JeeCoreDaoImpl;

@Repository
public class CmsChannelDaoImpl extends JeeCoreDaoImpl<CmsChannel> implements
		CmsChannelDao {
	
	public CmsChannel getByPath(String path) {
		String hql = "from CmsChannel c where c.path=?";
		return (CmsChannel) findUnique(hql, path);
	}

	@SuppressWarnings("unchecked")
	
	public List<CmsChannel> getRoots(String sysType) {
		String hql = "select chnl from CmsChannel chnl where chnl.parent.id is null and chnl.sysType=? order by chnl.priority asc";
		return find(hql, sysType);
	}

	
	public List<CmsChannel> getRoots(String sysType, Long[] hasChildIds) {
		String s = StringUtils.join(hasChildIds, ',');
		getSession().enableFilter("channelFilter").setParameter("hasChild", s);
		return getRoots(sysType);
	}
}