package com.ntsky.bbs.domain;

import java.util.List;
import java.util.Set;

/**
 * 表情持久化类
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class UserFace extends Entity{

	private String name;
	private String path;
	private int type;
	
	private List userFaces;	
	
	public List getUserFaces() {
		return userFaces;
	}
	public void setUserFaces(List userFaces) {
		this.userFaces = userFaces;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
