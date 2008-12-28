package com.jeecms.cms.manager.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.CmsChannelDao;
import com.jeecms.cms.entity.ChnlModel;
import com.jeecms.cms.entity.CmsChannel;
import com.jeecms.cms.manager.ChnlModelMng;
import com.jeecms.cms.manager.CmsChannelMng;
import com.jeecms.core.JeeCoreManagerImpl;
import com.ponyjava.common.hibernate3.Condition;
import com.ponyjava.common.hibernate3.OrderBy;
import com.ponyjava.common.hibernate3.Updater;

@Service
@Transactional
public class CmsChannelMngImpl extends JeeCoreManagerImpl<CmsChannel> implements
		CmsChannelMng {
	
	public CmsChannel getByPath(String path) {
		return getCmsChannelDao().getByPath(path);
	}

	
	public List<CmsChannel> getChild(String sysType, Long pid, int orderBy,
			boolean isDisplay, boolean hasChild, int start, int count) {
		CmsChannel example = new CmsChannel();
		if (pid == null) {
			pid = getRoot(sysType).getId();
		}
		example.setParent(new CmsChannel(pid));
		if (isDisplay) {
			example.setDisplay(isDisplay);
		}
		if (hasChild) {
			example.setHasChild(hasChild);
		}
		Condition[] conds = null;
		switch (orderBy) {
		case 0:
			conds = new Condition[] { OrderBy.asc("priority") };
			break;
		case 1:
			conds = new Condition[] { OrderBy.desc("priority") };
			break;
		case 2:
			conds = new Condition[] { OrderBy.asc("visitTotal") };
			break;
		case 3:
			conds = new Condition[] { OrderBy.desc("visitTotal") };
			break;
		default:
			conds = new Condition[] { OrderBy.asc("priority") };
		}
		if (start == 0 && count == 0) {
			return findByEgList(example, conds);
		} else {
			return findByEgList(example, conds, start, count);
		}
	}

	
	public List<CmsChannel> getRoots(String sysType) {
		return getCmsChannelDao().getRoots(sysType);
	}

	
	public CmsChannel getRoot(String sysType) {
		List<CmsChannel> list = getRoots(sysType);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	
	public List<CmsChannel> getRoots(String sysType, boolean hasChild) {
		if (hasChild) {
			Long[] mids = chnlModelMng.getHasChildIds(sysType);
			return getCmsChannelDao().getRoots(sysType, mids);
		} else {
			return getRoots(sysType);
		}
	}

	
	public Object updateByUpdater(Updater updater) {
		CmsChannel bean = (CmsChannel) updater.getBean();
		bean.setParent(null);
		CmsChannel entity = (CmsChannel) super.updateByUpdater(updater);
		return entity;
	}

	
	public CmsChannel save(CmsChannel chnl) {
		if (chnl.getContributeLevel() == null) {
			chnl.setContributeLevel(Integer.MAX_VALUE);
		}
		if (chnl.getDisplay() == null) {
			chnl.setDisplay(true);
		}
		if (chnl.getPriority() == null) {
			chnl.setPriority(100);
		}
		chnl.setVisitToday(0L);
		chnl.setVisitTotal(0L);
		chnl.setStatDate(new Date());
		Long mid = chnl.getModel().getId();
		ChnlModel model = chnlModelMng.findById(mid);
		chnl.setModel(model);
		chnl.setHasChild(model.getHasChild());
		CmsChannel parent = chnl.getParent();
		if (parent != null) {
			Long pid = parent.getId();
			// 如果父节点ID为null则将父节点对象设置为null，以免hibernate发生错误。并直接保存对象。
			if (pid == null) {
				chnl.setParent(null);
				super.save(chnl);
			} else {
				chnl.setParent(parent);
				findById(pid).getChild().add(chnl);
			}
		} else {
			super.save(chnl);
		}
		return chnl;
	}

	
	public CmsChannel findById(Serializable id) {
		CmsChannel entity = super.findById(id);
		return entity;
	}

	
	public CmsChannel deleteById(Serializable id) {
		CmsChannel entity = findById(id);
		CmsChannel parent = entity.getParent();
		super.delete(entity);
		if (parent != null) {
			parent.getChild().remove(entity);
		}
		return entity;
	}

	@Autowired
	private ChnlModelMng chnlModelMng;

	@Autowired
	public void setCmsChannelDao(CmsChannelDao dao) {
		super.setDao(dao);
	}

	public CmsChannelDao getCmsChannelDao() {
		return (CmsChannelDao) super.getDao();
	}
}
