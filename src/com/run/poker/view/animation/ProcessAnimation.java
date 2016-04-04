package com.run.poker.view.animation;

import com.run.poker.entity.player.Enemy;
import com.run.poker.manager.GameManager;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.util.Duration;

public class ProcessAnimation {
	
	private final Enemy enemy;
	
	public ProcessAnimation(Enemy enemy) {
		this.enemy = enemy;
	}

	public Animation create() {
		
		SequentialTransition sequence = new SequentialTransition();
		sequence.setCycleCount(1);
		sequence.setAutoReverse(false);
		
		PauseTransition t1 = new PauseTransition(Duration.seconds(0.1));
		t1.setOnFinished(e -> enemy.setActive(true));
		sequence.getChildren().add(t1);
		
		PauseTransition t2 = new PauseTransition(Duration.seconds(1));
		t2.setOnFinished(e -> {
			GameManager.process(enemy);
			enemy.setActive(false);
		});
		sequence.getChildren().add(t2);
		
		return sequence;
	}
}
