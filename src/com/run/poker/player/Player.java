package com.run.poker.player;

import com.run.poker.card.Card;
import com.run.poker.entity.PlayerEntity;

import javafx.scene.canvas.GraphicsContext;

/**
 * The Player model.
 * @author RuN
 *
 */
public class Player extends PlayerEntity {
	
	public Player() {

	}
	
	public Player(String name) {
		this.setName(name);
		this.setTitle("Beginner");
		this.setGold(1000);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		Card[] a = cards.toArray(new Card[cards.size()]);
		for (int i = 0; i < a.length; i++) {
			if (i > 0) {
				a[i].x += a[i - 1].x + Card.WIDTH + 10;
			} else {
				a[i].x += this.x;
			}
			a[i].y += this.y;
			a[i].draw(gc);
		}
	}
}