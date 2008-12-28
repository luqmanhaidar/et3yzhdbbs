package com.jeecms.article.manager;

import com.jeecms.article.entity.Article;
import com.jeecms.cms.entity.CmsAdmin;
import com.jeecms.core.JeeCoreManager;
import com.ponyjava.common.page.Pagination;

public interface ArticleMng extends JeeCoreManager<Article> {
	/**
	 * Ϊǰ̨��ǩ��������б�
	 * 
	 * @param webId
	 * @param chnlId
	 * @param ctgId
	 * @param searchKey
	 * @param hasTitleImg
	 * @param recommend
	 * @param orderBy
	 * @param isPage
	 * @param firstResult
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getForTag(Long webId, Long chnlId, Long ctgId,
			String searchKey, Boolean hasTitleImg, boolean recommend,
			int orderBy, boolean isPage, int firstResult, int pageNo,
			int pageSize);

	/**
	 * ������·�ҳ����
	 * 
	 * @param chnlId
	 * @param pageNo
	 * @param count
	 * @return
	 */
	public Pagination getPage(Long chnlId, int pageNo, int count);

	/**
	 * ����Ա��������
	 * 
	 * @param arti
	 * @param admin
	 * @return
	 */
	public Article save(Article arti, CmsAdmin admin);
}