package com.jeecms.core.manager;

import java.util.List;

import com.jeecms.core.entity.Website;
import com.ponyjava.common.page.Pagination;

/**
 * 站点管理。
 * 
 * <p>
 * 站点管理是整个jeesys的核心，由于访问频繁，必须使用缓存。
 * </P>
 * <p>
 * 这个接口设计的很一般，基本能用。
 * </p>
 * 
 * @author liufang
 */
public interface WebsiteMng {
	/**
	 * 将所有站点读入缓存
	 */
	public void loadAllWebsiteToCache();

	/**
	 * 根据域名从缓存查找站点。
	 * 
	 * @param domainName
	 *            站点域名
	 * @return
	 */
	public Website getWebsite(String domainName);

	/**
	 * 根据ID从缓存查找站点
	 * 
	 * @param id
	 *            站点ID
	 * @return
	 */
	public Website getWebsite(Long id);

	/**
	 * 保存站点到数据库和缓存，保存域名到缓存
	 * 
	 * @param website
	 */
	public void saveWebsite(Website website);

	/**
	 * 从缓存中获得所有站点列表
	 * 
	 * @return
	 */
	public List<Website> getAllWebsite();

	/**
	 * 从数据库获得分页列表
	 * 
	 * @param page
	 *            第几页
	 * @param countPerPage
	 *            每页几条
	 * @param order
	 *            是否排序。ture：后添加先显示。
	 * @return
	 */
	public Pagination getAllWebsite(int page, int countPerPage,
			boolean order);

	/**
	 * 从缓存和数据库中删除站点和缓存
	 * 
	 * @param website
	 * @return
	 */
	public boolean removeWebsite(Long id);

	/**
	 * 更新缓存和数据库的站点，对象中null字段不更新。
	 * 
	 * @param website
	 * @return
	 */
	public boolean updateWebsite(Website website);

	/**
	 * 获得管理员的所有站点
	 * 
	 * @param unitedId
	 * @return
	 */
	public List<Website> getListByUserUnited(Long unitedId);
}
