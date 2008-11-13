package com.ntsky.bbs.web.schedule.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.ntsky.bbs.domain.Stat;
import com.ntsky.bbs.domain.ActLog;
import com.ntsky.bbs.service.StatService;
import com.ntsky.bbs.service.ActLogService;
import com.ntsky.bbs.util.BeanFactory;
/**
 * 统计功能数据类
 *  
 * <ul>统计功能　：　
 * 	<li>操作记录（actlog）</li>
 * 　<li>访问日志(stat)</li>
 * </ul>
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:34 $
 */
public class CountSingleton {

	public static Logger logger = Logger.getLogger(CountSingleton.class.getName());

	private static CountSingleton countInstance = null;

	/**
	 * 取得统计数据实例
	 * @return
	 */
	public synchronized static CountSingleton getInstance(){
		if(countInstance==null){
			countInstance = new CountSingleton();
		}
		return countInstance;
	}
	
	private List actlogs = new ArrayList(); 
	private List stats = new ArrayList();
	
	public void addActLog(ActLog actLog){
		actlogs.add(actLog);
	}
	
	public List getActLogs(){
		return actlogs;
	}
	
	public void clearActlogs(){
		actlogs.clear();
	}
	
	
	public void addStat(Stat stat){
		stats.add(stat);
	}
	
	public List getStats(){
		return stats;
	}
	
	public void clearStats(){
		stats.clear();
	}
	
}
