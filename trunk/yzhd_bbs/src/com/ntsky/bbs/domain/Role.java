package com.ntsky.bbs.domain;

import java.util.Map;

public class Role extends Entity{

	private String name;
	private int type;	
	private String description;
	private String permissions;
	private String icon;
	private int minTopic;
	private String maxEp;
	
	private Map permissionMap;
	public Map getPermissionMap() {
		return permissionMap;
	}
	public void setPermissionMap(Map permissionMap) {
		this.permissionMap = permissionMap;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getMinTopic() {
		return minTopic;
	}
	public void setMinTopic(int minTopic) {
		this.minTopic = minTopic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getMaxEp() {
		return maxEp;
	}
	public void setMaxEp(String maxEp) {
		this.maxEp = maxEp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
}
