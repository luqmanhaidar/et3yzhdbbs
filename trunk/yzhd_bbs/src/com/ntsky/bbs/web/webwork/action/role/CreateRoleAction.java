package com.ntsky.bbs.web.webwork.action.role;

import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;

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
 * 创建角色
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:33 $
 */
public class CreateRoleAction extends RoleActionSupport implements ModelDriven{

	private Role role = new Role();
	
	/**
	 * 创建角色
	 * @throws Exception
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　创建角色
		if(!isPermisson("3_3")){
			setWarnMessage("您没有创建角色的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */	
		
		/* ------------ 记录日志 -----------*/
		recordActLog("创建角色");
		/* -------------------------------*/
		
		// 权限
		Map permissionMap = getRequest().getParameterMap();
		Set set = permissionMap.entrySet();
        Iterator iterator = set.iterator();
        Map.Entry entry = null;
        String permisssions = "";
        while (iterator.hasNext()) {
            entry = (Map.Entry) iterator.next();
            if(!(("name".equals(entry.getKey())) || ("type".equals(entry.getKey())) || ("description".equals(entry.getKey())) || ("icon".equals(entry.getKey())) || ("submit".equals(entry.getKey())) || ("minTopic".equals(entry.getKey())))){
            	permisssions = permisssions + entry.getKey()+"="+((String[])entry.getValue())[0]+"|";
            }
        }
		try{
			role.setPermissions(permisssions);
			roleService.createRole(role);
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
	
	/**
	 * 提供创建角色需要的信息
	 * 
	 * @return String 
	 */
	public String create() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("创建角色["+role.getName()+"]信息");
		}		
		try{
			super.roleService.createRole(role);
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
