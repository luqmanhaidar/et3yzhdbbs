package com.ntsky.bbs.web.webwork.action.link;

import java.util.List;

import com.ntsky.bbs.service.LinkService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

public abstract class LinkActionSupport extends BasicActionSupport {

	protected LinkService linkService;

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}
	
	// 友情链接集合
	private List picLinks = null;	
	private List textLinks = null;
	public List getPicLinks() {
		return picLinks;
	}

	public void setPicLinks(List picLinks) {
		this.picLinks = picLinks;
	}

	public List getTextLinks() {
		return textLinks;
	}

	public void setTextLinks(List textLinks) {
		this.textLinks = textLinks;
	}	
	
}
