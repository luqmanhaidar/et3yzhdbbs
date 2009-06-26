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


public class Test extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "Test";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TEXT = "text";
	public static final String ALIAS_RR = "rr";
	
	//date formats
	
	//columns START
	private java.lang.Integer id;
	private java.lang.String text;
	private java.lang.String rr;
	//columns END

	public Test(){
	}

	public Test(
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
	public void setText(java.lang.String value) {
		this.text = value;
	}
	
	public java.lang.String getText() {
		return this.text;
	}
	public void setRr(java.lang.String value) {
		this.rr = value;
	}
	
	public java.lang.String getRr() {
		return this.rr;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("Text",getText())
			.append("Rr",getRr())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getText())
			.append(getRr())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Test == false) return false;
		if(this == obj) return true;
		Test other = (Test)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getText(),other.getText())
			.append(getRr(),other.getRr())
			.isEquals();
	}
}

