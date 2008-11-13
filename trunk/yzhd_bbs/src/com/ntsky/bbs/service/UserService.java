package com.ntsky.bbs.service;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.UserException;
import com.ntsky.bbs.exception.LoginException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

public interface UserService extends BaseService{

	/**
	 * 注册用户
	 * 
	 * @param user 用户对象
	 */
	public void signupUser(User user) throws ServiceException;
		
	/**
	 * 创建用户
	 * @param user 用户对象
	 * @throws ServiceException
	 */
	public void createUser(User user) throws ServiceException;
	
	/**
	 * 删除用户
	 * 
	 * @param userId 用户编号
	 */
	public void deleteUser(long userId) throws ServiceException ;
	
	/**
	 * 根据用户id取得用户的详细信息
	 * 
	 * @param userId 用户编号
	 * @return User 用户信息
	 */
	public User getUser(long userId) throws ServiceException ;
	
	/**
	 * 根据用户角色列表用户
	 * @param roles
	 * @return
	 * @throws DAOException
	 */
	public List getUsersByRoles(String roles) throws ServiceException ;	
	
	/**
	 * 根据用户名取得用户详细信息
	 * 
	 * @param username 用户名 
	 * @return　User 用户信息
	 */
	public User getUser(String username) throws ServiceException ;
	
	/**
	 * 根据用户名和密码取得用户信息
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return User 用户信息
	 */
	public User getUser(String username,String password) throws ServiceException ;
	
	/**
	 * 取得用户列表
	 * 
	 * @param username 用户名关键字
	 * @param order 排序字段
	 * @return QueryResult 用户列表
	 * @throws ServiceException
	 */
	public QueryResult getUsers(String username,Map orderMap,Pagination pagination) throws ServiceException;	
	
	/**
	 * 根据用户角色取得用户列表
	 * 
	 * @param username 用户名关键字
	 * @param roels 用户角色 -1 全部用户
	 * @param order 排序字段
	 * @return QueryResult 用户列表
	 * @throws ServiceException
	 */
	public QueryResult getUsersByRoles(String username,int roles, Map orderMap,Pagination pagination) throws ServiceException;	
		
	/**
	 * 修改用户信息
	 * 
	 * @param User user 用户信息
	 * @return void 用户信息
	 */
	public void editUser(User user) throws ServiceException ;
	
	/**
	 * 分页列表用户信息
	 * 
	 * @param place 对象
	 * @param pagination 分页对象
	 * @return List 操作日志列表
	 */
	//public List getUsers(String place, Pagination pagination);

	/**
	 * 验证用户登录
	 * 
	 * @param username 用户名
	 * @param password 用户密码
	 * @param User 用户对象
	 * @throws LoginException 登陆Exception
	 */
	public User authLogin(String username, String password) throws LoginException;	
	
	/**
	 * 用户登录时更新登录时间和IP
	 * 
	 * @param userId 用户编号
	 * @param lastLoginTime 最后登陆时间
	 * @param lastLoginIp 最后登陆的IP
	 * @throws ServiceException
	 */
	public void updateLoginInfo(int userId,String lastLoginTime,String lastLoginIp) throws ServiceException;

	/**
	 * 检测用户答案
	 * @param userId 用户编号
	 * @param answer 用户提示问题答案
	 * @throws ServiceException
	 */
	public String checkAnswer(int userId,String answer) throws ServiceException,UserException;
	
	/**
	 * 修改用户密码
	 * 
	 * @param username 用户名
	 * @param oldPassword 旧密码
	 * @param password 新密码
	 * @throws UserException 用户异常
	 */
	public void editUserPassword(String username,String oldPassword,String password) throws UserException,ServiceException;
	
	/**
	 * 用户总数
	 * @throws DAOException
	 */
	public int countUser() throws ServiceException;
	
	/**
	 * 最新注册的用户
	 * @return
	 * @throws DAOException
	 */
	public User getNewlyUser() throws ServiceException;
	
	/**
	 * 锁定用户
	 * @param userId 用户编号 
	 * @param isLock 是否锁定
	 */
	public void lockUser(int userId,int isLock) throws ServiceException;
	
	/**
	 * 锁定用户
	 * @param userId 用户编号 
	 * @param isLock 是否锁定
	 */
	public void StarUser(int userId,int isStar) throws ServiceException;
	/**
	 * 更新用户角色
	 * @param userId 用户编号
	 * @param roles 角色
	 * @throws DAOException
	 */
	public void updateUserRoles(int userId,String roles) throws ServiceException;

	/**
	 * 更新用户在线时长
	 * @param username 用户名
	 * @param time 在线时长
	 * @throws DAOException
	 */
	public void updateOnlineTime(String username,int time) throws ServiceException;
	
	public List findStarUser(int num) throws DAOException;
	public String findRoleId(String username) throws ServiceException;
	
}
