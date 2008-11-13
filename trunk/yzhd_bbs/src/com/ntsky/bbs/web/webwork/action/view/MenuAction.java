package com.ntsky.bbs.web.webwork.action.view;

import java.util.List;
import com.ntsky.bbs.util.memory.ForumSingleton;

/**
 * Menu页面
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */
public class MenuAction extends ViewActionSupport{
	
	private List forums;
	public List getForums(){
		return forums;
	}
	
	/**
	 * 访问首页
	 * 
	 */
	public String execute() throws Exception {

		if(logger.isInfoEnabled()){
			logger.info("返回到Menu页面...");
		}
		// 论坛数据
		forums = ForumSingleton.getInstance().getForums();
		
		return SUCCESS;
	}
	
}
