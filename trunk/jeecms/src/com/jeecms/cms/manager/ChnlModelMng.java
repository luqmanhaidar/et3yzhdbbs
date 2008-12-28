package com.jeecms.cms.manager;

import java.util.List;

import com.jeecms.cms.entity.ChnlModel;
import com.jeecms.core.JeeCoreManager;

public interface ChnlModelMng extends JeeCoreManager<ChnlModel> {
	/**
	 * �����Ŀģ��
	 * 
	 * @param sysType
	 * @return
	 */
	public List<ChnlModel> getModels(String sysType);

	/**
	 * ��ÿ������ӽڵ��ģ��id����
	 * 
	 * @param sysType
	 * @return
	 */
	public Long[] getHasChildIds(String sysType);

}