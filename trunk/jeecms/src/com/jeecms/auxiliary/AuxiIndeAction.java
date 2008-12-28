package com.jeecms.auxiliary;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.auxiliary.entity.AuxiConfig;
import com.jeecms.auxiliary.manager.AuxiConfigMng;
import com.jeecms.core.IndeBaseAction;

/**
 * 辅助系统独立页面的action祖先。
 * <p>
 * 处理AuxiConfig配置
 * </p>
 * 
 * @author liufang
 * 
 */
@SuppressWarnings("serial")
public class AuxiIndeAction extends IndeBaseAction {
	
	protected String getSolution() {
		return getWeb().getSolutions().get(Constants.AUXILIARY_SYS);
	}

	
	protected String getSysType() {
		return Constants.AUXILIARY_SYS;
	}

	public AuxiConfig getConfig() {
		return auxiConfigMng.findById(getWebId());
	}

	@Autowired
	protected AuxiConfigMng auxiConfigMng;
}
