package com.run.poker.entity.player;

import java.util.Stack;

import com.run.poker.ai.decision.Decision;
import com.run.poker.utils.GameUtils;

/**
 * Enemy Bots.
 * @author RuN
 *
 */
public class Enemy extends PlayerEntity {
	
	/**
	 * Stack for back tracing the decision tree.
	 */
	private final Stack<Decision> backTrack = new Stack<>();
	
	public Enemy() {
		name.set(GameUtils.randomName());
		title.set(GameUtils.randomTitle());
		money.set(GameUtils.random(500, 5000));
		holdCards.setHidden(false);
	}
	
	public void execute(Decision decision) {
		
	}
	
	public Stack<Decision> getBackTrack() {
		return backTrack;
	}
	
	public Decision getFinalDecision() {
		return backTrack.get(backTrack.size() - 1);
	}
}