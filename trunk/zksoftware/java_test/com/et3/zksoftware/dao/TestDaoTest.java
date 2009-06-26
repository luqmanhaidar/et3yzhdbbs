/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package com.et3.zksoftware.dao;

import java.util.List;

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


public class TestDaoTest extends BaseDaoTestCase{
	
	private TestDao dao;
	/**通过spring注入TestDao*/
	public void setTestDao(TestDao dao) {
		this.dao = dao;
	}
	
	@Override
	protected void onTearDownInTransaction() throws Exception {
		super.onTearDownInTransaction();
	}
	
	@Override
	protected String[] getDbUnitDataFiles() {
		return new String[]{"classpath:common_testdata.xml","classpath:Test_testdata.xml"};
	}
	
	public void testFindByPageRequest() {
		int pageNumber = 1;
		int pageSize = 10;
		
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		pageRequest.setSortColumns(null);
		
		pageRequest.getFilters().put("text", "1");
		pageRequest.getFilters().put("rr", "1");
		
		Page page = dao.findByPageRequest(pageRequest);
		
		assertEquals(pageNumber,page.getThisPageNumber());
		assertEquals(pageSize,page.getPageSize());
		List resultList = (List)page.getResult();
		assertNotNull(resultList);
		
	}
	
}
