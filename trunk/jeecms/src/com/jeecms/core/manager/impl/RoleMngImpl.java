package com.jeecms.core.manager.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.core.JeeCoreManagerImpl;
import com.jeecms.core.dao.RoleDao;
import com.jeecms.core.entity.Role;
import com.jeecms.core.entity.Admin;
import com.jeecms.core.manager.RoleMng;
import com.ponyjava.common.hibernate3.Updater;

@Service
@Transactional
public class RoleMngImpl extends JeeCoreManagerImpl<Role> implements RoleMng {
	
	public Object updateByUpdater(Updater updater) {
		Role role = (Role) super.updateByUpdater(updater);
		Admin.funcChange();
		return role;
	}

	
	public Role save(Role role) {
		super.save(role);
		return role;
	}

	
	public Role findById(Serializable id) {
		Role role = super.findById(id);
		return role;
	}

	
	public Role deleteById(Serializable id) {
		Role role = super.deleteById(id);
		return role;
	}

	@Autowired
	public void setRoleDao(RoleDao dao) {
		super.setDao(dao);
	}

	protected RoleDao getRoleDao() {
		return (RoleDao) super.getDao();
	}

}
