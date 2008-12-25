package com.ntsky.bbs.domain;

import java.util.Date;

public class Post extends Entity{

	private int forumId;
	private int topicId;	
	private String title;
	private String content;
	private int userId;
	private String username;
	private String ip;
	private Date dateCreated;
	private Date dateEdited;
	private int editCount;
	
	private Topic topic;
	private String alias;
	public String getAlias() {		
		if("".equals(alias)){
			return username;
		}else{
			return alias;
		}
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public Date getDateEdited() {
		return dateEdited;
	}
	public void setDateEdited(Date dateEdited) {
		this.dateEdited = dateEdited;
	}
	public int getEditCount() {
		return editCount;
	}
	public void setEditCount(int editCount) {
		this.editCount = editCount;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	
}
