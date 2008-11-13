package com.ntsky.bbs.xml.bean;

import java.util.Map;

/**
 * 脏话
 * @author ntsky
 *
 */
public class Badword {

	private int id ;
	private String oldStr;
	private String replaceStr;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOldStr() {
		return oldStr;
	}
	public void setOldStr(String oldStr) {
		this.oldStr = oldStr;
	}
	public String getReplaceStr() {
		return replaceStr;
	}
	public void setReplaceStr(String replaceStr) {
		this.replaceStr = replaceStr;
	}
	
}
