package com.ntsky.bbs.web.webwork.action.level;

import com.ntsky.bbs.util.memory.LevelSingleton;
import com.opensymphony.xwork.Preparable;

public class LevelsAction extends LevelActionSupport implements Preparable {

	public void prepare() throws Exception {
		this.setLevels(levelService.findLevels());
	}
	
	public String execute() throws Exception {
		
		if(!isPermisson("4_4")){
			setWarnMessage("您没有等级设置的权限.");
			return NO_PERMISSION;
		}
		recordActLog("等级设置");
		//this.setLevels(levelService.findLevels());
		return SUCCESS;
    }
	
	public String doDelete() throws Exception {
		if(!isPermisson("4_4")){
			setWarnMessage("您没有等级设置的权限.");
			return NO_PERMISSION;
		}
		recordActLog("等级设置");
		//this.getParameters(id);
		String id=this.getParameter("id");
		levelService.removeLevel(levelService.getLevel(id));
		LevelSingleton.getInstance().setLevels(levelService.findLevels());
		return DELETE;
	}

}
