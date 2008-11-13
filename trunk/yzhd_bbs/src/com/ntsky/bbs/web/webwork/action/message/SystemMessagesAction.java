package com.ntsky.bbs.web.webwork.action.message;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.webwork.interceptor.SessionAware;
import com.opensymphony.xwork.ModelDriven;

import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.domain.Message;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.RoleService;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 查看系统发送的短消息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:26 $
 */
public class SystemMessagesAction extends MessageActionSupport implements ModelDriven{

	private Message message = new Message();
	
	private RoleService roleService;
	public void setRoleService(RoleService roleService){
		this.roleService = roleService;
	}
	
	// 用户角色列表
	private List userRoles = null;
	public void setUserRoles(List userRoles){
		this.userRoles = userRoles;
	}
	public List getUserRoles(){
		return this.userRoles;
	}	
	
	// 管理角色列表
	private List managerRoles = null;
	public List getManagerRoles() {
		return managerRoles;
	}
	public void setManagerRoles(List managerRoles) {
		this.managerRoles = managerRoles;
	}
	
	/**
	 * 系统短消息
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("查看系统短消息...");
		}
		QueryResult queryResult = null;
		try{
			queryResult = super.messageService.getMessages(super.getSessionAdmin().getUsername(),2,new Pagination(getPaginationStart()));
			setManagerRoles(roleService.getRoles(1));
			//setUserRoles(roleService.getRoles(0));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}		
		setMessages(queryResult.getItems());
		setPagination(queryResult.getPagination());
		return SUCCESS;
	}
	
	public Object getModel() {
		return this.message;
	}
	
	public String doDefault() throws Exception {
		return SUCCESS;
	}

}
