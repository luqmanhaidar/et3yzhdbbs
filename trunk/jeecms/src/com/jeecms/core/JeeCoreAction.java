package com.jeecms.core;

import javax.servlet.http.Cookie;

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
import com.ponyjava.common.struts2.ContextPvd;
import com.ponyjava.common.struts2.action.BaseAction;
import com.ponyjava.common.struts2.interceptor.DomainNameAware;

/**
 * jeesys��action���ȡ�
 * <p>
 * �����������û���¼������·������Ϣ
 * </p>
 * 
 * @author liufang
 * 
 */
@SuppressWarnings("serial")
public class JeeCoreAction extends BaseAction implements DomainNameAware {
	/**
	 * ָ����¼����cookie����
	 */
	public static final String COOKIE_COUNT = "_countPerPage";
	/**
	 * cookie��ָ��������¼��
	 */
	public static final int COOKIE_MAX_COUNT = 200;
	/**
	 * Ĭ�ϼ�¼��
	 */
	public static final int DEFAULT_COUNT = 20;
	/**
	 * �������磺www.sina.com
	 */
	protected String domainName;

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

	/**
	 * ���ҳ��cookieָ����ÿҳ��ʾ��¼��
	 * 
	 * @return
	 */
	protected int getCookieCount() {
		Cookie c = contextPvd.getCookie(COOKIE_COUNT);
		int count = 0;
		if (c != null) {
			try {
				count = Integer.parseInt(c.getValue());
			} catch (Exception e) {
			}
		}
		if (count <= 0) {
			count = DEFAULT_COUNT;
		} else if (count > 200) {
			count = COOKIE_MAX_COUNT;
		}
		return count;
	}

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

	
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
}
