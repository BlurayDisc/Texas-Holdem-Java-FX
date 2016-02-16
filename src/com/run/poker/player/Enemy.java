package com.run.poker.player;

import com.run.poker.card.Card;
import com.run.poker.entity.BasePlayerEntity;
import com.run.poker.utils.GameUtils;

import javafx.scene.canvas.GraphicsContext;

/**
 * Enemy Bots.
 * @author RuN
 *
 */
public class Enemy extends BasePlayerEntity {
	
	public Enemy() {
		this.name.set(GameUtils.randomName());
		this.title.set(GameUtils.randomTitle());
		this.gold.set(GameUtils.random(500, 5000));
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		int i = 0;
		for (Card card: holdCards) {
			int x = this.x + i * (Card.WIDTH + Card.GAP);
			int y = this.y;
			card.move(x, y);
			card.draw(gc);
			i++;
		}
	}
}