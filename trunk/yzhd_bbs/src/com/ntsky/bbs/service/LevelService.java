package com.ntsky.bbs.service;

import java.util.List;

import com.ntsky.bbs.domain.Level;
import com.ntsky.bbs.exception.DAOException;

public interface LevelService extends BaseService {
	public List findLevels() throws DAOException ;
	
	public Level getLevel(String id) throws DAOException ;
	
	public void saveLevel(Level level) throws DAOException ;
}
