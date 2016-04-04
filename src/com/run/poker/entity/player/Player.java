package com.run.poker.entity.player;

import com.run.poker.manager.GameManager;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * The Player model.
 * @author RuN
 *
 */
public class Player extends PlayerEntity {
	
	public Player(String newName) {
		name.set(newName);
		title.set("Beginner");
		money.set(1000);
	}
	
	@Override
	public Animation flash() {
		PauseTransition playerHighlight = new PauseTransition(Duration.seconds(0.1));
		playerHighlight.setOnFinished(e -> {
			setActive(true);
			GameManager.enablePlayerOptions(true);
			GameManager.print(this);
		});
		return playerHighlight;
	}
	
	public void onAction() {
		setActive(false);
		GameManager.enablePlayerOptions(false);
	}
}