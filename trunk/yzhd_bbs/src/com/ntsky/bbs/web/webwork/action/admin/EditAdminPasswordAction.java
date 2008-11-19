package com.ntsky.bbs.web.webwork.action.admin;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.exception.UserException;
import com.ntsky.bbs.util.config.ResourceConfig;
import com.ntsky.framework.util.HttpUtil;
/**
 * 修改管理员密码
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $
 */
public class EditAdminPasswordAction extends AdminActionSupport{

	// 旧密码
	private String oldPassword;
	public void setOldPassword(String oldPassword){
		this.oldPassword = oldPassword;
	}
	
	// 密码
	private String password;
	public void setPassword(String password){
		this.password = password;
	}	
	
	/**
	 * 修改密码
	 * @return String success 
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　修改管理员密码
		if(!isPermisson("1_8")){
			setWarnMessage("您没有修改管理员密码的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */
		
		/* ------------ 记录日志 ---------- */
		recordActLog("修改管理员密码");
		/* ------------------------------- */
		
		if(logger.isInfoEnabled()){
			logger.info("修改密码......");
			logger.info("旧密码 ： " + oldPassword + " | 新密码 : " + password);
		}
		try{
			// 创建管理员密码
			adminService.editAdminPassword(super.getSessionAdmin().getId().intValue(),oldPassword,password);
			setActionMessage("更新管理员密码成功.");			
			super.admins = adminService.getAdmins();
		}
		catch(UserException ue){
			super.setWarnMessage(ue.getMessage());
			return INPUT;
		}		
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
    }
	
	/**
	 * 打开修改密码页面
	 */
	public String doDefault() throws Exception {
		if(!isPermisson("1_8")){
			setWarnMessage("您没有修改管理员密码的权限.");
			return NO_PERMISSION;
		}
		if(logger.isInfoEnabled()){
			logger.info("打开修改密码页面......");
		}
		
		return SUCCESS;
	}
}
