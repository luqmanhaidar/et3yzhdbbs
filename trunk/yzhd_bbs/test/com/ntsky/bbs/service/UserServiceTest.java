package com.ntsky.bbs.service;

import java.util.Date;

import com.ntsky.framework.util.security.MD5;

import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.service.UserService;

public class UserServiceTest extends NTskyServiceTestCase{
	
	private UserService userService;
	private User user = null;
	
    protected void setUp() throws Exception {
    	userService = (UserService)super.getBean("userService");
    	
    	user = new User();
    	user.setUsername("ntsky");
    	user.setPassword(MD5.md5("123"));
    	user.setQuestion("12345");
    	user.setAnswer("it's ok");
    	user.setEmail("yntsky@gmail.com");
    	user.setRoles("1");
    	user.setRegisterTime(new Date());
    	user.setLastLoginTime(new Date());
    	user.setLastLoginIp("127.0.0.1");
    }

	public void testSignupUser() {
		userService.signupUser(user);
	}
		
	public void testDeleteUser() {
		userService.deleteUser(2);
	}
	
	public void testGetUser() {
		userService.getUser(1);
		try {
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block　e.printStackTrace();}
		}
		userService.getUser(1);
		//userService.getUser("ntsky","202cb962ac59075b964b07152d234b70");
	}
	
	public void testEditUser(){
		//user.setId(new Long(1));
		userService.editUser(user);
	}
	
	public void setDown(){
		user = null;
		userService = null;
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(UserServiceTest.class);
	}	
	
}
