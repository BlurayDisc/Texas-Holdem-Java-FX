package com.run.poker.entity;

import javafx.scene.canvas.GraphicsContext;

/**
 * Abstract game object.
 * @author RuN
 *
 */
public abstract class GameEntity {
	
	public abstract void draw(GraphicsContext gc);
}