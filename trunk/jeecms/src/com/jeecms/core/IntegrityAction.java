package com.jeecms.core;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.core.entity.Member;
import com.jeecms.core.entity.User;
import com.jeecms.core.manager.MemberMng;
import com.jeecms.core.manager.UserMng;

public class IntegrityAction extends FrontAction {
	/**
	 * �û���Դ����ַ���磺http://www.sina.com/res_base/sina_com_www
	 * 
	 * @return
	 */
	public String getRoot() {
		if (root == null) {
			root = getWeb().getUserResUrl();
		}
		return root;
	}

	/**
	 * ϵͳ��Դ����ַ���磺http://www.sina.com/front_res
	 * 
	 * @return
	 */
	public String getSysResRoot() {
		if (sysResRoot == null) {
			sysResRoot = getWeb().getSysResUrl();
		}
		return sysResRoot;
	}

	public int getPageNo() {
		return pageNo;
	}

	/**
	 * ��û�ԱID
	 * 
	 * @return
	 */
	public Long getMemberId() {
		Member m = getMember();
		if (m != null) {
			return m.getId();
		} else {
			return null;
		}
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

	private String root;
	private String sysResRoot;

	protected String wholeUrl;
	protected int pageNo = 0;
	@Autowired
	protected MemberMng memberMng;
	@Autowired
	protected UserMng userMng;
}
