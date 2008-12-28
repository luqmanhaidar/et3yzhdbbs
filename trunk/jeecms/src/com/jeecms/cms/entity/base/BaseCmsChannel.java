package com.jeecms.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cms_channel table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="cms_channel"
 */

public abstract class BaseCmsChannel  implements Serializable {

	public static String REF = "CmsChannel";
	public static String PROP_PARENT = "parent";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_MODEL = "model";
	public static String PROP_TITLE_IMG = "titleImg";
	public static String PROP_OUTER_URL = "outerUrl";
	public static String PROP_WEBSITE = "website";
	public static String PROP_DISPLAY = "display";
	public static String PROP_VISIT_TODAY = "visitToday";
	public static String PROP_TPL_INDEX = "tplIndex";
	public static String PROP_HAS_CHILD = "hasChild";
	public static String PROP_TITLE = "title";
	public static String PROP_PRIORITY = "priority";
	public static String PROP_PATH = "path";
	public static String PROP_NAME = "name";
	public static String PROP_PARAM3 = "param3";
	public static String PROP_CONTRIBUTE_LEVEL = "contributeLevel";
	public static String PROP_PARAM2 = "param2";
	public static String PROP_VISIT_TOTAL = "visitTotal";
	public static String PROP_PARAM1 = "param1";
	public static String PROP_SYS_TYPE = "sysType";
	public static String PROP_ID = "id";
	public static String PROP_STAT_DATE = "statDate";
	public static String PROP_KEYWORDS = "keywords";
	public static String PROP_TPL_CONTENT = "tplContent";


