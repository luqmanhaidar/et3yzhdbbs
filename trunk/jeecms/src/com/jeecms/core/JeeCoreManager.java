package com.jeecms.core;

import java.io.Serializable;

import com.ponyjava.common.hibernate3.BaseManager;

public interface JeeCoreManager<T extends Serializable> extends BaseManager<T> {
	/**
	 * ����վ��������ˣ���������
	 * 
	 * @param id
	 * @return
	 */
	public T findByIdForCheck(Serializable id);
}
