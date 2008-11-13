package com.ntsky.bbs.web.webwork.action.xml;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 注册配置
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:28 $
 */
public class RegisterConfigAction extends XmlActionSupport {

	/**
	 * 注册管理
	 * <pre>
	 * 	执行成功迁移到 registerConfig.ftl
	 * </pre>
	 * @return String success 
	 */
	public String doSet() throws Exception {
			
		/* ---------- 权限判断 ------------ */
		//　注册设置
		if(!isPermisson("1_3")){
			setWarnMessage("您没有注册设置的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("注册设置");
		/* -------------------------------*/			
		
		if(logger.isInfoEnabled()){
			logger.info("注册设定");
		}		
		super.xmlDataService.editXml(Symbols.CONFIG_REGISTER,super.getRequest().getParameterMap());
		
		this.setRegisterMap();
		
		super.setActionMessage("设置注册配置信息成功!");
		
		return SUCCESS;
    }

	private void setRegisterMap(){
		Map registerMap = super.xmlDataService.select(Symbols.CONFIG_REGISTER);
		super.setPropertyMap(registerMap);
	}
	
	/**
	 * 取得注册配置信息
	 * <ul>
	 *  <li>注册配置信息查询</li>
	 * </ul>
	 */
	public String doOpen() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　注册设置
		if(!isPermisson("1_3")){
			setWarnMessage("您没有注册设置的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("注册设置");
		/* -------------------------------*/		
		
		if(logger.isDebugEnabled()){
			logger.debug("检索注册配置信息");		
		}
		this.setRegisterMap();
		return SUCCESS;
	}
	
}
