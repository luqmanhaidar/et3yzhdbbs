package com.jeecms.article.dao;

import com.jeecms.article.entity.ArtiCtg;
import com.jeecms.core.JeeCoreDao;

public interface ArtiCtgDao extends JeeCoreDao<ArtiCtg> {
	/**
	 * 根据站点ID和lebel获得对象
	 * 
	 * @param webId
	 * @param label
	 * @return
	 */
	public ArtiCtg getArtiCtg(Long webId, String label);
}