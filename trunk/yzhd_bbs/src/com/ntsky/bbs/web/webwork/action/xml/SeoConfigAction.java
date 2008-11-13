package com.ntsky.bbs.web.webwork.action.xml;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.util.FreemarkerStatic;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.opensymphony.webwork.ServletActionContext;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateHashModel;

/**
 * SEO相关配置
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:28 $
 */
public class SeoConfigAction extends XmlActionSupport {
	
	/**
	 * SEO配置设置
	 * <pre>
	 * 	执行成功迁移到 seoConfig.ftl
	 * </pre>
	 * @return String success 
	 */
	public String doSet() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　SEO相关配置
		if(!isPermisson("1_2")){
			setWarnMessage("您没有SEO相关配置的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("SEO相关配置");
		/* -------------------------------*/	
		
		if(logger.isInfoEnabled()){
			logger.info("设置SEO相关配置信息...");
		}
		// 修改配置数据
		super.xmlDataService.editXml(Symbols.CONFIG_SEO,super.getRequest().getParameterMap());
		this.setSeoMap();
		// 设置Application信息
		ServletActionContext.getServletContext().setAttribute(Symbols.SEO,SystemConfig.getInstance().getPropertyMap(Symbols.SEO));		
		return SUCCESS;
    }
	
	private void setSeoMap(){
		Map seoMap = super.xmlDataService.select(Symbols.CONFIG_SEO);
		super.setPropertyMap(seoMap);				
	}
	
	/**
	 * 取得SEO相关配置信息
	 * <ul>
	 *  <li>SEO相关配置信息查询</li>
	 * </ul>
	 */
	public String doOpen() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　SEO相关配置
		if(!isPermisson("1_2")){
			setWarnMessage("您没有SEO相关配置的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("SEO相关配置");
		/* -------------------------------*/	
		
		if(logger.isInfoEnabled()){
			logger.info("取得SEO相关配置信息");	
		}
		this.setSeoMap();	
		return SUCCESS;
	}
	
}
