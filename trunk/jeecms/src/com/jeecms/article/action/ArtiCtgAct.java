package com.jeecms.article.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.jeecms.article.entity.ArtiCtg;
import com.jeecms.article.manager.ArtiCtgMng;
import com.jeecms.core.JeeCoreManager;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("article.artiCtgAct")
public class ArtiCtgAct extends com.jeecms.cms.CmsSysAction {
	public String doList() {
		this.pagination = artiCtgMng.findAll(pageNo, getCookieCount());
		return LIST;
	}

	public String doAdd() {
		return ADD;
	}

	public String doSave() {		
		artiCtgMng.save(bean);
		addActionMessage("添加成功");
		return doList();
	}

	public String doEdit() {
		this.bean = artiCtgMng.findById(id);
		return EDIT;
	}

	public String doUpdate() {
		artiCtgMng.updateDefault(bean);
		addActionMessage("修改成功");
		return doList();
	}

	public String doDelete() {
		try {
			if(id!=null) {
				artiCtgMng.deleteById(id);
			} else {
				artiCtgMng.deleteById(ids);
			}
		} catch (DataIntegrityViolationException e) {
			addActionError("记录已被引用，不能删除!");
		}
		return doList();
	}
	
	
	protected Object getBeanInput() {
		return getBean();
	}

	
	protected JeeCoreManager<ArtiCtg> getManager() {
		return artiCtgMng;
	}
	
	@Autowired
	private ArtiCtgMng artiCtgMng;
	private ArtiCtg bean;

	public ArtiCtg getBean() {
		return bean;
	}

	public void setBean(ArtiCtg bean) {
		this.bean = bean;
	}
}