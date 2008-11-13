package com.ntsky.bbs.web.webwork.action.admin;

import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.config.ResourceConfig;

/**
 * 管理员列表
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $
 */
public class AdminsAction extends AdminActionSupport implements Preparable{

	/**
	 * 管理员列表
	 * <pre>
	 * 	执行成功迁移到 admins.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {

		/* ---------- 权限判断 ------------ */
		//　列表论坛系统管理员
		if(!isPermisson("3_4")){
			setWarnMessage("您没有列表论坛系统管理员的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */
		
		/* ------------ 记录日志 ---------- */
		recordActLog("列表论坛系统管理员");
		/* ------------------------------- */		
		
		
		/*if(logger.isDebugEnabled()){
			logger.debug("列表论坛系统管理员信息");
		}*/
		return SUCCESS;
    }
	
	/**
	 * 执行此ManageAction的准备信息
	 * <ul>
	 * 	<li>取得管理员管理页面数据</li>
	 *  <li>设置权限页面数据</li>
	 * </ul>
	 */
	public void prepare() throws Exception {
		try{
			super.admins = adminService.getAdmins();
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		// 设置管理资源信息
		setResources(ResourceConfig.getResources("admin"));
		if(logger.isInfoEnabled()){
			logger.info("初始化管理员信息");		
		}
	}
}
