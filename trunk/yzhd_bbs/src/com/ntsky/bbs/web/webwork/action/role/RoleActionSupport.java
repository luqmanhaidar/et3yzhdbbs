package com.ntsky.bbs.web.webwork.action.role;

import java.util.List;

import com.ntsky.bbs.service.RoleService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

/**
 * 角色抽象类
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:34 $
 */
public abstract class RoleActionSupport extends BasicActionSupport{
	
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
	
	// 资源数组
	private List resources = null;
	public List getResources() {
		return resources;
	}
	public void setResources(List resources) {
		this.resources = resources;
	}	
	
	protected RoleService roleService;
	public void setRoleService(RoleService roleService){
		this.roleService = roleService;
	}
	
}
