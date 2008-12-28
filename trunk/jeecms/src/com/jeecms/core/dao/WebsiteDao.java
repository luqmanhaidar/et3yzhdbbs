package com.jeecms.core.dao;

import java.util.List;

import com.jeecms.core.JeeCoreDao;
import com.jeecms.core.entity.Website;

public interface WebsiteDao extends JeeCoreDao<Website> {
	public List<Website> getListByUserUnited(Long unitedId);
}
