package com.jeecms.article.manager;

import com.jeecms.article.entity.Article;
import com.jeecms.cms.entity.CmsAdmin;
import com.jeecms.core.JeeCoreManager;
import com.ponyjava.common.page.Pagination;

public interface ArticleMng extends JeeCoreManager<Article> {
	/**
	 * 为前台标签获得文章列表
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
	 * 获得文章分页数据
	 * 
	 * @param chnlId
	 * @param pageNo
	 * @param count
	 * @return
	 */
	public Pagination getPage(Long chnlId, int pageNo, int count);

	/**
	 * 管理员保存文章
	 * 
	 * @param arti
	 * @param admin
	 * @return
	 */
	public Article save(Article arti, CmsAdmin admin);
}