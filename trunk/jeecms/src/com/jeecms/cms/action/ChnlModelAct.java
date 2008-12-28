package com.jeecms.cms.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.jeecms.cms.entity.ChnlModel;
import com.jeecms.cms.entity.ChnlModelItem;
import com.jeecms.cms.manager.ChnlModelMng;
import com.jeecms.core.JeeCoreManager;
import com.jeecms.core.util.PriorityComparator;

@SuppressWarnings("serial")
public abstract class ChnlModelAct extends com.jeecms.cms.CmsSysAction {
	public String doList() {
		this.list = chnlModelMng.getModels(getSysType());
		return LIST;
	}

	public String doAdd() {
		return ADD;
	}

	public String doSave() {
		Set<ChnlModelItem> addItems = clearEmptyItems();
		if (hasActionErrors()) {
			return doAdd();
		}
		bean.setSysType(getSysType());
		bean.setItems(addItems);
		chnlModelMng.save(bean);
		addActionMessage("添加成功");
		return doList();
	}

	public String doEdit() {
		this.bean = chnlModelMng.findById(id);
		return EDIT;
	}

	public String doCopy() {
		isCopy = true;
		this.bean = chnlModelMng.findById(id);
		this.bean.setName(null);
		return EDIT;
	}

	public String doUpdate() {
		Set<ChnlModelItem> addItems = clearEmptyItems();
		if (hasActionErrors()) {
			id = bean.getId();
			return doEdit();
		}
		ChnlModel model = chnlModelMng.findById(bean.getId());
		Set<ChnlModelItem> items = model.getItems();
		Set<ChnlModelItem> rmItems = new HashSet<ChnlModelItem>();
		for (ChnlModelItem oitem : items) {
			// 待更新对象
			ChnlModelItem uitem = null;
			for (ChnlModelItem it : modelItems) {
				if (oitem.getId().equals(it.getId())) {
					uitem = it;
					break;
				}
			}
			if (uitem != null) {
				// 更新对象
				oitem.setName(uitem.getName());
				oitem.setLabel(uitem.getLabel());
				oitem.setPriority(uitem.getPriority());
				oitem.setChecked(uitem.getChecked());
			} else {
				// 待删除对象
				rmItems.add(oitem);
			}
		}
		items.removeAll(rmItems);
		items.addAll(addItems);
		chnlModelMng.update(model);
		chnlModelMng.updateDefault(bean);
		addActionMessage("修改成功");
		return doList();
	}

	/**
	 * 去除name、id为空的对象，并将id为空的对象返回
	 * 
	 * @return
	 */
	private Set<ChnlModelItem> clearEmptyItems() {
		if (modelItems == null) {
			addActionError("模型内容不能为空！");
		}
		Set<ChnlModelItem> addItems = new TreeSet<ChnlModelItem>(
				new PriorityComparator());
		// 去除标题为空的投票选项
		for (int i = 0; i < modelItems.size(); i++) {
			ChnlModelItem it = modelItems.get(i);
			if (it == null || StringUtils.isBlank(it.getName())) {
				modelItems.remove(i);
				i--;
			} else {
				it.setModel(bean);
				if (it.getChecked() == null) {
					it.setChecked(false);
				}
				if (it.getId() == null) {
					addItems.add(it);
					modelItems.remove(i);
					i--;
				}
			}
		}
		if (modelItems.size() <= 0 && addItems.size() <= 0) {
			addActionError("模型内容不能为空！");
		}
		return addItems;
	}

	public String doDelete() {
		try {
			if (id != null) {
				chnlModelMng.deleteById(id);
			} else {
				chnlModelMng.deleteById(ids);
			}
		} catch (DataIntegrityViolationException e) {
			addActionError("记录已被引用，不能删除!");
		}
		return doList();
	}

	
	protected Object getBeanInput() {
		return getBean();
	}

	protected abstract String getSysType();

	
	protected JeeCoreManager<ChnlModel> getManager() {
		return chnlModelMng;
	}

	@Autowired
	protected ChnlModelMng chnlModelMng;
	private ChnlModel bean;
	private List<ChnlModelItem> modelItems;
	private boolean isCopy;

	public ChnlModel getBean() {
		return bean;
	}

	public void setBean(ChnlModel bean) {
		this.bean = bean;
	}

	public List<ChnlModelItem> getModelItems() {
		return modelItems;
	}

	public void setModelItems(List<ChnlModelItem> modelItems) {
		this.modelItems = modelItems;
	}

	public boolean isCopy() {
		return isCopy;
	}

	public void setCopy(boolean isCopy) {
		this.isCopy = isCopy;
	}
}
