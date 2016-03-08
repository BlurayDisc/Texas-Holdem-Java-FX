package com.run.poker.entity;

import javafx.scene.canvas.GraphicsContext;

/**
 * Abstract game object.
 * @author RuN
 *
 */
public abstract class GameEntity {

	public double x, y;
	protected double scale;
	protected double width, height;
	
	public GameEntity() {
		this.x = 0.0;
		this.y = 0.0;
		this.scale = 1.0;
	}
	
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
	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Scaling.
	 * <p> The Sub classes of the GameEntity object must provide 
	 * the implementation of this field within it's draw(gc) method.
	 * @param scale
	 */
	public void setScale(double scale) {
		this.scale = scale;
	}
}