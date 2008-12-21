package com.ntsky.bbs.web.webwork.action.user;

import java.util.Map;
import java.net.URLDecoder;

import com.opensymphony.webwork.interceptor.SessionAware;
import com.opensymphony.xwork.ModelDriven;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
/**
 * 检测用户
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.4 $ $Date: 2008/11/09 09:32:02 $
 */
public class EditUserAction extends UserActionSupport implements ModelDriven,SessionAware{

	private User user = new User();
	public User getUser(){
		return user;
	}
	
	/**
	 * 更新用户资料
	 */
	public String execute() throws Exception {
		String username = getSessionUser().getUsername();
		if(logger.isInfoEnabled()){
			logger.info("更新用户[' " + username + " ']的信息.") ;
		}
		try{
			user.setId(super.getSessionUser().getId());
			System.out.println("dsfdsfds"+user.getFace());
			userService.editUser(user);
			setActionMessage("修改[' "+username+" ']的资料成功");
			// 修改资料成功后，重新设置用户Session
			super.setUserSession(session, userService.getUser(getSessionUser().getId()));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		if(logger.isInfoEnabled()){
			logger.info("用户是否存在 ： " + (user==null) );
			logger.info("用户名 ： " + user.getUsername());
		}
		
		return SUCCESS;
	}
		
	/**
	 * 修改用户资料
	 * @return
	 * @throws Exception
	 */
	public String doEdit() throws Exception {
		System.out.println("Y1");
		if(logger.isInfoEnabled()){
			logger.info("修改用户 ['"+getSessionUser().getUsername()+"'] 的资料") ;
		}
		System.out.println("Y2");
		try{
			user = super.userService.getUser(super.getSessionUser().getId().intValue());
			System.out.println("Y3");
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		if(logger.isInfoEnabled()){
			logger.info("返回到用户资料编辑页面");
		}
		return SUCCESS;
	}

	public Object getModel() {
		return this.user;
	}

	private Map session;
	public void setSession(Map session) {
		this.session = session;
	}
}
