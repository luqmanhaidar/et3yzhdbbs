package com.jeecms.cms.entity;

import static com.jeecms.core.Constants.SPT;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.jeecms.cms.entity.base.BaseCmsChannel;
import com.jeecms.core.PageBaseAction;
import com.jeecms.core.util.PriorityInterface;
import com.ponyjava.common.util.SelectTreeInterface;

public class CmsChannel extends BaseCmsChannel implements SelectTreeInterface,
		PriorityInterface {
	/**
	 * ������ӵ�ַ
	 * 
	 * @return
	 */
	public String getUrl() {
		if (getParent() == null) {
			// ��ҳ
			return getWebsite().getWebUrl();
		} else if (!StringUtils.isBlank(getOuterUrl())) {
			// �ⲿ����
			if (getOuterUrl().startsWith("http")) {
				return getOuterUrl();
			} else {
				return getWebsite().getWebUrl() + getOuterUrl();
			}
		} else if (getModel().getHasChild()) {
			// ������Ŀ
			StringBuilder sb = getWebsite().getWebUrlBuf().append(SPT).append(
					getPath()).append(SPT).append(PageBaseAction.INDEX).append(
					".").append(getWebsite().getSuffix());
			return sb.toString();
		} else {
			// ��ҳ
			StringBuilder sb = new StringBuilder();
			sb.append(getWebsite().getWebUrl()).append(SPT).append(getPath())
					.append(".").append(getWebsite().getSuffix());
			return sb.toString();
		}
	}

	/**
	 * ѡ����Ŀģ���ַ
	 * 
	 * @return
	 */
	public String chooseTplChannel() {
		if (!StringUtils.isBlank(getTplIndex())) {
			return getWebsite().getTplRoot().append(getTplIndex()).toString();
		} else {
			return getModel().defIndexTpl();
		}
	}

	/**
	 * ѡ������ģ���ַ
	 * 
	 * @return
	 */
	public String chooseTplContent() {
		if (!StringUtils.isBlank(getTplContent())) {
			return getWebsite().getTplRoot().append(getTplContent()).toString();
		} else {
			return getModel().defContentTpl();
		}
	}

	/**
	 * ����ӽڵ�ID����
	 * 
	 * @return
	 */
	public Set<Long> getChildIds() {
		Set<Long> idSet = new HashSet<Long>();
		if (getId() != null) {
			idSet.add(getId());
		}
		Set<CmsChannel> child = getChild();
		if (child != null && child.size() > 0) {
			for (CmsChannel c : child) {
				Set<Long> cset = c.getChildIds();
				idSet.addAll(cset);
			}
		}
		return idSet;
	}

	/**
	 * �����б���
	 */
	private String selectTree;
	/**
	 * div�����Ƿ�ΪҶ�ӽڵ�
	 */
	private Boolean treeLeaf;

	public String getSelectTreeName() {
		return getName();
	}

	public String getTreeName() {
		if (getModel() != null) {
			return getName() + "[<span style='color:red'>"
					+ getModel().getShortName() + "</span>]";
		} else {
			return getName();
		}
	}

	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public CmsChannel () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsChannel (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsChannel (
		java.lang.Long id,
		com.jeecms.cms.entity.ChnlModel model,
		com.jeecms.core.entity.Website website,
		java.lang.String sysType,
		java.lang.Integer priority,
		java.lang.Boolean hasChild,
		java.lang.Boolean display) {

		super (
			id,
			model,
			website,
			sysType,
			priority,
			hasChild,
			display);
	}

	/* [CONSTRUCTOR MARKER END] */

	public String getSelectTree() {
		return selectTree;
	}

	public void setSelectTree(String selectTree) {
		this.selectTree = selectTree;
	}

	public Boolean getTreeLeaf() {
		return treeLeaf;
	}

	public void setTreeLeaf(Boolean treeLeaf) {
		this.treeLeaf = treeLeaf;
	}

}