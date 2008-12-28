package com.jeecms.core.action;

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.jeecms.core.entity.Member;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("core.memberAct")
public class MemberAct extends com.jeecms.core.JeeCoreAction {
	public String doList() {
		this.pagination = memberMng.findAll(pageNo, getCookieCount());
		return LIST;
	}

	public String doAdd() {
		return ADD;
	}

	public String doSave() {
		memberMng.save(bean);
		return doAdd();
	}

	public String doEdit() {
		this.bean = memberMng.findById(id);
		return EDIT;
	}

	public String doUpdate() {
		memberMng.updateDefault(bean);
		addActionMessage("修改成功");
		return doList();
	}

	public String doDelete() {
		try {
			memberMng.deleteById(id);
			memberMng.deleteById(ids);
		} catch (DataIntegrityViolationException e) {
			addActionError("记录已被引用，不能删除!");
		}
		return doList();
	}

	private Member bean;

	public Member getBean() {
		return bean;
	}

	public void setBean(Member bean) {
		this.bean = bean;
	}
}
