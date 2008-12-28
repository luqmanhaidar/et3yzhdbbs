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
	 * ͨ��ͳһ�û�ID��ȡGUEST��ID
	 * 
	 * @param webId
	 * @param userID
	 * @return
	 */
	public Member getGuestByUserId(Long webId, Long userID);

	/**
	 * ע���û�
	 * 
	 * @param user
	 * @param hasUser
	 *            �Ƿ���ͳһ�û�
	 * @return
	 * @throws GuestRegisteredException
	 *             �û��Ѿ�ע��
	 */
	public Member register(Member user, boolean hasUser)
			throws GuestRegisteredException;

	/**
	 * ��õ�¼�Ļ�Ա
	 * <p>
	 * �������Ա��¼�ˣ��ɴ�ͨ��userId��û�Ա
	 * </p>
	 * 
	 * @param webId
	 * @param userId
	 * @return
	 */
	public Member getLoginMember(Long webId, Long userId, Long memberId);
}