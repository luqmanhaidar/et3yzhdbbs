package com.jeecms.cms.dao;

import java.util.List;

import com.jeecms.cms.entity.CmsChannel;
import com.jeecms.core.JeeCoreDao;

public interface CmsChannelDao extends JeeCoreDao<CmsChannel> {
	/**
	 * ����·��������Ŀ
	 * 
	 * @param path
	 * @return
	 */
	public CmsChannel getByPath(String path);

	/**
	 * ���Ҹ���Ŀ
	 * 
	 * @param sysType
	 *            ϵͳ����
	 * @return
	 */
	public List<CmsChannel> getRoots(String sysType);

	/**
	 * ���Ҹ���Ŀ�����������������˲������ӽڵ����Ŀ��
	 * 
	 * @param sysType
	 * @param hasChildIds
	 * @return
	 */
	public List<CmsChannel> getRoots(String sysType, Long[] hasChildIds);
}