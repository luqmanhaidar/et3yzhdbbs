package com.jeecms.cms.manager.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.core.JeeCoreManagerImpl;
import com.jeecms.cms.dao.CmsConfigDao;
import com.jeecms.cms.entity.CmsConfig;
import com.jeecms.cms.manager.CmsConfigMng;
import com.ponyjava.common.hibernate3.Updater;

@Service
@Transactional
public class CmsConfigMngImpl extends JeeCoreManagerImpl<CmsConfig> implements
		CmsConfigMng {
	
	public Object updateByUpdater(Updater updater) {
		CmsConfig config = (CmsConfig) super.updateByUpdater(updater);
		return config;
	}

	
	public CmsConfig save(CmsConfig config) {
		super.save(config);
		return config;
	}

	
	public CmsConfig findById(Serializable id) {
		CmsConfig config = super.findById(id);
		return config;
	}

	
	public CmsConfig deleteById(Serializable id) {
		CmsConfig config = super.deleteById(id);
		return config;
	}

	@Autowired
	public void setCmsConfigDao(CmsConfigDao dao) {
		super.setDao(dao);
	}

	public CmsConfigDao getCmsConfigDao() {
		return (CmsConfigDao) super.getDao();
	}

}
