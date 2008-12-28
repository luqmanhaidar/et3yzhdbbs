package com.jeecms.cms.manager.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.ChnlModelDao;
import com.jeecms.cms.entity.ChnlModel;
import com.jeecms.cms.entity.CmsConfig;
import com.jeecms.cms.manager.ChnlModelMng;
import com.jeecms.core.JeeCoreManagerImpl;
import com.ponyjava.common.hibernate3.Updater;

@Service
@Transactional
public class ChnlModelMngImpl extends JeeCoreManagerImpl<ChnlModel> implements
		ChnlModelMng {
	
	public Long[] getHasChildIds(String sysType) {
		List<ChnlModel> list = getChnlModelDao().getHasChild(sysType);
		Long[] ids = null;
		if (list != null && list.size() > 0) {
			ids = new Long[list.size()];
			for (int i = 0; i < list.size(); i++) {
				ids[i] = list.get(i).getId();
			}
		}
		return ids;
	}

	
	public List<ChnlModel> getModels(String sysType) {
		ChnlModel model = new ChnlModel();
		model.setSysType(sysType);
		return findByEgList(model);
	}

	
	public Object updateByUpdater(Updater updater) {
		ChnlModel model = (ChnlModel) super.updateByUpdater(updater);
		return model;
	}

	
	public ChnlModel save(ChnlModel model) {
		model.setConfig(new CmsConfig(model.getWebsite().getId()));
		super.save(model);
		return model;
	}

	
	public ChnlModel findById(Serializable id) {
		ChnlModel model = super.findById(id);
		return model;
	}

	
	public ChnlModel deleteById(Serializable id) {
		ChnlModel model = super.deleteById(id);
		return model;
	}

	@Autowired
	public void setChnlModelDao(ChnlModelDao dao) {
		super.setDao(dao);
	}

	public ChnlModelDao getChnlModelDao() {
		return (ChnlModelDao) super.getDao();
	}

}
