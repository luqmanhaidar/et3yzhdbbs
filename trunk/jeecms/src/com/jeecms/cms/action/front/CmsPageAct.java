package com.jeecms.cms.action.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jeecms.article.entity.Article;
import com.jeecms.article.manager.ArticleMng;
import com.jeecms.cms.entity.CmsChannel;
import com.jeecms.cms.entity.CmsConfig;
import com.jeecms.cms.manager.CmsChannelMng;
import com.jeecms.cms.manager.CmsConfigMng;
import com.jeecms.core.PageBaseAction;
import com.ponyjava.common.page.Paginable;
import com.ponyjava.common.page.SimplePage;

@Scope("prototype")
@Controller("cms.cmsPageAct")
public class CmsPageAct extends PageBaseAction {
	
	protected void beforeAll() {
		super.beforeAll();
	}

	
	protected void sysIndex() {
		sysType = getConfig().getDefaultSystem();
		chnl = cmsChannelMng.getRoot(sysType);
		indexChnl = chnl;
		indexId = chnl.getId();
	}

	
	protected void chnlIndex(String chnlName) {
		chnl = cmsChannelMng.getByPath(chnlName);
	}

	
	protected void content(String chnlName, Long id) {
		arti = articleMng.findById(id);
		chnl = arti.getChannel();
		int count = arti.getPageCount();
		pagination = new SimplePage(pageNo, 1, count);
	}

	
	protected void alone(String chnlName) {
		chnl = cmsChannelMng.getByPath(chnlName);
	}

	
	protected void afterChnl() {
		super.afterChnl();
		// @ TODO 需要改变统计浏览次数的策略。目前的方法在大量并发下容易出现脏数据，并且频繁更新数据库，性能不佳。
		chnl.setVisitTotal(chnl.getVisitTotal() + 1);
		tplPath = chnl.chooseTplChannel();
	}

	
	protected void afterContent() {
		super.afterContent();
		arti.setVisitTotal(arti.getVisitTotal() + 1);
		tplPath = arti.chooseTpl();
	}

	
	protected void prepareTplParams() {
		super.prepareTplParams();
		sysType = chnl.getSysType();
	}

	public CmsChannel getChnl() {
		return chnl;
	}

	public Article getArti() {
		return arti;
	}

	public Long getIndexId() {
		if (indexId == null) {
			indexId = indexChnl.getId();
		}
		return indexId;
	}

	public CmsChannel getIndexChnl() {
		if (indexChnl == null) {
			indexChnl = cmsChannelMng.getRoot(sysType);
		}
		return indexChnl;
	}

	public String getSysType() {
		return sysType;
	}

	public CmsConfig getConfig() {
		return cmsConfigMng.findById(getWebId());
	}

	@Autowired
	private CmsConfigMng cmsConfigMng;
	@Autowired
	private CmsChannelMng cmsChannelMng;
	@Autowired
	private ArticleMng articleMng;
	private CmsChannel chnl;
	private Article arti;
	private Long indexId;
	private CmsChannel indexChnl;
	private String sysType;
	private Paginable pagination;

	public void setChnl(CmsChannel chnl) {
		this.chnl = chnl;
	}

	public Paginable getPagination() {
		return pagination;
	}

}
