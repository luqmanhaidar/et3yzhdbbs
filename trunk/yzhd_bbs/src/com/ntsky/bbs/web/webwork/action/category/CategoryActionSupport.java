package com.ntsky.bbs.web.webwork.action.category;

import java.util.List;

import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

import com.ntsky.bbs.service.CategoryService;

/**
 * 主题类别(专题)
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */
public abstract class CategoryActionSupport extends BasicActionSupport{
	
	private List categories = null;
	public List getCategories() {
		return categories;
	}
	public void setCategories(List categories) {
		this.categories = categories;
	}
	
	protected CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService){
		this.categoryService = categoryService;
	}

}
