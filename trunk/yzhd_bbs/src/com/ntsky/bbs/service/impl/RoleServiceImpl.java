package com.ntsky.bbs.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ntsky.bbs.domain.Role;
import com.ntsky.bbs.dao.RoleDAO;
import com.ntsky.bbs.dao.UserDAO;
import com.ntsky.bbs.service.RoleService;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.memory.RoleSingleton;
/**
 * 论坛模块业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class RoleServiceImpl implements RoleService{

	private static Logger logger = Logger.getLogger(RoleServiceImpl.class);
	
	private RoleDAO roleDAO;
	
	// 角色数据处理对象
	public void setRoleDAO(RoleDAO roleDAO){
		this.roleDAO = roleDAO;
	}
	
	private UserDAO userDAO;
	public void setUserDAO(UserDAO userDAO){
		this.userDAO = userDAO;
	}
	
	/**
	 * 创建角色信息
	 * <ul>
	 *   <li>添加信息到数据</li>
	 *   <li>添加信息到内存</li>
	 * </ul>
	 * @param role 角色
	 */
	public void createRole(Role role) throws ServiceException {
		try{
			if(role.getType()!=0){
				role.setMinTopic(-1);
			}
			roleDAO.save(role);
			// 写入到内存
			RoleSingleton.getInstance().addRole(role);
		}
		catch(DAOException daoException){
			throw new ServiceException("创建角色["+role.getName()+"]发生错误.");
		}		
	}	
	
	/**
	 * 修改角色信息
	 * 
	 * @param role 帮助
	 */
	public void editRole(Role role) throws ServiceException {
		try{
			roleDAO.update(role);
			
			// 重置内存信息
			RoleSingleton.getInstance().resetRole(role);
		}
		catch(DAOException daoException){
			throw new ServiceException("更新角色["+role.getName()+"]发生错误.");
		}
	}	
	
	/**
	 * 删除角色信息
	 * 
	 * @param roleId 角色编号
	 * @throws ServiceException
	 */
	public void deleteRole(int roleId) throws ServiceException {
		try {
			roleDAO.delete(getRole(roleId));
			// 删除内存中信息
			RoleSingleton.getInstance().deleteRole(String.valueOf(roleId));
		
			// 更新所有原先为roleId角色的用户为新的角色4(系统角色)
			userDAO.updateNewRole(String.valueOf(roleId), "4");
		}
		catch(DAOException daoException){
			throw new ServiceException("删除roleId[\""+roleId+"\"]对应角色发生错误.");
		}
	}
	
	/**
	 * 取得角色
	 * 
	 * @param roleId 角色编号
	 * @return role 编号对应的角色信息
	 * @throws ServiceException 
	 */
	public Role getRole(int roleId) throws ServiceException {
		try {

			return roleDAO.findRole(new Long(roleId));
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}
	
	/**
	 * 列表全部的角色信息
	 * 
	 * @param isAdmin 是否为管理组
	 * @return List 用户列表
	 * @throws ServiceException
	 */	
	public void setRolesToMemory() throws ServiceException {
		try {
			List roels = roleDAO.findRoles();
			RoleSingleton.getInstance().setRoles(roels);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}	
	}	
	
	/**
	 * 列表全部的角色信息
	 * 
	 * @param type 是否为管理组
	 * @return List 用户列表
	 * @throws ServiceException
	 */
	public List getRoles(int type) throws ServiceException {
		try {
			return roleDAO.findRoles(type);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}
	
}
