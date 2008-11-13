package com.ntsky.bbs.web.webwork.action.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.memory.OnlineUserSingleton;

/**
 * 论坛首页
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */
public class OnlineUsersAction extends ViewActionSupport{
	
	private List onlineUsers;
	public List getOnlineUsers() {
		return onlineUsers;
	}
	public void setOnlineUsers(List onlineUsers) {
		this.onlineUsers = onlineUsers;
	}


	/**
	 * 统计信息分页显示
	 * 
	 */
	public String execute() throws Exception {

		if(logger.isInfoEnabled()){
			logger.info("在线用户列表...");
		}
		Object[] objectArray = OnlineUserSingleton.getInstance().getOnlineUserMap().values().toArray();
		// 起始值
		int start_index = getPaginationStart();
		// 分页信息
		Pagination pagination = new Pagination(start_index);
		pagination.setTotalRecord(objectArray.length);
		super.setPagination(pagination);
		
		// 数据信息
		List onlineUsers = new ArrayList();
		for (; start_index < objectArray.length; start_index++) {
			onlineUsers.add(objectArray[start_index]);
		}
		this.setOnlineUsers(onlineUsers);
		return SUCCESS;
	}
	
}
