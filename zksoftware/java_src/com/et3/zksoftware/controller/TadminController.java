/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package com.et3.zksoftware.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

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
import com.ntsky.framework.util.security.MD5;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


@Controller
public class TadminController extends BaseSpringController{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private TadminManager tadminManager;
	
	private final String LIST_ACTION = "redirect:/admin/Tadmin/list.do";
	
	public TadminController() {
	}
	
	/** 
	 * 通过spring自动注入
	 **/
	public void setTadminManager(TadminManager manager) {
		this.tadminManager = manager;
	}
	
	/** 
	 * 进入查询页面
	 **/
	public ModelAndView query(HttpServletRequest request,HttpServletResponse response) {
		return new ModelAndView("/admin/Tadmin/query");
	}
	
	/** 
	 * 执行搜索 
	 **/
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) {
		PageRequest pageRequest = newPageRequest(request,DEFAULT_SORT_COLUMNS);
		Page page = this.tadminManager.findByPageRequest(pageRequest);
		savePage(page, request);
		return new ModelAndView("/admin/Tadmin/list");
	}
	
	/** 
	 * 查看对象
	 **/
	public ModelAndView show(HttpServletRequest request,HttpServletResponse response) throws Exception {
		java.lang.Integer id = new java.lang.Integer(request.getParameter("id"));
		Tadmin tadmin = (Tadmin)tadminManager.getById(id);
		return new ModelAndView("/admin/Tadmin/show","tadmin",tadmin);
	}
	
	/** 
	 * 进入新增页面
	 **/
	public ModelAndView create(HttpServletRequest request,HttpServletResponse response,Tadmin tadmin) throws Exception {
		return new ModelAndView("/admin/Tadmin/create","tadmin",tadmin);
	}
	
	/** 
	 * 保存新增对象
	 **/
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response,Tadmin tadmin) throws Exception {
		tadmin.setPassword(MD5.md5(tadmin.getPassword()));
		tadminManager.save(tadmin);
		return new ModelAndView(LIST_ACTION);
	}
	
	/**
	 * 进入更新页面
	 **/
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		java.lang.Integer id = new java.lang.Integer(request.getParameter("id"));
		Tadmin tadmin = (Tadmin)tadminManager.getById(id);
		return new ModelAndView("/admin/Tadmin/edit","tadmin",tadmin);
	}
	
	/**
	 * 保存更新对象
	 **/
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response) throws Exception {
		java.lang.Integer id = new java.lang.Integer(request.getParameter("id"));
		
		Tadmin tadmin = (Tadmin)tadminManager.getById(id);
		bind(request,tadmin);
		tadmin.setPassword(MD5.md5(tadmin.getPassword()));
		tadminManager.update(tadmin);
		return new ModelAndView(LIST_ACTION);
	}
	
	/**
	 *删除对象
	 **/
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response) {
		String[] items = request.getParameterValues("items");
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			
			tadminManager.removeById(id);
		}
		return new ModelAndView(LIST_ACTION);
	}
	
}

