/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package com.et3.zksoftware.service;

import cn.org.rapid_framework.mock.MockOpenSessionInViewFilter;

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


public class TadminManagerTest extends BaseManagerTestCase{

	private TadminManager manager;
	/**通过spring注入TadminDao*/
	public void setTadminManager(TadminManager manager) {
		this.manager = manager;
	}

	@Override
	protected String[] getDbUnitDataFiles() {
		return new String[]{"classpath:common_testdata.xml","classpath:Tadmin_testdata.xml"};
	}

	@Override
	public void onTearDownInTransaction() throws Exception {
		super.onTearDownInTransaction();
	}
	
	public void testCrud() {
		Tadmin obj = new Tadmin();
		
	  	obj.setUsername(new java.lang.String("1"));
	  	obj.setPassword(new java.lang.String("1"));
		
		manager.save(obj);
		manager.getEntityDao().flush();
		
		manager.update(obj);
		manager.getEntityDao().flush();
		
		assertNotNull(obj.getId());
		
		manager.removeById(obj.getId());
		manager.getEntityDao().flush();
	
	}
}
