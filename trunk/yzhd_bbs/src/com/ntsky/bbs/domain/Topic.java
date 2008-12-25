package com.ntsky.bbs.domain;

import java.util.Date;
import java.util.Set;

public class Topic extends Entity{

	private int forumId;
	private int categoryId;
	private int mood;
	private String title;
	private int views;
	private int isTop;
	private int isVote;
	private Date dateCreated;
	//private int lastPostId;
	private String lastPostUser;
	private int replies;
	private int status;
	private int isDelete;
	private Long firstPostId;
	private String username;
	private Date lastPostTime;
	private String content;
	private Category category;
	
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
	
	private String firstPicPath;
	
	private int cntPost;//回复次数
	
	private int favId;//收藏编号
	
	private String shortTitle;
	private Forum forum;
		
	private Post firstPost;
	public int getFavId() {
		return favId;
	}
	public void setFavId(int favId) {
		this.favId = favId;
	}
	public void setFirstPost(Post firstPost){
		this.firstPost = firstPost;
	}
	public Post getFirstPost(){
		return firstPost;
	}
	
	private Post lastPost;
	public void setLastPost(Post lastPost){
		this.lastPost = lastPost;
	}
	public Post getLastPost(){
		return this.lastPost;
	}
	
	public void addPost(Post post) {
		post.setTopic(this);
	    posts.add(post);
	}
	
	private Set posts;	
	public Set getPosts() {
		return posts;
	}
	public void setPosts(Set posts) {
		this.posts = posts;
	}
	
	public String getLastPostUser() {
		return lastPostUser;
	}
	public void setLastPostUser(String lastPostUser) {
		this.lastPostUser = lastPostUser;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public int getIsTop() {
		return isTop;
	}
	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}
	public int getIsVote() {
		return isVote;
	}
	public void setIsVote(int isVote) {
		this.isVote = isVote;
	}
	public int getMood() {
		return mood;
	}
	public void setMood(int mood) {
		this.mood = mood;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	public int getReplies() {
		return replies;
	}
	public void setReplies(int replies) {
		this.replies = replies;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public Date getLastPostTime() {
		return lastPostTime;
	}
	public void setLastPostTime(Date lastPostTime) {
		this.lastPostTime = lastPostTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getFirstPostId() {
		return firstPostId;
	}
	public void setFirstPostId(Long firstPostId) {
		this.firstPostId = firstPostId;
	}
	public int getCntPost() {
		return cntPost;
	}
	public void setCntPost(int cntPost) {
		this.cntPost = cntPost;
	}
	public String getShortTitle() {
		if(title.length()>13){			
			return title.substring(0, 13)+"...";
		}else{
			return title;
		}		
	}
	public String getAutoTitle(int length){
		if(title.length()>length){			
			return title.substring(0, length)+"...";
		}else{
			return title;
		}
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public String getFirstPicPath() {
		boolean flag=false;
		if(content.indexOf("<img")!=-1){
			String con[]=content.split("\"");
			for(int i=0;i<con.length;i++){
				if(flag){
					if(con[i].trim().indexOf("<img")!=-1){
						flag=true;
					}
				}else{
					if(con[i].trim().indexOf("src")!=-1){
						firstPicPath=con[i+1];
						break;
					}
				}				
			}
		}
		return firstPicPath;
	}
	public void setFirstPicPath(String firstPicPath) {
		this.firstPicPath = firstPicPath;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	
}
