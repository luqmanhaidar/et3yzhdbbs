package com.ntsky.bbs.web.webwork.action.user;

import java.util.Map;
import java.net.URLDecoder;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.opensymphony.xwork.ModelDriven;

/**
 * 查看用户资料
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:25 $
 */
public class ViewUserAction extends UserActionSupport {

	private String username;
	public void setUsername(String username){
		this.username = username;
	}
	
	private User user;
	public User getUser(){
		return user;
	}
	
	/**
	 * 检测用户
	 */
	public String execute() throws Exception {
		
		// ---------- 权限 : 查看用户信息 ------------
		if(super.isAccess("canViewUser")==0){
			return NO_PERMISSION;
		}
		
		username = new String(username.getBytes("iso-8859-1"));
		if(logger.isInfoEnabled()){
			logger.info("查找用户名 : " + username + "的信息.") ;
		}

		user = userService.getUser(username);
		if(logger.isInfoEnabled()){
			if(user==null){
				logger.info("用户['"+username+"']不存在");
			}
			else{
				logger.info("查看用户[' " + user.getUsername() + "' ]的详细资料");
			}
		}
		return SUCCESS;
	}
		
}
