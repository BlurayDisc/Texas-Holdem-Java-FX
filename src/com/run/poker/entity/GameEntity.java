package com.run.poker.entity;

import javafx.scene.canvas.GraphicsContext;

/**
 * Base Game Entity object.
 * @author RuN
 *
 */
public abstract class GameEntity {

	protected double x, y;
	protected double width, height;
	protected double scale;
	protected boolean visible;
	
	public GameEntity() {
		this.x = 0.0;
		this.y = 0.0;
		this.width = 0.0;
		this.height = 0.0;
		this.scale = 1.0;
		this.visible = true;
	}
	
	/**
	 * Moves this GameEntity to a point of (x, y) located on the Canvas.
	 * @param x the x-coordinate.
	 * @param y the y-coordinate.
	 */
	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Translates this object by a point of (x, y).
	 * @param x the x-coordinate.
	 * @param y the y-coordinate.
	 */
	public void translate(double x, double y) {
		this.x += x;
		this.y += y;
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
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void update(GameEntity entity) {
		entity.x = this.x;
		entity.y = this.y;
		entity.scale = this.scale;
		entity.visible = this.visible;
	}
	
	/**
	 * Draws this GameEntity with the passed in GraphicsContext.
	 * @param gc The GC of an initialized Canvas.
	 */
	public abstract void draw(GraphicsContext gc);
}