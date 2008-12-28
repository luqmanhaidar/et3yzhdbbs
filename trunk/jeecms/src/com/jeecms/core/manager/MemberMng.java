package com.jeecms.core.manager;

import java.util.List;

import com.jeecms.core.JeeCoreManager;
import com.jeecms.core.entity.Member;
import com.jeecms.core.exception.GuestRegisteredException;
import com.ponyjava.common.page.Pagination;

public interface MemberMng extends JeeCoreManager<Member> {
	public Pagination getAll(Long webId, int page, int countPerPage);

	public List<Member> getAll(Long webId);

	/**
	 * 通过统一用户ID获取GUEST的ID
	 * 
	 * @param webId
	 * @param userID
	 * @return
	 */
	public Member getGuestByUserId(Long webId, Long userID);

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @param hasUser
	 *            是否有统一用户
	 * @return
	 * @throws GuestRegisteredException
	 *             用户已经注册
	 */
	public Member register(Member user, boolean hasUser)
			throws GuestRegisteredException;

	/**
	 * 获得登录的会员
	 * <p>
	 * 如果管理员登录了，可从通过userId获得会员
	 * </p>
	 * 
	 * @param webId
	 * @param userId
	 * @return
	 */
	public Member getLoginMember(Long webId, Long userId, Long memberId);
}