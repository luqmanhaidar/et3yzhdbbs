package com.jeecms.core.manager;

import javax.naming.AuthenticationException;

import com.jeecms.core.JeeCoreManager;
import com.jeecms.core.entity.User;
import com.jeecms.core.exception.UserRegisterException;

public interface UserMng extends JeeCoreManager<User> {
	/**
	 * ͨ����¼�������û�����ʹ�û��档
	 * 
	 * @param loginName
	 * @return �����û����û������ڷ���null��
	 */
	public User getUserByLoginName(String loginName);

	/**
	 * ��֤���������û�������֤ʧ���׳��쳣��
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 * @throws AuthenticationException
	 */
	public User authenticate(String loginName, String password);

	/**
	 * ��¼����¼�ɹ��󱣴���session��
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 * @throws AuthenticationException
	 */
	public User login(String loginName, String password);

	/**
	 * ��������
	 * 
	 * @param oldPwd
	 * @param newPwd
	 * @return �Ƿ���³ɹ�
	 */
	public boolean updatePassword(User user, String oldPwd, String newPwd);

	/**
	 * ��������
	 * 
	 * @param id
	 * @param newPwd
	 */
	public void updatePassword(Long id, String newPwd);

	/**
	 * ���µ�¼��Ϣ
	 * 
	 * @param user
	 */
	public void updateLoginInfo(User user);

	/**
	 * ע���û�
	 * 
	 * @param user
	 * @param isExist
	 *            �û��Ƿ����
	 * @return
	 * @throws UserRegisterException
	 */
	public User register(User user, boolean isExist)
			throws UserRegisterException;
}