	// constructors
	public BaseCmsChannel () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsChannel (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsChannel (
		java.lang.Long id,
		com.jeecms.cms.entity.ChnlModel model,
		com.jeecms.core.entity.Website website,
		java.lang.String sysType,
		java.lang.Integer priority,
		java.lang.Boolean hasChild,
		java.lang.Boolean display) {

		this.setId(id);
		this.setModel(model);
		this.setWebsite(website);
		this.setSysType(sysType);
		this.setPriority(priority);
		this.setHasChild(hasChild);
		this.setDisplay(display);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String sysType;
	private java.lang.String path;
	private java.lang.String name;
	private java.lang.String titleImg;
	private java.lang.String tplIndex;
	private java.lang.String tplContent;
	private java.lang.String title;
	private java.lang.String keywords;
	private java.lang.String description;
	private java.lang.Long visitTotal;
	private java.lang.Long visitToday;
	private java.util.Date statDate;
	private java.lang.String outerUrl;
	private java.lang.Integer contributeLevel;
	private java.lang.Integer priority;
	private java.lang.Boolean hasChild;
	private java.lang.Boolean display;
	private java.lang.String param1;
	private java.lang.String param2;
	private java.lang.String param3;

	// many to one
	private com.jeecms.cms.entity.CmsChannel parent;
	private com.jeecms.cms.entity.ChnlModel model;
	private com.jeecms.core.entity.Website website;

	// collections
	private java.util.Set<com.jeecms.cms.entity.CmsChannel> child;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="CHANNEL_ID"
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
	 * Return the value associated with the column: SYS_TYPE
	 */
	public java.lang.String getSysType () {
		return sysType;
	}

	/**
	 * Set the value related to the column: SYS_TYPE
	 * @param sysType the SYS_TYPE value
	 */
	public void setSysType (java.lang.String sysType) {
		this.sysType = sysType;
	}



	/**
	 * Return the value associated with the column: PATH
	 */
	public java.lang.String getPath () {
		return path;
	}

	/**
	 * Set the value related to the column: PATH
	 * @param path the PATH value
	 */
	public void setPath (java.lang.String path) {
		this.path = path;
	}



	/**
	 * Return the value associated with the column: NAME
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: NAME
	 * @param name the NAME value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}



	/**
	 * Return the value associated with the column: TITLE_IMG
	 */
	public java.lang.String getTitleImg () {
		return titleImg;
	}

	/**
	 * Set the value related to the column: TITLE_IMG
	 * @param titleImg the TITLE_IMG value
	 */
	public void setTitleImg (java.lang.String titleImg) {
		this.titleImg = titleImg;
	}



	/**
	 * Return the value associated with the column: TPL_INDEX
	 */
	public java.lang.String getTplIndex () {
		return tplIndex;
	}

	/**
	 * Set the value related to the column: TPL_INDEX
	 * @param tplIndex the TPL_INDEX value
	 */
	public void setTplIndex (java.lang.String tplIndex) {
		this.tplIndex = tplIndex;
	}



	/**
	 * Return the value associated with the column: TPL_CONTENT
	 */
	public java.lang.String getTplContent () {
		return tplContent;
	}

	/**
	 * Set the value related to the column: TPL_CONTENT
	 * @param tplContent the TPL_CONTENT value
	 */
	public void setTplContent (java.lang.String tplContent) {
		this.tplContent = tplContent;
	}



	/**
	 * Return the value associated with the column: TITLE
	 */
	public java.lang.String getTitle () {
		return title;
	}

	/**
	 * Set the value related to the column: TITLE
	 * @param title the TITLE value
	 */
	public void setTitle (java.lang.String title) {
		this.title = title;
	}



	/**
	 * Return the value associated with the column: KEYWORDS
	 */
	public java.lang.String getKeywords () {
		return keywords;
	}

	/**
	 * Set the value related to the column: KEYWORDS
	 * @param keywords the KEYWORDS value
	 */
	public void setKeywords (java.lang.String keywords) {
		this.keywords = keywords;
	}



	/**
	 * Return the value associated with the column: DESCRIPTION
	 */
	public java.lang.String getDescription () {
		return description;
	}

	/**
	 * Set the value related to the column: DESCRIPTION
	 * @param description the DESCRIPTION value
	 */
	public void setDescription (java.lang.String description) {
		this.description = description;
	}



	/**
	 * Return the value associated with the column: VISIT_TOTAL
	 */
	public java.lang.Long getVisitTotal () {
		return visitTotal;
	}

	/**
	 * Set the value related to the column: VISIT_TOTAL
	 * @param visitTotal the VISIT_TOTAL value
	 */
	public void setVisitTotal (java.lang.Long visitTotal) {
		this.visitTotal = visitTotal;
	}



	/**
	 * Return the value associated with the column: VISIT_TODAY
	 */
	public java.lang.Long getVisitToday () {
		return visitToday;
	}

	/**
	 * Set the value related to the column: VISIT_TODAY
	 * @param visitToday the VISIT_TODAY value
	 */
	public void setVisitToday (java.lang.Long visitToday) {
		this.visitToday = visitToday;
	}



	/**
	 * Return the value associated with the column: STAT_DATE
	 */
	public java.util.Date getStatDate () {
		return statDate;
	}

	/**
	 * Set the value related to the column: STAT_DATE
	 * @param statDate the STAT_DATE value
	 */
	public void setStatDate (java.util.Date statDate) {
		this.statDate = statDate;
	}



	/**
	 * Return the value associated with the column: OUTER_URL
	 */
	public java.lang.String getOuterUrl () {
		return outerUrl;
	}

	/**
	 * Set the value related to the column: OUTER_URL
	 * @param outerUrl the OUTER_URL value
	 */
	public void setOuterUrl (java.lang.String outerUrl) {
		this.outerUrl = outerUrl;
	}



	/**
	 * Return the value associated with the column: CONTRIBUTE_LEVEL
	 */
	public java.lang.Integer getContributeLevel () {
		return contributeLevel;
	}

	/**
	 * Set the value related to the column: CONTRIBUTE_LEVEL
	 * @param contributeLevel the CONTRIBUTE_LEVEL value
	 */
	public void setContributeLevel (java.lang.Integer contributeLevel) {
		this.contributeLevel = contributeLevel;
	}



	/**
	 * Return the value associated with the column: PRIORITY
	 */
	public java.lang.Integer getPriority () {
		return priority;
	}

	/**
	 * Set the value related to the column: PRIORITY
	 * @param priority the PRIORITY value
	 */
	public void setPriority (java.lang.Integer priority) {
		this.priority = priority;
	}



	/**
	 * Return the value associated with the column: HAS_CHILD
	 */
	public java.lang.Boolean getHasChild () {
		return hasChild;
	}

	/**
	 * Set the value related to the column: HAS_CHILD
	 * @param hasChild the HAS_CHILD value
	 */
	public void setHasChild (java.lang.Boolean hasChild) {
		this.hasChild = hasChild;
	}



	/**
	 * Return the value associated with the column: IS_DISPLAY
	 */
	public java.lang.Boolean getDisplay () {
		return display;
	}

	/**
	 * Set the value related to the column: IS_DISPLAY
	 * @param display the IS_DISPLAY value
	 */
	public void setDisplay (java.lang.Boolean display) {
		this.display = display;
	}



	/**
	 * Return the value associated with the column: PARAM1
	 */
	public java.lang.String getParam1 () {
		return param1;
	}

	/**
	 * Set the value related to the column: PARAM1
	 * @param param1 the PARAM1 value
	 */
	public void setParam1 (java.lang.String param1) {
		this.param1 = param1;
	}



	/**
	 * Return the value associated with the column: PARAM2
	 */
	public java.lang.String getParam2 () {
		return param2;
	}

	/**
	 * Set the value related to the column: PARAM2
	 * @param param2 the PARAM2 value
	 */
	public void setParam2 (java.lang.String param2) {
		this.param2 = param2;
	}



	/**
	 * Return the value associated with the column: PARAM3
	 */
	public java.lang.String getParam3 () {
		return param3;
	}

	/**
	 * Set the value related to the column: PARAM3
	 * @param param3 the PARAM3 value
	 */
	public void setParam3 (java.lang.String param3) {
		this.param3 = param3;
	}



	/**
	 * Return the value associated with the column: PARENT
	 */
	public com.jeecms.cms.entity.CmsChannel getParent () {
		return parent;
	}

	/**
	 * Set the value related to the column: PARENT
	 * @param parent the PARENT value
	 */
	public void setParent (com.jeecms.cms.entity.CmsChannel parent) {
		this.parent = parent;
	}



	/**
	 * Return the value associated with the column: MODEL_ID
	 */
	public com.jeecms.cms.entity.ChnlModel getModel () {
		return model;
	}

	/**
	 * Set the value related to the column: MODEL_ID
	 * @param model the MODEL_ID value
	 */
	public void setModel (com.jeecms.cms.entity.ChnlModel model) {
		this.model = model;
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
	 * Return the value associated with the column: child
	 */
	public java.util.Set<com.jeecms.cms.entity.CmsChannel> getChild () {
		return child;
	}

	/**
	 * Set the value related to the column: child
	 * @param child the child value
	 */
	public void setChild (java.util.Set<com.jeecms.cms.entity.CmsChannel> child) {
		this.child = child;
	}

	public void addTochild (com.jeecms.cms.entity.CmsChannel cmsChannel) {
		if (null == getChild()) setChild(new java.util.TreeSet<com.jeecms.cms.entity.CmsChannel>());
		getChild().add(cmsChannel);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.CmsChannel)) return false;
		else {
			com.jeecms.cms.entity.CmsChannel cmsChannel = (com.jeecms.cms.entity.CmsChannel) obj;
			if (null == this.getId() || null == cmsChannel.getId()) return false;
			else return (this.getId().equals(cmsChannel.getId()));
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