package com.ntsky.bbs.web.webwork.action.role;

import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.framework.util.HttpUtil;
import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.StringUtil;
import com.ntsky.framework.util.lumaqq.IPSeeker;

import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.domain.Role;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 删除角色
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:34 $
 */
public class DeleteRoleAction extends RoleActionSupport {

	private int roleId;
	public void setRoleId(int roleId){
		this.roleId = roleId;
	}
	
	/**
	 * 删除角色
	 * @throws Exception
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　更新角色
		if(!isPermisson("3_3")){
			setWarnMessage("您没有删除角色的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */	
		
		/* ------------ 记录日志 -----------*/
		recordActLog("删除角色");
		/* -------------------------------*/			
		
		try{
			roleService.deleteRole(roleId);
			
			if(logger.isInfoEnabled()){
				logger.info("成功创建角色']");
			}
			setActionMessage("成功删除角色");
			
			// 初始角色列表
			setManagerRoles(roleService.getRoles(1));
			setUserRoles(roleService.getRoles(0));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
}
