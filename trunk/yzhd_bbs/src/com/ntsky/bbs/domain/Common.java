package com.ntsky.bbs.domain;

import java.util.List;
import java.util.Set;

public class Common extends Entity{

	private String name;
	private int type;
	private int ordering;
	
	/*private CommonKey key;
	public CommonKey getKey() {
		return key;
	}
	public void setKey(CommonKey key) {
		this.key = key;
	}*/	
	
	private List faces;	
	public List getFaces() {
		return faces;
	}
	public void setFaces(List faces) {
		this.faces = faces;
	}
	
	private List helps;
	public List getHelps(){
		return helps;
	}
	public void setHelps(List helps) {
		this.helps = helps;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrdering() {
		return ordering;
	}
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
