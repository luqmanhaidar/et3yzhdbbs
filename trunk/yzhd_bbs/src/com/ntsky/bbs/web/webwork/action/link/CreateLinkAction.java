package com.ntsky.bbs.web.webwork.action.link;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.domain.Link;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 添加帮助
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.3 $ $Date: 2008/10/17 17:21:30 $
 */
public class CreateLinkAction extends LinkActionSupport implements ModelDriven,Preparable{
	
	private Link link = new Link();
	
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　添加友情链接
		if(!isPermisson("6_2")){
			setWarnMessage("您没有添加友情链接的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("添加友情链接");
		/* -------------------------------*/		
		
		int linkType = super.getIntParameter("linkType");
		if(logger.isInfoEnabled()){
			if(linkType==0){
				logger.info("添加文本友情链接");
			}
			else{
				logger.info("添加图片友情链接");
			}
		}
		try{
			linkService.createLink(link);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		if(linkType==0){
			setActionMessage("成功添加文本友情链接");
		}
		else{
			setActionMessage("成功添加图片友情链接");
		}
		return SUCCESS;
	}
	
	public Object getModel() {
		return this.link;
	}

	public void prepare() throws Exception {
		setTextLinks(linkService.getLinks(0));
		setPicLinks(linkService.getLinks(1));		
	}

}
