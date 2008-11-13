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
 * 脏话过滤设置
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $
 */
public class SecuritysAction extends XmlActionSupport {
	
	private List badwords;
	public List getBadwords() {
		return badwords;
	}
	public void setBadwords(List badwords) {
		this.badwords = badwords;
	}

	/**
	 * 脏话过滤设置
	 * <pre>
	 * 	执行成功迁移到 badwords.ftl
	 * </pre>
	 * 
	 * @return String success 
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　列表脏话过滤
		if(!isPermisson("4_2")){
			setWarnMessage("您没有列表脏话过滤的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("列表脏话过滤");
		/* -------------------------------*/			
		
		if(logger.isInfoEnabled()){
			logger.info("脏话过滤设置...");
		}
		setBadwords(xmlDataService.getBadwords());
		return SUCCESS;
    }
	
	/**
	 * 设置基本配置信息
	 * @throws Exception
	 */
	public void prepare() throws Exception {

	}
	
}
