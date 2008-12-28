package com.jeecms.core.entity;

import com.jeecms.core.entity.base.BaseFunction;
import com.jeecms.core.util.PriorityInterface;
import com.ponyjava.common.util.SelectTreeInterface;

public class Function extends BaseFunction implements SelectTreeInterface,
		PriorityInterface {
	private static final long serialVersionUID = 1L;
	/**
	 * 功能集的分隔符
	 */
	public static final String FUNC_SPLIT = "@";
	/**
	 * 下拉列表树
	 */
	private String selectTree;

	public String getSelectTreeName() {
		return getName();
	}

	public String getTreeName() {
		return getName();
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Function() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Function(java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Function(java.lang.Long id, java.lang.Integer priority,
			java.lang.Boolean menu) {

		super(id, priority, menu);
	}

	/* [CONSTRUCTOR MARKER END] */

	public String getSelectTree() {
		return selectTree;
	}

	public void setSelectTree(String selectTree) {
		this.selectTree = selectTree;
	}
}