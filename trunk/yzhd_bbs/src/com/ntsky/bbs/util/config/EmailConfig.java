package com.ntsky.bbs.util.config;

import com.ntsky.bbs.xml.bean.Email;

public class EmailConfig {
	
	private static Email email = null;
	
	/**
	 * 设置Email信息
	 * @param email
	 */
	public static void setEmail(Email mail){
		email = mail;
	}

	/**
	 * 取得整个Email信息
	 * @return
	 */
	public static Email getEmail(){
		return email;
	}
	
	/**
	 * 取得发送邮件服务器
	 * @return
	 */
	public static String getSmtpHost(){
		return email.getSmtpHost();
	}
	
	/**
	 * 取得管理员邮箱
	 * @return
	 */
	public static String getSystemMail(){
		return email.getSystemMail();
	}
	
	/**
	 * 取得用户名
	 * @return
	 */
	public static String getUserName(){
		return email.getUsername();
	}
	
	/**
	 * 取得密码
	 * @return
	 */
	public static String getPassword(){
		return email.getPassword();
	}
	
	/**
	 * 根据dataId和属性主键取得属性值
	 * 
	 * @return value 属性值
	 */
	public static String getPropertyValue(String dataId,String key){
		return DataUtil.getPropertyValue(email.getBodys(),dataId,key);
	}
	
}
