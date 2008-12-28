package com.jeecms.cms.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jeecms.cms.entity.CmsConfig;
import com.jeecms.cms.manager.CmsConfigMng;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("cms.cmsConfigAct")
public class CmsConfigAct extends com.jeecms.core.JeeCoreAction {

	public String doConfigEdit() {
		this.bean = cmsConfigMng.findById(getWebId());
		return EDIT;
	}

	public String doConfigUpdate() {
		bean.setId(getWebId());
		cmsConfigMng.updateDefault(bean);
		addActionMessage("ÐÞ¸Ä³É¹¦");
		return doConfigEdit();
	}

	@Autowired
	private CmsConfigMng cmsConfigMng;
	private CmsConfig bean;

	public CmsConfig getBean() {
		return bean;
	}

	public void setBean(CmsConfig bean) {
		this.bean = bean;
	}
}
