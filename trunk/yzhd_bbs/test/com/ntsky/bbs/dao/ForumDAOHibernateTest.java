package com.ntsky.bbs.dao;

import java.util.HashMap;

import com.ntsky.bbs.domain.Forum;

public class ForumDAOHibernateTest extends BaseDAOCase {
    private Forum forum = null;
    private ForumDAO dao = null;

    public void setForumDAO(ForumDAO dao) {
        this.dao = dao;
    }

    public void testSave(){
    	Forum forum = new Forum();
    	forum.setName("");
    	forum.setParentId(0);
    	
    }
    
    public void testFindMaxBranchId(){
    	dao.findMaxBranchId();
    }
    
    public void testFindForums(){
		assertNotNull(dao.findForums ());
    }
    
    public void testFindForum(){
    	dao.load(Forum.class,new Long(1));
    	//System.out.println(dao.findForum(1));
    	//assertNotNull((Forum)dao.findForum(1));
    	//assertEquals(((Forum)dao.findForum(1)).getForumId(),1);
    }
    
}
