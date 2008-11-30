package com.ntsky.bbs.web.webwork.action.level;

import com.ntsky.bbs.domain.Level;
import com.ntsky.bbs.util.memory.LevelSingleton;
import com.opensymphony.xwork.ModelDriven;

public class EditLevelAction extends LevelActionSupport implements ModelDriven {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8299987530025986767L;
	private Level level = new Level();

	public Object getModel() {
		return this.level;
	}
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String execute() throws Exception {
		level.setId(Long.parseLong(this.getParameter("id")));
		level.setName(this.getParameter("name"));
		level.setMoney(Integer.parseInt(this.getParameter("money")));
		level.setDescription(this.getParameter("description"));
		level.setIcon(this.getParameter("icon"));
		levelService.saveLevel(level);
		LevelSingleton.getInstance().setLevels(levelService.findLevels());
		return SUCCESS;
	}

	public String doEdit() throws Exception {
		String id=this.getParameter("id");
		level=levelService.getLevel(id);
		return EDIT;
	}

}
