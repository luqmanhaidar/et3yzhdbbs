package com.jeecms.cms.manager;

import java.util.List;

import com.jeecms.cms.entity.ChnlModel;
import com.jeecms.core.JeeCoreManager;

public interface ChnlModelMng extends JeeCoreManager<ChnlModel> {
	/**
	 * 获得栏目模型
	 * 
	 * @param sysType
	 * @return
	 */
	public List<ChnlModel> getModels(String sysType);

	/**
	 * 获得可以有子节点的模型id数组
	 * 
	 * @param sysType
	 * @return
	 */
	public Long[] getHasChildIds(String sysType);

}