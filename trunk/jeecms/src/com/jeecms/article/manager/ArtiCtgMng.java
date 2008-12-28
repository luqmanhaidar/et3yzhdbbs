package com.jeecms.article.manager;

import java.util.List;

import com.jeecms.core.JeeCoreManager;
import com.jeecms.article.entity.ArtiCtg;

public interface ArtiCtgMng extends JeeCoreManager<ArtiCtg> {
	/**
	 * 获得站点文章类型
	 * 
	 * @param webId
	 * @param disabled
	 *            是否停止使用，为null则查询所有。
	 * @return
	 */
	public List<ArtiCtg> getList(Long webId, Boolean disabled);

	/**
	 * 根据label获得id
	 * 
	 * @param webId
	 * @param label
	 * @return
	 */
	public ArtiCtg getArtiCtg(Long webId, String label);
}