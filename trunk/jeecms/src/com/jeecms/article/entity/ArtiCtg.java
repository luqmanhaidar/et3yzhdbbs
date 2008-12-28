package com.jeecms.article.entity;

import com.jeecms.article.entity.base.BaseArtiCtg;

public class ArtiCtg extends BaseArtiCtg {
	private static final long serialVersionUID = 1L;

	/**
	 * 获得带注释的名称
	 * 
	 * @return
	 */
	public String getNameWithComment() {
		return getName() + " attr='" + getLabel() + "'";
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public ArtiCtg () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ArtiCtg (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ArtiCtg (
		java.lang.Long id,
		com.jeecms.core.entity.Website website,
		java.lang.String label,
		java.lang.Boolean hasTitleimg,
		java.lang.Boolean disabled) {

		super (
			id,
			website,
			label,
			hasTitleimg,
			disabled);
	}

	/* [CONSTRUCTOR MARKER END] */

}