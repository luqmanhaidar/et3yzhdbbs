package com.jeecms.article.dao.impl;

import org.springframework.stereotype.Repository;

import com.jeecms.article.entity.ArtiCtg;
import com.jeecms.article.dao.ArtiCtgDao;
import com.jeecms.core.JeeCoreDaoImpl;

@Repository
public class ArtiCtgDaoImpl extends JeeCoreDaoImpl<ArtiCtg> implements
		ArtiCtgDao {
	public ArtiCtg getArtiCtg(Long webId, String label) {
		String hql = "from ArtiCtg a where a.website.id=? and a.label=?";
		return (ArtiCtg) findUnique(hql, webId, label);
	}
}