package com.ntsky.bbs.web.webwork.action.level;

import java.util.List;

import com.ntsky.bbs.service.LevelService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

public class LevelActionSupport extends BasicActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -270305495916489600L;
	private List levels=null;
	protected LevelService levelService;
	public List getLevels() {
		return levels;
	}
	public void setLevels(List levels) {
		this.levels = levels;
	}
	public LevelService getLevelService() {
		return levelService;
	}
	public void setLevelService(LevelService levelService) {
		this.levelService = levelService;
	}

}
