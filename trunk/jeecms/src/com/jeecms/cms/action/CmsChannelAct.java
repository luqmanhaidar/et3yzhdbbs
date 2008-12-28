package com.jeecms.cms.action;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.jeecms.cms.entity.ChnlModel;
import com.jeecms.cms.entity.ChnlModelItem;
import com.jeecms.cms.entity.CmsChannel;
import com.jeecms.cms.manager.ChnlModelMng;
import com.jeecms.cms.manager.CmsChannelMng;
import com.jeecms.core.JeeCoreManager;

@SuppressWarnings("serial")
public abstract class CmsChannelAct extends com.jeecms.cms.CmsSysAction {
	public String doLeft() {
		// 根
		treeRoot = new CmsChannel();
		treeRoot.setName("根目录");
		treeRoot.setTreeLeaf(false);
		// 内容。取所有列表，找出父菜单。
		List<CmsChannel> all = cmsChannelMng.getRoots(getSysType());
		treeRoot.setChild(new LinkedHashSet<CmsChannel>(all));
		return LEFT;
	}

	public String doList() {
		if (pid == null) {
			this.list = cmsChannelMng.getRoots(getSysType());
			hasChild = true;
		} else {
			CmsChannel c = cmsChannelMng.findById(pid);
			this.list = new ArrayList<CmsChannel>(c.getChild());
			hasChild = c.getModel().getHasChild();
		}
		models = chnlModelMng.getModels(getSysType());
		hasChild = true;
		return LIST;
	}

	public String doAdd() {
		if (pid != null) {
			parent = cmsChannelMng.findById(pid);
		}
		model = chnlModelMng.findById(modelId);
		itemMap = model.getDiplayItemMap();
		String root = contextPvd.getAppRoot();
		tplChannlList = model.tplChannlList(root);
		tplContentList = model.tplContentList(root);
		return ADD;
	}

	public String doSave() {
		bean.setSysType(getSysType());
		cmsChannelMng.save(bean);
		addActionMessage("添加成功");
		return doList();
	}

	public String doEdit() {
		bean = cmsChannelMng.findById(id);
		model = this.bean.getModel();
		itemMap = model.getDiplayItemMap();
		hasChild = model.getHasChild();
		if (hasChild) {
			models = chnlModelMng.getModels(getSysType());
		}
		String root = contextPvd.getAppRoot();
		tplChannlList = model.tplChannlList(root);
		String indexTpl = bean.getTplIndex();
		// 当前模板不在默认方案中。
		if (!StringUtils.isBlank(indexTpl) && !tplChannlList.contains(indexTpl)) {
			tplChannlList.add(indexTpl);
		}
		tplContentList = model.tplContentList(root);
		String contentTpl = bean.getTplContent();
		// 当前模板不在默认方案中。
		if (!StringUtils.isBlank(contentTpl)
				&& !tplContentList.contains(contentTpl)) {
			tplContentList.add(contentTpl);
		}
		return EDIT;
	}

	public String doUpdate() {
		cmsChannelMng.updateDefault(bean);
		addActionMessage("修改成功");
		return doList();
	}

	public String doDelete() {
		try {
			if (id != null) {
				cmsChannelMng.deleteById(id);
			} else {
				cmsChannelMng.deleteById(ids);
			}
		} catch (DataIntegrityViolationException e) {
			addActionError("记录已被引用，不能删除!");
		}
		return doList();
	}

	public String doPriorityUpdate() {
		for (int i = 0; i < wids.length; i++) {
			CmsChannel f = cmsChannelMng.findById(wids[i]);
			f.setPriority(prioritys[i]);
			cmsChannelMng.update(f);
		}
		return doList();
	}

	
	protected Object getBeanInput() {
		return getBean();
	}

	protected abstract String getSysType();

	
	protected JeeCoreManager<CmsChannel> getManager() {
		return cmsChannelMng;
	}

	@Autowired
	private CmsChannelMng cmsChannelMng;
	@Autowired
	private ChnlModelMng chnlModelMng;
	private CmsChannel bean;
	private CmsChannel parent;
	private CmsChannel treeRoot;
	private Long pid;
	private List<ChnlModel> models;
	private List<String> tplChannlList;
	private List<String> tplContentList;
	private ChnlModel model;
	private Long modelId;
	private Map<String, ChnlModelItem> itemMap;
	private boolean hasChild;

	private Long[] wids;
	private int[] prioritys;

	public CmsChannel getBean() {
		return bean;
	}

	public void setBean(CmsChannel bean) {
		this.bean = bean;
	}

	public CmsChannel getTreeRoot() {
		return treeRoot;
	}

	public void setTreeRoot(CmsChannel treeRoot) {
		this.treeRoot = treeRoot;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long[] getWids() {
		return wids;
	}

	public void setWids(Long[] wids) {
		this.wids = wids;
	}

	public int[] getPrioritys() {
		return prioritys;
	}

	public void setPrioritys(int[] prioritys) {
		this.prioritys = prioritys;
	}

	public List<ChnlModel> getModels() {
		return models;
	}

	public void setModels(List<ChnlModel> models) {
		this.models = models;
	}

	public ChnlModel getModel() {
		return model;
	}

	public void setModel(ChnlModel model) {
		this.model = model;
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public Map<String, ChnlModelItem> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<String, ChnlModelItem> itemMap) {
		this.itemMap = itemMap;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public CmsChannel getParent() {
		return parent;
	}

	public void setParent(CmsChannel parent) {
		this.parent = parent;
	}

	public List<String> getTplChannlList() {
		return tplChannlList;
	}

	public void setTplChannlList(List<String> tplChannlList) {
		this.tplChannlList = tplChannlList;
	}

	public List<String> getTplContentList() {
		return tplContentList;
	}

	public void setTplContentList(List<String> tplContentList) {
		this.tplContentList = tplContentList;
	}
}
