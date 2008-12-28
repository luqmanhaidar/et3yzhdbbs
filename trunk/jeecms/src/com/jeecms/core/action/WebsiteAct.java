package com.jeecms.core.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.jeecms.core.JeeCoreAction;
import com.jeecms.core.entity.EmailSender;
import com.jeecms.core.entity.Website;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("core.websiteAct")
public class WebsiteAct extends JeeCoreAction {
	public String doList() {
		this.pagination = websiteMng.getAllWebsite(pageNo, getCookieCount(),
				true);
		return LIST;
	}

	public String doAdd() {
		this.unitedList = websiteMng.getAllWebsite();
		this.parentList = unitedList;
		return ADD;
	}

	public String doSave() {
		if (bean.getUnited() != null && bean.getUnited().getId() == null) {
			bean.setUnited(null);
		}
		if (bean.getParent() != null && bean.getParent().getId() == null) {
			bean.setParent(null);
		}
		websiteMng.saveWebsite(bean);
		addActionMessage("添加成功！");
		return doList();
	}

	public String doEdit() {
		this.bean = websiteMng.getWebsite(id);
		this.unitedList = websiteMng.getAllWebsite();
		this.parentList = unitedList;
		return EDIT;
	}

	public String doUpdate() {
		websiteMng.updateWebsite(bean);
		addActionMessage("修改成功");
		return doList();
	}

	public String doEmailEdit() {
		this.email = getWeb().getEmailSender();
		return "emailEdit";
	}

	public String doEmailUpdate() {
		Website updater = new Website();
		if (email.getUserPwd() != null && email.getUserPwd().trim().equals("")) {
			email.setUserPwd(null);
		}
		updater.setEmailSender(email);
		updater.setId(getWebId());
		websiteMng.updateWebsite(updater);
		addActionMessage("修改成功");
		return doEmailEdit();
	}

	public String doSiteEdit() {
		this.bean = getWeb();
		return "siteEdit";
	}

	public String doSiteUpdate() {
		bean.setId(getWebId());
		websiteMng.updateWebsite(bean);
		addActionMessage("修改成功");
		return doSiteEdit();
	}

	public String doDelete() {
		try {
			if (ids != null && ids.length > 0) {
				for (Long webId : ids) {
					websiteMng.removeWebsite(webId);
				}
			} else if (id != null) {
				websiteMng.removeWebsite(id);
			}
		} catch (DataIntegrityViolationException e) {
			addActionError("记录已被引用，不能删除!");
		}
		return doList();
	}

	private Website bean;
	private EmailSender email;
	private List<Website> unitedList;
	private List<Website> parentList;

	public Website getBean() {
		return bean;
	}

	public void setBean(Website bean) {
		this.bean = bean;
	}

	public List<Website> getUnitedList() {
		return unitedList;
	}

	public void setUnitedList(List<Website> unitedList) {
		this.unitedList = unitedList;
	}

	public List<Website> getParentList() {
		return parentList;
	}

	public void setParentList(List<Website> parentList) {
		this.parentList = parentList;
	}

	public EmailSender getEmail() {
		return email;
	}

	public void setEmail(EmailSender email) {
		this.email = email;
	}
}
