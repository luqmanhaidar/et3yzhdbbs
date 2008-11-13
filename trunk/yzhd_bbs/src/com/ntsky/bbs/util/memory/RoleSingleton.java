package com.ntsky.bbs.util.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ntsky.bbs.domain.Role;
import com.ntsky.bbs.service.RoleService;
import com.ntsky.bbs.service.UserService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.framework.util.StringUtil;

public class RoleSingleton {

	public static Map roleMap = new HashMap();
	
	private static RoleSingleton roleSingleton = null;
	
	/**
	 * 获取角色内存实例
	 * @return
	 */
	public synchronized static RoleSingleton getInstance(){
		if(roleSingleton==null){
			roleSingleton = new RoleSingleton();
		}
		return roleSingleton;
	}
	private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private UserService userService=(UserService)bf.getBean("userService");
	private RoleService roleService=(RoleService)bf.getBean("roleService");
	// 设置Role数据
	public void setRoles(List list){
		Object[] roleArray = list.toArray();
		Role role = null;
		for (int i = 0; i < roleArray.length; i++) {
			role = (Role)roleArray[i];
			role.setPermissionMap(splitPermissionsToMap(role.getPermissions()));
			roleMap.put(String.valueOf(role.getId()),role);
		}
	}
	
	/**
	 * 根据角色编号取得角色信息
	 * 
	 * @param roleId 角色编号
	 * @return Role 角色数据
	 */
	public Role getRole(String roleId){
		//System.out.println("fadfasfdaasfas"+roleId);
		return (Role)roleMap.get(roleId);
	}
	
	/**
	 * 添加角色
	 * @param role
	 */
	public void addRole(Role role){
		roleMap.put(String.valueOf(role.getId()),role);
	}
	
	/**
	 * 删除角色
	 * @param roleId 角色编号
	 */
	public void deleteRole(String roleId){
		if(roleMap.containsKey(roleId)){
			roleMap.remove(roleId);
		}
	}
	
	/**
	 * 重置role信息
	 * @param roleId
	 */
	public void resetRole(Role role){
		if(roleMap.containsKey(String.valueOf(role.getId()))){
			role.setPermissionMap(splitPermissionsToMap(role.getPermissions()));
			roleMap.put(String.valueOf(role.getId()),role);
		}
	}
	
	public String getRoleIdByName(String username){
		return userService.findRoleId(username);
	}
	
	/**
	 * 将权限字符串转换成Map
	 * @param permissions
	 * @return map 权限MAP
	 */
	public Map splitPermissionsToMap(String permissions){
		String[] perArray = StringUtil.splitStringToArray(permissions,"|");
		Map perMap = new HashMap();
		int len = 0;
		for (int i = 0; i < perArray.length; i++) {
			len = perArray[i].indexOf("=");
			perMap.put(perArray[i].substring(0,len),perArray[i].substring(len+1));
		}
		return perMap;
	}
	
	public void main(String[] args) {
		String permissions = "a=1|b=2";
		String[] perArray = StringUtil.splitStringToArray(permissions,"|");
		Map perMap = new HashMap();
		int len = 0;
		for (int i = 0; i < perArray.length; i++) {
			System.out.println(" per  = " + perArray[i]);
			len = perArray[i].indexOf("=");
			perMap.put(perArray[i].substring(0,len),perArray[i].substring(len+1));
		}
	}
	
}
