package com.run.poker.entity.player;

import com.run.poker.utils.GameUtils;

/**
 * Enemy Bots.
 * @author RuN
 *
 */
public class Enemy extends PlayerEntity {
	
	public Enemy() {
		name.set(GameUtils.randomName());
		title.set(GameUtils.randomTitle());
		money.set(GameUtils.random(500, 5000));
		holdCards.setHidden(false);
	}
}