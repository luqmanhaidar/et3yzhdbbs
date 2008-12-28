package com.jeecms.article.dao;

import com.jeecms.article.entity.Article;
import com.jeecms.core.JeeCoreDao;
import com.ponyjava.common.page.Pagination;

public interface ArticleDao extends JeeCoreDao<Article> {
	public Pagination getForTag(Long webId, Long chnlId, Long ctgId,
			String searchKey, Boolean hasTitleImg, boolean recommend,
			int orderBy, boolean isPage, int firstResult, int pageNo,
			int pageSize);

	/**
	 * ������·�ҳ����
	 * 
	 * @param chnlIds
	 * @param pageNo
	 * @param count
	 * @return
	 */
	public Pagination getPage(Long[] chnlIds, int pageNo, int count);

	/**
	 * ��һƪ����
	 * 
	 * @param webId
	 * @param artiId
	 * @return
	 */
	public Article getNextArticle(Long webId, Long artiId);

	/**
	 * ��һƪ����
	 * 
	 * @param webId
	 * @param artiId
	 * @return
	 */
	public Article getPreArticle(Long webId, Long artiId);
}