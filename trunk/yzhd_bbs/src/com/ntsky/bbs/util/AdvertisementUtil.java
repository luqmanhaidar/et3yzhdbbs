package com.ntsky.bbs.util;

import com.ntsky.bbs.domain.Link;
import com.ntsky.bbs.service.LinkService;

public class AdvertisementUtil {
	private Link ad;//顶部ad
	private Link ad2;
	private Link ad3;
	private Link ad4;
    private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private LinkService ls=(LinkService)bf.getBean("linkService");
	
	public Link getAd() {
		Link link=ls.findRandomLinkByType(1);
		if(link==null){
			link=new Link();
		}
		return link;
	}
	public Link getAd2() {
		Link link=ls.findRandomLinkByType(2);
		if(link==null){
			link=new Link();
		}
		return link;
	}
	public Link getAd3() {
		Link link=ls.findRandomLinkByType(3);
		if(link==null){
			link=new Link();
		}
		return link;
	}
	public Link getAd4() {
		Link link=ls.findRandomLinkByType(4);
		if(link==null){
			link=new Link();
		}
		return link;
	}
}
