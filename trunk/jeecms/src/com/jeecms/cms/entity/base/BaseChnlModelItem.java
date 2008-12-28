package com.jeecms.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cms_chnl_model_item table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="cms_chnl_model_item"
 */

public abstract class BaseChnlModelItem  implements Serializable {

	public static String REF = "ChnlModelItem";
	public static String PROP_NAME = "name";
	public static String PROP_HELP = "help";
	public static String PROP_MODEL = "model";
	public static String PROP_INPUT_TYPE = "inputType";
	public static String PROP_CHECKED = "checked";
	public static String PROP_ID = "id";
	public static String PROP_LABEL = "label";
	public static String PROP_PRIORITY = "priority";


	// constructors
	public BaseChnlModelItem () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseChnlModelItem (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseChnlModelItem (
		java.lang.Long id,
		com.jeecms.cms.entity.ChnlModel model,
		java.lang.String name,
		java.lang.Integer priority,
		java.lang.Boolean checked) {

		this.setId(id);
		this.setModel(model);
		this.setName(name);
		this.setPriority(priority);
		this.setChecked(checked);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.String label;
	private java.lang.String help;
	private java.lang.String inputType;
	private java.lang.Integer priority;
	private java.lang.Boolean checked;

	// many to one
	private com.jeecms.cms.entity.ChnlModel model;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="MITEM_ID"
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
	 * Return the value associated with the column: LABEL
	 */
	public java.lang.String getLabel () {
		return label;
	}

	/**
	 * Set the value related to the column: LABEL
	 * @param label the LABEL value
	 */
	public void setLabel (java.lang.String label) {
		this.label = label;
	}



	/**
	 * Return the value associated with the column: HELP
	 */
	public java.lang.String getHelp () {
		return help;
	}

	/**
	 * Set the value related to the column: HELP
	 * @param help the HELP value
	 */
	public void setHelp (java.lang.String help) {
		this.help = help;
	}



	/**
	 * Return the value associated with the column: INPUT_TYPE
	 */
	public java.lang.String getInputType () {
		return inputType;
	}

	/**
	 * Set the value related to the column: INPUT_TYPE
	 * @param inputType the INPUT_TYPE value
	 */
	public void setInputType (java.lang.String inputType) {
		this.inputType = inputType;
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
	 * Return the value associated with the column: IS_CHECKED
	 */
	public java.lang.Boolean getChecked () {
		return checked;
	}

	/**
	 * Set the value related to the column: IS_CHECKED
	 * @param checked the IS_CHECKED value
	 */
	public void setChecked (java.lang.Boolean checked) {
		this.checked = checked;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.ChnlModelItem)) return false;
		else {
			com.jeecms.cms.entity.ChnlModelItem chnlModelItem = (com.jeecms.cms.entity.ChnlModelItem) obj;
			if (null == this.getId() || null == chnlModelItem.getId()) return false;
			else return (this.getId().equals(chnlModelItem.getId()));
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