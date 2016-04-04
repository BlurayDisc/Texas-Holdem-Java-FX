package com.run.poker.entity.player;

import java.util.Stack;

import com.run.poker.ai.decision.Decision;
import com.run.poker.manager.GameManager;
import com.run.poker.utils.GameUtils;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.util.Duration;

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
	
	@Override
	public Animation flash() {
		
		SequentialTransition sequence = new SequentialTransition();
		sequence.setCycleCount(1);
		sequence.setAutoReverse(false);
		
		PauseTransition t1 = new PauseTransition(Duration.seconds(0.1));
		t1.setOnFinished(e -> setActive(true));
		
		PauseTransition t2 = new PauseTransition(Duration.seconds(1));
		t2.setOnFinished(e -> {
			GameManager.process(this);
			setActive(false);
		});
		sequence.getChildren().addAll(t1, t2);
		
		return sequence;
	}
}