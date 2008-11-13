package com.ntsky.bbs.web.webwork.action.role;

import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.config.ResourceConfig;
/**
 * 角色管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:34 $
 */
public class RolesAction extends RoleActionSupport implements Preparable{

	/**
	 * 角色管理
	 * <pre>
	 * 	执行成功迁移到 roles.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　列表角色
		if(!isPermisson("3_3")){
			setWarnMessage("您没有列表角色的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("列表角色");
		/* -------------------------------*/
		
		if(logger.isDebugEnabled()){
			logger.debug("列表论坛全部角色信息");
		}
		return SUCCESS;
    }
	
	/**
	 * 执行此ManageAction的准备信息
	 * <ul>
	 * 	<li>取得角色管理页面数据</li>
	 *  <li>设置权限页面数据</li>
	 * </ul>
	 */
	public void prepare() throws Exception {
		try{
			setManagerRoles(roleService.getRoles(1));
			setUserRoles(roleService.getRoles(0));
			setSpecialRoles(roleService.getRoles(2));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		// 设置资源信息
		// setResources(ResourceConfig.getResources("user"));
		if(logger.isInfoEnabled()){
			logger.info("初始化角色信息");		
		}
	}
}
