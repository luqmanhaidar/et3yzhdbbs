package com.ntsky.bbs.web.webwork.action.actlog;

import java.util.List;

import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
/**
 * 操作日志管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */
public class ActLogsAction extends ActLogActionSupport implements Preparable{

	/**
	 * 操作日志管理
	 * <pre>
	 * 	执行成功迁移到 actLogs.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
				
		/* ---------- 权限判断 ------------ */
		//　列表操作记录
		if(!isPermisson("6_6")){
			setWarnMessage("您没有列表操作记录的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("列表操作日志");
		/* -------------------------------*/
		
		if(logger.isInfoEnabled()){
			logger.info("列表操作日志信息");
		}
		return SUCCESS;
    }

	/**
	 * 执行此ManageAction的准备信息
	 * <ul>
	 *  <li>初始化统计信息</li>
	 * </ul>
	 */
	public void prepare() throws Exception {
		QueryResult queryResult = null;
		try{
			if(logger.isInfoEnabled()){
				logger.info("检索操作日志,关键字为:　"+super.getWd()+" 数据初始记录 : " + getPaginationStart());
			}
			queryResult = super.actLogService.getActLogs(super.getWd(),new Pagination(getPaginationStart()));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		super.setActLogs(queryResult.getItems());
		super.setPagination(queryResult.getPagination());
		if(logger.isDebugEnabled()){
			logger.debug("初始化操作日志信息");		
		}
	}
	
}
