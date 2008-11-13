package com.ntsky.bbs.web.schedule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ntsky.bbs.service.ForumService;
import com.ntsky.bbs.service.UserService;
import com.ntsky.bbs.util.BeanFactory;

/**
 * 网站首页数据定时器
 * 
 * <pre>执行信息的如下 : </pre>
 * <ol>
 * 		
 * <ol>
 * 
 * @author 姚君林
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:33 $
 */
public class IndexTimer {
	
	public static Logger logger = Logger.getLogger(IndexTimer.class);
	
	private final Timer timer = new Timer();
    private final int minutes = 2;
	
    private ServletContext sc;
    public IndexTimer(ServletContext sc){
    	this.sc = sc;
    }
    
    public void start() {
        GregorianCalendar curTime = new GregorianCalendar();
        curTime.set(GregorianCalendar.HOUR_OF_DAY, 23);
        curTime.set(GregorianCalendar.MINUTE, 59);
        /*DateFormat df = DateFormat.getDateInstance();
        System.out.println("=====curTime is:" + df.format(curTime.getTime())
                + curTime.get(GregorianCalendar.HOUR_OF_DAY)
                + curTime.get(GregorianCalendar.MINUTE));*/
        timer.schedule(new TimerTask() {
            public void run() {
            	countForum();
            }
            /**
             * 技术论坛信息
             * 论坛状态信息
             */
            private void countForum() {
            	try{
	            	ForumService forumService = (ForumService)BeanFactory.getInstance(sc).getBean("forumService");
	            	forumService.getForums();
            	}
            	catch(Exception ex){
            		logger.error("刷新论坛内存数据失败....");
            	}
            }
        }, curTime.getTime(),86400000);
    }


    public static void main(String[] args) {
    	//IndexTimer it = new IndexTimer();
    	//it.start();
	}
}
