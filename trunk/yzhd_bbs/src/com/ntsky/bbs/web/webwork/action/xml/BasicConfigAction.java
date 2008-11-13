package com.ntsky.bbs.web.webwork.action.xml;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.util.FreemarkerStatic;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateHashModel;

/**
 * 基本信息设定管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:28 $
 */
public class BasicConfigAction extends XmlActionSupport {
	
	/**
	 * 基本信息设置
	 * <pre>
	 * 	执行成功迁移到 basicConfig.ftl
	 * </pre>
	 * 更新application中basic信息
	 * 
	 * @return String success 
	 */
	public String doSet() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　设定基本信息
		if(!isPermisson("1_1")){
			setWarnMessage("您没有设定基本信息的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("设定基本信息");
		/* -------------------------------*/			
		
		if(logger.isInfoEnabled()){
			logger.info("设置基础配置信息");
		}
		// 修改配置数据
		super.xmlDataService.editXml(Symbols.CONFIG_BASIC,super.getRequest().getParameterMap());
		// 设置Application信息
		ServletActionContext.getServletContext().setAttribute(Symbols.BASIC,SystemConfig.getInstance().getPropertyMap("basic"));
		this.setBasicMap();
		super.setActionMessage("设置基本配置信息成功!");
		return SUCCESS;
    }

	private void setBasicMap(){
		// 设置基本信息
		Map basicMap = super.xmlDataService.select(Symbols.CONFIG_BASIC);
		super.setPropertyMap(basicMap);				
	}
	
	/**
	 * 取得基本信息
	 * <ul>
	 *  <li>基本信息查询</li>
	 * </ul>
	 */
	public String doOpen() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　设定基本信息
		if(!isPermisson("1_1")){
			setWarnMessage("您没有设定基本信息的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("设定基本信息");
		/* -------------------------------*/			
		
		if(logger.isDebugEnabled()){
			logger.debug("打开检索基本配置信息");		
		}
		this.setBasicMap();	
		
		/*Configuration config = new Configuration();
		BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
		TemplateHashModel staticModels = wrapper.getStaticModels();
		TemplateHashModel fileStatics =
		    (TemplateHashModel) staticModels.get("java.io.File"); 
		SimpleHash root = new SimpleHash();
		root.put("File",fileStatics);
		config.setObjectWrapper(wrapper);*/
		//FreemarkerStatic.loadProperties();
		return SUCCESS;
	}

}
