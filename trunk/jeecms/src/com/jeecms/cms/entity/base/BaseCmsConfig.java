package com.jeecms.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cms_config table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="cms_config"
 */

public abstract class BaseCmsConfig  implements Serializable {

	public static String REF = "CmsConfig";
	public static String PROP_COMMENT_NEED_CHECK = "commentNeedCheck";
	public static String PROP_WEBSITE = "website";
	public static String PROP_CHECK_COUNT = "checkCount";
	public static String PROP_ID = "id";
	public static String PROP_DEFAULT_SYSTEM = "defaultSystem";


	// constructors
	public BaseCmsConfig () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsConfig (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.Boolean commentNeedCheck;
	private java.lang.Integer checkCount;
	private java.lang.String defaultSystem;

	// one to one
	private com.jeecms.core.entity.Website website;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="CONFIG_ID"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: COMMENT_NEED_CHECK
	 */
	public java.lang.Boolean getCommentNeedCheck () {
		return commentNeedCheck;
	}

	/**
	 * Set the value related to the column: COMMENT_NEED_CHECK
	 * @param commentNeedCheck the COMMENT_NEED_CHECK value
	 */
	public void setCommentNeedCheck (java.lang.Boolean commentNeedCheck) {
		this.commentNeedCheck = commentNeedCheck;
	}



	/**
	 * Return the value associated with the column: CHECK_COUNT
	 */
	public java.lang.Integer getCheckCount () {
		return checkCount;
	}

	/**
	 * Set the value related to the column: CHECK_COUNT
	 * @param checkCount the CHECK_COUNT value
	 */
	public void setCheckCount (java.lang.Integer checkCount) {
		this.checkCount = checkCount;
	}



	/**
	 * Return the value associated with the column: DEFAULT_SYSTEM
	 */
	public java.lang.String getDefaultSystem () {
		return defaultSystem;
	}

	/**
	 * Set the value related to the column: DEFAULT_SYSTEM
	 * @param defaultSystem the DEFAULT_SYSTEM value
	 */
	public void setDefaultSystem (java.lang.String defaultSystem) {
		this.defaultSystem = defaultSystem;
	}



	/**
	 * Return the value associated with the column: website
	 */
	public com.jeecms.core.entity.Website getWebsite () {
		return website;
	}

	/**
	 * Set the value related to the column: website
	 * @param website the website value
	 */
	public void setWebsite (com.jeecms.core.entity.Website website) {
		this.website = website;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.CmsConfig)) return false;
		else {
			com.jeecms.cms.entity.CmsConfig cmsConfig = (com.jeecms.cms.entity.CmsConfig) obj;
			if (null == this.getId() || null == cmsConfig.getId()) return false;
			else return (this.getId().equals(cmsConfig.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}