package com.ntsky.bbs.web.webwork.action.user;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Enumeration;

import com.opensymphony.xwork.ModelDriven;

import com.ntsky.framework.util.HttpUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.util.config.SystemConfig;

/**
 * 列表用户
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:25 $
 */
public class UserListAction extends UserActionSupport {
	
	/**
	 * 用户列表
	 */
	private List users;
	public List getUsers(){
		return this.users;
	}
	
	/**
	 * 列表用户
	 */
	public String execute() throws Exception {

		String username = new String(getParameter("username").getBytes("iso-8859-1"));
		if(logger.isInfoEnabled()){
			logger.info("查找用户名 : " + username + "的信息.") ;
		}
		Map orderMap = new HashMap();
		orderMap.put(getParameter(Symbols.SORT),getParameter(Symbols.ORDER));
		QueryResult queryResult = userService.getUsers(username,orderMap,new Pagination(getPaginationStart(),SystemConfig.getInstance().getIntPropertyValue(Symbols.PAGINATION,Symbols.PAGINATION_USER)));
		
		if(logger.isInfoEnabled()){
			logger.info("列表用户信息.");
		}
		this.users = queryResult.getItems();
		super.setPagination(queryResult.getPagination());
		return SUCCESS;
	}
		
}
