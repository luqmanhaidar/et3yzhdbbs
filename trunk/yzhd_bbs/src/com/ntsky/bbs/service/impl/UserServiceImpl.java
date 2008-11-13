package com.ntsky.bbs.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ntsky.framework.mail.HtmlMail;
import com.ntsky.framework.mail.Mail;
import com.ntsky.framework.mail.MailException;
import com.ntsky.framework.mail.Sender;
import com.ntsky.framework.util.security.MD5;
import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.dao.UserDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.LoginException;
import com.ntsky.bbs.exception.MailSenderException;
import com.ntsky.bbs.exception.ObjectNotExistException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.exception.UserException;
import com.ntsky.bbs.service.UserService;
import com.ntsky.bbs.util.config.EmailConfig;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

public class UserServiceImpl implements UserService{

	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO){
		this.userDAO = userDAO;
	}
	
	/**
	 * 注册用户
	 * @param user 用户对象
	 */
	public void signupUser(User user) throws ServiceException {
		try{
			user.setLastLoginTime(new Date());
			user.setRegisterTime(new Date());
			user.setLoginTimes(1);
			user.setRoles("4");
			String password = user.getPassword();
			String answer = user.getAnswer();
			user.setPassword(MD5.md5(password));
			user.setAnswer(MD5.md5(answer));
			userDAO.save(user);					
		}
		catch(DAOException daoException){
			throw new ServiceException("注册用户["+user.getUsername()+"]发生错误.");
		}
		/*catch (MailException mailEx) {
			logger.error("mail config is error, please config the email.");
			logger.error("mail exception info is : \r\n", mailEx);
			throw new MailSenderException(mailEx.getMessage());
		}*/
	}
		
	/**
	 * 创建用户
	 * @param user 用户对象
	 * @throws ServiceException
	 */
	public void createUser(User user) throws ServiceException {
		try{
			user.setLastLoginTime(new Date());
			user.setRegisterTime(new Date());
			user.setLoginTimes(1);
			String password = user.getPassword();
			String answer = user.getAnswer();
			user.setPassword(MD5.md5(password));
			user.setAnswer(MD5.md5(answer));
			user.setFace("images/face/default.gif");
			userDAO.save(user);
		}
		catch(DAOException daoException){
			throw new ServiceException("创建用户["+user.getUsername()+"]发生错误.");
		}
	}	
	
	/**
	 * 根据用户角色列表用户
	 * @param roles
	 * @return
	 * @throws DAOException
	 */
	public List getUsersByRoles(String roles) throws ServiceException{
		try{
			return userDAO.findUsersByRoles(roles);
		}
		catch(DAOException de){
			throw new DAOException(de.getMessage());
		}
	}	
	
	/**
	 * 删除用户
	 * @param userId 用户编号
	 */
	public void deleteUser(long userId) throws ServiceException {
		try{
			userDAO.delete(getUser(userId));
		}
		catch(DAOException daoException){
			throw new ServiceException("删除用户["+userId+"]发生错误.");
		}
	}
	
	/**
	 * 根据用户id取得用户的详细信息
	 * @param userId 用户编号
	 * @return User 用户信息
	 */
	public User getUser(long userId) throws ServiceException {
		try{
			return userDAO.findUser(userId);
		}
		catch(DAOException daoException){
			throw new ServiceException("根据用户ID['"+userId+"']检索用户发生错误.");
		}
	}
	
	/**
	 * 根据用户名取得用户详细信息
	 * @param username 用户名 
	 * @return　User 用户信息
	 */
	public User getUser(String username) throws ServiceException {
		try {
			return userDAO.findUser(username);
		}
		catch(DAOException daoException){
			throw new ServiceException("根据用户名['"+username+"']检索用户发生错误.");
		}
	}
	
	/**
	 * 根据用户名和密码取得用户信息
	 * @param username 用户名
	 * @param password 密码
	 * @return User 用户信息
	 */
	public User getUser(String username,String password){
		try{
			return userDAO.findUser(username,password);
		}
		catch(DAOException daoException){
			throw new ServiceException("根据用户名['"+username+"']和密码检索用户发生错误.");
		}
	}

	/**
	 * 取得用户列表
	 * 
	 * @param username 用户名关键字
	 * @param order 排序字段
	 * @return QueryResult 用户列表
	 * @throws ServiceException
	 */
	public QueryResult getUsers(String username,Map orderMap,Pagination pagination) throws ServiceException{
		try{
			return userDAO.findUsers(username,orderMap,pagination);
		}
		catch(DAOException daoException){
			throw new ServiceException("列表用户发生错误.");
		}
	}	
	
	/**
	 * 根据用户角色取得用户列表
	 * 
	 * @param username 用户名关键字
	 * @param roels 用户角色 -1 全部用户
	 * @param order 排序字段
	 * @return QueryResult 用户列表
	 * @throws ServiceException
	 */
	public QueryResult getUsersByRoles(String username,int roles, Map orderMap,Pagination pagination) throws ServiceException {
		try{
			return userDAO.findUsersByRoles(username,roles,orderMap,pagination);
		}
		catch(DAOException daoException){
			throw new ServiceException("根据角色列表用户发生错误...");
		}		
	}	
	
	/**
	 * 修改用户信息
	 * @param User user 用户信息
	 * @return void 用户信息
	 */
	public void editUser(User user) {
		try{
			User tempUser = userDAO.findUser(user.getId().intValue());
			tempUser.setAddress(user.getAddress());
			tempUser.setBirthday(user.getBirthday());
			tempUser.setSex(user.getSex());
			tempUser.setEmail(user.getEmail());
			tempUser.setFace(user.getFace());
			tempUser.setGmail(user.getGmail());
			tempUser.setSignature(user.getSignature());
			tempUser.setImIcq(user.getImIcq());
			tempUser.setImMsn(user.getImMsn());
			tempUser.setImQq(user.getImQq());
			tempUser.setImYahoo(user.getImYahoo());
			tempUser.setWebsite(user.getWebsite());
			tempUser.setIntro(user.getIntro());
			tempUser.setAlias(user.getAlias());
			userDAO.update(tempUser);
		}
		catch(DAOException daoException){
			throw new ServiceException("更新用户[' "+user.getUsername()+" ']信息失败.");
		}
	}
	
	/**
	 * 验证用户登录
	 * 
	 * @param username 用户名
	 * @param password 用户密码
	 * @param User 用户对象
	 * @throws LoginException 登陆Exception
	 */
	public User authLogin(String username, String password) throws LoginException {
		User user = getUser(username);
		if(user==null){
			throw new LoginException("用户名不存在!");
		}
		else{
			if(!(MD5.md5(password).equals(user.getPassword()))){
				throw new LoginException("用户密码错误!");
			}
			else{
				if(user.getIsLock()==1){
					throw new LoginException("用户已被锁定，请联系管理员!");
				}
				return user;
			}
		}
	}	
	
	/**
	 * 分页列表用户信息
	 * @param place 对象
	 * @param pagination 分页对象
	 * @return List 操作日志列表
	 */
	//public List getUsers(String place, Pagination pagination);

	
	/**
	 * 用户登录时更新登录时间和IP
	 * 
	 * @param userId 用户编号
	 * @param lastLoginTime 最后登陆时间
	 * @param lastLoginIp 最后登陆的IP
	 * @throws ServiceException
	 */
	public void updateLoginInfo(int userId,String lastLoginTime,String lastLoginIp) throws ServiceException{
		try {
			userDAO.updateLoginInfo(userId,lastLoginTime,lastLoginIp);
		}
		catch(DAOException daoException){
			throw new ServiceException("更新用户登陆后的信息发生错误.");
		}
	}	
	
	/**
	 * 检测用户答案
	 * @param userId 用户编号
	 * @param answer 用户提示问题答案
	 * @throws ServiceException
	 */
	public String checkAnswer(int userId,String answer) throws ServiceException,UserException{
		User user = null;
		try {
			user = getUser(userId);
			/*if(logger.isDebugEnabled()){
				logger.debug("根据userId["+userId+"]取得用户信息");
			}*/
			if(user==null){
				throw new UserException("用户不存在");
			}
			else {
				/*if(logger.isDebugEnabled()){
					logger.debug("密码提示问题答案 ["+answer+"] | 加密后的答案 ["+MD5.md5(answer)+"] | 用户原始答案　["+user.getAnswer()+"]");
				}*/
				if (!(MD5.md5(answer).equals(user.getAnswer()))){
					throw new UserException("您输入的密码提示问题答案['"+answer+"']错误");
				}
				else {
					int ranInt = 0;
					ranInt = (new Random()).nextInt(999999);
					if (ranInt < 100000)
						ranInt += 100000;
					String newPassword = String.valueOf(ranInt);
					// 更新用户的密码为新密码
					updateUserPassword(user.getId().intValue(),MD5.md5(newPassword));
					return String.valueOf(ranInt);
				}
			}
		}
		catch(UserException userException){
			throw new UserException(userException.getMessage());
		}			
		catch(DAOException daoException){
			throw new ServiceException("取回密码发生错误.");
		}	
	}	

	/**
	 * 修改用户密码
	 * 
	 * @param username 用户名
	 * @param oldPassword 旧密码
	 * @param password 新密码
	 * @throws UserException 用户异常
	 */
	public void editUserPassword(String username,String oldPassword,String password) throws UserException{
		try{
			User user = userDAO.findUser(username);
			if(user==null){
				throw new UserException("用户['"+username+"']不存在");
			}
			else{
				user = userDAO.findUser(username,MD5.md5(oldPassword));
				if(user==null){
					throw new UserException("您输入的旧密码错误,请重新输入");
				}
				else{
					updateUserPassword(user.getId().intValue(),MD5.md5(password));
				}
			}
		}
		catch(UserException userException){
			throw new UserException(userException.getMessage());
		}			
		catch(DAOException DAOException){
			throw new ServiceException("修改用户密码发生错误.");
		}	
	}
	
	/**
	 * 更新用户密码
	 * @param userId 用户编号
	 * @param newPassword 新密码
	 * @throws ServiceException
	 */
	private void updateUserPassword(int userId,String newPassword) throws ServiceException {
		try{
			userDAO.updateUserPassword(userId,newPassword);
		}
		catch(DAOException DAOException){
			throw new ServiceException("更新用户密码.");
		}	
	}
	
	/**
	 * 用户总数
	 * @throws DAOException
	 */
	public int countUser() throws ServiceException {
		try{
			return userDAO.countUser();
		}
		catch(DAOException DAOException){
			throw new ServiceException("统计用户总数.");
		}	
	}
	
	/**
	 * 最新注册的用户
	 * @return
	 * @throws DAOException
	 */
	public User getNewlyUser() throws ServiceException{
		try{
			return userDAO.findNewlyUser();
		}
		catch(DAOException DAOException){
			throw new ServiceException("取得最新注册的用户发生错误.");
		}	
	}

	/**
	 * 锁定用户
	 * @param userId 用户编号 
	 * @param isLock 是否锁定
	 */
	public void lockUser(int userId,int isLock) throws ServiceException{
		try{
			userDAO.lockUser(userId,isLock);
		}
		catch(DAOException DAOException){
			throw new ServiceException("锁定用户操作发生错误...");
		}	
	}
	/**
	 * 锁定用户
	 * @param userId 用户编号 
	 * @param isLock 是否锁定
	 */
	public void StarUser(int userId,int isStar) throws ServiceException{
		try{
			userDAO.StarUser(userId, isStar);
		}
		catch(DAOException DAOException){
			throw new ServiceException("锁定用户操作发生错误...");
		}	
	}
	/**
	 * 更新用户角色
	 * @param userId 用户编号
	 * @param roles 角色
	 * @throws DAOException
	 */
	public void updateUserRoles(int userId,String roles) throws ServiceException {
		try{
			userDAO.updateUserRoles(userId,roles);
		}
		catch(DAOException DAOException){
			throw new ServiceException("锁定用户操作发生错误...");
		}	
	}	
	
	/**
	 * 更新用户在线时长
	 * @param username 用户名
	 * @param time 在线时长
	 * @throws DAOException
	 */
	public void updateOnlineTime(String username,int time) throws ServiceException {
		try{
			userDAO.updateOnlineTime(username,time);
		}
		catch(DAOException DAOException){
			throw new ServiceException("更新用户在线时长发生错误...");
		}	
	}

	public List findStarUser(int num) throws DAOException {
		// TODO Auto-generated method stub
		return userDAO.findStarUser(num);
	}	
	
	public String findRoleId(String username) throws ServiceException{
		return userDAO.findRoleId(username);
	}
	
}
