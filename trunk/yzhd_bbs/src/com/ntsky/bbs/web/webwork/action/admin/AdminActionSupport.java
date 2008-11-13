package com.ntsky.bbs.web.webwork.action.admin;

import java.util.List;

import com.ntsky.bbs.service.AdminService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

public abstract class AdminActionSupport extends BasicActionSupport {

	protected AdminService adminService;

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	/**
	 * 管理员列表
	 */
	protected List admins;
	public List getAdmins(){
		return admins;
	}
	
	// 管理资源数组
	private List resources = null;
	public List getResources() {
		return resources;
	}
	public void setResources(List resources) {
		this.resources = resources;
	}
	
}
