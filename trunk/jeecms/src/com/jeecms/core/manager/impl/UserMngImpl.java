package com.jeecms.core.manager.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.jeecms.core.JeeCoreManagerImpl;
import com.jeecms.core.dao.UserDao;
import com.jeecms.core.entity.User;
import com.jeecms.core.exception.UserRegisterException;
import com.jeecms.core.manager.UserMng;
import com.ponyjava.common.hibernate3.Updater;
import com.ponyjava.common.struts2.ContextPvd;
import com.ponyjava.common.util.PwdEncoder;

@Service
@Transactional
public class UserMngImpl extends JeeCoreManagerImpl<User> implements UserMng {
	
	public User authenticate(String loginName, String password) {
		User user = getUserByLoginName(loginName);
		if (user != null) {
			String md5Pwd = pwdEncoder.encodePassword(password);
			if (md5Pwd.equals(user.getPassword())) {
				return user;
			}
		}
		return null;
	}

	
	public User login(String loginName, String password) {
		User united = authenticate(loginName, password);
		updateLoginInfo(united);
		contextPvd.setSessionAttr(User.USER_KEY, united);
		return united;
	}

	
	public boolean updatePassword(User user, String oldPwd, String newPwd) {
		if (pwdEncoder.isPasswordValid(user.getPassword(), oldPwd)) {
			user.setPassword(pwdEncoder.encodePassword(newPwd));
			update(user);
			return true;
		} else {
			return false;
		}
	}

	
	public void updatePassword(Long id, String newPwd) {
		User united = findById(id);
		united.setPassword(pwdEncoder.encodePassword(newPwd));
		update(united);
	}

	
	public void updateLoginInfo(User admin) {
		admin.setLastLoginTime(admin.getCurrentLoginTime());
		admin.setLastLoginIp(admin.getCurrentLoginIp());
		admin.setCurrentLoginTime(new java.sql.Timestamp(System
				.currentTimeMillis()));
		admin.setCurrentLoginIp(contextPvd.getRemoteIp());
		if (admin.getLoginCount() == null || admin.getLoginCount() < 0) {
			admin.setLoginCount(0L);
		}
		admin.setLoginCount(admin.getLoginCount() + 1);
	}

	/**
	 * 通过登录名查找用户，并使用缓存。
	 * 
	 * @param loginName
	 * @return 返回用户。用户不存在返回null。
	 */
	
	public User getUserByLoginName(String loginName) {
		Element e = userLoginNameCache.get(loginName);
		if (e != null) {
			Serializable userId = (Serializable) e.getObjectValue();
			return findById(userId);
		} else {
			User user = getUserDao().getUserByLoginName(loginName);
			if (user != null) {
				userLoginNameCache.put(new Element(loginName, user.getId()));
			}
			return user;
		}
	}

	
	public User register(User user, boolean isExist) {
		Assert.notNull(user);
		Assert.isTrue(!StringUtils.isBlank(user.getLoginName()));
		User origUser = getUserByLoginName(user.getLoginName());
		if (isExist) {
			if (origUser == null) {
				throw new UserRegisterException("用户不存在！");
			}
			return origUser;
		} else {
			if (origUser != null) {
				throw new UserRegisterException("该用户名已注册！");
			}
			return save(user);
		}
	}

	
	public Object updateByUpdater(Updater updater) {
		User udt = (User) updater.getBean();
		// 密码加密
		String p = udt.getPassword();
		if (!StringUtils.isBlank(p)) {
			udt.setPassword(pwdEncoder.encodePassword(p));
		} else {
			udt.setPassword(null);
		}
		// 处理缓存
		String afterName = udt.getLoginName();
		if (afterName != null) {
			Long id = udt.getId();
			User before = findById(id);
			String beforeName = before.getLoginName();
			if (!afterName.equals(beforeName)) {
				userLoginNameCache.remove(beforeName);
				userLoginNameCache.put(new Element(afterName, id));
			}
		}

		User after = (User) super.updateByUpdater(updater);
		return after;
	}

	
	public User save(User user) {
		Assert.notNull(user);
		Assert.isTrue(!StringUtils.isBlank(user.getPassword()), "密码不能为空！");
		// 密码加密
		String p = pwdEncoder.encodePassword(user.getPassword());
		user.setPassword(p);
		// 是否禁用
		if (user.getDisabled() == null) {
			user.setDisabled(false);
		}
		// 初始化信息
		String ip = contextPvd.getRemoteIp();
		Date now = new Timestamp(System.currentTimeMillis());
		user.setCreateTime(now);
		user.setCurrentLoginIp(ip);
		user.setCurrentLoginTime(now);
		user.setLastLoginIp(ip);
		user.setLastLoginTime(now);
		user.setLoginCount(0L);
		super.save(user);
		// 处理缓存
		userLoginNameCache.put(new Element(user.getLoginName(), user.getId()));
		return user;
	}

	
	public User findById(Serializable id) {
		User user = super.findById(id);
		return user;
	}

	
	public User deleteById(Serializable id) {
		User user = super.deleteById(id);
		// 处理缓存
		if (user != null) {
			Element e = userLoginNameCache.get(user.getLoginName());
			if (e != null) {
				userLoginNameCache.remove(user.getLoginName());
			}
		}
		return user;
	}

	@Autowired
	private PwdEncoder pwdEncoder;
	@Autowired
	private ContextPvd contextPvd;
	@Autowired
	@Qualifier("userLoginName")
	private Cache userLoginNameCache;

	@Autowired
	public void setUserDao(UserDao dao) {
		super.setDao(dao);
	}

	public UserDao getUserDao() {
		return (UserDao) super.getDao();
	}
}
