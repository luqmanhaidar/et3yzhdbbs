package com.jeecms.cms.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.jeecms.cms.entity.CmsAdmin;
import com.jeecms.core.JeeCoreManager;
import com.jeecms.core.entity.Admin;
import com.jeecms.core.entity.Role;
import com.jeecms.core.manager.RoleMng;
import com.ponyjava.common.hibernate3.OrderBy;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("cms.cmsAdminAct")
public class CmsAdminAct extends com.jeecms.cms.CmsSysAction {
	public String doList() {
		this.pagination = cmsAdminMng.findAll(pageNo, getCookieCount(),
				new OrderBy[] { OrderBy.desc("id") });
		return LIST;
	}

	public String doAdd() {
		this.roleList = roleMng.findAll();
		return ADD;
	}

	public String doSave() {
		bean.setWebsite(getWeb());
		bean = cmsAdminMng.register(bean, !isNewUser);
		if (roles != null) {
			bean.getAdmin().setRoles(new HashSet<Role>(roles));
		}
		addActionMessage("添加成功");
		return doList();
	}

	public String doEdit() {
		this.bean = cmsAdminMng.findById(id);
		this.roleList = roleMng.findAll();
		return EDIT;
	}

	public String doUpdate() {
		Admin admin = bean.getAdmin();
		admin.setRoles(new HashSet<Role>(roles));
		bean.setAdmin(null);
		if (admin != null) {
			adminMng.updateDefault(admin);
		}
		cmsAdminMng.updateDefault(bean);
		addActionMessage("修改成功");
		return doList();
	}

	public String doDelete() {
		try {
			if (id != null) {
				cmsAdminMng.deleteById(id);
			} else {
				cmsAdminMng.deleteById(ids);
			}
		} catch (DataIntegrityViolationException e) {
			addActionError("记录已被引用，不能删除!");
		}
		return doList();
	}

	
	protected Object getBeanInput() {
		return getBean();
	}

	
	protected JeeCoreManager<CmsAdmin> getManager() {
		return cmsAdminMng;
	}

	@Autowired
	private RoleMng roleMng;
	private List<Role> roleList;
	private List<Role> roles;
	private CmsAdmin bean;
	private boolean isNewUser;

	public CmsAdmin getBean() {
		return bean;
	}

	public void setBean(CmsAdmin bean) {
		this.bean = bean;
	}

	public boolean isNewUser() {
		return isNewUser;
	}

	public void setNewUser(boolean isNewUser) {
		this.isNewUser = isNewUser;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
