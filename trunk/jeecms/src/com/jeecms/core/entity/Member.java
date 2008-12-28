package com.jeecms.core.entity;

import org.apache.commons.lang.StringUtils;

import com.jeecms.core.entity.base.BaseMember;

public class Member extends BaseMember {
	private static final long serialVersionUID = 1L;

	/**
	 * 获得登录名
	 * 
	 * @return
	 */
	public String getLoginName() {
		User user = getUser();
		if (user != null) {
			return user.getLoginName();
		} else {
			return null;
		}
	}

	/**
	 * 获得名字。返回昵称，如不存在则返回登录名。
	 * 
	 * @return
	 */
	public String getName() {
		String s = getNickName();
		if (!StringUtils.isBlank(s)) {
			return s;
		} else {
			return getLoginName();
		}
	}

	/**
	 * 在session的保存的key。
	 */
	public static final String MEMBER_KEY = "_member_key";

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Member() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Member(java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Member(java.lang.Long id, com.jeecms.core.entity.Website website,
			com.jeecms.core.entity.User user, java.lang.Boolean disabled) {

		super(id, website, user, disabled);
	}
	/* [CONSTRUCTOR MARKER END] */

}