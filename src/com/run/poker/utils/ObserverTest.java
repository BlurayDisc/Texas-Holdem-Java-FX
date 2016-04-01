package com.run.poker.utils;

import java.util.Observable;
import java.util.Observer;

public class ObserverTest {

	public static void main(String[] args) {
		ChipsManager manager = new ChipsManager();
		Player run = new Player("run");
		Player ying = new Player("ying");
		manager.addObserver(run);
		manager.addObserver(ying);
		manager.set(50);
		manager.set(100);
	}
}

class ChipsManager extends Observable {
	
	private int chips;
	
	public ChipsManager() { }
	
	public boolean isCheckable() {
		return chips == 0;
	}
	
	public void add(int chips) {
		set(this.chips + chips);
		setChanged();
		notifyObservers(chips);
	}
	
	public void set(int chips) {
		this.chips = chips;
	}
	
	public int get() {
		return chips;
	}
}

class Player implements Observer {
	
	private String name;
	
	public Player(String name) {
		this.name = name;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(name + " " + arg);
	}
}