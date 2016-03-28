package com.run.poker.entity.chips;

import com.run.poker.entity.player.Player;

public class ObserverTest {

	public static void main(String[] args) {
		ChipsManager manager = ChipsManager.getInstance();
		Player run = new Player("run");
		Player ying = new Player("ying");
		manager.addObserver(run);
		manager.addObserver(ying);
		manager.set(50);
		manager.set(100);
	}
}