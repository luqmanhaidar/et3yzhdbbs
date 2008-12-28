package com.jeecms.core;

import java.io.Serializable;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeecms.core.entity.Website;
import com.jeecms.core.exception.DataNotFountException;
import com.ponyjava.common.struts2.interceptor.DataAccessException;
import com.ponyjava.common.struts2.interceptor.DataControlAware;

/**
 * վ��������ݿ���ACTION��
 * <p>
 * վ���������ֻ�����������ڵ�վ���н���CURD����
 * </p>
 * 
 * @author liufang
 * 
 */
public abstract class WebsiteDataAccessControlAction extends JeeCoreAction
		implements DataControlAware {
	protected static final Logger log = LoggerFactory
			.getLogger(WebsiteDataAccessControlAction.class);

	public void controlSave() {
		setBeanWebsite(getBeanInput());
	}

	public void controlEdit() {
		if (!getPersistentDomain(super.id).equals(domainName)) {
			addActionError("��Ȩ���ʣ�");
			throw new DataAccessException("��Ȩ���ʣ�Entity="
					+ getPersistent(super.id).getClass().getName() + ";ID"
					+ getBeanId());
		}
	}

	public void controlUpdate() {
		if (!getPersistentDomain(null).equals(domainName)) {
			addActionError("��Ȩ���ʣ�");
			throw new DataAccessException("��Ȩ���ʣ�Entity=" + getBeanClassName()
					+ ";ID" + getBeanId());
		}
		setBeanWebsite(getBeanInput(), getPersistentWebsite(null));
	}

	public void controlDelete() {
		if (super.id != null) {
			checkDelete(super.id);
		}
		if (super.ids != null) {
			for (Long id : super.ids) {
				checkDelete(id);
			}
		}
	}

	private void checkDelete(Long id) {
		if (id != null && !getPersistentDomain(id).equals(domainName)) {
			addActionError("��Ȩ���ʣ�");
			throw new DataAccessException("��Ȩ���ʣ�Entity="
					+ getPersistent(super.id).getClass().getName() + ";ID" + id);
		}
	}

	protected Serializable getBeanId() {
		Object entity = getBeanInput();
		Serializable id;
		try {
			id = (Serializable) PropertyUtils.getSimpleProperty(entity, "id");
		} catch (Exception e) {
			throw new DataNotFountException("��ȡʵ����IDʧ�ܣ�ID�����ڡ�");
		}
		return id;
	}

	protected Object getPersistent(Serializable id) {
		if (id == null) {
			id = getBeanId();
		}
		Object po = getManager().findByIdForCheck(id);
		return po;
	}

	protected Website getPersistentWebsite(Serializable id) {
		return (Website) getPersistentProperty("website", id);
	}

	protected String getPersistentDomain(Serializable id) {
		Website w = getPersistentWebsite(id);
		return w.getDomain();
	}

	protected Object getPersistentProperty(String field, Serializable id) {
		Object po = getPersistent(id);
		try {
			Object o = PropertyUtils.getSimpleProperty(po, field);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFountException("��ȡʵ��������ʧ�ܣ�field=" + field);
		}
	}

	protected void setBeanWebsite(Object bean, Website website) {
		try {
			PropertyUtils.setSimpleProperty(bean, "website", website);
		} catch (Exception e) {
			log.error("setWebsiteʧ�ܣ�", e);
		}
	}

	protected void setBeanWebsite(Object bean) {
		setBeanWebsite(bean, getWeb());
	}

	protected String getBeanClassName() {
		return getBeanInput().getClass().getName();
	}

	protected abstract Object getBeanInput();

	protected abstract JeeCoreManager<? extends Serializable> getManager();
}
