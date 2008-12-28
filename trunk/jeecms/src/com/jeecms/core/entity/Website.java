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
	 * 用户相对根路径。/WEB-INF/user_base/
	 */
	public static final String USER_ROOT = SPT + WEBINF + SPT + USER_BASE + SPT;
	/**
	 * 页面访问默认的后缀
	 */
	public static final String DEF_SUFFIX = "htm";

	/**
	 * 内容图片的基础路径 http://www.nc138.com/res_base/nc138/arti/upload/
	 * 
	 * @return
	 */
	// @ TODO 需进一步考虑
	public String getUploadRes(String sysType) {
		String path = getResUrlBuf().append(SPT).append(sysType).append(SPT)
				.append(UPLOAD_PATH).toString();
		return path;
	}

	/**
	 * 获得站点的访问路径。如：http://www.nc138.com 或 http://www.nc138.com:8080/CmsSys
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
	 * 获得站点资源访问路径
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
	 * 获得站点的资源路径。如：http://www.sina.com/res_base/sina_com_www
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
	 * 获得系统资源路径。如：http://www.sian.com/res_base/system
	 * 
	 * @return
	 */
	public String getSysResUrl() {
		return getResUrlBuf().append(SPT).append(RES_SYS).toString();
	}

	/**
	 * 获得用户相对根路径。如：/WEB-INF/user_base/ponyjava_com_www
	 * 
	 * @return
	 */
	public StringBuilder getUserRoot() {
		StringBuilder sb = new StringBuilder(USER_ROOT);
		sb.append(getResPath());
		return sb;
	}

	/**
	 * 获得用户绝对根路径。如：realRoot/WEB-INF/user_base/RES_PATH
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
	 * 获得模板相对路径。如：/WEB-INF/user_base/ponyjava_com_www/template
	 * 
	 * @return
	 */
	public StringBuilder getTplRoot() {
		return getUserRoot().append(SPT).append(TEMPLATE);
	}

	/**
	 * 获得模板绝对路径
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
	 * 获得资源根路径。如：/res_base/RES_PATH
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
	 * 获得上传根路径。如：/res_base/RES_PATH/upload
	 * 
	 * @return
	 */
	public StringBuilder getUploadRoot() {
		return getResRootBuf().append(SPT).append(UPLOAD_PATH);
	}

	/**
	 * 获得上传根路径。如：http://www.sina.com/res_base/RES_PATH/upload
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
	 * 获得模板相对路径。如：/WEB-INF/tpl_base/RES_PATH/sysType/solution/tplName
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
	 * 获得会员联合站ID
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
	 * 获取根域名
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