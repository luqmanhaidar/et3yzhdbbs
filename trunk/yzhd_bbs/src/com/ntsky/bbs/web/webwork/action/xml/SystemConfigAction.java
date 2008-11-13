package com.ntsky.bbs.web.webwork.action.xml;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 系统信息设定管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:28 $
 */
public class SystemConfigAction extends XmlActionSupport {
	
	/**
	 * 系统信息设置
	 * 
	 * <pre>
	 * 	执行成功迁移到 systemConfig.ftl
	 * </pre>
	 * @return String success 
	 */
	public String doSet() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　系统设置
		if(!isPermisson("1_4")){
			setWarnMessage("您没有系统设置的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("系统设置");
		/* -------------------------------*/			
		
		if(logger.isInfoEnabled()){
			logger.info("设置系统配置信息");
		}
		// 修改配置数据
		super.xmlDataService.editXml(Symbols.CONFIG_SYSTEM,super.getRequest().getParameterMap());
		
		this.setSystemMap();
		return SUCCESS;
    }
	
	/**
	 * 设置系统配置映射数组
	 *
	 */
	private void setSystemMap(){
		Map systemMap = super.xmlDataService.select(Symbols.CONFIG_SYSTEM);
		super.setPropertyMap(systemMap);
	}

	/**
	 * 取得系统信息
	 * <ul>
	 *  <li>系统信息查询</li>
	 * </ul>
	 */
	public String doOpen() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　系统设置
		if(!isPermisson("1_4")){
			setWarnMessage("您没有系统设置的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("系统设置");
		/* -------------------------------*/	
		
		if(logger.isInfoEnabled()){
			logger.info("检索系统配置信息");		
		}
		
		this.setSystemMap();
		return SUCCESS;
	}
	
}
