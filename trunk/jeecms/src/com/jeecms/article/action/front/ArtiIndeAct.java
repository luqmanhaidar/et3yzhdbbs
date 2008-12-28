package com.jeecms.article.action.front;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jeecms.article.manager.ArticleMng;
import com.jeecms.cms.CmsIndeAction;
import com.jeecms.cms.Constants;
import com.ponyjava.common.page.Pagination;

/**
 * 文章独立模板
 * 
 * @author liufang
 * 
 */
@Scope("prototype")
@Controller("article.artiIndeAct")
public class ArtiIndeAct extends CmsIndeAction {
	private static final Logger log = LoggerFactory
			.getLogger(ArtiIndeAct.class);

	public String search() {
		if (!StringUtils.isBlank(searchKey)) {
			try {
				searchKey = new String(searchKey.getBytes("ISO8859_1"), "gbk");
			} catch (UnsupportedEncodingException e) {
				log.error("文章搜索时，编码转换异常！", e);
			}
		}
		Boolean hasTitleImg;
		switch (hasImg) {
		case 2:
			hasTitleImg = false;
			break;
		case 1:
			hasTitleImg = true;
			break;
		default:
			hasTitleImg = null;
		}
		if (count > 200) {
			count = 200;
		}
		pagination = articleMng.getForTag(getWebId(), chnlId, null, searchKey,
				hasTitleImg, recommend == 1 ? true : false, orderBy, true, 0,
				pageNo, count);
		return handleResult("Search");
	}

	
	protected String getSysType() {
		return Constants.ARTICLE_SYS;
	}

	private int count;
	private String searchKey;

	private int orderBy = 0;
	private int recommend = 0;
	private int hasImg = 0;
	private Long chnlId = null;

	private Pagination pagination;
	@Autowired
	private ArticleMng articleMng;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getHasImg() {
		return hasImg;
	}

	public void setHasImg(int hasImg) {
		this.hasImg = hasImg;
	}

	public Long getChnlId() {
		return chnlId;
	}

	public void setChnlId(Long chnlId) {
		this.chnlId = chnlId;
	}
}