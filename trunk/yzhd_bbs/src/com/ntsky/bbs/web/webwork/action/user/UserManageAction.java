package com.ntsky.bbs.web.webwork.action.user;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Enumeration;

import com.opensymphony.xwork.ModelDriven;

import com.ntsky.framework.util.HttpUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.RoleService;
import com.ntsky.bbs.util.memory.StarUserSingleton;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.util.config.SystemConfig;

/**
 * 系统管理用户
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/17 18:13:19 $
 */
public class UserManageAction extends UserActionSupport {
	
	/**
	 * 用户列表
	 */
	private List users;
	public List getUsers(){
		return this.users;
	}
	
	private int isLock;
	private int isStar;
	public void setIsLock(int isLock){
		this.isLock = isLock;
	}
	public int getIsLock(){
		return isLock;
	}	
	
	private int userId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * 封号和解封
	 */
	public String doIsLock() throws Exception {
		try{
			userService.lockUser(userId,isLock);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return super.MANAGE;
	}
	
	/**
	 * 设置论坛之星
	 */
	public String doIsStar() throws Exception {
		try{
			userService.StarUser(userId, isStar);
			StarUserSingleton.getInstance().setStars(userService.findStarUser(2));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return super.MANAGE;
	}
	/**
	 * 封号和解封
	 */
	public String doDelete() throws Exception {
		try{
			userService.deleteUser(userId);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return super.MANAGE;
	}
	
	private RoleService roleService;
	public void setRoleService(RoleService roleService){
		this.roleService = roleService;
	}	
	// 用户角色列表
	private List userRoles = null;
	public void setUserRoles(List userRoles){
		this.userRoles = userRoles;
	}
	public List getUserRoles(){
		return this.userRoles;
	}	
	
	// 管理角色列表
	private List managerRoles = null;
	public List getManagerRoles() {
		return managerRoles;
	}
	public void setManagerRoles(List managerRoles) {
		this.managerRoles = managerRoles;
	}
	
	private User user = null;
	public User getUser(){
		return this.user;
	}
	
	/**
	 * 设置角色
	 * @return
	 * @throws Exception
	 */
	public String doSetRole() throws Exception {
		try{
			setUserRoles(roleService.getRoles(0));
			setManagerRoles(roleService.getRoles(1));
			// 设置用户信息
			user = userService.getUser(userId);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return "setRole";
	}	
	
	/**
	 * 更新用户角色
	 * @return
	 * @throws Exception
	 */
	public String doUpdateRole() throws Exception {
		try{
			String roles = super.getParameter("roles");
			userService.updateUserRoles(userId,roles);
			super.setActionMessage("更新用户角色成功!");
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}		
		return "updateRole";
	}
	public int getIsStar() {
		return isStar;
	}
	public void setIsStar(int isStar) {
		this.isStar = isStar;
	}
	
}
