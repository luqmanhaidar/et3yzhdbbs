package com.ntsky.bbs.domain;

import java.io.Serializable;

/**
 * Used as a base class for objects needing this property.
 *
 * @author ntsky
 * @link http://www.ntsky.com
 */
public abstract class Entity implements Serializable{
	
	private Long id ;
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String toString() {
        return ("[" + this.getClass().getName() + "]");
    }
}
