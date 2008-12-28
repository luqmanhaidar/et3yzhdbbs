package com.jeecms.cms.manager.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.jeecms.cms.dao.CmsAdminDao;
import com.jeecms.cms.entity.CmsAdmin;
import com.jeecms.cms.manager.CmsAdminMng;
import com.jeecms.core.JeeCoreManagerImpl;
import com.jeecms.core.entity.Admin;
import com.jeecms.core.manager.AdminMng;
import com.ponyjava.common.hibernate3.Updater;

@Service
@Transactional
public class CmsAdminMngImpl extends JeeCoreManagerImpl<CmsAdmin> implements
		CmsAdminMng {
	
	public CmsAdmin register(CmsAdmin cmsAdmin, boolean isExist) {
		Assert.notNull(cmsAdmin);
		Admin admin = cmsAdmin.getAdmin();
		Assert.notNull(admin);
		admin.setWebsite(cmsAdmin.getWebsite());
		Admin oadmin = adminMng.register(admin, isExist);
		CmsAdmin ocadmin = findById(oadmin.getId());
		if (ocadmin != null) {
			return ocadmin;
		} else {
			cmsAdmin.setAdmin(oadmin);
			return save(cmsAdmin);
		}
	}

	
	public Object updateByUpdater(Updater updater) {
		CmsAdmin admin = (CmsAdmin) super.updateByUpdater(updater);
		return admin;
	}

	
	public CmsAdmin save(CmsAdmin admin) {
		super.save(admin);
		return admin;
	}

	
	public CmsAdmin findById(Serializable id) {
		CmsAdmin admin = super.findById(id);
		return admin;
	}

	
	public CmsAdmin deleteById(Serializable id) {
		CmsAdmin admin = super.deleteById(id);
		return admin;
	}

	@Autowired
	private AdminMng adminMng;

	@Autowired
	public void setCmsAdminDao(CmsAdminDao dao) {
		super.setDao(dao);
	}

	public CmsAdminDao getCmsAdminDao() {
		return (CmsAdminDao) super.getDao();
	}
}
