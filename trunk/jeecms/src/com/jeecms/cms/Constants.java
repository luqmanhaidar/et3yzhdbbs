package com.jeecms.cms;

import java.util.LinkedHashMap;
import java.util.Map;

public class Constants {
	/**
	 * ����ϵͳ����
	 */
	public static final String ARTICLE_SYS = "article";
	/**
	 * ͼƬϵͳ����
	 */
	public static final String PICTURE_SYS = "picture";
	/**
	 * ����ϵͳ����
	 */
	public static final String DOWNLOAD_SYS = "download";
	/**
	 * ��Աϵͳ����
	 */
	public static final String MEMBER_SYS = "cms_member";
	/**
	 * ����ϵͳ����
	 */
	public static final String COMMON_SYS = "cms_common";

	/**
	 * CMSϵͳ����
	 */
	public static final Map<String, String> CMSSYS_TYPES = new LinkedHashMap<String, String>();
	static {
		CMSSYS_TYPES.put(ARTICLE_SYS, "����ϵͳ");
		CMSSYS_TYPES.put(PICTURE_SYS, "ͼƬϵͳ");
		CMSSYS_TYPES.put(DOWNLOAD_SYS, "����ϵͳ");
	}

	public static String getSysName(String sysType) {
		return CMSSYS_TYPES.get(sysType);
	}
}
