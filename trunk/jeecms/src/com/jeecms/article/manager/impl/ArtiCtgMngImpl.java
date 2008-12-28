package com.jeecms.article.manager.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.article.dao.ArtiCtgDao;
import com.jeecms.article.entity.ArtiCtg;
import com.jeecms.article.manager.ArtiCtgMng;
import com.jeecms.core.JeeCoreManagerImpl;
import com.jeecms.core.entity.Website;
import com.ponyjava.common.hibernate3.Updater;

@Service
@Transactional
public class ArtiCtgMngImpl extends JeeCoreManagerImpl<ArtiCtg> implements
		ArtiCtgMng {
	public ArtiCtg getArtiCtg(Long webId, String label) {
		return getDao().getArtiCtg(webId, label);
	}

	public List<ArtiCtg> getList(Long webId, Boolean disabled) {
		ArtiCtg eg = new ArtiCtg();
		eg.setWebsite(new Website(webId));
		eg.setDisabled(disabled);
		return findByEgList(eg);
	}

	
	public Object updateByUpdater(Updater updater) {
		ArtiCtg ctg = (ArtiCtg) super.updateByUpdater(updater);
		return ctg;
	}

	
	public ArtiCtg save(ArtiCtg ctg) {
		super.save(ctg);
		return ctg;
	}

	
	public ArtiCtg findById(Serializable id) {
		ArtiCtg ctg = super.findById(id);
		return ctg;
	}

	
	public ArtiCtg deleteById(Serializable id) {
		ArtiCtg ctg = super.deleteById(id);
		return ctg;
	}

	@Autowired
	public void setArtiCtgDao(ArtiCtgDao dao) {
		super.setDao(dao);
	}

	public ArtiCtgDao getDao() {
		return (ArtiCtgDao) super.getDao();
	}

}
