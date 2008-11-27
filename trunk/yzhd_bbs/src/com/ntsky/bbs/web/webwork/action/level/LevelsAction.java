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
		//this.setLevels(levelService.findLevels());
		return SUCCESS;
    }
	
	public String doDelete() throws Exception {
		//this.getParameters(id);
		String id=this.getParameter("id");
		levelService.removeLevel(levelService.getLevel(id));
		return DELETE;
	}

}
