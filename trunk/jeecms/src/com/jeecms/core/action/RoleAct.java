package com.jeecms.core.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.jeecms.core.entity.Function;
import com.jeecms.core.entity.Role;
import com.jeecms.core.manager.FunctionMng;
import com.jeecms.core.manager.RoleMng;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("core.roleAct")
public class RoleAct extends com.jeecms.core.JeeCoreAction {
	public String doList() {
		this.pagination = roleMng.findAll(pageNo, getCookieCount());
		return LIST;
	}

	public String doAdd() {
		// Ѱ�Ҹ��ڵ�
		List<Function> roots = functionMng.getRoots();
		if (roots != null && roots.size() > 0) {
			this.funcRoot = roots.get(0);
		} else {
			addActionError("������ӹ��ܲ˵���");
		}
		return ADD;
	}

	public String doSave() {
		bean.setFunctions(new HashSet<Function>(functions));
		roleMng.save(bean);
		addActionMessage("��ӳɹ���");
		return doList();
	}

	public String doEdit() {
		this.bean = roleMng.findById(id);
		// Ѱ�Ҹ��ڵ�
		List<Function> roots = functionMng.getRoots();
		if (roots != null && roots.size() > 0) {
			this.funcRoot = roots.get(0);
		} else {
			addActionError("������ӹ��ܲ˵���");
		}
		return EDIT;
	}

	public String doUpdate() {
		bean.setFunctions(new HashSet<Function>(functions));
		roleMng.updateDefault(bean);
		addActionMessage("�޸ĳɹ�");
		return doList();
	}

	public String doDelete() {
		try {
			if (id != null) {
				roleMng.deleteById(id);
			} else {
				roleMng.deleteById(ids);
			}
		} catch (DataIntegrityViolationException e) {
			addActionError("��¼�ѱ����ã�����ɾ��!");
		}
		return doList();
	}

	@Autowired
	private RoleMng roleMng;
	private Role bean;
	@Autowired
	private FunctionMng functionMng;
	private List<Function> funcList;
	private Function funcRoot;
	private List<Function> functions;

	public Role getBean() {
		return bean;
	}

	public void setBean(Role bean) {
		this.bean = bean;
	}

	public List<Function> getFuncList() {
		return funcList;
	}

	public void setFuncList(List<Function> funcList) {
		this.funcList = funcList;
	}

	public Function getFuncRoot() {
		return funcRoot;
	}

	public void setFuncRoot(Function funcRoot) {
		this.funcRoot = funcRoot;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

}
