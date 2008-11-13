package com.ntsky.bbs.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ntsky.bbs.domain.Role;
import com.ntsky.bbs.exception.DAOException;

/**
 * 角色模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface RoleDAO extends BaseDAO{

	/**
	 * 根据类别查找全部的角色信息
	 * 
	 * @param type 是否为管理组
	 * @return List 用户列表
	 */
	public List findRoles(int type) throws DAOException ;

	/**
	 * 查找全部的角色信息
	 * 
	 * @param isAdmin 是否为管理组
	 * @return List 用户列表
	 */
	public List findRoles() throws DAOException ;
	
	/**
	 * 根据角色编号查找的角色对象
	 * @param roleId 角色编号
	 * @return Role 角色对象 
	 */	
	public Role findRole(long roleId) throws DAOException ;
	
	/**
	 * 根据文章数查找角色
	 * @param minTopic 最少主题数  
	 * @return 角色对象
	 */
	public Role findRole(int minTopic) throws DAOException ;
	
}
