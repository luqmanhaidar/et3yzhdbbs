package com.ntsky.bbs.web.webwork.action.help;

import java.util.List;

import com.ntsky.bbs.web.webwork.action.BasicActionSupport;
import com.ntsky.bbs.service.HelpService;
import com.ntsky.bbs.service.CommonService;

/**
 * 帮助抽象类
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:29 $
 */
public abstract class HelpActionSupport extends BasicActionSupport{
	
	private List helps = null;
	
	public void setHelps(List helps){
		this.helps = helps;
	}
	
	public List getHelps(){
		return this.helps;
	}	
	
	protected HelpService helpService;
	
	public void setHelpService(HelpService helpService){
		this.helpService = helpService;
	}

	
	protected CommonService commonService;
	
	public void setCommonService(CommonService commonService){
		this.commonService = commonService;
	}
}
