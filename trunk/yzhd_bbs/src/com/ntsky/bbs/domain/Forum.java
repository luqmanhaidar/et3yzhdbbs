package com.ntsky.bbs.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Forum extends Entity{
	
	private String name;
	private int parentId;
	private String parentEnum;
	private int depth;
	private int displayOrder;
	private int branchId;
	private String masters;
	private String description;
	private Date dateCreated;
	private String signImage;
	private int totalTopic;
	private int totalPost;
	private int totalTodayPost;
	private int status;
	private String rules;
	private int isTop;
	private int isMasters;
	private int isAdmin;
	
	private List indexTopic;
	
	private Topic lastPostTopic;
	public Topic getLastPostTopic() {
		return lastPostTopic;
	}
	public void setLastPostTopic(Topic lastPostTopic) {
		this.lastPostTopic = lastPostTopic;
	}
	
	private List forums;
	public List getForums() {
		return forums;
	}
	public void setForums(List forums) {
		this.forums = forums;
	}
	
	public String getRules() {
		return rules;
	}
	public void setRules(String rules) {
		this.rules = rules;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getMasters() {
		return masters;
	}
	public void setMasters(String masters) {
		this.masters = masters;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentEnum() {
		return parentEnum;
	}
	public void setParentEnum(String parentEnum) {
		this.parentEnum = parentEnum;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getSignImage() {
		return signImage;
	}
	public void setSignImage(String signImage) {
		this.signImage = signImage;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTotalPost() {
		return totalPost;
	}
	public void setTotalPost(int totalPost) {
		this.totalPost = totalPost;
	}
	public int getTotalTodayPost() {
		return totalTodayPost;
	}
	public void setTotalTodayPost(int totalTodayPost) {
		this.totalTodayPost = totalTodayPost;
	}
	public int getTotalTopic() {
		return totalTopic;
	}
	public void setTotalTopic(int totalTopic) {
		this.totalTopic = totalTopic;
	}
	public int getIsTop() {
		return isTop;
	}
	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}
	public List getIndexTopic() {
		return indexTopic;
	}
	public void setIndexTopic(List indexTopic) {
		this.indexTopic = indexTopic;
	}
	public int getIsMasters() {
		return isMasters;
	}
	public void setIsMasters(int isMasters) {
		this.isMasters = isMasters;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

}
