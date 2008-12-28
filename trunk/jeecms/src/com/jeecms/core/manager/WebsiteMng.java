package com.jeecms.core.manager;

import java.util.List;

import com.jeecms.core.entity.Website;
import com.ponyjava.common.page.Pagination;

/**
 * վ�����
 * 
 * <p>
 * վ�����������jeesys�ĺ��ģ����ڷ���Ƶ��������ʹ�û��档
 * </P>
 * <p>
 * ����ӿ���Ƶĺ�һ�㣬�������á�
 * </p>
 * 
 * @author liufang
 */
public interface WebsiteMng {
	/**
	 * ������վ����뻺��
	 */
	public void loadAllWebsiteToCache();

	/**
	 * ���������ӻ������վ�㡣
	 * 
	 * @param domainName
	 *            վ������
	 * @return
	 */
	public Website getWebsite(String domainName);

	/**
	 * ����ID�ӻ������վ��
	 * 
	 * @param id
	 *            վ��ID
	 * @return
	 */
	public Website getWebsite(Long id);

	/**
	 * ����վ�㵽���ݿ�ͻ��棬��������������
	 * 
	 * @param website
	 */
	public void saveWebsite(Website website);

	/**
	 * �ӻ����л������վ���б�
	 * 
	 * @return
	 */
	public List<Website> getAllWebsite();

	/**
	 * �����ݿ��÷�ҳ�б�
	 * 
	 * @param page
	 *            �ڼ�ҳ
	 * @param countPerPage
	 *            ÿҳ����
	 * @param order
	 *            �Ƿ�����ture�����������ʾ��
	 * @return
	 */
	public Pagination getAllWebsite(int page, int countPerPage,
			boolean order);

	/**
	 * �ӻ�������ݿ���ɾ��վ��ͻ���
	 * 
	 * @param website
	 * @return
	 */
	public boolean removeWebsite(Long id);

	/**
	 * ���»�������ݿ��վ�㣬������null�ֶβ����¡�
	 * 
	 * @param website
	 * @return
	 */
	public boolean updateWebsite(Website website);

	/**
	 * ��ù���Ա������վ��
	 * 
	 * @param unitedId
	 * @return
	 */
	public List<Website> getListByUserUnited(Long unitedId);
}
