package com.ntsky.bbs.web.webwork.action.level;

import com.opensymphony.xwork.Preparable;

public class LevelsAction extends LevelActionSupport implements Preparable {

	public void prepare() throws Exception {
		this.setLevels(levelService.findLevels());
	}
	
	public String execute() throws Exception {
		
		/*if(!isPermisson("2_2")){
			setWarnMessage("您没有论坛管理的权限.");
			return NO_PERMISSION;
		}*/
		return SUCCESS;
    }

}
