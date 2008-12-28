package com.jeecms.core.manager;

import java.util.List;

import com.jeecms.core.JeeCoreManager;
import com.jeecms.core.entity.Admin;
import com.jeecms.core.exception.UserRegisterException;
import com.ponyjava.common.page.Pagination;

/**
 * 管理员manager接口
 * 
 * @author liufang
 * 
 */
public interface AdminMng extends JeeCoreManager<Admin> {
	/**
	 * 通过统一用户ID获得管理员
	 * 
	 * @param webId
	 * @param userId
	 * @return
	 */
	public Admin getAdminByUserId(Long webId, Long userId);

	/**
	 * 获得某站点管理员
	 * 
	 * @param webId
	 * @return
	 */
	public Pagination getAll(Long webId, int page, int countPerPage);

	/**
	 * 根据统一用户ID获得管理员列表
	 * 
	 * @param unitedId
	 * @return
	 */
	public List<Admin> getListByUserId(Long userId);

	/**
	 * 获得登录状态的管理员
	 * 
	 * @param webId
	 * @param adminId
	 * @param userId
	 * @return
	 */
	public Admin getLoginAdmin(Long webId, Long adminId, Long userId);

	/**
	 * 获得登录状态的管理员
	 * 
	 * @param webId
	 * @param adminId
	 * @param userId
	 * @return
	 */
	public Admin getLoginAdmin(String domain, Long adminId, Long userId);

	/**
	 * 注册管理员
	 * 
	 * @param admin
	 * @param isExist
	 * @return
	 */
	public Admin register(Admin admin, boolean isExist)
			throws UserRegisterException;
}