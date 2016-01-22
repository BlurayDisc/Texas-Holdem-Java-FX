package com.run.poker.entity;

import javafx.scene.canvas.GraphicsContext;

/**
 * Abstract game object.
 * @author RuN
 *
 */
public abstract class GameEntity {

	public int x, y;
	
	/**
	 * 
	 * @param gc
	 */
	public abstract void draw(GraphicsContext gc);
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
}