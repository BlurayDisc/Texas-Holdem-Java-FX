package com.run.poker.entity.player;

import com.run.poker.utils.GameUtils;

import javafx.scene.canvas.GraphicsContext;

/**
 * Enemy Bots.
 * @author RuN
 *
 */
public class Enemy extends PlayerEntity {
	
	public Enemy() {
		this.name.set(GameUtils.randomName());
		this.title.set(GameUtils.randomTitle());
		this.gold.set(GameUtils.random(500, 5000));
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		//Fully clears the canvas.
		//gc.clearRect(0, 0, 800, 600); 
		this.holdCards.draw(gc);
	}
}