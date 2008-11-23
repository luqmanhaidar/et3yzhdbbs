package com.ntsky.bbs.dao;

import java.util.List;

import com.ntsky.bbs.domain.Level;
import com.ntsky.bbs.exception.DAOException;


public interface LevelDAO extends BaseDAO {
	public List findLevels() throws DAOException ;
	
	public Level getLevel(Long id) throws DAOException ;
	
	public void saveLevel(Level level) throws DAOException ;

}
