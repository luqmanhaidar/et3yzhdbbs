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

import com.ntsky.bbs.domain.Stat;
import com.ntsky.bbs.service.StatService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.web.schedule.data.CountSingleton;

/**
 * 统计功能定时器
 * 
 * <ul>
 * 统计功能 ：
 * <li>访问日志(stat)</li>
 * </ul>
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:33 $
 */
public class StatTask extends QuartzJobBean {
	
	public static Logger logger = Logger.getLogger(StatTask.class.getName());
	
	public void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		
		CountSingleton countInstance = CountSingleton.getInstance();
		
		// 取得统计信息
		List stats = countInstance.getStats();
		
		this.writeStatToDB(Application.getInstance().getServletContext(),stats);
		
		countInstance.clearStats();
		
	}
	
	
	public void writeStatToDB(ServletContext sc, List stats){
		Connection conn = null;
		PreparedStatement stmt =null;
		Stat stat = null;
		try{
			StatService statService = (StatService)BeanFactory.getInstance(sc).getBean("statService");			
			for (int i = 0; i < stats.size(); i++) {
				statService.createStat((Stat)stats.get(i));
			}
		}
		catch(Exception ex){
			logger.error("统计访问日志发生错误",ex);
		}
		finally{

		}
	}
}