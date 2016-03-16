package com.run.poker.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

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
public abstract class FixedArrayEntity<E extends GameEntity> extends EntityGroup<E> {
	
	protected double gap;
	
	public FixedArrayEntity() {
		this.gap = 10.0;
	}
	
	//*****************************
	//* Fixed Array Entity Methods *
	//*****************************
	
	/**
	 * Adds a new entity to this object, the newly attached entity will 
	 * inherit game 2d coordinates values from this object.
	 * @param e
	 */
	@Override
	public void add(E e) {
		list.add(e);
		update(e);
	}
	
	public int frequency(GameEntity e) {
		return Collections.frequency(list, e);
	}
	
	public void addAll(Collection<? extends E> list) {
		this.list.addAll(list);
	}
	
	/**
	 * Sorts items in this collection from smallest -> largest.
	 */
	public void sort() {
		list.sort(null);
	}
	
	/**
	 * Sorts items in this collection from largest -> smallest.
	 */
	public void reverseSort() {
		list.sort(Collections.reverseOrder());
	}
	
	public List<E> list() {
		return list;
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
	
	/**
	 * Sorts and moves the objects based on their new positions in the 
	 * array.
	 */
	@Override
	public void draw(GraphicsContext gc) {
		for (int i = 0; i < list.size(); i++) {
			E e = list.get(i);
			pushBack(e, i);
			e.draw(gc);
		}
	}
	
	@Override
	public String toString() {
		String str = this.getClass().getSimpleName() + " Hands: ";
		for (E e: list) {
			str += e + ", ";
		}
		return str;
	}
}