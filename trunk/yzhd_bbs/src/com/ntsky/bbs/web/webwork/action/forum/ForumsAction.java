package com.ntsky.bbs.web.webwork.action.forum;

import java.util.List;

import com.opensymphony.xwork.Preparable;

/**
 * 论坛管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/11/05 16:54:39 $
 */
public class ForumsAction extends ForumActionSupport implements Preparable{

	/**
	 * 论坛管理
	 * <pre>
	 * 	执行成功迁移到 forums.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		/*if(logger.isDebugEnabled()){
			//logger.debug("列表全部论坛信息");
		}*/
		if(!isPermisson("2_2")){
			setWarnMessage("您没有论坛管理的权限.");
			return NO_PERMISSION;
		}
		return SUCCESS;
    }
	
	/**
	 * 执行此ManageAction的准备信息
	 * <ul>
	 * 	<li>取得论坛管理页面数据</li>
	 * </ul>
	 */
	public void prepare() throws Exception {
		setForums(forumService.getForums());
		if(logger.isDebugEnabled()){
			//logger.debug("初始化论坛信息");		
		}
	}
	
	/**
	 * 创建论坛版块信息
	 * 
	 * @return String 执行信息
	 */
	public String doOpenCreatePage() throws Exception {
		// 初始化论坛列表
		if(logger.isInfoEnabled()){
			//logger.info("打开创建论坛页面..");
		}
		return SUCCESS;
	}

}
