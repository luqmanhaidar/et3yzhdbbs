package com.jeecms.auxiliary.manager.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.core.JeeCoreManagerImpl;
import com.jeecms.auxiliary.dao.AuxiConfigDao;
import com.jeecms.auxiliary.entity.AuxiConfig;
import com.jeecms.auxiliary.manager.AuxiConfigMng;
import com.ponyjava.common.hibernate3.Updater;

@Service
@Transactional
public class AuxiConfigMngImpl extends JeeCoreManagerImpl<AuxiConfig> implements
		AuxiConfigMng {
	
	public Object updateByUpdater(Updater updater) {
		AuxiConfig config = (AuxiConfig) super.updateByUpdater(updater);
		return config;
	}

	
	public AuxiConfig save(AuxiConfig config) {
		super.save(config);
		return config;
	}

	
	public AuxiConfig findById(Serializable id) {
		AuxiConfig config = super.findById(id);
		return config;
	}

	
	public AuxiConfig deleteById(Serializable id) {
		AuxiConfig config = super.deleteById(id);
		return config;
	}

	@Autowired
	public void setAuxiConfigDao(AuxiConfigDao dao) {
		super.setDao(dao);
	}

	public AuxiConfigDao getAuxiConfigDao() {
		return (AuxiConfigDao) super.getDao();
	}
}
