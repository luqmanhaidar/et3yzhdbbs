package com.ntsky.bbs.web.webwork.action.link;

import java.util.List;

import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.opensymphony.xwork.Preparable;

/**
 * 友情链接管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/17 17:21:30 $
 */
public class LinksAction extends LinkActionSupport implements Preparable{

	/**
	 * 帮助管理
	 * <pre>
	 * 	执行成功迁移到 links.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　列表友情链接
		if(!isPermisson("6_2")){
			setWarnMessage("您没有列表友情链接的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("列表友情链接");
		/* -------------------------------*/
		
		if(logger.isInfoEnabled()){
			logger.info("列表友情链接信息");
		}
		return SUCCESS;
    }
	
	/**
	 * 执行此ManageAction的准备信息
	 * <ul>
	 * 	<li>取得友情链接页面数据</li>
	 *  <li>初始化友情链接信息</li>
	 * </ul>
	 */
	public void prepare() throws Exception {
		
		try{
			setTextLinks(linkService.getLinks(0));
			setPicLinks(linkService.getLinks(1));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		if(logger.isDebugEnabled()){
			logger.debug("初始化友情链接信息");		
		}
		
	}
	
}
