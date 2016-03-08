package com.run.poker.ai.decision;

import java.util.Stack;

import com.run.poker.controller.GameController;
import com.run.poker.entity.player.Enemy;

/**
 * 
 * @author RuN
 *
 */
public abstract class Decision {

	private static Stack<Decision> backTrack;
	protected static GameController controller;
	protected Enemy entity;
	
	public Decision() {
		Decision.controller = GameController.getInstance();
		Decision.backTrack = new Stack<>();	
	}
	
	/**
	 * Main method for the generating and routing of bot logical 
	 * decisions, also calls the setEntity() and process() methods 
	 * in a loop, provides back tracking feature.
	 */
	public void execute() {
		backTrack.push(this);
		Decision decision;
		do {
			decision = process();
			decision.setEntity(entity);
			decision = decision.process();
		} while (decision != null);
	}
	
	/**
	 * Implements the procedures for each Decision.
	 */
	protected abstract Decision process();
	
	public static Stack<Decision> result() {
		return backTrack;
	}
	
	public void setEntity(Enemy entity) {
		this.entity = entity;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}