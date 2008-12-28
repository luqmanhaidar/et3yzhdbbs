package com.jeecms.auxiliary.action;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.jeecms.auxiliary.entity.Msg;
import com.jeecms.auxiliary.manager.MsgMng;
import com.jeecms.core.JeeCoreManager;
import com.ponyjava.common.hibernate3.OrderBy;
import com.ponyjava.common.util.ComUtils;
import com.ponyjava.common.util.StrUtils;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("auxiliary.msgAct")
public class MsgAct extends com.jeecms.auxiliary.AuxiSysAction {
	public String doList() {
		this.pagination = msgMng.findAll(pageNo, getCookieCount(), OrderBy
				.desc("id"));
		return LIST;
	}

	public String doSave() {
		msgMng.save(bean);
		addActionMessage("添加成功");
		return doList();
	}

	public String doEdit() {
		bean = msgMng.findById(id);
		return EDIT;
	}

	public String doUpdate() {
		bean.setAdmin(getAdmin());
		bean.setReplyTime(ComUtils.now());
		handleString(bean);
		msgMng.updateDefault(bean);
		addActionMessage("修改成功");
		return doList();
	}

	private void handleString(Msg m) {
		String title = m.getTitle();
		m.setTitle(StringEscapeUtils.escapeHtml(title));
		String cm = m.getContentMember();
		String ca = m.getContentAdmin();
		cm = StrUtils.getCn(cm, getConfig().getMsgMaxSize());
		ca = StrUtils.getCn(ca, getConfig().getMsgMaxSize());
		m.setContentMember(StrUtils.txt2htm(cm));
		m.setContentAdmin(StrUtils.txt2htm(ca));
	}

	public String doDelete() {
		try {
			if (id != null) {
				msgMng.deleteById(id);
			} else {
				msgMng.deleteById(ids);
			}
		} catch (DataIntegrityViolationException e) {
			addActionError("记录已被引用，不能删除!");
		}
		return doList();
	}

	
	protected Object getBeanInput() {
		return getBean();
	}

	
	protected JeeCoreManager<Msg> getManager() {
		return msgMng;
	}

	@Autowired
	private MsgMng msgMng;
	private Msg bean;

	public Msg getBean() {
		return bean;
	}

	public void setBean(Msg bean) {
		this.bean = bean;
	}
}
