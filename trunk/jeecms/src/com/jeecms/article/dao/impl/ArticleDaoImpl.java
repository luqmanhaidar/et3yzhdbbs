package com.jeecms.article.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jeecms.article.dao.ArticleDao;
import com.jeecms.article.entity.ArtiCtg;
import com.jeecms.article.entity.Article;
import com.jeecms.cms.entity.CmsChannel;
import com.jeecms.core.JeeCoreDaoImpl;
import com.jeecms.core.entity.Website;
import com.ponyjava.common.hibernate3.Finder;
import com.ponyjava.common.page.Pagination;

@Repository
public class ArticleDaoImpl extends JeeCoreDaoImpl<Article> implements
		ArticleDao {
	@SuppressWarnings("unchecked")
	public Pagination getForTag(Long webId, Long chnlId, Long ctgId,
			String searchKey, Boolean hasTitleImg, boolean recommend,
			int orderBy, boolean isPage, int firstResult, int pageNo,
			int pageSize) {
		Article eg = new Article();
		eg.setWebsite(new Website(webId));
		if (chnlId != null) {
			eg.setChannel(new CmsChannel(chnlId));
		}
		if (ctgId != null) {
			eg.setCtg(new ArtiCtg(ctgId));
		}
		if (recommend) {
			eg.setRecommend(recommend);
		}
		if (hasTitleImg != null) {
			eg.setHasTitleImg(hasTitleImg);
		}
		Order order;
		switch (orderBy) {
		case 3:
			order = Order.asc("visitTotal");
			break;
		case 2:
			order = Order.desc("visitTotal");
			break;
		case 1:
			order = Order.asc("releaseDate");
			break;
		default:
			order = Order.desc("releaseDate");
		}
		Criteria crit = getCritByEg(eg, false, null);
		if (!StringUtils.isBlank(searchKey)) {
			Disjunction disj = Restrictions.disjunction();
			disj.add(Restrictions.like("title", searchKey, MatchMode.ANYWHERE));
			disj.add(Restrictions.like("tags", searchKey, MatchMode.ANYWHERE));
			disj.add(Restrictions.like("description", searchKey,
					MatchMode.ANYWHERE));
			crit.add(disj);
		}
		if (isPage) {
			return findByCriteria(crit, pageNo, pageSize, null, order);
		} else {
			crit.setFirstResult(firstResult);
			crit.setMaxResults(pageSize);
			crit.addOrder(order);
			List<Article> list = crit.list();
			return new Pagination(pageNo, list.size(), pageSize, list);
		}
	}

	
	public Pagination getPage(Long[] chnlIds, int pageNo, int count) {
		String hql = "from Article a where a.channel.id in (:chnlIds) order by a.id desc";
		Finder f = Finder.create(hql);
		f.setParameterList("chnlIds", chnlIds);
		return find(f, pageNo, count);
	}

	public Article getNextArticle(Long webId, Long artiId) {
		String hql = "from Article a where a.website.id=? and a.id>? order by a.id asc";
		return (Article) findUnique(hql, webId, artiId);
	}

	public Article getPreArticle(Long webId, Long artiId) {
		String hql = "from Article a where a.website.id=? and a.id<? order by a.id desc";
		return (Article) findUnique(hql, webId, artiId);
	}
}