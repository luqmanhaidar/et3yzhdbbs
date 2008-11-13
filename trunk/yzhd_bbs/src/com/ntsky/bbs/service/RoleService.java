package com.ntsky.bbs.service;

import java.util.List;

import com.ntsky.bbs.domain.Role;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 角色模块业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface RoleService extends BaseService{

	/**
	 * 创建角色信息
	 * 
	 * @param role 角色
	 */
	public void createRole(Role role) throws ServiceException ;
	
	/**
	 * 修改角色信息
	 * 
	 * @param role 角色
	 */
	public void editRole(Role role) throws ServiceException ;
	
	/**
	 * 删除角色信息
	 * 
	 * @param roleId 角色编号
	 * @throws ServiceException
	 */
	public void deleteRole(int roleId) throws ServiceException ;
	
	/**
	 * 取得角色
	 * 
	 * @param roleId 角色编号
	 * @return role 编号对应的角色信息
	 * @throws ServiceException 
	 */
	public Role getRole(int roleId) throws ServiceException ;
	
	/**
	 * 设置全部的角色到内存
	 * 
	 * @return List 用户列表
	 * @throws ServiceException
	 */	
	public void setRolesToMemory() throws ServiceException ;
	
	/**
	 * 根据组类别列表全部的角色信息
	 * 
	 * @param type 用户组分类 (-1、roles)
	 * @return List 用户列表
	 * @throws ServiceException
	 */
	public List getRoles(int type) throws ServiceException ;
	
}
