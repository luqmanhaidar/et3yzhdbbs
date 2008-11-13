package com.ntsky.bbs.web.webwork.action.stat;

import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.ntsky.framework.util.HttpUtil;
import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.StringUtil;
import com.ntsky.framework.util.lumaqq.IPSeeker;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Stat;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 检索统计信息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:29 $
 */
public class SearchStatAction extends StatActionSupport {

	/**
	 * 检索统计信息  new String(super.value.getBytes("iso-8859-1"))
	 * 
	 * @return String 执行信息
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　检索访问记录
		if(!isPermisson("6_5")){
			setWarnMessage("您没有检索访问记录的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */
		/* ------------ 记录日志 -----------*/
		recordActLog("检索访问记录");
		/* -------------------------------*/	
		
		if(logger.isInfoEnabled()){
			//logger.info("检索统计信息.检索字段['"+super.field+"'],匹配的值['"+super.wd+"']");
			logger.info("检索统计信息.检索字段['"+super.getParameter("field")+"'], 匹配的值['"+super.getWd()+"']");
		}
	    // 创建统计信息
	    try{
			QueryResult queryResult = statService.getStats(super.getParameter("field"),super.getWd(),new Pagination(super.getPaginationStart()));
			setStats(queryResult.getItems());
			super.setPagination(queryResult.getPagination());
	    }
		catch(ServiceException se){
			throw new ActionException(se);
		}  
	    return SUCCESS;
    }
}
