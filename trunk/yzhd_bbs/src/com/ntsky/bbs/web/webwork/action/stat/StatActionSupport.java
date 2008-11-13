package com.ntsky.bbs.web.webwork.action.stat;

import java.util.List;

import com.ntsky.bbs.service.StatService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

public abstract class StatActionSupport extends BasicActionSupport {

	protected StatService statService;

	public void setStatService(StatService statService) {
		this.statService = statService;
	}
		
	public List stats;
	public List getStats() {
		return stats;
	}
	public void setStats(List stats) {
		this.stats = stats;
	}
	
}
