package com.run.poker.entity.player;

import javafx.scene.canvas.GraphicsContext;

/**
 * The Player model.
 * @author RuN
 *
 */
public class Player extends PlayerEntity {
	
	public Player(String name) {
		this.name.set(name);
		this.title.set("Beginner");
		this.gold.set(1000);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		//Fully clears the canvas.
		//gc.clearRect(0, 0, 800, 600); 
		this.holdCards.draw(gc);
	}
}