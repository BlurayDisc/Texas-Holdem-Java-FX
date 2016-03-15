package com.run.poker.entity.player;

import com.run.poker.utils.GameUtils;

/**
 * Enemy Bots.
 * @author RuN
 *
 */
public class Enemy extends PlayerEntity {
	
	public Enemy() {
		this.name.set(GameUtils.randomName());
		this.title.set(GameUtils.randomTitle());
		this.money.set(GameUtils.random(500, 5000));
		this.setVisible(true);
		this.setScale(0.75);
	}
}