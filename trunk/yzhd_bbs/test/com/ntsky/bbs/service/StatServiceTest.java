package com.ntsky.bbs.service;

import java.util.Date;

import com.ntsky.bbs.domain.Stat;
import com.ntsky.bbs.service.StatService;

public class StatServiceTest extends NTskyServiceTestCase{

	private StatService statService;
	private Stat stat = null;
	
    protected void setUp() throws Exception {
    	statService = (StatService)super.getBean("statService");
    	
    	stat = new Stat();
    	stat.setUsername("ntsky");
    	stat.setPlace("shenzhennnashan");
    	stat.setReferer("bbs/index.jsp");
    	stat.setAgent("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; .NET CLR 1.1.4322)");
    	stat.setLanguage("zh-cn");
    	stat.setViewTime(new Date());
    }
	
	public void testCreateStat(){
		statService.createStat(stat);
	}
		
	public void testGetStat(){
		assertNotNull(statService.getStat(1));
	}
	
	public void testDeleteStat(){
		statService.deleteStat(1);
	}	
	
	public void testDeleteStats(){
		statService.deleteStats(new String[]{"1"});
	}
	
	public void testDeleteAll(){
		statService.deleteAllStat();
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(StatServiceTest.class);
	}		
	
}
