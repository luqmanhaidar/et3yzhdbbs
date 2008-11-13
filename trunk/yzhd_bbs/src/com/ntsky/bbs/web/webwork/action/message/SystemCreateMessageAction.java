package com.ntsky.bbs.web.webwork.action.message;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.webwork.interceptor.SessionAware;
import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.domain.Message;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.RoleService;
import com.ntsky.bbs.service.UserService;

/**
 * 发送系统消息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $
 */
public class SystemCreateMessageAction extends MessageActionSupport implements ModelDriven,Preparable{

	private Message message = new Message();
	
	private Integer[] roles;
	public void setRoles(Integer[] roles){
		this.roles = roles;
	}
	
	private UserService userService;
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	/**
	 * 系统管理员发送短消息
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　添加消息
		if(!isPermisson("6_3")){
			setWarnMessage("您没有添加消息的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("添加消息");
		/* -------------------------------*/	
		
		if(logger.isInfoEnabled()){
			logger.info("添加的消息 ['"+message.getTitle()+"'] ");
		}
		try{
			String appoint = super.getParameter("appoint");
			message.setSender(super.getSessionAdmin().getUsername());
			Object[] userArray = null;
			User user = null;
			// 取得发送管理信息的角色
			if("1".equals(appoint)){
				for (int i = 0; i < roles.length; i++) {
					userArray = userService.getUsersByRoles(String.valueOf(roles[i])).toArray();
					for (int j = 0; j < userArray.length; j++) {
						user = (User)userArray[j];
						message.setReceiver(user.getUsername());
						// 发送消息
						messageService.createMessage(message);
					}
				}
			}
			else{
				messageService.createMessage(message);
			}
			super.setActionMessage("发送系统短消息成功！");
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	public Object getModel() {
		return this.message;
	}
	
	public String doDefault() throws Exception {
		return SUCCESS;
	}

	
	private RoleService roleService;
	public void setRoleService(RoleService roleService){
		this.roleService = roleService;
	}	
	
	// 管理角色列表
	private List managerRoles = null;
	public List getManagerRoles() {
		return managerRoles;
	}
	public void setManagerRoles(List managerRoles) {
		this.managerRoles = managerRoles;
	}
	public void prepare() throws Exception {
		setManagerRoles(roleService.getRoles(1));
	}

}
