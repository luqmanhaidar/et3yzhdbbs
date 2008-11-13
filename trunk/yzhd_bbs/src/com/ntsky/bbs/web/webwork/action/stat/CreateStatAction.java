package com.ntsky.bbs.web.webwork.action.stat;

import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.ntsky.framework.util.HttpUtil;
import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.StringUtil;
import com.ntsky.framework.util.lumaqq.IPSeeker;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.domain.Stat;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.web.schedule.data.CountSingleton;

/**
 * 创建统计信息Action
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:29 $
 */
public class CreateStatAction extends StatActionSupport {

	/**
	 * 创建统计信息
	 * 
	 * @return String 执行信息
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("创建统计信息");
		}
		
		if((Symbols.FALSE).equals(SystemConfig.getInstance().getPropertyValue(Symbols.CONFIG_SYSTEM,"isOpenStat"))){
			if(logger.isInfoEnabled()){
				logger.info("统计功能已关闭...");
			}
			return NONE; 
		}
		
		HttpServletRequest request = super.getRequest();
		// IP地址
	    String ip = HttpUtil.getRemoteAddr(request);
	    // 浏览器头信息
	    String agent = HttpUtil.getHeader(request,"User-Agent");
	    // 浏览器语言
	    String language = HttpUtil.getHeader(request,"Accept-Language");
	    //　访问URI
	    String uri = request.getRequestURI();
	    if(logger.isInfoEnabled()){
	    	logger.info("访问URI为 : " + uri);
	    }

	    String referer = request.getParameter("referer");
	    if(logger.isInfoEnabled()){
	    	logger.info("访问referer为 : " + referer);
	    }	    
	    
	    Stat stat = new Stat();
	    Admin admin = super.getSessionAdmin();
	    if(admin==null){
	    	User user = super.getSessionUser();
	    	if(user==null){
	    		stat.setUsername("guest");
	    	}
	    	else{
	    		stat.setUsername(user.getUsername());
	    	}
	    }
	    else{
	    	stat.setUsername(admin.getUsername());
	    }
	    stat.setAgent(agent);
	    stat.setLanguage(language);
	    // IP地址数据包路径
	    if(logger.isInfoEnabled()){
	    	logger.info("QQIP地址库的路径 : " + StringUtil.applyRelativePath(Application.getInstance().getWebRealPath(),"WEB-INF/lib/QQWry.dat"));
	    }
	    IPSeeker ipSeeker = IPSeeker.getInstance(StringUtil.applyRelativePath(Application.getInstance().getWebRealPath(),"WEB-INF/lib/QQWry.dat"));
	    stat.setPlace(ip + "("+ipSeeker.getCountry(ip)+ipSeeker.getArea(ip)+")");
	    stat.setReferer(uri);
	    stat.setViewTime(new Date());
	    // 记录统计信息
	    CountSingleton.getInstance().addStat(stat);
	    return NONE;
    }
}
