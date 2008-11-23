package com.ntsky.bbs.service.impl;

import java.util.List;

import com.ntsky.bbs.dao.LevelDAO;
import com.ntsky.bbs.domain.Level;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.service.LevelService;

public class LevelServiceImpl extends BaseServiceImpl implements LevelService {
	
	private LevelDAO levelDAO;

	public LevelDAO getLevelDAO() {
		return levelDAO;
	}

	public void setLevelDAO(LevelDAO levelDAO) {
		this.levelDAO = levelDAO;
	}

	public List findLevels() throws DAOException {
		return levelDAO.findLevels();
	}

	public Level getLevel(String id) throws DAOException {
		return levelDAO.getLevel(Long.parseLong(id));
	}

	public void saveLevel(Level level) throws DAOException {
		// TODO Auto-generated method stub

	}

}
