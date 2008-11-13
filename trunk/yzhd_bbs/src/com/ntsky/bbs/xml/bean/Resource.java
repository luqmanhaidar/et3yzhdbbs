package com.ntsky.bbs.xml.bean;
import java.util.Map;

/**
 * 资源数据
 * @author ntsky
 *
 */
public class Resource {

	private int id ;
	private String name;
	private Map permissionMap;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map getPermissionMap() {
		return permissionMap;
	}
	public void setPermissionMap(Map permissionMap) {
		this.permissionMap = permissionMap;
	}
	
}
