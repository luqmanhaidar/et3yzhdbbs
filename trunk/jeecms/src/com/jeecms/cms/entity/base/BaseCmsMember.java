package com.jeecms.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cms_member table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="cms_member"
 */

public abstract class BaseCmsMember  implements Serializable {

	public static String REF = "CmsMember";
	public static String PROP_MEMBER = "member";
	public static String PROP_WEBSITE = "website";
	public static String PROP_DISABLED = "disabled";
	public static String PROP_COUPON = "coupon";
	public static String PROP_ID = "id";
	public static String PROP_SCORE = "score";
	public static String PROP_GROUP = "group";


	// constructors
	public BaseCmsMember () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsMember (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsMember (
		java.lang.Long id,
		com.jeecms.core.entity.Website website,
		com.jeecms.cms.entity.CmsMemberGroup group,
		boolean disabled) {

		this.setId(id);
		this.setWebsite(website);
		this.setGroup(group);
		this.setDisabled(disabled);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.Integer score;
	private java.lang.Integer coupon;
	private boolean disabled;

	// one to one
	private com.jeecms.core.entity.Member member;

	// many to one
	private com.jeecms.core.entity.Website website;
	private com.jeecms.cms.entity.CmsMemberGroup group;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="MEMBER_ID"
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
	 * Return the value associated with the column: SCORE
	 */
	public java.lang.Integer getScore () {
		return score;
	}

	/**
	 * Set the value related to the column: SCORE
	 * @param score the SCORE value
	 */
	public void setScore (java.lang.Integer score) {
		this.score = score;
	}



	/**
	 * Return the value associated with the column: COUPON
	 */
	public java.lang.Integer getCoupon () {
		return coupon;
	}

	/**
	 * Set the value related to the column: COUPON
	 * @param coupon the COUPON value
	 */
	public void setCoupon (java.lang.Integer coupon) {
		this.coupon = coupon;
	}



	/**
	 * Return the value associated with the column: IS_DISABLED
	 */
	public boolean isDisabled () {
		return disabled;
	}

	/**
	 * Set the value related to the column: IS_DISABLED
	 * @param disabled the IS_DISABLED value
	 */
	public void setDisabled (boolean disabled) {
		this.disabled = disabled;
	}



	/**
	 * Return the value associated with the column: member
	 */
	public com.jeecms.core.entity.Member getMember () {
		return member;
	}

	/**
	 * Set the value related to the column: member
	 * @param member the member value
	 */
	public void setMember (com.jeecms.core.entity.Member member) {
		this.member = member;
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



	/**
	 * Return the value associated with the column: GROUP_ID
	 */
	public com.jeecms.cms.entity.CmsMemberGroup getGroup () {
		return group;
	}

	/**
	 * Set the value related to the column: GROUP_ID
	 * @param group the GROUP_ID value
	 */
	public void setGroup (com.jeecms.cms.entity.CmsMemberGroup group) {
		this.group = group;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.CmsMember)) return false;
		else {
			com.jeecms.cms.entity.CmsMember cmsMember = (com.jeecms.cms.entity.CmsMember) obj;
			if (null == this.getId() || null == cmsMember.getId()) return false;
			else return (this.getId().equals(cmsMember.getId()));
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