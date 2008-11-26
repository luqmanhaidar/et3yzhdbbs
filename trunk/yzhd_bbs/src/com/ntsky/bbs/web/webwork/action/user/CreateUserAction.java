package com.ntsky.bbs.web.webwork.action.user;

import java.util.List;

import com.opensymphony.xwork.ModelDriven;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Message;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.MessageService;
import com.ntsky.bbs.service.RoleService;
import com.ntsky.bbs.util.config.RegisterConfig;
import com.ntsky.framework.mail.MailException;

/**
 * 创建用户
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/11/05 16:54:39 $
 */
public class CreateUserAction extends UserActionSupport implements ModelDriven {

	private User user = new User();
	
	private RoleService roleService;
	public void setRoleService(RoleService roleService){
		this.roleService = roleService;
	}
	
	private MessageService messageService;
	
	public void setMessageService(MessageService messageService){
		this.messageService = messageService;
	}
	
	/**
	 * 创建用户
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　创建系统用户
		if(!isPermisson("3_1")){
			setWarnMessage("您没有创建系统用户的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("创建系统用户");
		/* -------------------------------*/
		
		if(logger.isInfoEnabled()){
			logger.info("创建用户的用户名 : " + user.getUsername());
		}
		try{
			User tempUser = super.userService.getUser(user.getUsername());
			if( tempUser == null ){
				user.setLastLoginIp(super.getRemoteAddr());
				userService.createUser(user);
				
				// 判断是否需要发送欢迎信息
				messageService.deleteReceiverMessage(user.getUsername());
				messageService.deleteSendMessage(user.getUsername());
				if((Symbols.TRUE).equals(RegisterConfig.getPropertyValue("isWelcome"))){
					// 发送欢迎信息
					Message message = new Message();
					message.setSender("admin");
					message.setReceiver(user.getUsername());
					message.setTitle("欢迎信息!");
					message.setContent(RegisterConfig.getPropertyValue("welcomeInfo"));
					message.setStatus(1);
					messageService.createMessage(message);
				}
				super.setActionMessage("添加系统用户[ "+user.getUsername()+" ]成功!");
			}
			else{
				setActionMessage("用户["+user.getUsername()+"]已存在,请选择其它用户名");
				return INPUT;
			}
		}
		catch(MailException mse){
			//logger.error("mail exception info is : \r\n", mse);
			super.setWarnMessage("<span style=\"color:red\">添加用户成功</span>，但是系统发送欢迎邮件失败!");
			return WARN;
		}	
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}

	public Object getModel() {
		System.out.println("haha createuser ======================");
		return user;
	}
	
	// 管理角色列表
	private List managerRoles = null;
	public List getManagerRoles() {
		return managerRoles;
	}
	public void setManagerRoles(List managerRoles) {
		this.managerRoles = managerRoles;
	}
	public String doDefault() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　创建系统用户
		if(!isPermisson("3_1")){
			setWarnMessage("您没有创建系统用户的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("创建系统用户");
		/* -------------------------------*/
		
		if(logger.isInfoEnabled()){
			//logger.info("添加系统用户....");
		}
		setManagerRoles(roleService.getRoles(1));
		
		return SUCCESS;
	}
		
}
