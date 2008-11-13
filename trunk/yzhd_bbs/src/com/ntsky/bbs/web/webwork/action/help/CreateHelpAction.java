package com.ntsky.bbs.web.webwork.action.help;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.webwork.interceptor.SessionAware;
import com.opensymphony.xwork.ModelDriven;

import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.domain.Help;

/**
 * 添加帮助
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:29 $
 */
public class CreateHelpAction extends HelpActionSupport implements ModelDriven{

	private String resultMsg;
	private Map urls = new HashMap();

	public Map getUrls() {
		return urls;
	}
	
	public void setUrls(Map urls){
		this.urls = urls;
	}
	
	private Help help = new Help();
	
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　添加帮助
		if(!isPermisson("6_4")){
			setWarnMessage("您没有添加帮助的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */
		/* ------------ 记录日志 -----------*/
		recordActLog("添加帮助");
		/* -------------------------------*/	
		
		if(logger.isDebugEnabled()){
			logger.debug(
					"帮助标题 : " + help.getTitle() +
					"帮助类别 : " + help.getType() 
			);
		}
		helpService.createHelp(help);
		super.setActionMessage("添加帮助成功.");
		String[] urlArray = StringUtil.splitStringToArray(super.getParameter("urlArray"),",");
		for (int i = 0; i < urlArray.length; i++) {
			int equal = urlArray[i].indexOf(":");
			urls.put(urlArray[i].substring(0,equal),urlArray[i].substring(equal+1));
		}
		setUrls(urls);
		return SUCCESS;
	}
	
	/**
	 * 检验失败
	 */
	public void validate(){
		//super.setHelps(helpService.selectHelps());
	}
	
	public Object getModel() {
		return this.help;
	}

}
