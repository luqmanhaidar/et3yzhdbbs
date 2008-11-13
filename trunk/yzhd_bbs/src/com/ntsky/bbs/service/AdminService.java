package com.ntsky.bbs.service;

import java.util.List;

import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.exception.LoginException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.exception.UserException;

/**
 * 管理员模块业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface AdminService {

	/**
	 * 创建管理员
	 * 
	 * @param admin
	 */
	public void createAdmin(Admin admin) throws ServiceException ;
	
	/**
	 * 删除管理员
	 * 
	 * @param adminId 管理员编号
	 */
	public void deleteAdmin(int adminId) throws ServiceException ;
	
	/**
	 * 修改管理员密码
	 * @param adminId 管理员编号
	 * @param oldPassword 旧密码
	 * @param password 新密码
	 * @throws UserException
	 * @throws ServiceException
	 */
	public void editAdminPassword(int adminId,String oldPassword,String password) throws UserException,ServiceException;
	
	/**
	 * 修改管理员信息
	 * 
	 * @param admin 管理员信息
	 */
	public void editAdmin(Admin admin) throws ServiceException ;
	
	/**
	 * 取得管理员
	 * 
	 * @param adminId 管理员编号
	 */
	public Admin getAdmin(int adminId) throws ServiceException ;
	
	/**
	 * 根据用户名取得管理员
	 * 
	 * @param username 管理员名称
	 */
	public Admin getAdmin(String username) throws ServiceException ;
	
	/**
	 * 取得管理员列表
	 * 
	 * @return List 管理员列表
	 */
	public List getAdmins() throws ServiceException ;

	/**
	 * 验证管理员登录
	 * 
	 * @param username 用户名
	 * @param password 用户密码
	 * @param Admin 用户对象
	 * @throws LoginException 登陆Exception
	 */
	public Admin authLogin(String username, String password) throws LoginException;	
	
	/**
	 * 管理员登录时更新登录时间和IP
	 * 
	 * @param adminId 用户编号
	 * @param lastLoginTime 最后登陆时间
	 * @param lastLoginIp 最后登陆的IP
	 * @throws ServiceException
	 */
	public void updateLoginInfo(int adminId,String lastLoginTime,String lastLoginIp) throws ServiceException;	
	
}
