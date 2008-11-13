package com.ntsky.bbs.web.webwork.action.xml;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.exception.XMLException;
import com.ntsky.bbs.util.FreemarkerStatic;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.xml.bean.Badword;
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
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:28 $
 */
public class BadwordManageAction extends XmlActionSupport {
	
	private Badword badword;
	public Badword getBadword() {
		return badword;
	}

	/**
	 * 脏话过滤设置
	 * <pre>
	 * 	执行成功迁移到 badwordftl
	 * </pre>
	 * 
	 * @return String success 
	 */
	public String execute() throws Exception {
		return SUCCESS;
    }
	
	/**
	 * 编号
	 */
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 根据ID查询待过滤文字的信息
	 * @throws Exception
	 */
	public String select() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　查找脏话过滤
		if(!isPermisson("4_2")){
			setWarnMessage("您没有查找脏话过滤的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("查找脏话过滤");
		/* -------------------------------*/			
		
		try{
			badword = xmlDataService.getBadword(id);
		}
		catch(XMLException xe){
			throw new ActionException(xe);
		}
		return SUCCESS;
	}
	
}
