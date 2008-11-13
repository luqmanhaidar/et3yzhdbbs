package com.ntsky.bbs.domain;

import java.util.Date;

import com.ntsky.bbs.domain.Forum;

public class Announcement extends Entity{

	private String title;
	private String content;
	private String author;
	private int forumId;
	private Date dateCreated;
	
	private Forum forum ;
	private String link;
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	
	public String getAuthor() {
		return author;
	}  
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
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
	
	
	
}
