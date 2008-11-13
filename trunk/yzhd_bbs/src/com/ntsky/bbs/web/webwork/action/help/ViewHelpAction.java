package com.ntsky.bbs.web.webwork.action.help;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.domain.Help;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 察看帮助信息
 * 
 * @author ntsky
 * @link http://www.ntsky.com
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:29 $
 */
public class ViewHelpAction extends HelpActionSupport{

	private List helpTypes;

	public List getHelpTypes(){
		return helpTypes;
	}
	
	public String execute() throws Exception {
		try{
			helpTypes = super.commonService.getAllHelps();
			return SUCCESS;
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
	}
	
}
