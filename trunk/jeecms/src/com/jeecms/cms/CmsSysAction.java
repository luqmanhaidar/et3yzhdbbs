package com.jeecms.cms;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.entity.CmsAdmin;
import com.jeecms.cms.entity.CmsConfig;
import com.jeecms.cms.entity.CmsMember;
import com.jeecms.cms.manager.CmsAdminMng;
import com.jeecms.cms.manager.CmsConfigMng;
import com.jeecms.cms.manager.CmsMemberMng;
import com.jeecms.core.WebsiteDataAccessControlAction;
import com.jeecms.core.exception.AdminNotFoundException;

/**
 * jeecms��action���ȡ�
 * <p>
 * ����cms����Ա
 * </p>
 * 
 * @author liufang
 * 
 */
@SuppressWarnings("serial")
public abstract class CmsSysAction extends WebsiteDataAccessControlAction {
	/**
	 * ���JEECMS����ԱID
	 * <p>
	 * ������������׳��쳣
	 * </p>
	 * 
	 * @return
	 */
	public Long getCmsAdminId() throws AdminNotFoundException {
		// �������ʿ��ƣ�adminId������ȷ��
		Long adminId = (Long) contextPvd.getSessionAttr(CmsAdmin.ADMIN_KEY);
		if (adminId == null) {
			CmsAdmin admin = cmsAdminMng.findById(getAdminId());
			adminId = admin.getId();
			if (admin == null) {
				throw new AdminNotFoundException("������JEECMSϵͳ�Ĺ���Ա��");
			} else {
				contextPvd.setSessionAttr(CmsAdmin.ADMIN_KEY, admin.getId());
			}
		}
		return adminId;
	}

	/**
	 * ���JEECMS����Ա����
	 * 
	 * @return
	 */
	public CmsAdmin getCmsAdmin() throws AdminNotFoundException {
		return cmsAdminMng.findById(getCmsAdminId());
	}

	/**
	 * ���JEECMS��Ա����
	 * 
	 * @return
	 */
	public CmsMember getCmsMember() {
		return cmsMemberMng.findById(getMemberId());
	}

	/**
	 * ���JEECMS��Ա����ID
	 * 
	 * @return
	 */
	public Long getCmsMemberId() {
		return getCmsMember().getId();
	}

	/**
	 * ���JEECMS���ö���
	 * 
	 * @return
	 */
	public CmsConfig getConfig() {
		return cmsConfigMng.findById(getWebId());
	}

	@Autowired
	protected CmsAdminMng cmsAdminMng;
	@Autowired
	protected CmsConfigMng cmsConfigMng;
	@Autowired
	protected CmsMemberMng cmsMemberMng;
}
