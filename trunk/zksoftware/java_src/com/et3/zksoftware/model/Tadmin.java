/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package com.et3.zksoftware.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.et3.zksoftware.model.*;
import com.et3.zksoftware.dao.*;
import com.et3.zksoftware.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class Tadmin extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "Tadmin";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERNAME = "username";
	public static final String ALIAS_PASSWORD = "password";
	
	//date formats
	
	//columns START
	private java.lang.Integer id;
	private java.lang.String username;
	private java.lang.String password;
	//columns END

	public Tadmin(){
	}

	public Tadmin(
		java.lang.Integer id
	){
		this.id = id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("Username",getUsername())
			.append("Password",getPassword())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getUsername())
			.append(getPassword())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Tadmin == false) return false;
		if(this == obj) return true;
		Tadmin other = (Tadmin)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getUsername(),other.getUsername())
			.append(getPassword(),other.getPassword())
			.isEquals();
	}
}

