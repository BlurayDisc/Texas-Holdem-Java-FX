package com.run.poker.entity.chips;

import java.util.Observable;

public class ChipsManager extends Observable {
	
	private static final ChipsManager INSTANCE = new ChipsManager();
	
	private int chips;
	
	public ChipsManager() { }
	
	public static ChipsManager getInstance() {
		return ChipsManager.INSTANCE;
	}
	
	public void add(int chips) {
		set(this.chips + chips);
	}
	
	public void set(int chips) {
		this.chips = chips;
		this.setChanged();
		this.notifyObservers(chips);
	}
	
	public int get() {
		return chips;
	}
}