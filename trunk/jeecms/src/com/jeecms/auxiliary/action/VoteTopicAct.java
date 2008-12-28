package com.jeecms.auxiliary.action;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.jeecms.auxiliary.entity.VoteItem;
import com.jeecms.auxiliary.entity.VoteTopic;
import com.jeecms.auxiliary.manager.VoteTopicMng;
import com.jeecms.core.JeeCoreManager;
import com.jeecms.core.util.PriorityComparator;
import com.ponyjava.common.hibernate3.OrderBy;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("auxiliary.voteTopicAct")
public class VoteTopicAct extends com.jeecms.auxiliary.AuxiSysAction {
	public String doList() {
		this.pagination = voteTopicMng.findAll(pageNo, getCookieCount(),
				OrderBy.desc("id"));
		return LIST;
	}

	public String doAdd() {
		return ADD;
	}

	public String doSave() {
		bean.setItems(notEmptyItems());
		if (hasActionErrors()) {
			return doAdd();
		}
		if (bean.getDisabled() == null) {
			bean.setDisabled(false);
		}
		if (bean.getCurrent() == null) {
			bean.setCurrent(false);
		}
		voteTopicMng.save(bean);
		addActionMessage("添加成功");
		return doList();
	}

	public String doEdit() {
		this.bean = voteTopicMng.findById(id);
		return EDIT;
	}

	public String doUpdate() {
		Set<VoteItem> items = notEmptyItems();
		if (hasActionErrors()) {
			id = bean.getId();
			return doEdit();
		}
		if (bean.getDisabled() == null) {
			bean.setDisabled(false);
		}
		if (bean.getCurrent() == null) {
			bean.setCurrent(false);
		}
		voteTopicMng.updateTopic(bean, items);
		addActionMessage("修改成功");
		return doList();
	}

	/**
	 * 去除name、id为空的对象，并将id为空的对象返回
	 * 
	 * @return
	 */
	private Set<VoteItem> notEmptyItems() {
		Set<VoteItem> items = new TreeSet<VoteItem>(new PriorityComparator());
		if (voteItems == null) {
			addActionError("投票项不能为空！");
		}
		// 去除标题为空的投票选项
		for (VoteItem it : voteItems) {
			if (it != null && !StringUtils.isBlank(it.getTitle())) {
				it.setTopic(bean);
				items.add(it);
			}
		}
		if (items.size() <= 0) {
			addActionError("投票项不能为空！");
		}
		return items;
	}

	public String doDelete() {
		try {
			if (id != null) {
				voteTopicMng.deleteById(id);
			} else {
				voteTopicMng.deleteById(ids);
			}
		} catch (DataIntegrityViolationException e) {
			addActionError("记录已被引用，不能删除!");
		}
		return doList();
	}

	
	protected Object getBeanInput() {
		return getBean();
	}

	
	protected JeeCoreManager<VoteTopic> getManager() {
		return voteTopicMng;
	}

	@Autowired
	private VoteTopicMng voteTopicMng;
	private VoteTopic bean;
	private List<VoteItem> voteItems;

	public VoteTopic getBean() {
		return bean;
	}

	public void setBean(VoteTopic bean) {
		this.bean = bean;
	}

	public List<VoteItem> getVoteItems() {
		return voteItems;
	}

	public void setVoteItems(List<VoteItem> voteItems) {
		this.voteItems = voteItems;
	}
}