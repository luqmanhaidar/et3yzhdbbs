package com.ntsky.bbs.dao;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.ntsky.bbs.domain.Link;
import com.ntsky.bbs.dao.LinkDAO;
import com.ntsky.bbs.util.page.Pagination;

/**
 * 友情链接模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class LinkDAOTest extends BaseDAOCase{

    private LinkDAO linkDAO = null;
    private Link link = null;
    
    public void setLinkDAO(LinkDAO linkDAO) {
        this.linkDAO = linkDAO;
    }
    
    public void testSaveLinkDAO(){
    	link = new Link();
    	link.setName("ntsky's bbs");
    	link.setUrl("http://www.ntsky.com");
    	link.setIsLogo((short)0);
    	link.setLogo("../aa.gif");
    	link.setDescription("ntsky's website");
    	link.setDisplayOrder(0);
    	linkDAO.save(link);
    }
    
}
