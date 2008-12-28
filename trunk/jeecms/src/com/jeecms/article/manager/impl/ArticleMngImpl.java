package com.jeecms.article.manager.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.article.dao.ArticleDao;
import com.jeecms.article.entity.Article;
import com.jeecms.article.manager.ArticleMng;
import com.jeecms.cms.entity.CmsAdmin;
import com.jeecms.cms.manager.CmsChannelMng;
import com.jeecms.core.JeeCoreManagerImpl;
import com.ponyjava.common.hibernate3.Updater;
import com.ponyjava.common.page.Pagination;
import com.ponyjava.common.struts2.ContextPvd;

@Service
@Transactional
public class ArticleMngImpl extends JeeCoreManagerImpl<Article> implements
		ArticleMng {
	
	public Pagination getForTag(Long webId, Long chnlId, Long ctgId,
			String searchKey, Boolean hasTitleImg, boolean recommend,
			int orderBy, boolean isPage, int firstResult, int pageNo,
			int pageSize) {
		return getDao().getForTag(webId, chnlId, ctgId, searchKey, hasTitleImg,
				recommend, orderBy, isPage, firstResult, pageNo, pageSize);
	}

	
	public Pagination getPage(Long chnlId, int pageNo, int count) {
		Set<Long> idSet = cmsChannelMng.findById(chnlId).getChildIds();
		Long[] ids = idSet.toArray(new Long[idSet.size()]);
		return getDao().getPage(ids, pageNo, count);
	}

	
	public Object updateByUpdater(Updater updater) {
		Article bean = (Article) updater.getBean();
		if (StringUtils.isBlank(bean.getTitleImg())) {
			bean.setTitleImg("");
			bean.setHasTitleImg(false);
		} else {
			bean.setHasTitleImg(true);
		}
		int origCount = super.findById(bean.getId()).getPageCount();
		bean.setPageCount(bean.getPageCountFromContent());
		Article arti = (Article) super.updateByUpdater(updater);
		arti.writeContent(contextPvd.getAppRoot(), origCount);
		return arti;
	}

	
	public Article save(Article arti, CmsAdmin admin) {
		if (StringUtils.isBlank(arti.getTitleImg())) {
			arti.setTitleImg("");
			arti.setHasTitleImg(false);
		} else {
			arti.setHasTitleImg(true);
		}
		arti.setDisabled(false);
		arti.setReject(false);
		Calendar nowCal = Calendar.getInstance();
		Date now = new java.sql.Timestamp(nowCal.getTimeInMillis());
		arti.setReleaseSysDate(now);
		// ���û�����뷢��ʱ�䣬��ȡϵͳʱ�䣻������뷢��ʱ�䣬����ϵ�ǰʱ���롣
		Date relDate = arti.getReleaseDate();
		if (relDate == null) {
			arti.setReleaseDate(now);
		} else {
			// ����ʱ����
			arti.setReleaseDate(appendHms(relDate, nowCal));
		}
		arti.setStatDate(now);
		arti.setVisitToday(0L);
		arti.setVisitTotal(0L);
		arti.setAdminInput(admin);
		// @ TODO �����������
		arti.setCheck(true);
		arti.setPageCount(arti.getPageCountFromContent());
		// @ TODO ������Դ·����������������·���ı������
		super.save(arti);
		arti.writeContent(contextPvd.getAppRoot(), 0);
		Article parti = getDao().getPreArticle(arti.getWebsite().getId(),
				arti.getId());
		if (parti != null) {
			parti.setNext(arti);
			arti.setPre(parti);
		}
		return arti;
	}

	
	public Article findById(Serializable id) {
		Article arti = super.findById(id);
		arti.setRootReal(contextPvd.getAppRoot());
		// @ TODO ������Դ·����������������·���ı������
		return arti;
	}

	
	public Article deleteById(Serializable id) {
		// ������һƪ����һƪ
		Article arti = findById(id);
		Article pre = arti.getPre();
		Article next = arti.getNext();
		if (pre != null) {
			pre.setNext(arti.getNext());
		}
		if (next != null) {
			next.setPre(arti.getPre());
		}
		super.deleteById(id);
		arti.deleteContentFile(contextPvd.getAppRoot());
		return arti;
	}

	/**
	 * ����ʱ�䵱ǰ��ʱ����
	 * 
	 * @param d
	 * @param now
	 * @return
	 */
	private Date appendHms(Date d, Calendar now) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
		cal.set(Calendar.SECOND, now.get(Calendar.SECOND));
		return new java.sql.Timestamp(cal.getTimeInMillis());
	}

	@Autowired
	private CmsChannelMng cmsChannelMng;
	@Autowired
	private ContextPvd contextPvd;

	@Autowired
	public void setDao(ArticleDao dao) {
		super.setDao(dao);
	}

	protected ArticleDao getDao() {
		return (ArticleDao) super.getDao();
	}
}
