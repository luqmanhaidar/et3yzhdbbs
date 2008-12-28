package com.jeecms.cms.manager;

import java.util.List;

import com.jeecms.core.JeeCoreManager;
import com.jeecms.cms.entity.CmsChannel;

public interface CmsChannelMng extends JeeCoreManager<CmsChannel> {
	/**
	 * �����Ŀ�ӽڵ㡣
	 * <p>
	 * start��count��Ϊ0ʱ�������ƽ��������
	 * </p>
	 * 
	 * @param pid
	 *            ���ڵ�ID
	 * @param orderBy
	 *            ����ʽ��0�����ȼ�����1�����ȼ�����2�������������3�������������
	 * @param isDisplay
	 *            �Ƿ�ֻ��ȡ��ʾ����Ŀ
	 * @param hasChild
	 *            �Ƿ�ֻ��ȡ���������ݵ���Ŀ
	 * @param start
	 * @param count
	 * @return
	 */
	public List<CmsChannel> getChild(String sysType, Long pid, int orderBy,
			boolean isDisplay, boolean hasChild, int start, int count);

	/**
	 * ͨ����Ŀ·�������Ŀ����
	 * 
	 * @param path
	 * @return
	 */
	public CmsChannel getByPath(String path);

	/**
	 * ��ø���Ŀ�б�
	 * 
	 * @param sysType
	 *            ϵͳ����
	 * @return
	 */
	public List<CmsChannel> getRoots(String sysType);

	/**
	 * ��ø���Ŀ
	 * 
	 * @param sysType
	 * @return
	 */
	public CmsChannel getRoot(String sysType);

	/**
	 * ��ø���Ŀ�����������������˲������ӽڵ����Ŀ
	 * 
	 * @param sysType
	 * @param hasChild
	 * @return
	 */
	public List<CmsChannel> getRoots(String sysType, boolean hasChild);
}