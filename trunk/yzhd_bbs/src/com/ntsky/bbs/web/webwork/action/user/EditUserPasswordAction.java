package com.ntsky.bbs.web.webwork.action.user;

import java.util.Map;

import com.opensymphony.webwork.ServletActionContext;

import com.ntsky.framework.util.HttpUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.LoginException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.exception.UserException;

/**
 * 修改用户密码
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:25 $
 */
public class EditUserPasswordAction extends UserActionSupport {
	
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
	 * 修改用户密码
	 * 
	 */
	public String execute() throws Exception {
		super.setWarnMessage(null);
		if(logger.isInfoEnabled()){
			logger.info("修改用户密码");
		}
		String username = super.getSessionUser().getUsername();
		try{
			super.userService.editUserPassword(username,oldPassword,password);
			// 提示信息
			setActionMessage("修改用户[' "+username+" ']密码成功");
		}
		catch(UserException ue){
			super.setWarnMessage(ue.getMessage());
			return INPUT;
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		if(logger.isInfoEnabled()){
			logger.info("修改用户[' "+username+" ']密码成功");
		}
		return SUCCESS;
	}
	
	public String doDefault() throws Exception{
		return SUCCESS;
	}
	
}
