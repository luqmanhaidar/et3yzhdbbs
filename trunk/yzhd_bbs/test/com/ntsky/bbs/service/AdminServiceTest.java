package com.ntsky.bbs.service;

import java.util.Date;

import com.ntsky.bbs.domain.Admin;

public class AdminServiceTest extends NTskyServiceTestCase{

	private AdminService adminService;
	private Admin admin = null;
	
    protected void setUp() throws Exception {
    	adminService = (AdminService)super.getBean("adminService");
    	
    	admin = new Admin();
    	admin.setUsername("哈哈");
    	admin.setPassword("124567");
    	admin.setPermissions("1,2,3,4,5");
    	admin.setLastLoginIp("127.0.0.1");
    	admin.setLastLoginTime(new Date());
    }
	
	public void testCreateAdmin(){
		adminService.createAdmin(admin);
	}
	
	public void testAuthLogin(){
		adminService.authLogin("ntsky","ntskyqaz");
	}
	
	public void testUpdateLoginInfo(){
		adminService.updateLoginInfo(1,"2006-00-01 00:00:00","127.0.0.1");
	}
	
	public void testGetAdmins(){
		assertNotNull(adminService.getAdmins());
	}
	
	public void testGetAdmin(){
		assertNotNull(adminService.getAdmin(1));
	}

	public void testDeleteAdmin(){
		adminService.deleteAdmin(2);
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(AdminServiceTest.class);
	}		
	
}
