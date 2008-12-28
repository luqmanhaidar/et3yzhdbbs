package com.jeecms.article.manager;

import java.util.List;

import com.jeecms.core.JeeCoreManager;
import com.jeecms.article.entity.ArtiCtg;

public interface ArtiCtgMng extends JeeCoreManager<ArtiCtg> {
	/**
	 * ���վ����������
	 * 
	 * @param webId
	 * @param disabled
	 *            �Ƿ�ֹͣʹ�ã�Ϊnull���ѯ���С�
	 * @return
	 */
	public List<ArtiCtg> getList(Long webId, Boolean disabled);

	/**
	 * ����label���id
	 * 
	 * @param webId
	 * @param label
	 * @return
	 */
	public ArtiCtg getArtiCtg(Long webId, String label);
}