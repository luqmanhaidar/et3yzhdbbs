package com.jeecms.cms.action;

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.jeecms.cms.entity.CmsMember;
import com.jeecms.core.JeeCoreManager;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("cms.cmsMemberAct")
public class CmsMemberAct extends com.jeecms.cms.CmsSysAction {
	public String doList() {
		this.pagination = cmsMemberMng.findAll(pageNo, getCookieCount());
		return LIST;
	}

	public String doAdd() {
		return ADD;
	}

	public String doSave() {
		cmsMemberMng.save(bean);
		addActionMessage("��ӳɹ�");
		return doAdd();
	}

	public String doEdit() {
		this.bean = cmsMemberMng.findById(id);
		return EDIT;
	}

	public String doUpdate() {
		cmsMemberMng.updateDefault(bean);
		addActionMessage("�޸ĳɹ�");
		return doList();
	}

	public String doDelete() {
		try {
			cmsMemberMng.deleteById(id);
			cmsMemberMng.deleteById(ids);
		} catch (DataIntegrityViolationException e) {
			addActionError("��¼�ѱ����ã�����ɾ��!");
		}
		return doList();
	}

	
	protected Object getBeanInput() {
		return getBean();
	}

	
	protected JeeCoreManager<CmsMember> getManager() {
		return cmsMemberMng;
	}

	private CmsMember bean;

	public CmsMember getBean() {
		return bean;
	}

	public void setBean(CmsMember bean) {
		this.bean = bean;
	}
}
