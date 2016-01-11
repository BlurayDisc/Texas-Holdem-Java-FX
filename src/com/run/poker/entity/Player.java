package com.run.poker.entity;

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
		// TODO Auto-generated method stub
		
	}
}