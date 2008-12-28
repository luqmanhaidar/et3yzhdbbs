package com.jeecms.auxiliary.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.jeecms.auxiliary.entity.MsgCtg;
import com.jeecms.auxiliary.manager.MsgCtgMng;
import com.jeecms.core.JeeCoreManager;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("auxiliary.msgCtgAct")
public class MsgCtgAct extends com.jeecms.auxiliary.AuxiSysAction {
	public String doList() {
		this.pagination = msgCtgMng.findAll(pageNo, getCookieCount());
		return LIST;
	}

	public String doAdd() {
		return ADD;
	}

	public String doSave() {
		msgCtgMng.save(bean);
		addActionMessage("添加成功");
		return doList();
	}

	public String doEdit() {
		this.bean = msgCtgMng.findById(id);
		return EDIT;
	}

	public String doUpdate() {
		msgCtgMng.updateDefault(bean);
		addActionMessage("修改成功");
		return doList();
	}

	public String doDelete() {
		try {
			if (id != null) {
				msgCtgMng.deleteById(id);
			} else {
				msgCtgMng.deleteById(ids);
			}
		} catch (DataIntegrityViolationException e) {
			addActionError("记录已被引用，不能删除!");
		}
		return doList();
	}

	
	protected Object getBeanInput() {
		return getBean();
	}

	
	protected JeeCoreManager<MsgCtg> getManager() {
		return msgCtgMng;
	}

	@Autowired
	private MsgCtgMng msgCtgMng;
	private MsgCtg bean;

	public MsgCtg getBean() {
		return bean;
	}

	public void setBean(MsgCtg bean) {
		this.bean = bean;
	}
}
