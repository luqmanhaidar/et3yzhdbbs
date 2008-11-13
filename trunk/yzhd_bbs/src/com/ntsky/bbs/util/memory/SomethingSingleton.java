package com.ntsky.bbs.util.memory;

import com.ntsky.bbs.domain.Link;
import com.ntsky.bbs.service.LinkService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;

public class SomethingSingleton {
private static SomethingSingleton somethingSingleton=null;
	
	public synchronized static SomethingSingleton getInstance(){
		if(somethingSingleton==null){
			somethingSingleton = new SomethingSingleton();
		}
		return somethingSingleton;
	}
	
	/*
	 * <option value="1">网页顶部</option>
	 * <option value="2">首页中间</option>
	 * <option value="3">帖子下面</option>
	 * <option value="4">右下角</option>
	 * <option value="5">友情链接</option>
	 */
	private Link ad1;
	private Link ad2;
	private Link ad3;
	private Link ad4;
	
	private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private LinkService ls=(LinkService)bf.getBean("linkService");
	
	public Link getAd1() {
		return ls.findRandomLinkByType(1);
	}
	public void setAd1(Link ad1) {
		this.ad1 = ad1;
	}
	public Link getAd2() {
		return ls.findRandomLinkByType(2);
	}
	public void setAd2(Link ad2) {
		this.ad2 = ad2;
	}
	public Link getAd3() {
		return ls.findRandomLinkByType(3);
	}
	public void setAd3(Link ad3) {
		this.ad3 = ad3;
	}
	public Link getAd4() {
		return ls.findRandomLinkByType(4);
	}
	public void setAd4(Link ad4) {
		this.ad4 = ad4;
	}
	
	

	
}
