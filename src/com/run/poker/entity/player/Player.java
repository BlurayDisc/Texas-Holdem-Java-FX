package com.run.poker.entity.player;

/**
 * The Player model.
 * @author RuN
 *
 */
public class Player extends PlayerEntity {
	
	public Player(String newName) {
		name.set(newName);
		title.set("Beginner");
		money.set(1000);
	}
}