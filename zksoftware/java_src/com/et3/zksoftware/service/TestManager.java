/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package com.et3.zksoftware.service;

import org.springframework.stereotype.Component;

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

@Component
public class TestManager extends BaseManager<Test,java.lang.Integer>{

	private TestDao testDao;
	/**通过spring注入TestDao*/
	public void setTestDao(TestDao dao) {
		this.testDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.testDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return testDao.findByPageRequest(pr);
	}
	
}
