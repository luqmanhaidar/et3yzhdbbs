package com.ntsky.bbs.util.memory;

import java.util.ArrayList;
import java.util.List;

public class LevelSingleton {
	private static LevelSingleton levelSingleton;
	public synchronized static LevelSingleton getInstance(){
		if(levelSingleton==null){
			levelSingleton = new LevelSingleton();
		}
		return levelSingleton;
	}
	
	private List levels=null;
	public List getLevels() {
		return levels;
	}

	public void setLevels(List levels) {
		this.levels = levels;
	}
}
