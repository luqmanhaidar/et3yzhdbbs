package com.ntsky.bbs.web.webwork.action.xml;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 金钱信息设定管理Action
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:28 $
 */
public class MoneyConfigAction extends XmlActionSupport {
	
	/**
	 * 金钱信息设置
	 * <pre>
	 * 	执行成功迁移到 moneyConfig.ftl
	 * </pre>
	 * @return String success 
	 */
	public String doSet() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　金钱设置　
		if(!isPermisson("1_7")){
			setWarnMessage("您没有积分设置的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("积分设置");
		/* -------------------------------*/	
		
		if(logger.isInfoEnabled()){
			logger.info("设置积分配置信息");
		}
		// 修改金钱数据
		super.xmlDataService.editXml(Symbols.CONFIG_MONEY,super.getRequest().getParameterMap());
		this.setMoneyConfigMap();
		return SUCCESS;
    }

	private void setMoneyConfigMap(){
		// 设置基本信息
		Map basicMap = super.xmlDataService.select(Symbols.CONFIG_MONEY);
		super.setPropertyMap(basicMap);				
	}
	
	/**
	 * 取得金钱信息
	 * <ul>
	 *  <li>查询金钱信息</li>
	 * </ul>
	 */
	public String doOpen() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　金钱设置　
		if(!isPermisson("1_7")){
			setWarnMessage("您没有金钱设置的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("积分设置");
		/* -------------------------------*/			
		
		if(logger.isDebugEnabled()){
			logger.debug("检索积分配置信息");		
		}
		this.setMoneyConfigMap();	
		return SUCCESS;
	}
	
	/**
	 * 设置基本配置信息
	 * @throws Exception
	 */
	public void prepare() throws Exception {

	}
	
}
