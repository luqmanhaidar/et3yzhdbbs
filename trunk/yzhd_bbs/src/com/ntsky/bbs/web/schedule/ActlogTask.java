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

import com.ntsky.bbs.domain.ActLog;
import com.ntsky.bbs.service.ActLogService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.web.schedule.data.CountSingleton;

/**
 * 统计功能定时器
 * 
 * <ul>
 * 统计功能 ：
 * <li>操作记录（actlog）</li>
 * <li>访问日志(stat)</li>
 * </ul>
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:33 $
 */
public class ActlogTask extends QuartzJobBean {
	
	public static Logger logger = Logger.getLogger(ActlogTask.class.getName());
	
	public void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		
		CountSingleton countInstance = CountSingleton.getInstance();
		
		// 取得统计信息
		List actlogs = countInstance.getActLogs();
		// 将统计数组中的数据入库
		this.writeActlogToDB(Application.getInstance().getServletContext(),actlogs);
		
		//　清空数组
		countInstance.clearActlogs();
	}
	
	private void writeActlogToDB(ServletContext sc, List actlogs){
		Connection conn = null;
		PreparedStatement stmt =null;
		ActLog actLog = null;
		try{
			ActLogService actLogService = (ActLogService)BeanFactory.getInstance(sc).getBean("actLogService");			
			//conn.prepareStatement("insert into ntsky_t_act_log()")
			//stmt = conn.prepareStatement(sql);
			for (int i = 0; i < actlogs.size(); i++) {
				actLogService.createActLog((ActLog)actlogs.get(i));
			}
		}
		catch(Exception ex){
			logger.error("统计操作日志发生错误",ex);
		}
		finally{
			
		}
	}
	
}