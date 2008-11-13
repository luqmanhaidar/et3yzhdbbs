package com.ntsky.bbs.web.webwork.action.xml;

import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.XMLException;
import com.ntsky.bbs.xml.bean.Badword;

public class DeleteBadwordAction extends XmlActionSupport {
	
	private Badword badword;
	public Badword getBadword() {
		return badword;
	}
	
	public String execute() throws Exception{
		if(!isPermisson("4_2")){
			setWarnMessage("您没有删除脏话过滤的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("查找脏话过滤");
		/* -------------------------------*/			
		
		try{
			badword = xmlDataService.getBadwordById(id);
			xmlDataService.removeBadword(badword);
		}
		catch(XMLException xe){
			throw new ActionException(xe);
		}
		return SUCCESS;
	}
}
