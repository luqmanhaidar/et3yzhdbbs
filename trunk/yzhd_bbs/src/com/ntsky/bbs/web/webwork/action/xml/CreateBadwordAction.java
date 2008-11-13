package com.ntsky.bbs.web.webwork.action.xml;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.FreemarkerStatic;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.xml.bean.Badword;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ModelDriven;

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
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:28 $
 */
public class CreateBadwordAction extends XmlActionSupport implements ModelDriven {
	
	private Badword badword = new Badword();;
	
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
		//　添加脏话过滤
		if(!isPermisson("4_2")){
			setWarnMessage("您没有添加脏话过滤的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("添加脏话过滤");
		/* -------------------------------*/
		
		if(logger.isInfoEnabled()){
			logger.info("脏话过滤设置...");
		}
		try{
			xmlDataService.createBadword(badword);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
    }
	
	/**
	 * 打开添加页面
	 *
	 */
	public String doDefault() throws Exception {
		/* ---------- 权限判断 ------------ */
		//　添加脏话过滤
		if(!isPermisson("4_2")){
			setWarnMessage("您没有添加脏话过滤的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("添加脏话过滤");
		/* -------------------------------*/		
		
		if(logger.isInfoEnabled()){
			logger.info("添加脏话过滤");
		}
		return SUCCESS;
	}

	public Object getModel() {
		return this.badword;
	}
}
