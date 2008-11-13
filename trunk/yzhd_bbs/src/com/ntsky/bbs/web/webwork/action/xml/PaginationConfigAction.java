package com.ntsky.bbs.web.webwork.action.xml;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.util.FreemarkerStatic;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateHashModel;

/**
 * 每页记录数配置管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:29 $
 */
public class PaginationConfigAction extends XmlActionSupport {
	
	/**
	 * 每页记录数设置
	 * <pre>
	 * 	执行成功迁移到 paginationConfig.ftl
	 * </pre>
	 * @return String success 
	 */
	public String doSet() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　分页设置　
		if(!isPermisson("1_6")){
			setWarnMessage("您没有分页设置的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("分页设置");
		/* -------------------------------*/	
		
		if(logger.isInfoEnabled()){
			logger.info("设置每页记录数全局配置信息...");
		}
		// 修改配置数据
		super.xmlDataService.editXml(Symbols.CONFIG_PAGINATION,super.getRequest().getParameterMap());
		this.setPaginationMap();
		return SUCCESS;
    }

	private void setPaginationMap(){
		// 设置每页记录数
		Map paginationMap = super.xmlDataService.select(Symbols.CONFIG_PAGINATION);
		super.setPropertyMap(paginationMap);				
	}
	
	/**
	 * 取得每页记录数配置信息
	 * <ul>
	 *  <li>每页记录数信息查询</li>
	 * </ul>
	 */
	public String doOpen() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　分页设置　
		if(!isPermisson("1_6")){
			setWarnMessage("您没有分页设置的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("分页设置");
		/* -------------------------------*/	
		
		if(logger.isDebugEnabled()){
			logger.debug("设置每页记录数查寻...");		
		}
		this.setPaginationMap();	
		return SUCCESS;
	}
	
}
