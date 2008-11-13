package com.ntsky.bbs.web.webwork.action.role;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ntsky.bbs.domain.Role;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.memory.RoleSingleton;
import com.opensymphony.xwork.ModelDriven;

/**
 * 修改角色
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.3 $ $Date: 2008/11/07 17:30:55 $
 */
public class EditRoleAction extends RoleActionSupport implements ModelDriven {

	private Role role = new Role();
	
	/**
	 * 修改角色
	 * @throws Exception
	 */
	public String update() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　更新角色
		if(!isPermisson("3_3")){
			setWarnMessage("您没有更新角色的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */	
		
		/* ------------ 记录日志 -----------*/
		recordActLog("更新角色");
		/* -------------------------------*/		
		
		Map permissionMap = getRequest().getParameterMap();
		Set set = permissionMap.entrySet();
        Iterator iterator = set.iterator();
        Map.Entry entry = null;
        String permisssions = "";
        while (iterator.hasNext()) {
            entry = (Map.Entry) iterator.next();
            if(!(("name".equals(entry.getKey())) || ("type".equals(entry.getKey())) || ("submit".equals(entry.getKey())) || ("description".equals(entry.getKey())) || ("icon".equals(entry.getKey())) || ("minTopic".equals(entry.getKey())))){
            	permisssions = permisssions + entry.getKey()+"="+((String[])entry.getValue())[0]+"|";
            }
        }
        role.setPermissions(permisssions);
		try{
			roleService.editRole(role);
			if(logger.isInfoEnabled()){
				logger.info("成功创建角色 [' "+role.getName()+" ']");
			}
			
			// 初始角色列表
			setManagerRoles(roleService.getRoles(1));
			setUserRoles(roleService.getRoles(0));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	private int roleId;
	public void setRoleId(int roleId){
		this.roleId = roleId;
	}
	
	public Role getRole(){
		return this.role;
	}
	/**
	 * 查询角色
	 * 
	 * @return String 
	 */
	public String edit() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　修改角色（根据ID查找角色）
		if(!isPermisson("3_3")){
			setWarnMessage("您没有修改角色的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */	
		
		/* ------------ 记录日志 -----------*/
		recordActLog("修改角色");
		/* -------------------------------*/
		
		if(logger.isInfoEnabled()){
			logger.info("查询角色["+roleId+"]信息");
		}		
		try{
			//role = RoleSingleton.getInstance().getRole(String.valueOf(roleId));
			role=roleService.getRole(roleId);
			Object id ="id";
			Object value=roleId;
			role.setPermissionMap(RoleSingleton.getInstance().splitPermissionsToMap(role.getPermissions()));
	        role.getPermissionMap().put(id, value);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}

	public Object getModel() {
		return this.role;
	}	
	
}
