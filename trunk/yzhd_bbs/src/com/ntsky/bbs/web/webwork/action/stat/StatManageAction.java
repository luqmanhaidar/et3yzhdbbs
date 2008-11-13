package com.ntsky.bbs.web.webwork.action.stat;

import java.util.List;

import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 删除统计
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:29 $
 */
public class StatManageAction extends StatActionSupport{
	
	// 删除列表
	private String[] ids;
	public void setId(String[] ids){
		this.ids = ids;
	}
	
	/**
	 * 执行删除统计
	 * <pre>
	 * 	执行成功迁移到 stats.ftl
	 * </pre>
	 * @return String success 
	 */
	public String doDelete() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　删除访问记录
		if(!isPermisson("6_5")){
			setWarnMessage("您没有删除访问记录的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("删除访问记录");
		/* -------------------------------*/
		
		if(logger.isInfoEnabled()){
			logger.info("删除统计信息");
		}
		QueryResult queryResult = null;
		try{
			// ids
			if(logger.isInfoEnabled()){
				logger.info("ids是否存在 : " + (ids==null));
			}
			statService.deleteStats(ids);
			// 设置页面数据和分页
			queryResult = statService.getStats("place","",new Pagination(super.getPaginationStart()));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}  
		setStats(queryResult.getItems());
		super.setPagination(queryResult.getPagination());
		return SUCCESS;
    }
	
	public List stats;
	public List getStats() {
		return stats;
	}
	public void setStats(List stats) {
		this.stats = stats;
	}
	
	/**
	 * 删除全部统计信息
	 * @return
	 * @throws Exception
	 */
	public String doDeleteAll() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　清空访问记录
		if(!isPermisson("6_5")){
			setWarnMessage("您没有清空访问记录的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("清空访问记录");
		/* -------------------------------*/	
		
		try{
			statService.deleteAllStat();
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}  
		return SUCCESS;
	}
	
}
