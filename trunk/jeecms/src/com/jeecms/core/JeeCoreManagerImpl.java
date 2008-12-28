package com.jeecms.core;

import java.io.Serializable;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ponyjava.common.hibernate3.BaseManagerImpl;

public class JeeCoreManagerImpl<T extends Serializable> extends
		BaseManagerImpl<T> implements JeeCoreManager<T> {
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public T findByIdForCheck(Serializable id) {
		return findById(id);
	}
}
