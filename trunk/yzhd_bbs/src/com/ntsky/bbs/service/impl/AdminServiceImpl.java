package com.ntsky.bbs.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.ntsky.framework.util.security.MD5;

import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.dao.AdminDAO;
import com.ntsky.bbs.service.AdminService;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.LoginException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.exception.UserException;

/**
 * 管理员模块业务处理实现
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class AdminServiceImpl implements AdminService{
	
	public static Logger logger = Logger.getLogger(AdminServiceImpl.class); 
	
	private AdminDAO adminDAO;
	
	public void setAdminDAO(AdminDAO adminDAO){
		this.adminDAO = adminDAO;
	}

	/**
	 * 创建管理员
	 * 
	 * @param admin 管理员信息
	 */
	public void createAdmin(Admin admin) throws ServiceException{
		try{
			admin.setPassword(MD5.md5(admin.getPassword()));
			adminDAO.save(admin);
		}
		catch(DAOException daoException){
			throw new ServiceException("创建管理员['"+admin.getUsername()+"']发生错误.");
		}
	}

	/**
	 * 删除管理员
	 * 
	 * @param adminId 管理员编号
	 */
	public void deleteAdmin(int adminId) throws ServiceException {
		Admin admin = null;
		try{
			admin = adminDAO.findAdmin(adminId);
			adminDAO.delete(admin);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}

	/**
	 * 修改管理员信息
	 * 
	 * @param admin 管理员信息
	 */
	public void editAdmin(Admin admin) throws ServiceException {
		Admin tempAdmin = null;
		try{
			tempAdmin = adminDAO.findAdmin(admin.getId().intValue());
			tempAdmin.setPermissions(admin.getPermissions());
			adminDAO.update(tempAdmin);
		}
		catch(DAOException daoException){
			throw new ServiceException("更新管理员['"+tempAdmin.getUsername()+"'发生错误]");
		}
	}

	/**
	 * 取得管理员
	 * 
	 * @param adminId 管理员编号
	 */
	public Admin getAdmin(int adminId) throws ServiceException {
		try{
			return adminDAO.findAdmin(adminId);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}

	/**
	 * 取得管理员列表
	 * 
	 * @return List 管理员列表
	 */
	public List getAdmins() throws ServiceException {
		try{
			return adminDAO.findAdmins();
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}
	
	/**
	 * 根据用户名取得管理员信息
	 * 
	 * @param username 用户名
	 * @return Admin 管理员信息
	 */
	public Admin getAdmin(String username) throws ServiceException {
		try{
			return adminDAO.findAdmin(username);
		}
		catch(DAOException daoException){
			throw new ServiceException("列表管理员发生错误.");
		}
	}
	
	/**
	 * 验证管理员登录
	 * 
	 * @param username 用户名
	 * @param password 用户密码
	 * @param Admin 管理员对象
	 * @throws LoginException 登陆Exception
	 */
	public Admin authLogin(String username, String password) throws LoginException {
		Admin admin = getAdmin(username);
		if(admin==null){
			throw new LoginException("用户名不存在!");
		}
		else{
			/*if(logger.isDebugEnabled()){
				logger.debug("输入的密码 ： " + password + " 加密后的值 : " + MD5.md5(password));
				logger.debug("库中密码信息 : " + admin.getPassword());
			}*/
			if(!(MD5.md5(password).equals(admin.getPassword()))){
				throw new LoginException("用户密码错误!");
			}
			else{
				return admin;
			}
		}
	}
	
	/**
	 * 修改管理员密码
	 * 
	 * @param adminId 用户名
	 * @param oldPassword 旧密码
	 * @param password 新密码
	 * @throws UserException 用户异常
	 */
	public void editAdminPassword(int adminId,String oldPassword,String password) throws UserException,ServiceException{
		try{
			Admin admin = adminDAO.findAdmin(adminId);
			if(admin==null){
				throw new UserException("该管理员不存在");
			}
			else{
				/*if(logger.isDebugEnabled()){
					logger.debug("管理员名称 ： " + admin.getUsername() + " | 密码 ： " + MD5.md5(oldPassword));
				}*/
				admin = adminDAO.findAdmin(admin.getUsername(),MD5.md5(oldPassword));
				if(admin==null){
					throw new UserException("您输入的旧密码错误,请重新输入");
				}
				else{
					adminDAO.updateAdminPassword(adminId,MD5.md5(password));
				}
			}
		}
		catch(UserException userException){
			throw new UserException(userException.getMessage());
		}			
		catch(ServiceException serviceException){
			throw new ServiceException("修改用户密码发生错误.");
		}	
	}
	
	/**
	 * 管理员登录时更新登录时间和IP
	 * 
	 * @param adminId 用户编号
	 * @param lastLoginTime 最后登陆时间
	 * @param lastLoginIp 最后登陆的IP
	 * @throws ServiceException
	 */
	public void updateLoginInfo(int adminId,String lastLoginTime,String lastLoginIp) throws ServiceException{
		try{
			adminDAO.updateLoginInfo(adminId,lastLoginTime,lastLoginIp);
		}
		catch(ServiceException serviceException){
			throw new ServiceException("更新管理员登陆信息发生错误.");
		}	
	}	
	
}
