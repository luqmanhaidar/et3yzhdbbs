package com.ntsky.bbs.web.webwork.action.stat;

import java.util.List;

import com.opensymphony.xwork.Preparable;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 访问日志管理Action
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:29 $
 */
public class StatsAction extends StatActionSupport implements Preparable{

	/**
	 * 统计管理
	 * <pre>
	 * 	执行成功迁移到 stats.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　列表访问记录
		if(!isPermisson("6_5")){
			setWarnMessage("您没有列表访问记录的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("列表访问记录");
		/* -------------------------------*/
		
		if(logger.isInfoEnabled()){
			logger.info("列表访问记录");
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
		
		QueryResult queryResult = statService.getStats("place","",new Pagination(super.getPaginationStart()));
		setStats(queryResult.getItems());
		super.setPagination(queryResult.getPagination());
		if(logger.isDebugEnabled()){
			logger.debug("初始化统计信息");		
		}
		
	}
	
}
