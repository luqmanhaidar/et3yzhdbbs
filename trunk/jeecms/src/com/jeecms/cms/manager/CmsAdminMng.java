package com.jeecms.cms.manager;

import com.jeecms.cms.entity.CmsAdmin;
import com.jeecms.core.JeeCoreManager;
import com.jeecms.core.exception.UserRegisterException;

public interface CmsAdminMng extends JeeCoreManager<CmsAdmin> {
	/**
	 * ×¢²ácms»áÔ±
	 * 
	 * @param cmsAdmin
	 * @param isExist
	 * @return
	 * @throws UserRegisterException
	 */
	public CmsAdmin register(CmsAdmin cmsAdmin, boolean isExist)
			throws UserRegisterException;
}