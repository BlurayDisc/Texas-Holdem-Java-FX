package com.run.poker.manager;

import com.run.poker.utils.GameUtils;

public enum GameState {
	
	GameInit,
	TheFlop,
	TheTurn,
	TheRiver;
	
	public GameState increment() {
		int max = GameState.values().length - 1;
		return GameState.values()[GameUtils.inc(ordinal(), max)];
	}
}
