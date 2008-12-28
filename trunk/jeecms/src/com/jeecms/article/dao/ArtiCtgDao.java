package com.jeecms.article.dao;

import com.jeecms.article.entity.ArtiCtg;
import com.jeecms.core.JeeCoreDao;

public interface ArtiCtgDao extends JeeCoreDao<ArtiCtg> {
	/**
	 * ����վ��ID��lebel��ö���
	 * 
	 * @param webId
	 * @param label
	 * @return
	 */
	public ArtiCtg getArtiCtg(Long webId, String label);
}