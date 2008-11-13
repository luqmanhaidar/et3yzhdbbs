package com.ntsky.bbs.dao;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.hibernate.SessionFactory;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.ntsky.bbs.dao.hibernate.HelpDAOHibernateImpl;
import com.ntsky.framework.util.StringUtil;

/**
 * Base class for running DAO tests.
 * @author mraible
 */
public abstract class BaseDAOCase extends AbstractTransactionalDataSourceSpringContextTests {
	
	protected final static Logger __logger = Logger.getLogger(HelpDAOHibernateImpl.class);

    protected String[] getConfigLocations() {
    	System.out.println("LOG4J : " + StringUtil.applyRelativePath(System.getProperty("user.dir"),"Log4j.xml"));
    	DOMConfigurator.configure(StringUtil.applyRelativePath(System.getProperty("user.dir"),"Log4j.xml"));
        return new String [] {"classpath*:/**/dao/applicationContext-*.xml"};
    }
    
    protected void flushSession(){
        SessionFactory sessionFactory =
                (SessionFactory)applicationContext.getBean("sessionFactory");
        sessionFactory.getCurrentSession().flush();
    }     
    
}
