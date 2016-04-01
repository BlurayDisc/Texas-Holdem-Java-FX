package com.run.poker.ai.decision;

import com.run.poker.entity.player.Enemy;
import com.run.poker.entity.table.Table;
import com.run.poker.manager.GameManager;

/**
 * 
 * @author RuN
 *
 */
public abstract class Decision {
	
	/**
	 * Entity.
	 */
	protected Enemy entity;
	
	/**
	 * Model.
	 */
	protected Table table;
	
	protected GameManager manager;
	
	/**
	 * Main method for the generating and routing of bot logical 
	 * decisions, also calls the setEntity() and process() methods 
	 * in a loop, provides back tracking feature.
	 */
	public void execute() {
		entity.getBackTrack().push(this);
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
	
	public void setEntity(Enemy entity) {
		this.entity = entity;
	}
	
	public void setTable(Table table) {
		this.table = table;
		this.manager = table.callManager();
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}