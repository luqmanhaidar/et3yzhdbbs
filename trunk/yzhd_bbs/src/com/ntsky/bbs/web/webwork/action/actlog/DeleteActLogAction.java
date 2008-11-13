package com.ntsky.bbs.web.webwork.action.actlog;

import java.util.List;

import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 删除操作日志
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */
public class DeleteActLogAction extends ActLogActionSupport{
	
	// 删除列表
	private String[] ids;
	public void setId(String[] ids){
		this.ids = ids;
	}
	
	// 是否删除全部
	public int isAll;
	private void setIsAll(int isAll){
		this.isAll = isAll;
	}
	
	
	/**
	 * 执行删除统计
	 * <pre>
	 * 	执行成功迁移到 actLogs.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　删除操作日志
		if(!isPermisson("6_6")){
			setWarnMessage("您没有删除操作日志的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("删除操作日志");
		/* ------------ 记录日志 -----------*/
		
		if(logger.isInfoEnabled()){
			logger.info("删除操作日志");
		}
		// isAll为1删除全部的统计信息
		QueryResult queryResult = null;
		try{
			if(isAll==1){
				actLogService.deleteAllActLog();
			}
			else{
				actLogService.deleteActLogs(ids);
			}
			// 设置页面数据和分页
			queryResult = actLogService.getActLogs(super.getParameter("place"),new Pagination(super.getIntParameter(Symbols.PARA_START)));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		super.setActLogs(queryResult.getItems());
		super.setPagination(queryResult.getPagination());
		return SUCCESS;
    }
	
}
