package com.ntsky.bbs.web.webwork.action.user;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Enumeration;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.framework.util.HttpUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.RoleService;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.util.config.SystemConfig;

/**
 * 系统管理用户
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:25 $
 */
public class UsersAction extends UserActionSupport implements Preparable {
	
	/**
	 * 用户列表
	 */
	private List users;
	public List getUsers(){
		return this.users;
	}
	
	private int roles = -1;
	public int getRoles() {
		return roles;
	}
	public void setRoles(int roles) {
		this.roles = roles;
	}
	
	/**
	 * 列表用户
	 * 操作者管理员
	 */
	public String execute() throws Exception {

		/* ---------- 权限判断 ------------ */
		//　列表用户
		if(!isPermisson("3_2")){
			setWarnMessage("您没有列表用户的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("列表用户");
		/* -------------------------------*/
		
		//String username = new String(getParameter("username").getBytes("iso-8859-1"));
		String username = getParameter("username");
		if(logger.isInfoEnabled()){
			logger.info("查找用户名 : " + username + "的信息.") ;
		}

		QueryResult queryResult = null;
		try{
			queryResult = userService.getUsersByRoles(username,roles,null,new Pagination(getPaginationStart(),SystemConfig.getInstance().getIntPropertyValue(Symbols.PAGINATION,Symbols.PAGINATION_USER)));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		if(logger.isInfoEnabled()){
			logger.info("系统管理用户信息...");
		}
		this.users = queryResult.getItems();
		super.setPagination(queryResult.getPagination());
		return super.SUCCESS;
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

	// 特殊用户列表
	private List specialRoles = null;
	public List getSpecialRoles() {
		return specialRoles;
	}
	public void setSpecialRoles(List specialRoles) {
		this.specialRoles = specialRoles;
	}	
	
	/**
	 * 用户列表前的准备工作
	 */
	public void prepare() throws Exception {
		
		setManagerRoles(roleService.getRoles(1));
		setUserRoles(roleService.getRoles(0));
		setSpecialRoles(roleService.getRoles(2));
	}
	
}
