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
	 * 获得链接地址
	 * 
	 * @return
	 */
	public String getUrl() {
		if (getParent() == null) {
			// 首页
			return getWebsite().getWebUrl();
		} else if (!StringUtils.isBlank(getOuterUrl())) {
			// 外部链接
			if (getOuterUrl().startsWith("http")) {
				return getOuterUrl();
			} else {
				return getWebsite().getWebUrl() + getOuterUrl();
			}
		} else if (getModel().getHasChild()) {
			// 正常栏目
			StringBuilder sb = getWebsite().getWebUrlBuf().append(SPT).append(
					getPath()).append(SPT).append(PageBaseAction.INDEX).append(
					".").append(getWebsite().getSuffix());
			return sb.toString();
		} else {
			// 单页
			StringBuilder sb = new StringBuilder();
			sb.append(getWebsite().getWebUrl()).append(SPT).append(getPath())
					.append(".").append(getWebsite().getSuffix());
			return sb.toString();
		}
	}

	/**
	 * 选择栏目模板地址
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
	 * 选择内容模板地址
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
	 * 获得子节点ID集合
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
	 * 下拉列表树
	 */
	private String selectTree;
	/**
	 * div树，是否为叶子节点
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