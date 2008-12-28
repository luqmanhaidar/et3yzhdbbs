package com.jeecms.cms.entity;

import com.jeecms.cms.entity.base.BaseCmsMember;



public class CmsMember extends BaseCmsMember {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsMember () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsMember (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsMember (
		java.lang.Long id,
		com.jeecms.core.entity.Website website,
		com.jeecms.cms.entity.CmsMemberGroup group,
		boolean disabled) {

		super (
			id,
			website,
			group,
			disabled);
	}

/*[CONSTRUCTOR MARKER END]*/


}