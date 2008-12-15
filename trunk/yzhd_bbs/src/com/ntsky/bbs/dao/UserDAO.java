package com.ntsky.bbs.dao;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 用户模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface UserDAO extends BaseDAO{

	/**
	 * 查找全部的用户信息
	 * 
	 * @param paramsMap 用户条件数组
	 * @param pagination 分页对象
	 * @return List 用户集合
	 */
	public List findUsers (final Map paramsMap, final Pagination pagination) throws DAOException;
	
	/**
	 * 根据用户编号查找的用户数据
	 * 
	 * @param userId 用户编号
	 * @return User 用户对象
	 */	
	public User findUser(long userId)  throws DAOException;
	
	/**
	 * 根据用户名查找用户信息
	 * 
	 * @param username 用户名称
	 * @return User 用户对象
	 */
	public User findUser(String username) throws DAOException;
	public String findRoleId(String username) throws DAOException ;
	/**
	 * 根据用户角色列表用户
	 * @param roles
	 * @return
	 * @throws DAOException
	 */
	public List findUsersByRoles(String roles) throws DAOException;
	
	/**
	 * 根据用户名和密码查找用户信息
	 * 
	 * @param username 用户名
	 * @param password 用户密码
	 * @return User 用户对象
	 */
	public User findUser(String username, String password) throws DAOException;
	
	/**
	 * 取得用户列表
	 * 
	 * @param username 用户名关键字
	 * @param order 排序字段
	 * @return QueryResult 用户列表
	 * @throws ServiceException
	 */
	public QueryResult findUsers(String username,Map orderMap,Pagination pagination) throws ServiceException;	
	
	/**
	 * 根据用户角色取得用户列表
	 * 
	 * @param username 用户名关键字
	 * @param roels 用户角色 -1 全部用户
	 * @param order 排序字段
	 * @return QueryResult 用户列表
	 * @throws ServiceException
	 */
	public QueryResult findUsersByRoles(String username,int roles, Map orderMap,Pagination pagination) throws DAOException ;	
	
	/**
	 * 用户登录时更新登录时间和IP
	 * 
	 * @param userId 用户编号
	 * @param lastLoginTime 最后登陆时间
	 * @param lastLoginIp 最后登陆的IP
	 * @throws ServiceException
	 */
	public void updateLoginInfo(int userId,String lastLoginTime,String lastLoginIp) throws DAOException;	

	/**
	 * 修改用户密码
	 * 
	 * @param userId 用户编号
	 * @param password 新密码
	 * @throws DAOException 数据处理异常
	 */	
	public void updateUserPassword(int userId,String password) throws DAOException;
	
	/**
	 * 用户总数
	 * @throws DAOException
	 */
	public int countUser() throws DAOException;
	
	/**
	 * 最新注册的用户
	 * @return
	 * @throws DAOException
	 */
	public User findNewlyUser() throws DAOException;
	
	/**
	 * 更新用户中主题和贴子相关信息
	 * @param username
	 * @param type 类型（topic,post）
	 * @param operator 操作方式(inc +1 des -1)
	 * @return
	 * @throws DAOException
	 */
	public void updateAboutPost(String username,String type,String operator) throws DAOException;

	/**
	 * 更新用户角色
	 * @param username 用户名
	 * @param roles 用户角色
	 * @throws DAOException
	 */
	public void updateRole(String username,String roles) throws DAOException;	

	/**
	 * 更新用户为新角色
	 * @param oldRole 旧角色
	 * @param newRole 新角色
	 * @throws DAOException
	 */
	public void updateNewRole(String oldRole,String newRole) throws DAOException ;
	
	/**
	 * 根据操作更新用户金钱信息
	 * @param username
	 * @param money
	 * @throws DAOException
	 */
	public void updateMoney(String username,int money) throws DAOException ;
	
	/**
	 * 锁定用户
	 * @param userId 用户编号 
	 * @param isLock 是否锁定
	 */
	public void lockUser(int userId,int isLock) throws DAOException ;
	/**
	 * 锁定用户
	 * @param userId 用户编号 
	 * @param isLock 是否锁定
	 */
	public void StarUser(int userId,int isStar) throws DAOException ;
	/**
	 * 更新用户角色
	 * @param userId 用户编号
	 * @param roles 角色
	 * @throws DAOException
	 */
	public void updateUserRoles(int userId,String roles) throws DAOException;
	
	/**
	 * 更新用户在线时长
	 * @param username 用户名
	 * @param time 在线时长
	 * @throws DAOException
	 */
	public void updateOnlineTime(String username,int time) throws DAOException ;
	
	public List findStarUser(int num) throws DAOException;
	
	/**
	 * 验证是否存在这个用户
	 * @return
	 * @throws DAOException
	 */
	public boolean hasThisUserByName(String username) throws DAOException;
	
}
