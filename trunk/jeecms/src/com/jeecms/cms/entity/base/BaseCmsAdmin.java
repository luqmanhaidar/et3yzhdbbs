package com.jeecms.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cms_admin table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="cms_admin"
 */

public abstract class BaseCmsAdmin  implements Serializable {

	public static String REF = "CmsAdmin";
	public static String PROP_WEBSITE = "website";
	public static String PROP_ID = "id";
	public static String PROP_CHECK_RIGHT = "checkRight";
	public static String PROP_ADMIN = "admin";


	// constructors
	public BaseCmsAdmin () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsAdmin (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsAdmin (
		java.lang.Long id,
		com.jeecms.core.entity.Website website) {

		this.setId(id);
		this.setWebsite(website);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.Integer checkRight;

	// one to one
	private com.jeecms.core.entity.Admin admin;

	// many to one
	private com.jeecms.core.entity.Website website;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="ADMIN_ID"
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
	 * Return the value associated with the column: CHECK_RIGHT
	 */
	public java.lang.Integer getCheckRight () {
		return checkRight;
	}

	/**
	 * Set the value related to the column: CHECK_RIGHT
	 * @param checkRight the CHECK_RIGHT value
	 */
	public void setCheckRight (java.lang.Integer checkRight) {
		this.checkRight = checkRight;
	}



	/**
	 * Return the value associated with the column: admin
	 */
	public com.jeecms.core.entity.Admin getAdmin () {
		return admin;
	}

	/**
	 * Set the value related to the column: admin
	 * @param admin the admin value
	 */
	public void setAdmin (com.jeecms.core.entity.Admin admin) {
		this.admin = admin;
	}



	/**
	 * Return the value associated with the column: WEBSITE_ID
	 */
	public com.jeecms.core.entity.Website getWebsite () {
		return website;
	}

	/**
	 * Set the value related to the column: WEBSITE_ID
	 * @param website the WEBSITE_ID value
	 */
	public void setWebsite (com.jeecms.core.entity.Website website) {
		this.website = website;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.CmsAdmin)) return false;
		else {
			com.jeecms.cms.entity.CmsAdmin cmsAdmin = (com.jeecms.cms.entity.CmsAdmin) obj;
			if (null == this.getId() || null == cmsAdmin.getId()) return false;
			else return (this.getId().equals(cmsAdmin.getId()));
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