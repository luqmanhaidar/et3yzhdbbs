package com.ntsky.bbs.web.webwork.action.help;

import java.util.Collection;
import java.util.List;

import com.opensymphony.xwork.Preparable;
import com.ntsky.bbs.domain.Help;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 帮助管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:29 $
 */
public class HelpsAction extends HelpActionSupport implements Preparable{

	/**
	 * 帮助管理
	 * <pre>
	 * 	执行成功迁移到 helps.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　列表帮助
		if(!isPermisson("6_4")){
			setWarnMessage("您没有修改帮助的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */
		/* ------------ 记录日志 -----------*/
		recordActLog("列表帮助");
		/* -------------------------------*/			
		
		if(logger.isDebugEnabled()){
			logger.debug("列表帮助信息");
		}
		return SUCCESS;
    }
	
	private List helpTypes;
	public List getHelpTypes(){
		return this.helpTypes;
	}
	
	/**
	 * 执行此ManageAction的准备信息
	 */
	public void prepare() throws Exception {		
		if(logger.isInfoEnabled()){
			logger.info("帮助标题关键字为 : " + super.getWd());
		}
		try{
			QueryResult queryResult = helpService.getHelps(super.getWd(),new Pagination(super.getPaginationStart(),10));
			setHelps(queryResult.getItems());
			setPagination(queryResult.getPagination());
			
			helpTypes = super.commonService.getHelpTypes();
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		
		if(logger.isInfoEnabled()){
			logger.info("初始化帮助信息");		
		}
	}
	
}
