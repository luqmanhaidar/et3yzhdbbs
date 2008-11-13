package com.ntsky.bbs.web.webwork.action.admin;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.config.ResourceConfig;
import com.ntsky.framework.util.HttpUtil;

/**
 * 删除管理员
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $
 */
public class DeleteAdminAction extends AdminActionSupport implements Preparable{

	private int adminId;
	public void setAdminId(int adminId){
		this.adminId = adminId;
	}
	
	/**
	 * 删除管理员
	 * <pre>
	 * 	执行成功迁移到 adminManage.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　删除管理员
		if(!isPermisson("3_4")){
			setWarnMessage("您没有删除管理员的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */				
		
		try{
			// 删除管理员
			adminService.deleteAdmin(adminId);
			
			setActionMessage("删除管理员成功.");	
			// 设置返回页面数据
			admins = adminService.getAdmins();
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
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
		// 设置管理资源信息
		setResources(ResourceConfig.getResources("admin"));
		if(logger.isInfoEnabled()){
			logger.info("初始化管理员信息");		
		}
	}

}
