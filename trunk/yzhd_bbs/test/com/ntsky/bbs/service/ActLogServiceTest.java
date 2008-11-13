package com.ntsky.bbs.service;

import java.util.Date;

import org.apache.log4j.Logger;

import com.ntsky.bbs.domain.ActLog;
import com.ntsky.bbs.service.ActLogService;

public class ActLogServiceTest extends NTskyServiceTestCase{
	
	private ActLogService actLogService;
	private ActLog actLog = null;
	
    protected void setUp() throws Exception {
    	actLogService = (ActLogService)super.getBean("actLogService");
    	
    	actLog = new ActLog();
    	actLog.setName("1234");
    	actLog.setIp("127.0.0.1");
    	actLog.setUsername("ntsky");
    	actLog.setTime(new Date());
    }
	
	public void testCreateActLog(){
		try{
			
			StackTraceElement[] stacks = (new Throwable()).getStackTrace();
			for (int i = 0; i < stacks.length; i++) {
				System.out.println(stacks[i].getClassName());
			}
			
			actLog.setName(stacks[0].getClassName()+ "." +stacks[0].getMethodName()+"--"+stacks[0].getLineNumber());
			
			actLogService.createActLog(actLog);
		}
		catch(Exception e){
			System.out.println("ttt"+e.getMessage());
		}
	}
		
	public void testDeleteActLog(){
		__logger.debug("删除操作日志");
		actLogService.deleteActLog(7);		
	}
	
	public void testGetActLog(){
		assertNotNull(actLogService.getActLog(7));
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(ActLogServiceTest.class);
	}		
	
}
