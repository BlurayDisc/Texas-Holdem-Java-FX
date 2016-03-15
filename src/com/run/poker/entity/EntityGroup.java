package com.run.poker.entity;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;

/**
 * Representing a GameEntity object, which is capable of having 
 * child GameEntities attached to itself. Its child GameEntities 
 * shares the same properties of the Parent, each child GameEntity 
 * can also have their properties modified individually.
 * 
 * @author RuN
 *
 */
public abstract class EntityGroup<E extends GameEntity> extends GameEntity {
	
	protected LinkedList<E> list;
	
	public EntityGroup() {
		this.list = new LinkedList<>();
	}
	
	//******************************
	//* Game Entity Array Methods  *
	//******************************
	
	/**
	 * Adds a new entity to this object, the newly attached entity will 
	 * inherit game 2d coordinates values from this object.
	 * @param entity
	 */
	public boolean add(E e) {
		update(e);
		return this.list.add(e);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void move(double x, double y) {
		super.move(x, y);
		for (E entity: list) {
			entity.move(x, y);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void translate(double x, double y) {
		super.translate(x, y);
		for (E entity: list) {
			entity.translate(x, y);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setScale(double scale) {
		super.setScale(scale);
		for (E entity: list) {
			entity.setScale(scale);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		for (E entity: list) {
			entity.setVisible(visible);
		}
	}
	
	//***********************
	//*   Linked List API   *
	//***********************

	public E get(int index) {
		 return list.get(index);
	}
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public boolean contains(Object o) {
		return list.contains(o);
	}

	public void clear() {
		list.clear();
	}
	
	public E getFirst() {
		return list.getFirst();
	}
	
	public E removeFirst() {
		return list.removeFirst();
	}
	
	public E removeLast() {
		return list.removeLast();
	}
	
	public E remove(int i) {
		return list.remove(i);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		for (E e: list) {
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