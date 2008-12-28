package com.jeecms.core.manager;

import java.util.List;

import com.jeecms.core.JeeCoreManager;
import com.jeecms.core.entity.Admin;
import com.jeecms.core.exception.UserRegisterException;
import com.ponyjava.common.page.Pagination;

/**
 * ����Աmanager�ӿ�
 * 
 * @author liufang
 * 
 */
public interface AdminMng extends JeeCoreManager<Admin> {
	/**
	 * ͨ��ͳһ�û�ID��ù���Ա
	 * 
	 * @param webId
	 * @param userId
	 * @return
	 */
	public Admin getAdminByUserId(Long webId, Long userId);

	/**
	 * ���ĳվ�����Ա
	 * 
	 * @param webId
	 * @return
	 */
	public Pagination getAll(Long webId, int page, int countPerPage);

	/**
	 * ����ͳһ�û�ID��ù���Ա�б�
	 * 
	 * @param unitedId
	 * @return
	 */
	public List<Admin> getListByUserId(Long userId);

	/**
	 * ��õ�¼״̬�Ĺ���Ա
	 * 
	 * @param webId
	 * @param adminId
	 * @param userId
	 * @return
	 */
	public Admin getLoginAdmin(Long webId, Long adminId, Long userId);

	/**
	 * ��õ�¼״̬�Ĺ���Ա
	 * 
	 * @param webId
	 * @param adminId
	 * @param userId
	 * @return
	 */
	public Admin getLoginAdmin(String domain, Long adminId, Long userId);

	/**
	 * ע�����Ա
	 * 
	 * @param admin
	 * @param isExist
	 * @return
	 */
	public Admin register(Admin admin, boolean isExist)
			throws UserRegisterException;
}