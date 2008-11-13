package com.ntsky.bbs.domain;

import java.util.Date;

public class Help extends Entity{
	
	private String title ;
	private String content ;
	private int type ;
	private Date dateCreated ;
	private int isHand;
	
	private Common common;
	public void setCommon(Common common){
		this.common = common;
	}
	public Common getCommon(){
		return this.common;
	}
	
	public int getIsHand() {
		return isHand;
	}
	public void setIsHand(int isHand) {
		this.isHand = isHand;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
