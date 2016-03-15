package com.run.poker.ai.decision;

import java.util.Stack;

import com.run.poker.entity.Table;
import com.run.poker.entity.player.Enemy;

/**
 * 
 * @author RuN
 *
 */
public abstract class Decision {

	/**
	 * Stack for back tracing the decision tree.
	 */
	private static Stack<Decision> backTrace = new Stack<>();
	
	/**
	 * Entity.
	 */
	protected Enemy entity;
	
	/**
	 * Model.
	 */
	protected Table table;
	
	/**
	 * Main method for the generating and routing of bot logical 
	 * decisions, also calls the setEntity() and process() methods 
	 * in a loop, provides back tracking feature.
	 */
	public void execute() {
		backTrace.push(this);
		Decision decision = process();
		if (decision != null) {
			decision.setTable(table);
			decision.setEntity(entity);
			decision.execute();
		}
	}
	
	/**
	 * Implements the procedures for each Decision.
	 */
	protected abstract Decision process();
	
	public static Stack<Decision> result() {
		return backTrace;
	}
	
	public void setEntity(Enemy entity) {
		this.entity = entity;
	}
	
	public void setTable(Table table) {
		this.table = table;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}