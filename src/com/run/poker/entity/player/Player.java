package com.run.poker.entity.player;

import com.run.poker.entity.Card;

/**
 * The Player model.
 * @author RuN
 *
 */
public class Player extends PlayerEntity {
	
	public Player(String name) {
		this.name.set(name);
		this.title.set("Beginner");
		this.money.set(1000);
		this.holdCards.setImage(Card.FRONT);
	}
}