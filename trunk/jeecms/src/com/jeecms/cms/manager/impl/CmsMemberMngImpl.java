package com.jeecms.cms.manager.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.core.JeeCoreManagerImpl;
import com.jeecms.cms.dao.CmsMemberDao;
import com.jeecms.cms.entity.CmsMember;
import com.jeecms.cms.manager.CmsMemberMng;
import com.ponyjava.common.hibernate3.Updater;

@Service
@Transactional
public class CmsMemberMngImpl extends JeeCoreManagerImpl<CmsMember> implements
		CmsMemberMng {
	
	public Object updateByUpdater(Updater updater) {
		CmsMember member = (CmsMember) super.updateByUpdater(updater);
		return member;
	}

	
	public CmsMember save(CmsMember member) {
		super.save(member);
		return member;
	}

	
	public CmsMember findById(Serializable id) {
		CmsMember member = super.findById(id);
		return member;
	}

	
	public CmsMember deleteById(Serializable id) {
		CmsMember member = super.deleteById(id);
		return member;
	}

	@Autowired
	public void setCmsMemberDao(CmsMemberDao dao) {
		super.setDao(dao);
	}

	public CmsMemberDao getCmsMemberDao() {
		return (CmsMemberDao) super.getDao();
	}

}
