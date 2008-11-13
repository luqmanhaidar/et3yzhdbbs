package com.ntsky.bbs.dao;

import java.util.Date;

import com.ntsky.bbs.domain.Admin;

/**
 * 管理模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class AdminDAOTest extends BaseDAOCase{

    private AdminDAO adminDAO = null;
    private Admin admin = null;
    
    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }
    
    public void testSaveAdmin(){
    	admin = new Admin();
    	admin.setUsername("ntsky");
    	admin.setPassword("123456");
    	admin.setPermissions("1,2");
    	admin.setLastLoginTime(new Date());
    	admin.setLastLoginIp("127.0.0.1");
    	adminDAO.save(admin);
    }
    
    public void testFindAdmin(){
    	//assertNull(adminDAO.findAdmin(100));
    	Admin tempAdmin = adminDAO.findAdmin(100);
    	System.out.println(tempAdmin);
    }

    public void testFindAdmins(){
    	adminDAO.findAdmins();
    }
    
    public void testFindAdminByNameAndPassword(){
    	Admin admin = adminDAO.findAdmin("ntsky","123456");
    	assertNotNull(admin);
    }
    
    public void testUpdateLoginInfo(){
    	adminDAO.updateLoginInfo(1,"","");
    }
    
}
