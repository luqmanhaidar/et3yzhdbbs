package com.jeecms.core;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.core.entity.Member;
import com.jeecms.core.entity.User;
import com.jeecms.core.manager.MemberMng;
import com.jeecms.core.manager.UserMng;

public class IntegrityAction extends FrontAction {
	/**
	 * 用户资源根地址。如：http://www.sina.com/res_base/sina_com_www
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
	 * 系统资源根地址。如：http://www.sina.com/front_res
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
	 * 获得会员ID
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
	 * 获得会员对象
	 * 
	 * @return
	 */
	public Member getMember() {
		Long memberId = (Long) contextPvd.getSessionAttr(Member.MEMBER_KEY);
		return memberMng.getLoginMember(getWebId(), getUserId(), memberId);
	}

	/**
	 * 获得用户ID
	 * 
	 * @return
	 */
	public Long getUserId() {
		return (Long) contextPvd.getSessionAttr(User.USER_KEY);
	}

	/**
	 * 获得用户对象
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
