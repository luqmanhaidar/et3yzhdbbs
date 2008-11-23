package com.ntsky.bbs.dao.hibernate;

import java.util.List;

import com.ntsky.bbs.dao.LevelDAO;
import com.ntsky.bbs.domain.Level;
import com.ntsky.bbs.exception.DAOException;

public class LevelDAOHibernateImpl extends BaseDAOHibernateImpl implements
		LevelDAO {

	public List findLevels() throws DAOException {		
		return this.getHibernateTemplate().find("from Level");
	}

	public Level getLevel(Long id) throws DAOException {
		Level level=new Level();
		level=(Level)super.get(Level.class, id);
		return level;
	}

	public void saveLevel(Level level) throws DAOException {
		// TODO Auto-generated method stub

	}

}
