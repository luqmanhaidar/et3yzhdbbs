package com.jeecms.core;

import static com.jeecms.core.Constants.SPT;
import static com.jeecms.core.Constants.TPL_DEF_SOLUTION;
import static com.jeecms.core.Constants.TPL_SUFFIX;

import java.io.File;

import com.ponyjava.common.struts2.interceptor.DomainNameAware;
import com.ponyjava.common.struts2.interceptor.UrlAware;

/**
 * 独立模板基类
 * <p>
 * 提供选择模板功能。获得翻页基本信息。
 * </p>
 * 
 * @author liufang
 * 
 */
public abstract class IndeBaseAction extends IntegrityAction implements
		DomainNameAware, UrlAware {
	public static final String INDE_RPEFIX = "sys_";

	/**
	 * 先查找方案模板，如果不存在则使用默认模板
	 * 
	 * @param tplName
	 * @return
	 */
	protected String handleResult(String tplName) {
		tplPath = getSolutionTpl(getSolution(), tplName);
		String real = contextPvd.getAppRealPath(tplPath);
		// @ TODO 是否使用缓存，以免每次都检查模板是否存在？
		if (!new File(real).exists()) {
			tplPath = getSolutionTpl(TPL_DEF_SOLUTION, tplName);
		}
		return SUCCESS;
	}

	private String getSolutionTpl(String solution, String tplName) {
		StringBuilder sb = getWeb().getTplRoot().append(SPT).append(
				getSysType()).append(SPT).append(solution).append(SPT).append(
				INDE_RPEFIX).append(tplName).append(TPL_SUFFIX);
		return sb.toString();
	}

	protected abstract String getSolution();

	protected abstract String getSysType();

	
	public void setDomainName(String domainName) {
		// empty is OK!
	}

	
	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}

	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	
	public void setPageSuffix(String pageSuffix) {
		this.pageSuffix = pageSuffix;
	}

	
	public void setPathParams(String[] pathParams) {
		// empty is OK!
	}

	
	public void setWholeUrl(String wholeUrl) {
		this.wholeUrl = wholeUrl;

	}

}