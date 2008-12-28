package com.jeecms.core.manager;

import javax.naming.AuthenticationException;

import com.jeecms.core.JeeCoreManager;
import com.jeecms.core.entity.User;
import com.jeecms.core.exception.UserRegisterException;

public interface UserMng extends JeeCoreManager<User> {
	/**
	 * 通过登录名查找用户，并使用缓存。
	 * 
	 * @param loginName
	 * @return 返回用户。用户不存在返回null。
	 */
	public User getUserByLoginName(String loginName);

	/**
	 * 认证。并返回用户对象。认证失败抛出异常。
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 * @throws AuthenticationException
	 */
	public User authenticate(String loginName, String password);

	/**
	 * 登录。登录成功后保存至session。
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 * @throws AuthenticationException
	 */
	public User login(String loginName, String password);

	/**
	 * 更新密码
	 * 
	 * @param oldPwd
	 * @param newPwd
	 * @return 是否更新成功
	 */
	public boolean updatePassword(User user, String oldPwd, String newPwd);

	/**
	 * 更新密码
	 * 
	 * @param id
	 * @param newPwd
	 */
	public void updatePassword(Long id, String newPwd);

	/**
	 * 更新登录信息
	 * 
	 * @param user
	 */
	public void updateLoginInfo(User user);

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @param isExist
	 *            用户是否存在
	 * @return
	 * @throws UserRegisterException
	 */
	public User register(User user, boolean isExist)
			throws UserRegisterException;
}