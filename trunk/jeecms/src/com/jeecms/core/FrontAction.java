package com.jeecms.core;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.core.entity.Website;
import com.jeecms.core.manager.WebsiteMng;
import com.opensymphony.xwork2.Action;
import com.ponyjava.common.struts2.ContextPvd;
import com.ponyjava.common.struts2.interceptor.DomainNameAware;

public class FrontAction implements Action {
	
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * 获得站点ID
	 * 
	 * @return
	 */
	public Long getWebId() {
		return getWeb().getId();
	}

	/**
	 * 获得站点对象
	 * 
	 * @return
	 */
	public Website getWeb() {
		String domain = (String) contextPvd
				.getRequestAttr(DomainNameAware.DOMAIN_NAME);
		Website website = websiteMng.getWebsite(domain);
		if (website == null) {
			// @ TODO 转发到一个友好的页面
			throw new RuntimeException("不存在与该域名匹配的站点："
					+ ServletActionContext.getRequest().getAttribute(
							DomainNameAware.DOMAIN_NAME));
		}
		return website;
	}

	/**
	 * /WEB-INF/user_base/ponyjava_com_www/template
	 * 
	 * @return
	 */
	public String getTplBase() {
		return getWeb().getTplRoot().toString();
	}

	/**
	 * /WEB-INF/user_base/
	 * 
	 * @return
	 */
	public String getUserBase() {
		return Website.USER_ROOT;
	}

	protected String pageLink;
	protected String pageSuffix;
	protected String tplPath;
	@Autowired
	protected WebsiteMng websiteMng;
	@Autowired
	protected ContextPvd contextPvd;

	public String getTplPath() {
		return tplPath;
	}

	public void setTplPath(String tplPath) {
		this.tplPath = tplPath;
	}

	public String getPageLink() {
		return pageLink;
	}

	public String getPageSuffix() {
		return pageSuffix;
	}
}
