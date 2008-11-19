package com.ntsky.bbs.web.webwork.action.admin;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.config.ResourceConfig;
import com.ntsky.framework.util.HttpUtil;
/**
 * 创建管理员
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/11/03 06:52:24 $
 */
public class CreateAdminAction extends AdminActionSupport implements Preparable,ModelDriven{

	private Admin admin = new Admin();
	private String username;
	private String password;

	/**
	 * 创建管理员
	 * <pre>
	 * 	执行成功迁移到 admins.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {

		/* ---------- 权限判断 ------------ */
		//　创建管理员
		if(!isPermisson("3_4")){
			setWarnMessage("您没有创建管理员的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */

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
			Admin tempAdmin = super.adminService.getAdmin(username);
			if(tempAdmin == null){
				admin.setUsername(username);
				admin.setPassword(password);
				admin.setPermissions(permissions);
				// 创建管理员
				adminService.createAdmin(admin);
				setActionMessage("创建管理员['"+admin.getUsername()+"']成功.");			
				super.admins = adminService.getAdmins();
				return SUCCESS;
			}
			else{
				setActionMessage("用户["+username+"]已存在,请选择其它用户名");
				return INPUT;
			}
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
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

	public Object getModel() {
		return this.admin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
