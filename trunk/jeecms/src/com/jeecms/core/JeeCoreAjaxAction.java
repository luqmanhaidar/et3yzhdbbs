package com.jeecms.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.core.entity.Admin;
import com.jeecms.core.entity.Member;
import com.jeecms.core.entity.User;
import com.jeecms.core.entity.Website;
import com.jeecms.core.manager.AdminMng;
import com.jeecms.core.manager.MemberMng;
import com.jeecms.core.manager.UserMng;
import com.jeecms.core.manager.WebsiteMng;
import com.opensymphony.xwork2.Action;
import com.ponyjava.common.struts2.ContextPvd;
import com.ponyjava.common.struts2.interceptor.DomainNameAware;

@SuppressWarnings("serial")
public class JeeCoreAjaxAction implements Action, DomainNameAware {

	/**
	 * ���վ��ID
	 * 
	 * @return
	 */
	public Long getWebId() {
		return getWeb().getId();
	}

	/**
	 * ���վ�����
	 * 
	 * @return
	 */
	public Website getWeb() {
		Website website = websiteMng.getWebsite(domainName);
		if (website == null) {
			// @ TODO ת����һ���Ѻõ�ҳ��
			throw new RuntimeException("�������������ƥ���վ�㣺"
					+ ServletActionContext.getRequest().getAttribute(
							DomainNameAware.DOMAIN_NAME));
		}
		return website;
	}

	/**
	 * ����û�ID
	 * 
	 * @return
	 */
	public Long getUserId() {
		return (Long) contextPvd.getSessionAttr(User.USER_KEY);
	}

	/**
	 * ����û�����
	 * 
	 * @return
	 */
	public User getUser() {
		return userMng.findById(getUserId());
	}

	/**
	 * ��ù���ԱID
	 * 
	 * @return
	 */
	public Long getAdminId() {
		return (Long) contextPvd.getSessionAttr(Admin.ADMIN_KEY);
	}

	/**
	 * ��ù���Ա����
	 * 
	 * @return
	 */
	public Admin getAdmin() {
		return adminMng.findById(getAdminId());
	}

	/**
	 * ��û�ԱID
	 * 
	 * @return
	 */
	public Long getMemberId() {
		return getMember().getId();
	}

	/**
	 * ��û�Ա����
	 * 
	 * @return
	 */
	public Member getMember() {
		Long memberId = (Long) contextPvd.getSessionAttr(Member.MEMBER_KEY);
		return memberMng.getLoginMember(getWebId(), getUserId(), memberId);
	}

	protected Map<String, Object> jsonRoot = new HashMap<String, Object>();
	protected String domainName;

	@Autowired
	protected WebsiteMng websiteMng;
	@Autowired
	protected ContextPvd contextPvd;
	@Autowired
	protected UserMng userMng;
	@Autowired
	protected AdminMng adminMng;
	@Autowired
	protected MemberMng memberMng;

	
	public String execute() throws Exception {
		return SUCCESS;
	}

	
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public Map<String, Object> getJsonRoot() {
		return jsonRoot;
	}

	public void setJsonRoot(Map<String, Object> jsonRoot) {
		this.jsonRoot = jsonRoot;
	}
}
