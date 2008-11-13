/*
 * Created Wed Mar 29 22:23:17 CST 2006 by MyEclipse Hibernate Tool.
 */ 
package com.ntsky.bbs.domain;

/**
 * A class that represents a row in the 'tb_link' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class Link extends Entity {

    private String name;

    private String url;

    private Short isLogo;

    private String logo;

    private String description;
    
    private Integer displayOrder;
    
    private Integer adverType;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Short getIsLogo() {
		return isLogo;
	}

	public void setIsLogo(Short isLogo) {
		this.isLogo = isLogo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getAdverType() {
		return adverType;
	}

	public void setAdverType(Integer adverType) {
		this.adverType = adverType;
	}

}
