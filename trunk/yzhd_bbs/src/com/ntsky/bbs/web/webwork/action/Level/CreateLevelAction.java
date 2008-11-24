package com.ntsky.bbs.web.webwork.action.Level;

import com.ntsky.bbs.domain.Level;
import com.opensymphony.xwork.ModelDriven;

public class CreateLevelAction extends LevelActionSupport implements
		ModelDriven {
	
	private Level level=new Level();
	
	public String execute() throws Exception {
		
		return SUCCESS;
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return level;
	}

}
