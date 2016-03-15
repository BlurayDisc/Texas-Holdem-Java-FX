package com.run.poker.entity;

import java.util.Collections;
import java.util.List;

/**
 * <p> Representing an array of GameEntity objects.
 * <p> This Entity is different from the ParentGameEntity, this 
 * object acts more like a concrete class. This object only holds 
 * an array of GameEntities of the same type.
 * @param <E>
 * 
 * @param <E> the type of elements in this list
 * 
 */
public abstract class FixedEntityGroup<E extends GameEntity> extends EntityGroup<E> {
	
	protected double gap;
	
	public FixedEntityGroup() {
		this.gap = 10.0;
	}
	
	//*****************************
	//* Game Entity Group Methods *
	//*****************************
	
	/**
	 * Adds a new entity to this object, the newly attached entity will 
	 * inherit game 2d coordinates values from this object.
	 * @param entity
	 */
	@Override
	public boolean add(E e) {
		update(e);
		pushBack(e, list.size());
		return this.list.add(e);
	}
	
	public int frequency(GameEntity e) {
		return Collections.frequency(list, e);
	}
	
	/**
	 * Sorts items in this collection from smallest -> largest.
	 */
	public void sort() {
		list.sort(null);
		rearrange();
	}
	
	/**
	 * Sorts items in this collection from largest -> smallest.
	 */
	public void reverseSort() {
		list.sort(Collections.reverseOrder());
		rearrange();
	}
	
	public List<E> list() {
		return list;
	}
	
	/**
	 * Sorts and moves the objects based on their new positions in the 
	 * array.
	 */
	private void rearrange() {
		for (int i = 0; i < list.size(); i++) {
			E e = list.get(i);
			pushBack(e, i);
		}
	}
	
	/**
	 * Translates an object in this array based on an index value.
	 * @param e
	 * @param n
	 */
	private void pushBack(E e, int n) {
		double x = this.x + scale * n * (e.width + gap);
		double y = this.y;
		e.move(x, y);
	}
}