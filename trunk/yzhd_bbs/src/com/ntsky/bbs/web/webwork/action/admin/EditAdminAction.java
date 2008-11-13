package com.ntsky.bbs.web.webwork.action.admin;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.config.ResourceConfig;
import com.ntsky.framework.util.HttpUtil;
/**
 * 更新管理员信息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/11/08 16:36:06 $
 */
public class EditAdminAction extends AdminActionSupport implements Preparable,ModelDriven{

	private Admin admin = new Admin();
	public Admin getAdmin(){
		return this.admin;
	}

	/**
	 * 修改管理员
	 * <pre>
	 * 	执行成功迁移到 adminManage.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　修改管理员
		if(!isPermisson("3_4")){
			setWarnMessage("您没有修改管理员的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		if(logger.isInfoEnabled()){
			//logger.info("修改管理员信息......");
		}
		String[] permissionsArr = HttpUtil.getParameterValues(super.getRequest(),"permission");
		try{
			String permissions = "";
			if(permissionsArr!=null&&permissionsArr.length>0){
				permissions = permissionsArr[0];
				// permissions信息
				for (int i = 1; i < permissionsArr.length; i++) {
					permissions = permissions + "," + permissionsArr[i];
				}
			}
			if(logger.isInfoEnabled()){
				//logger.info("管理员 [' "+admin.getUsername()+" '] 的权限 : " + permissions);
			}
			//System.out.println("fadsfadsfas"+Integer.parseInt(super.getRequest().getParameter("adminId").toString()));
			admin =adminService.getAdmin(Integer.parseInt(super.getRequest().getParameter("adminId").toString()));
			admin.setPermissions(permissions);
			// 创建管理员
			adminService.editAdmin(admin);
			setActionMessage("成功更新管理员['"+admin.getUsername()+"']的信息.");			
			super.admins = adminService.getAdmins();
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

	private int adminId;
	public void setAdminId(int adminId){
		this.adminId = adminId;
	}
	
	/**
	 * 修改用户资料(查找用户信息)
	 */
	public String doDefault() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　修改管理员
		if(!isPermisson("3_4")){
			setWarnMessage("您没有修改管理员的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */			
		
		try{
			admin = adminService.getAdmin(adminId);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	public Object getModel() {
		return this.admin;
	}

	public int getAdminId() {
		return adminId;
	}
}
