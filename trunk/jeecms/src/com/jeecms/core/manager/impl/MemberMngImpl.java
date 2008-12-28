package com.jeecms.core.manager.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.core.JeeCoreManagerImpl;
import com.jeecms.core.dao.MemeberDao;
import com.jeecms.core.entity.Member;
import com.jeecms.core.entity.User;
import com.jeecms.core.entity.Website;
import com.jeecms.core.exception.GuestRegisteredException;
import com.jeecms.core.manager.MemberMng;
import com.ponyjava.common.hibernate3.Updater;
import com.ponyjava.common.page.Pagination;
import com.ponyjava.common.struts2.ContextPvd;

@Service
@Transactional
public class MemberMngImpl extends JeeCoreManagerImpl<Member> implements
		MemberMng {
	
	public Pagination getAll(Long webId, int page, int countPerPage) {
		Member example = new Member();
		example.setWebsite(new Website(webId));
		return findByEg(example, page, countPerPage);
	}

	
	public List<Member> getAll(Long webId) {
		Member example = new Member();
		example.setWebsite(new Website(webId));
		return findByEgList(example);
	}

	
	public Member getGuestByUserId(Long webId, Long userId) {
		Member example = new Member();
		example.setWebsite(new Website(webId));
		example.setUser(new User(userId));
		List<Member> list = findByEgList(example);
		if (list.size() > 0) {
			Member guest = list.get(0);
			Hibernate.initialize(guest.getWebsite());
			return list.get(0);
		} else {
			return null;
		}
	}

	
	public Member register(Member user, boolean hasUser)
			throws GuestRegisteredException {
		// TODO Auto-generated method stub
		return null;
	}

	private Member getMember(Long webId, Long userId) {
		Member example = new Member();
		example.setWebsite(new Website(webId));
		example.setUser(new User(userId));
		List<Member> list = findByEgList(example);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	
	public Member getLoginMember(Long webId, Long userId, Long memberId) {
		// �û�δ��¼
		if (userId == null) {
			return null;
		}
		// ��Ա�ѵ�¼
		if (memberId != null) {
			Member member = findById(memberId);
			// ��Ա�뵱ǰվ��һ��
			if (member.getWebsite().getId().equals(webId)) {
				return member;
			}
		}
		// �û���¼����Աδ��¼
		Member m = getMember(webId, userId);
		// �����¼��Ϣ
		contextPvd.setSessionAttr(Member.MEMBER_KEY, m.getId());
		return m;
	}

	
	public Object updateByUpdater(Updater updater) {
		Member guest = (Member) super.updateByUpdater(updater);
		return guest;
	}

	
	public Member save(Member guest) {
		guest.setDisabled(false);
		guest.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		super.save(guest);
		return guest;
	}

	
	public Member findById(Serializable id) {
		Member guest = super.findById(id);
		return guest;
	}

	
	public Member deleteById(Serializable id) {
		Member guest = super.deleteById(id);
		return guest;
	}

	@Autowired
	private ContextPvd contextPvd;

	@Autowired
	public void setMemberDao(MemeberDao dao) {
		super.setDao(dao);
	}

	protected MemeberDao getMemberDao() {
		return (MemeberDao) super.getDao();
	}

}
