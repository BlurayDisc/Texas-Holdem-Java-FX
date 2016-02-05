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
	 * Draws this GameEntity with the passed in GraphicsContext.
	 * @param gc The GC of an initialized Canvas.
	 */
	public abstract void draw(GraphicsContext gc);
	
	/**
	 * Moves this GameEntity to a point(x, y) on the Canvas.
	 * @param x
	 * @param y
	 */
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
}