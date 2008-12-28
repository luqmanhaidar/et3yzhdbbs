package com.jeecms.core.manager.impl;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.core.JeeCoreManagerImpl;
import com.jeecms.core.dao.WebsiteDao;
import com.jeecms.core.entity.Website;
import com.jeecms.core.manager.WebsiteMng;
import com.ponyjava.common.hibernate3.OrderBy;
import com.ponyjava.common.page.Pagination;

/**
 * 站点管理实现。
 * <p>
 * 系统启动时，加载所有站点信息。使用hibernate二级缓存，应用缓存保存域名domain到id的关系。
 * </p>
 * <ul>
 * <li>修改website是检查是否修改域名，否则清空缓存。 </li>
 * <li>添加站点时，加入缓存</li>
 * </ul>
 * 
 * @author liufang
 * 
 */
@Service
@Transactional
public class WebsiteMngImpl extends JeeCoreManagerImpl<Website> implements
		WebsiteMng {
	
	@SuppressWarnings("unchecked")
	public List<Website> getAllWebsite() {
		return findAll();
	}

	
	public Pagination getAllWebsite(int page, int countPerPage, boolean order) {
		return findAll(page, countPerPage, new OrderBy[] { OrderBy.desc("id") });
	}

	
	public boolean removeWebsite(Long id) {
		Website website = getWebsiteDao().load(id);
		getWebsiteDao().delete(website);
		websiteDomainCache.remove(website.getDomain());
		return true;
	}

	
	public Website getWebsite(Long id) {
		Website w = null;
		w = getWebsiteDao().load(id);
		return w;
	}

	
	public Website getWebsite(String domainName) {
		Element e = websiteDomainCache.get(domainName);
		if (e != null) {
			Object websiteId = e.getObjectValue();
			return getWebsite((Long) websiteId);
		} else {
			log.warn("get website from cache, domain not exist:" + domainName);
			return null;
		}
	}

	
	public List<Website> getListByUserUnited(Long unitedId) {
		return getWebsiteDao().getListByUserUnited(unitedId);
	}

	
	public void loadAllWebsiteToCache() {
		websiteDomainCache.removeAll();
		List<Website> ws = getWebsiteDao().findAll();
		for (Website w : ws) {
			websiteDomainCache.put(new Element(w.getDomain(), w.getId()));
		}
	}

	
	public void saveWebsite(Website w) {
		w.setSuffix(Website.DEF_SUFFIX);
		getWebsiteDao().save(w);
		websiteDomainCache.put(new Element(w.getDomain(), w.getId()));
		getWebsiteDao().refresh(w);
	}

	
	public boolean updateWebsite(Website website) {
		Website before = getWebsite(website.getId());
		String beforeDomain = before.getDomain();
		Website after = (Website) getWebsiteDao().updateDefault(website);
		if (!after.getDomain().equals(beforeDomain)) {
			websiteDomainCache.remove(beforeDomain);
			websiteDomainCache
					.put(new Element(after.getDomain(), after.getId()));
		}
		return true;
	}

	@Autowired
	@Qualifier("websiteDomain")
	private Cache websiteDomainCache;

	@Autowired
	public void setWebsiteDao(WebsiteDao dao) {
		super.setDao(dao);
	}

	public WebsiteDao getWebsiteDao() {
		return (WebsiteDao) super.getDao();
	}
}
