package com.ntsky.bbs.domain;

import java.util.Date;

public class Stat extends Entity{

	private String username;
	private String place;
	private String referer;
	private String agent;
	private String language;
	private Date viewTime;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public Date getViewTime() {
		return viewTime;
	}
	public void setViewTime(Date viewTime) {
		this.viewTime = viewTime;
	}

}
