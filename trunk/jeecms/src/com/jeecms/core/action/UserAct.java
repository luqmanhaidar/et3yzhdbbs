package com.jeecms.core.action;

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.jeecms.core.entity.User;
import com.ponyjava.common.hibernate3.OrderBy;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("core.userAct")
public class UserAct extends com.jeecms.core.JeeCoreAction {
	public String doList() {
		this.pagination = userMng.findAll(pageNo, getCookieCount(),
				new OrderBy[] { OrderBy.desc("id") });
		return LIST;
	}

	public String doAdd() {
		return ADD;
	}

	public String doSave() {
		userMng.save(bean);
		addActionMessage("添加成功");
		return doList();
	}

	public String doEdit() {
		this.bean = userMng.findById(id);
		return EDIT;
	}

	public String doUpdate() {
		userMng.updateDefault(bean);
		addActionMessage("修改成功");
		return doList();
	}

	public String doDelete() {
		try {
			if (id != null) {
				userMng.deleteById(id);
			} else {
				userMng.deleteById(ids);
			}
		} catch (DataIntegrityViolationException e) {
			addActionError("记录已被引用，不能删除!");
		}
		return doList();
	}

	public String doEditPassword() {
		return "editPassword";
	}

	private User bean;

	public User getBean() {
		return bean;
	}

	public void setBean(User bean) {
		this.bean = bean;
	}
}
