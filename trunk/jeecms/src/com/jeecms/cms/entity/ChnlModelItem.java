package com.jeecms.cms.entity;

import com.jeecms.cms.entity.base.BaseChnlModelItem;
import com.jeecms.core.util.PriorityInterface;

public class ChnlModelItem extends BaseChnlModelItem implements
		PriorityInterface {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public ChnlModelItem () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ChnlModelItem (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ChnlModelItem (
		java.lang.Long id,
		com.jeecms.cms.entity.ChnlModel model,
		java.lang.String name,
		java.lang.Integer priority,
		java.lang.Boolean checked) {

		super (
			id,
			model,
			name,
			priority,
			checked);
	}

	/* [CONSTRUCTOR MARKER END] */
}