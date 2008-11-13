package com.ntsky.bbs.dao;

import java.util.Collection;
import java.util.Date;

import org.springframework.dao.DataAccessException;

import com.ntsky.bbs.dao.hibernate.ActLogDAOHibernateImpl;
import com.ntsky.bbs.domain.ActLog;
import com.ntsky.bbs.domain.Stat;
import com.ntsky.bbs.util.page.Pagination;

/**
 * 操作日志模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class ActlogDAOTest extends BaseDAOCase {

	private ActLogDAO actLogDAO = null;

	private ActLog actlog = null;

	public void setActLogDAO(ActLogDAO actLogDAO) {
		this.actLogDAO = actLogDAO;
	}

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		// super.onSetUpInTransaction();
		// this.setPopulateProtectedVariables(true);
		// actLogDAO = (ActLogDAO) this.applicationContext.getBean("actLogDAO");
	}

	public void onSetUpBeforeTransaction() {
		actlog = new ActLog();
		actlog.setName("add act log");
		actlog.setUsername("guest");
		actlog.setIp("127.0.0.1");
		actlog.setTime(new Date());
		/*
		 * person = new Person(); person.setFirstName("Sean");
		 * person.setLastName("Liu"); person.setUserName("forever");
		 */

		/*
		 * 联合查询 多表操作返回 List的场合 list().get(0)[0] list().get(0)[1]
		 * list().get(0)[2] list().get(0)[3]
		 * 
		 * select new XX('field1','field2')
		 */
	}

	public void testSaveActLog() {
		actLogDAO.save(actlog);
	}

	public void testFindActLog() {
		assertNull(actLogDAO.findActLog(100));
	}

	public void testFindActLogs() {
		actLogDAO.findActLogs("", new Pagination(0, 10));
	}

	public void testDeleteActLog(){
		actLogDAO.delete(actLogDAO.get(ActLog.class,new Long(1)));
	}
	
}
