package com.ntsky.bbs.web.webwork.action.level;

import com.ntsky.bbs.domain.Level;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.memory.LevelSingleton;
import com.opensymphony.xwork.ModelDriven;

public class CreateLevelAction extends LevelActionSupport implements
		ModelDriven {
	
	private Level level=new Level();
	
	public String execute() throws Exception {
		try{
			// 创建论坛
			
			level.setName(getParameter("name"));
			level.setDescription(this.getParameter("description"));
			level.setIcon(this.getParameter("icon"));
			level.setMoney(Integer.parseInt(this.getParameter("money")));
			levelService.saveLevel(level);
			
			LevelSingleton.getInstance().setLevels(levelService.findLevels());
			setActionMessage("添加等级 ['"+level.getName()+"'] 成功");
			
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	public String doDefault() throws Exception {
		
		return SUCCESS;
	}
	
	public Object getModel() {
		return level;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}
