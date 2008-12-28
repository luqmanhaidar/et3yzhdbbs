package com.jeecms.core;

import org.apache.commons.lang.StringUtils;

public abstract class PageBaseAction extends IntegrityAction {
	public static final String INDEX = "index";

	private void handlePathParams() {
		len = pathParams.length;
		pathName = pathParams[0];
		pageName = pathParams[len - 1];
		int lineIndex = pageName.indexOf("_");
		if (lineIndex != -1) {
			pageRaw = pageName.substring(0, lineIndex);
		} else {
			pageRaw = pageName;
		}
	}

	
	public String execute() throws Exception {
		handlePathParams();
		beforeAll();
		pageName = pathParams[len - 1];
		if (len == 1 && pageRaw.equals(INDEX)) {
			// ��ҳ
			sysIndex();
			afterChnl();
		} else if (len == 1 && StringUtils.isNumeric(pageRaw)) {
			// ����
			content(null, Long.parseLong(pageRaw));
			afterContent();
		} else if (len == 1) {
			alone(pageName);
			afterChnl();
		} else if (len == 2 && pageRaw.equals(INDEX)) {
			chnlIndex(pathName);
			afterChnl();
		} else if (len == 2 && StringUtils.isNumeric(pageRaw)) {
			content(pathName, Long.parseLong(pageRaw));
			afterContent();
		} else {
			throw new RuntimeException("����·��������");
		}
		afterAll();
		prepareTplParams();
		return SUCCESS;
	}

	/**
	 * ϵͳ��ҳ
	 */
	protected abstract void sysIndex();

	/**
	 * ��Ŀҳ
	 */
	protected abstract void chnlIndex(String chnlName);

	/**
	 * ����ҳ
	 */
	protected abstract void content(String chnlName, Long id);

	/**
	 * ��ҳ
	 */
	protected abstract void alone(String chnlName);

	protected void beforeAll() {
	}

	protected void afterAll() {
	}

	protected void afterChnl() {
	}

	protected void afterContent() {
	}

	protected void prepareTplParams() {
	}

	protected String[] pathParams;
	protected int len = 0;
	protected String pathName;
	protected String pageName;
	protected String pageRaw;

	public void setPathParams(String[] pathParams) {
		this.pathParams = pathParams;
	}

	public void setWholeUrl(String wholeUrl) {
		this.wholeUrl = wholeUrl;
	}

	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}

	public void setPageSuffix(String pageSuffix) {
		this.pageSuffix = pageSuffix;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
