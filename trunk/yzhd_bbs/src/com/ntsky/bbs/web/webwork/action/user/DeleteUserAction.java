package com.ntsky.bbs.web.webwork.action.user;

import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.LoginException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.framework.util.HttpUtil;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.interceptor.SessionAware;

public class DeleteUserAction extends UserActionSupport {

	private String[] ids;
	public void setIds(String[] ids){
		this.ids = ids;
	}
	
	/**
	 * 执行登陆
	 * 
	 */
	public String execute() throws Exception {
		
		if(logger.isInfoEnabled()){
			logger.info("执行删除: ");
		}
		try{
			ids = super.getRequest().getParameterValues("id");
			//System.out.println(ids);
			for (int i = 0; i < ids.length; i++) {
				userService.deleteUser(Long.parseLong(ids[i]));
			}	
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	
}
