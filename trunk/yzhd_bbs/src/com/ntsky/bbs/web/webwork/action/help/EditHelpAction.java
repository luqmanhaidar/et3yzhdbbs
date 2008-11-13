package com.ntsky.bbs.web.webwork.action.help;

import java.util.List;

import org.apache.log4j.Logger;

import com.ntsky.bbs.domain.Help;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.opensymphony.xwork.ModelDriven;
/**
 * 修改帮助
 * <ul>
 * 	<li>edit -- 检索帮助</li>
 *  <li>execute -- 更新帮助</li>
 * </ul>
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:29 $
 */ 

public class EditHelpAction extends HelpActionSupport implements ModelDriven{

	public static Logger logger = Logger.getLogger(EditHelpAction.class);
	
	private int helpId ;
	private Help help = new Help() ;

	public void setHelp(Help help){
		this.help = help;
	}
	
	public Help getHelp(){
		return this.help;
	}
	
	public void setHelpId(int helpId){
		this.helpId = helpId;
	}
	
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　更新帮助
		if(!isPermisson("6_4")){
			setWarnMessage("您没有更新帮助的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */
		/* ------------ 记录日志 -----------*/
		recordActLog("更新帮助");
		/* -------------------------------*/
		
		if(logger.isDebugEnabled()){
			logger.debug("修改帮助 : " +
					"帮助标题="+help.getTitle());
		}
		try{
			helpService.editHelp(help);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	/*public String deDefault() throws Exception{
		logger.debug("default");
		return SUCCESS;
	}
	
	public String deInput() throws Exception{
		logger.debug("input");
		return SUCCESS;
	}*/
	
	public void validate(){
		logger.debug("validate started : true");
		if(hasFieldErrors()){
			setHelp(helpService.getHelp(helpId));
		}
	}
	
	private List helpTypes;
	public List getHelpTypes(){
		return this.helpTypes;
	}
	
	public String edit() throws Exception{
		
		/* ---------- 权限判断 ------------ */
		//　修改帮助
		if(!isPermisson("6_4")){
			setWarnMessage("您没有修改帮助的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */
		/* ------------ 记录日志 -----------*/
		recordActLog("修改帮助");
		/* -------------------------------*/		
		
		if(logger.isDebugEnabled()){
			logger.debug("修改编号["+helpId+"]的帮助信息");
		}
		try{
			setHelp(helpService.getHelp(helpId));
			// 帮助类别 
			helpTypes = super.commonService.getHelpTypes();
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SELECT;
	}

	public Object getModel() {
		return this.help;
	}
	
}