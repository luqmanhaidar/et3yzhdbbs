package com.jeecms.article.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.jeecms.article.entity.ArtiCtg;
import com.jeecms.article.entity.Article;
import com.jeecms.article.manager.ArtiCtgMng;
import com.jeecms.article.manager.ArticleMng;
import com.jeecms.cms.Constants;
import com.jeecms.cms.entity.CmsChannel;
import com.jeecms.cms.manager.CmsChannelMng;
import com.jeecms.core.JeeCoreManager;
import com.jeecms.core.util.UploadRule;
import com.ponyjava.common.page.Pagination;
import com.ponyjava.common.util.ComUtils;
import com.ponyjava.common.util.SelectTreeUtils;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("article.articleAct")
public class ArticleAct extends com.jeecms.cms.CmsSysAction {
	public String doLeft() {
		List<CmsChannel> all = cmsChannelMng.getRoots(Constants.ARTICLE_SYS,
				true);
		if (all != null && all.size() > 0) {
			treeRoot = all.get(0);
		}
		return LEFT;
	}

	@SuppressWarnings("unchecked")
	public String doList() {
		if (chnlId == null) {
			List<CmsChannel> all = cmsChannelMng
					.getRoots(Constants.ARTICLE_SYS);
			if (all != null && all.size() > 0) {
				chnlId = all.get(0).getId();
			}
		}
		if (chnlId != null) {
			pagination = articleMng.getPage(chnlId, pageNo, getCookieCount());
		} else {
			pagination = new Pagination(1, 0, getCookieCount(), new ArrayList());
		}
		return LIST;
	}

	public String doAdd() {
		chnl = cmsChannelMng.findById(chnlId);
		artiCtgList = artiCtgMng.getList(getWebId(), false);
		// 设置上传规则
		UploadRule rule = new UploadRule(getWeb().getUploadRoot().toString(),
				Article.UPLOAD_PATH, true);
		contextPvd.setSessionAttr(UploadRule.KEY, rule);
		return ADD;
	}

	public String doSave() {
		if (bean.getBold() == null) {
			bean.setBold(false);
		}
		articleMng.save(bean, getCmsAdmin());
		addActionMessage("添加成功");
		return doList();
	}

	public String doEdit() {
		artiCtgList = artiCtgMng.getList(getWebId(), false);
		this.list = SelectTreeUtils.webTree(cmsChannelMng
				.getRoots(Constants.ARTICLE_SYS));
		this.bean = articleMng.findById(id);
		// 设置上传规则
		UploadRule rule = new UploadRule(getWeb().getUploadRoot().toString(),
				Article.UPLOAD_PATH, true);
		contextPvd.setSessionAttr(UploadRule.KEY, rule);
		return EDIT;
	}

	public String doUpdate() {
		Date rd = bean.getReleaseDate();
		if (rd == null) {
			bean.setReleaseDate(ComUtils.now());
		} else {
			bean.setReleaseDate(new Timestamp(rd.getTime()));
		}
		if (bean.getBold() == null) {
			bean.setBold(false);
		}
		articleMng.updateDefault(bean);
		addActionMessage("修改成功");
		return doList();
	}

	public String doDelete() {
		try {
			if (id != null) {
				articleMng.deleteById(id);
			} else {
				articleMng.deleteById(ids);
			}
		} catch (DataIntegrityViolationException e) {
			addActionError("记录已被引用，不能删除!");
		}
		return doList();
	}

	
	protected Object getBeanInput() {
		return getBean();
	}

	
	protected JeeCoreManager<Article> getManager() {
		return articleMng;
	}

	@Autowired
	private ArticleMng articleMng;
	@Autowired
	private CmsChannelMng cmsChannelMng;
	@Autowired
	private ArtiCtgMng artiCtgMng;
	private Article bean;
	private CmsChannel treeRoot;
	private Long chnlId;
	private CmsChannel chnl;
	private List<ArtiCtg> artiCtgList;

	public Article getBean() {
		return bean;
	}

	public void setBean(Article bean) {
		this.bean = bean;
	}

	public CmsChannel getTreeRoot() {
		return treeRoot;
	}

	public void setTreeRoot(CmsChannel treeRoot) {
		this.treeRoot = treeRoot;
	}

	public Long getChnlId() {
		return chnlId;
	}

	public void setChnlId(Long chnlId) {
		this.chnlId = chnlId;
	}

	public CmsChannel getChnl() {
		return chnl;
	}

	public void setChnl(CmsChannel chnl) {
		this.chnl = chnl;
	}

	public List<ArtiCtg> getArtiCtgList() {
		return artiCtgList;
	}

	public void setArtiCtgList(List<ArtiCtg> artiCtgList) {
		this.artiCtgList = artiCtgList;
	}
}
