package com.ntsky.bbs.web.webwork.action.help;

import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
/**
 * 删除帮助
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:29 $
 */

public class DeleteHelpAction extends HelpActionSupport{

	private int helpId;

	public void setHelpId(int helpId) {
		this.helpId = helpId;
	}

	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　删除帮助
		if(!isPermisson("6_4")){
			setWarnMessage("您没有删除帮助的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */
		/* ------------ 记录日志 -----------*/
		recordActLog("删除帮助");
		/* -------------------------------*/
		
		if(logger.isDebugEnabled()){
			logger.debug("删除编号[" + helpId + "]的帮助信息");
		}
		try{
			helpService.deleteHelp(helpId);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
}
