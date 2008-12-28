package com.jeecms.cms.entity;

import com.jeecms.cms.entity.base.BaseCmsConfig;

public class CmsConfig extends BaseCmsConfig {
	private static final long serialVersionUID = 1L;

	/**
	 * ���ģ�巽��
	 * 
	 * @param sysType
	 * @return
	 */
	public String getSolution(String sysType) {
		String solution = getWebsite().getSolutions().get(sysType);
		if (solution == null) {
			throw new RuntimeException("�����ڸ�ϵͳ��ģ�巽����" + sysType);
		}
		return solution;
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public CmsConfig() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsConfig(java.lang.Long id) {
		super(id);
	}

	/* [CONSTRUCTOR MARKER END] */

}