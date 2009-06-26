/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package com.et3.zksoftware.dao;

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


import java.io.Serializable;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Component;

@Component
public class TestDao extends BaseSpringJdbcDao<Test,java.lang.Integer>{
	
	static final String SELECT_PREFIX = "select id,text,rr from test ";
	
	public Class getEntityClass() {
		return Test.class;
	}
	
	public String getIdentifierPropertyName() {
		return "id";
	}
	
	/**
	 * return sql for deleteById();
	 */
	public String getDeleteByIdSql() {
		return "delete from test where id=?";
	}
	
	/**
	 * return sql for getById();
	 */
	public String getFindByIdSql() {
		return SELECT_PREFIX + " where id=? ";
	}
	
	public void save(Test entity) {
		String sql = "insert into test " 
			 + " (id,text,rr) " 
			 + " values "
			 + " (:id,:text,:rr)";
		insertWithIdentity(entity,sql); //for sqlserver and mysql
		
		//其它主键生成策略
		//insertWithOracleSequence(entity,"sequenceName",sql); //oracle sequence: 
		//insertWithDB2Sequence(entity,"sequenceName",sql); //db2 sequence:
		//insertWithUUID(entity,sql); //uuid
		//insertWithAssigned(entity,sql) //手工分配
	}
	
	public void update(Test entity) {
		String sql = "update test set "
					+ " id=:id,text=:text,rr=:rr "
					+ " where id=:id";
		getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));
	}
	
	public List findAll() {
		String sql = SELECT_PREFIX ;
		return getSimpleJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(getEntityClass()));
	}

	public Page findByPageRequest(PageRequest pageRequest) {
		//XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
		// $column$为字符串拼接, #column#为使用占位符. 以下为图方便采用sql拼接,适用性能要求不高的应�?使用占位符方式可以优化�?�? 
		// $column$ 为PageRequest.getFilters()中的key
		String sql = SELECT_PREFIX + " as a where 1=1 "
				+ "/~ and a.text = '$text$' ~/"
				+ "/~ and a.rr = '$rr$' ~/"
				+ "/~ order by #sortColumns# ~/";
		return pageQuery(sql,pageRequest);
	}
	

}
