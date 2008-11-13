package com.ntsky.bbs.web.schedule;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;

import org.springframework.scheduling.quartz.QuartzJobBean;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.service.ForumService;

/**
 * 论坛数据刷新
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/14 16:02:17 $
 */
public class IndexTask extends QuartzJobBean {
	
	public static Logger logger = Logger.getLogger(IndexTask.class.getName());
	
	public void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		
		this.flushForums(Application.getInstance().getServletContext());
	}
	
	private void flushForums(ServletContext sc){
		try{
			ForumService forumService = (ForumService)BeanFactory.getInstance(sc).getBean("forumService");			
			ForumSingleton.getInstance().resetForums(forumService.getForums());
			ForumSingleton.getInstance().setForumsInIndex(forumService.findForumsIsTop());
		}
		catch(Exception ex){
			logger.error("统计操作日志发生错误",ex);
		}
		finally{
			
		}
	}
	
}