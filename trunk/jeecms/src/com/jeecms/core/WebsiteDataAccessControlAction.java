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
 * 站点相关数据控制ACTION。
 * <p>
 * 站点相关数据只能在数据所在的站点中进行CURD操作
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
			addActionError("无权访问！");
			throw new DataAccessException("无权访问！Entity="
					+ getPersistent(super.id).getClass().getName() + ";ID"
					+ getBeanId());
		}
	}

	public void controlUpdate() {
		if (!getPersistentDomain(null).equals(domainName)) {
			addActionError("无权访问！");
			throw new DataAccessException("无权访问！Entity=" + getBeanClassName()
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
			addActionError("无权访问！");
			throw new DataAccessException("无权访问！Entity="
					+ getPersistent(super.id).getClass().getName() + ";ID" + id);
		}
	}

	protected Serializable getBeanId() {
		Object entity = getBeanInput();
		Serializable id;
		try {
			id = (Serializable) PropertyUtils.getSimpleProperty(entity, "id");
		} catch (Exception e) {
			throw new DataNotFountException("获取实体类ID失败！ID不存在。");
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
			throw new DataNotFountException("获取实体类属性失败！field=" + field);
		}
	}

	protected void setBeanWebsite(Object bean, Website website) {
		try {
			PropertyUtils.setSimpleProperty(bean, "website", website);
		} catch (Exception e) {
			log.error("setWebsite失败！", e);
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
