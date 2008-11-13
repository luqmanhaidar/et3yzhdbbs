package com.ntsky.bbs.web.listener;

import java.util.Date;

/**
 * 在线用户对象类
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:31 $
 */
public class OnlineInfo {
	
	// 用户名
	private String username;
	// ip地址
	private String ipAddress;
	// 登陆时间
	private Date date;
	
	private String roles;
	
	private int forumId;
	
	private int topicId;
	
	public OnlineInfo(){}
	
	public OnlineInfo(String username,String ipAddress,Date date){
		this.username = username;
		this.ipAddress = ipAddress;
		this.date = date;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}

