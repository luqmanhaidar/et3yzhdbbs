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
	 * 获得文章分页数据
	 * 
	 * @param chnlIds
	 * @param pageNo
	 * @param count
	 * @return
	 */
	public Pagination getPage(Long[] chnlIds, int pageNo, int count);

	/**
	 * 下一篇文章
	 * 
	 * @param webId
	 * @param artiId
	 * @return
	 */
	public Article getNextArticle(Long webId, Long artiId);

	/**
	 * 上一篇文章
	 * 
	 * @param webId
	 * @param artiId
	 * @return
	 */
	public Article getPreArticle(Long webId, Long artiId);
}