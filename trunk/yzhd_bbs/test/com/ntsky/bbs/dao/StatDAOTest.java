package com.ntsky.bbs.dao;

import java.util.Date;

import com.ntsky.bbs.domain.Stat;

public class StatDAOTest extends BaseDAOCase{
	
    private StatDAO statDAO = null;
    private Stat stat = null;
    
    public void setStatDAO(StatDAO logDAO) {
        this.statDAO = statDAO;
    }

    public void testSaveStat(){
    	stat = new Stat();
    	stat.setUsername("guest");
    	stat.setPlace("深圳南山");
    	stat.setReferer("/bbs/index.jsp");
    	stat.setAgent("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; .NET CLR 1.1.4322)");
    	stat.setLanguage("zh-cn");
    	stat.setViewTime(new Date());
    	statDAO.save(stat);
    }
    
    public void testFindLog(){
    	stat = statDAO.findStat(1);
    	assertEquals(stat.getUsername(),"guest");
    }
    
}
