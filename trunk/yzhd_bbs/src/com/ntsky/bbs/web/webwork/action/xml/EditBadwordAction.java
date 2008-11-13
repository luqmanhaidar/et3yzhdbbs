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
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $
 */
public class EditBadwordAction extends XmlActionSupport implements ModelDriven {
	
	private Badword badword = new Badword();;
	
	/**
	 * 更新脏话过滤
	 * <pre>
	 * 	执行成功迁移到 badwords.action
	 * </pre>
	 * 
	 * @return String success 
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("更新脏话过滤...");
		}
		try{
			xmlDataService.editBadword(badword);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
    }

	public Object getModel() {
		return this.badword;
	}
}
