package com.ntsky.bbs.web.webwork.action.actlog;

import java.util.List;

import com.ntsky.bbs.service.ActLogService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

/**
 * 操作日志抽象类
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:32 $
 */
public abstract class ActLogActionSupport extends BasicActionSupport{
	
	private List actLogs;
	public void setActLogs(List actLogs){
		this.actLogs = actLogs;
	}
	public List getActLogs(){
		return actLogs;
	}
	
}
