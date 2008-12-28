package com.jeecms.cms.entity;

import com.jeecms.cms.entity.base.BaseCmsAdmin;
import com.jeecms.core.entity.Admin;

public class CmsAdmin extends BaseCmsAdmin {
	private static final long serialVersionUID = 1L;
	/**
	 * ��session�ı����key��
	 */
	public static final String ADMIN_KEY = "_cms_admin_key";

	/**
	 * ��õ�¼��
	 * 
	 * @return
	 */
	public String getLoginName() {
		Admin admin = getAdmin();
		if (admin != null) {
			return admin.getLoginName();
		} else {
			return null;
		}
	}

	/**
	 * ����Ա�Ƿ����
	 * 
	 * @return
	 */
	public boolean isAdminDisabled() {
		return getAdmin().isAdminDisabled();
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public CmsAdmin() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsAdmin(java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsAdmin(java.lang.Long id, com.jeecms.core.entity.Website website) {

		super(id, website);
	}
	/* [CONSTRUCTOR MARKER END] */
}