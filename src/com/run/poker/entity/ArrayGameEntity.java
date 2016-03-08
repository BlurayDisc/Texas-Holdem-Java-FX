package com.run.poker.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class ArrayGameEntity extends GameEntity {
	
	protected double gap;
	private List<GameEntity> list;
	
	public ArrayGameEntity() {
		this.gap = 0.0;
		this.list = new ArrayList<>();
	}
	
	public void clear() {
		list.clear();
	}
	
	public void add(GameEntity entity) {
		move(entity, list.size());
		list.add(entity);
	}
	
	/**
	 * 
	 * @param e
	 * @param n
	 */
	public void move(GameEntity e, int n) {
		double x = this.x + scale * n * (width + gap);
		double y = this.y;
		e.move(x, y);
	}
}
