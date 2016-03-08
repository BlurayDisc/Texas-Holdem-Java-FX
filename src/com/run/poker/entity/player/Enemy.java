package com.run.poker.entity.player;

import com.run.poker.entity.Card;
import com.run.poker.utils.GameUtils;

/**
 * Enemy Bots.
 * @author RuN
 *
 */
public class Enemy extends PlayerEntity {
	
	/**
	 * Testing code, draws enemy hold cards.
	 */
	private boolean test = true;
	
	public Enemy() {
		this.name.set(GameUtils.randomName());
		this.title.set(GameUtils.randomTitle());
		this.money.set(GameUtils.random(500, 5000));
		this.scale = 0.75;
		this.holdCards.setScale(scale);
		this.holdCards.setImage(test ? Card.FRONT : Card.BACK);
		System.out.println(name.get() + " created");
	}
}