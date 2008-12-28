package com.jeecms.cms.dao;

import java.util.List;

import com.jeecms.cms.entity.CmsChannel;
import com.jeecms.core.JeeCoreDao;

public interface CmsChannelDao extends JeeCoreDao<CmsChannel> {
	/**
	 * 根据路径查找栏目
	 * 
	 * @param path
	 * @return
	 */
	public CmsChannel getByPath(String path);

	/**
	 * 查找根栏目
	 * 
	 * @param sysType
	 *            系统类型
	 * @return
	 */
	public List<CmsChannel> getRoots(String sysType);

	/**
	 * 查找根栏目，启动过滤器，过滤不能有子节点的栏目。
	 * 
	 * @param sysType
	 * @param hasChildIds
	 * @return
	 */
	public List<CmsChannel> getRoots(String sysType, Long[] hasChildIds);
}