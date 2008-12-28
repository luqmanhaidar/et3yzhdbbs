package com.jeecms.auxiliary.manager.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.auxiliary.dao.MsgCtgDao;
import com.jeecms.auxiliary.entity.MsgCtg;
import com.jeecms.auxiliary.manager.MsgCtgMng;
import com.jeecms.core.JeeCoreManagerImpl;
import com.ponyjava.common.hibernate3.Updater;

@Service
@Transactional
public class MsgCtgMngImpl extends JeeCoreManagerImpl<MsgCtg> implements
		MsgCtgMng {
	
	public Object updateByUpdater(Updater updater) {
		MsgCtg ctg = (MsgCtg) super.updateByUpdater(updater);
		return ctg;
	}

	
	public MsgCtg save(MsgCtg ctg) {
		super.save(ctg);
		return ctg;
	}

	
	public MsgCtg findById(Serializable id) {
		MsgCtg ctg = super.findById(id);
		return ctg;
	}

	
	public MsgCtg deleteById(Serializable id) {
		MsgCtg ctg = super.deleteById(id);
		return ctg;
	}

	@Autowired
	public void setMsgCtgDao(MsgCtgDao dao) {
		super.setDao(dao);
	}

	public MsgCtgDao getMsgCtgDao() {
		return (MsgCtgDao) super.getDao();
	}
}
