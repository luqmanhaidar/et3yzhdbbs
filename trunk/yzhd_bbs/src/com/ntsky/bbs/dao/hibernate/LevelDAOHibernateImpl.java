package com.ntsky.bbs.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.Order;

import com.ntsky.bbs.dao.LevelDAO;
import com.ntsky.bbs.domain.Level;
import com.ntsky.bbs.exception.DAOException;

public class LevelDAOHibernateImpl extends BaseDAOHibernateImpl implements
		LevelDAO {

	public List findLevels() throws DAOException {
		List levels=this.getSession().createCriteria(Level.class)
					.addOrder(Order.asc("money"))
					.list();
		return levels;
	}

	public Level getLevel(Long id) throws DAOException {
		Level level=new Level();
		level=(Level)super.get(Level.class, id);
		return level;
	}

	public void saveLevel(Level level) throws DAOException {
		this.getHibernateTemplate().save(level);
	}

	public void removeLevel(Level level) throws DAOException {
		this.getHibernateTemplate().delete(level);
	}

}
