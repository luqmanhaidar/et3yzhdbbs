package com.jeecms.core;

import java.io.Serializable;

import com.ponyjava.common.hibernate3.BaseManager;

public interface JeeCoreManager<T extends Serializable> extends BaseManager<T> {
	/**
	 * 用于站点数据审核，无需事务。
	 * 
	 * @param id
	 * @return
	 */
	public T findByIdForCheck(Serializable id);
}
