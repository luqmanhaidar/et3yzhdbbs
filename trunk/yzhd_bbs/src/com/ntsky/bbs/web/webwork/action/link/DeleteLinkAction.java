package com.ntsky.bbs.web.webwork.action.link;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.domain.Link;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 删除友情链接
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.3 $ $Date: 2008/10/17 17:21:30 $
 */
public class DeleteLinkAction extends LinkActionSupport{
	
	/**
	 * 删除友情链接
	 */
	public String execute() throws Exception {

		/* ---------- 权限判断 ------------ */
		//　删除友情链接
		if(!isPermisson("6_2")){
			setWarnMessage("您没有删除友情链接的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("删除友情链接");
		/* -------------------------------*/	
		
		if(logger.isInfoEnabled()){
			logger.info("删除友情链接");
		}
		try{
			linkService.deleteLink(super.getIntParameter("linkId"));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		setActionMessage("成功删除友情链接");
		return SUCCESS;
	}
	
}
