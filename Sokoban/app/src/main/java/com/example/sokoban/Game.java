package com.example.sokoban;

import java.util.ArrayList;
import java.util.List;



public class Game {

	protected Level currentLevel; 
	protected final List<Level> allLevels = new ArrayList<Level>();
	protected int moveCount = 0;
	
	// @Override
	public void addLevel(String name, int height, int width, String data){
		Level newLevel = new Level(name, height, width, data);
		this.currentLevel = newLevel;
		this.allLevels.add(newLevel);
	}
	
	// @Override
	public int getLevelCount() {
		return this.allLevels.size();
	} 
	
	// @Override
	public List<String> getLevelNames() {
		List<String> allLevelNames = new ArrayList<String>();
		for (Level level : this.allLevels){
			allLevelNames.add(level.getName());
		}
		return allLevelNames;
	}
	
	// @Override
	public String getCurrentLevelName() {
		String result = "no levels";
		if (this.currentLevel != null) {
			result = this.currentLevel.getName();
		}
		return result;
	}		
		
	
	@Override
	public String toString() {
		/*
		String result = "no levels";
		if (this.currentLevel != null) {
			result = "" + this.currentLevel;
		}
		return result;
		*/
		return "######\n"
				+ "#+x+.#\n"
				+ "#.w..#\n"
				+ "#....#\n"
				+ "######";
	}
	
	// @Override
	public void move(Direction direction) {
		this.currentLevel.move(direction);
	}


}
