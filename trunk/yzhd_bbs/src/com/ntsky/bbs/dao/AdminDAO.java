package com.ntsky.bbs.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 管理员模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:26 $ 
 */
public interface AdminDAO extends BaseDAO{

	/**
	 * 查找全部的管理员信息
	 * @return List 管理员集合
	 */
	public List findAdmins () throws DAOException ;
	
	/**
	 * 根据统计编号查找的统计数据
	 * @param adminId 管理编号
	 * @return Admin 统计数据
	 */	
	public Admin findAdmin(int adminId) throws DAOException ;
	
	/**
	 * 根据用户名和密码查询管理员
	 * @param username 用户名
	 * @param password 密码
	 * @return Admin 管理员信息
	 */
	public Admin findAdmin(String username, String password) throws DAOException;
	
	/**
	 * 根据用户名查询管理员
	 * @param username 用户名
	 * @return Admin 管理员信息
	 */
	public Admin findAdmin(String username) throws DAOException;	
	
	/**
	 * 更新管理员密码
	 * 
	 * @param adminId 管理员编号
	 * @param password 新密码
	 * @throws DAOException 
	 */
	public void updateAdminPassword(int adminId,String password) throws DAOException;
	
	/**
	 * 管理员登录时更新登录时间和IP
	 * 
	 * @param adminId 用户编号
	 * @param lastLoginTime 最后登陆时间
	 * @param lastLoginIp 最后登陆的IP
	 */
	public void updateLoginInfo(int userId,String lastLoginTime,String lastLoginIp) throws DAOException;
	
}
