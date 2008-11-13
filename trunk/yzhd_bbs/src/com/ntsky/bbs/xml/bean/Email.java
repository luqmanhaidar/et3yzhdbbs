package com.ntsky.bbs.xml.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 邮件信息
 * @author ntsky
 *
 */
public class Email {
	
	private String smtpHost = null;
	private String username = null;
	private String password = null;
	private String systemMail = null;
	private Map bodys = new HashMap();
	
	public Map getBodys() {
		return bodys;
	}
	public void setBodys(Map bodys) {
		this.bodys = bodys;
	}	
	public String getSystemMail() {
		return systemMail;
	}
	public void setSystemMail(String systemMail) {
		this.systemMail = systemMail;
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
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

}
