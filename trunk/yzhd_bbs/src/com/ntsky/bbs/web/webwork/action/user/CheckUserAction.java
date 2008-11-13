package com.ntsky.bbs.web.webwork.action.user;

import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.opensymphony.xwork.ModelDriven;

/**
 * 检测用户
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:25 $
 */
public class CheckUserAction extends UserActionSupport {

	private String username;
	public void setUsername(String username){
		this.username = username;
	}
	
	/**
	 * 检测用户
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("待检测的用户名 : " + username);
		}
		User user = userService.getUser(username);
		if(user==null){
			super.setActionMessage("该用户可以注册");
		}
		else{
			super.setActionMessage("该用户已存在,请重新选择用户");
		}
		return SUCCESS;
	}
		
}
