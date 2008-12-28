package com.jeecms.cms;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.entity.CmsConfig;
import com.jeecms.cms.manager.CmsConfigMng;
import com.jeecms.core.IndeBaseAction;

/**
 * JEECMS����ҳ���action���ȡ�
 * <p>
 * ����CmsConfig���ú͵�ǰϵͳģ�巽��
 * </p>
 * 
 * @author liufang
 * 
 */
@SuppressWarnings("serial")
public abstract class CmsIndeAction extends IndeBaseAction {
	
	protected String getSolution() {
		return getConfig().getSolution(getSysType());
	}

	public CmsConfig getConfig() {
		return cmsConfigMng.findById(getWebId());
	}

	@Autowired
	protected CmsConfigMng cmsConfigMng;
}
