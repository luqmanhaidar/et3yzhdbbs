package com.jeecms.core.entity;

import static com.jeecms.core.Constants.RES_BASE;
import static com.jeecms.core.Constants.RES_SYS;
import static com.jeecms.core.Constants.SPT;
import static com.jeecms.core.Constants.TEMPLATE;
import static com.jeecms.core.Constants.UPLOAD_PATH;
import static com.jeecms.core.Constants.USER_BASE;
import static com.jeecms.core.Constants.WEBINF;

import org.apache.commons.lang.StringUtils;

import com.jeecms.core.entity.base.BaseWebsite;

public class Website extends BaseWebsite {
	/**
	 * �û���Ը�·����/WEB-INF/user_base/
	 */
	public static final String USER_ROOT = SPT + WEBINF + SPT + USER_BASE + SPT;
	/**
	 * ҳ�����Ĭ�ϵĺ�׺
	 */
	public static final String DEF_SUFFIX = "htm";

	/**
	 * ����ͼƬ�Ļ���·�� http://www.nc138.com/res_base/nc138/arti/upload/
	 * 
	 * @return
	 */
	// @ TODO ���һ������
	public String getUploadRes(String sysType) {
		String path = getResUrlBuf().append(SPT).append(sysType).append(SPT)
				.append(UPLOAD_PATH).toString();
		return path;
	}

	/**
	 * ���վ��ķ���·�����磺http://www.nc138.com �� http://www.nc138.com:8080/CmsSys
	 * 
	 * @return
	 */
	public String getWebUrl() {
		return getWebUrlBuf().toString();
	}

	public StringBuilder getWebUrlBuf() {
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(getDomain());
		if (getPort() != null && !getPort().equals(80)) {
			sb.append(":").append(getPort());
		}
		if (getContextPath() != null) {
			sb.append(getContextPath());
		}
		return sb;
	}

	/**
	 * ���վ����Դ����·��
	 * 
	 * @return
	 */
	public String getResUrl() {
		if (StringUtils.isBlank(getBaseDomain())) {
			return getWebUrl();
		} else {
			return getResDomain();
		}
	}

	public StringBuilder getResUrlBuf() {
		if (StringUtils.isBlank(getBaseDomain())) {
			return getWebUrlBuf();
		} else {
			return new StringBuilder(getResDomain());
		}
	}

	/**
	 * ���վ�����Դ·�����磺http://www.sina.com/res_base/sina_com_www
	 * 
	 * @return
	 */
	public String getUserResUrl() {
		return getUserResUrlBuf().toString();
	}

	public StringBuilder getUserResUrlBuf() {
		return getResUrlBuf().append(SPT).append(RES_BASE).append(SPT).append(
				getResPath());
	}

	/**
	 * ���ϵͳ��Դ·�����磺http://www.sian.com/res_base/system
	 * 
	 * @return
	 */
	public String getSysResUrl() {
		return getResUrlBuf().append(SPT).append(RES_SYS).toString();
	}

	/**
	 * ����û���Ը�·�����磺/WEB-INF/user_base/ponyjava_com_www
	 * 
	 * @return
	 */
	public StringBuilder getUserRoot() {
		StringBuilder sb = new StringBuilder(USER_ROOT);
		sb.append(getResPath());
		return sb;
	}

	/**
	 * ����û����Ը�·�����磺realRoot/WEB-INF/user_base/RES_PATH
	 * 
	 * @param realRoot
	 * @return
	 */
	// public StringBuilder getUserRootReal(String realRoot) {
	// StringBuilder sb = new StringBuilder(USER_ROOT);
	// sb.append(getResPath());
	// return sb;
	// }
	/**
	 * ���ģ�����·�����磺/WEB-INF/user_base/ponyjava_com_www/template
	 * 
	 * @return
	 */
	public StringBuilder getTplRoot() {
		return getUserRoot().append(SPT).append(TEMPLATE);
	}

	/**
	 * ���ģ�����·��
	 * 
	 * @param realRoot
	 * @return
	 */
	public StringBuilder getTplRootReal(String realRoot) {
		StringBuilder sb = new StringBuilder(realRoot);
		sb.append(getTplRoot());
		return sb;
	}

	/**
	 * �����Դ��·�����磺/res_base/RES_PATH
	 * 
	 * @return
	 */
	public StringBuilder getResRootBuf() {
		StringBuilder sb = new StringBuilder();
		sb.append(SPT).append(RES_BASE).append(SPT).append(getResPath());
		return sb;
	}

	public String getResRoot() {
		return getResRootBuf().toString();
	}

	/**
	 * ����ϴ���·�����磺/res_base/RES_PATH/upload
	 * 
	 * @return
	 */
	public StringBuilder getUploadRoot() {
		return getResRootBuf().append(SPT).append(UPLOAD_PATH);
	}

	/**
	 * ����ϴ���·�����磺http://www.sina.com/res_base/RES_PATH/upload
	 * 
	 * @return
	 */
	public StringBuilder getUploadUrlBuf() {
		return getUserResUrlBuf().append(SPT).append(UPLOAD_PATH);
	}

	public String getUploadUrl() {
		return getUploadUrlBuf().toString();
	}

	/**
	 * ���ģ�����·�����磺/WEB-INF/tpl_base/RES_PATH/sysType/solution/tplName
	 * 
	 * @param tplName
	 * @param sysType
	 * @param solution
	 * @return
	 * 
	 * public String getTplPath(String tplName, String sysType, String solution) {
	 * if (solution == null) { solution = getTplCfg().getSolution(sysType); }
	 * return getTplBase() + SPT + sysType + SPT + solution + SPT + tplName; }
	 */
	/**
	 * ��û�Ա����վID
	 * 
	 * @return
	 */
	public Long getUnitedId() {
		Website united = getUnited();
		if (united != null) {
			return united.getId();
		} else {
			return getId();
		}
	}

	/**
	 * ��ȡ������
	 * 
	 * @return
	 */
	public String getRootDomain() {
		if (StringUtils.isBlank(getBaseDomain())) {
			return getDomain();
		} else {
			return getBaseDomain();
		}
	}

	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Website() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Website(java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Website(java.lang.Long id, java.lang.String domain,
			java.lang.String resPath, java.lang.Boolean close) {

		super(id, domain, resPath, close);
	}

	/* [CONSTRUCTOR MARKER END] */
}