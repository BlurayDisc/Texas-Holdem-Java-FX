package com.run.poker.view.animation;

import java.util.List;

import com.run.poker.ai.Analyser;
import com.run.poker.entity.player.Enemy;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.entity.table.Table;
import com.run.poker.manager.GameManager;
import com.run.poker.utils.GameUtils;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.util.Duration;

public class BetAnimation {
	
	private Table table;
	private final int index;
	private final List<PlayerEntity> list;

	public BetAnimation (Table table, int index) {
		this.table = table;
		this.list = table.playerList();
		this.index = index;
	}
	
	/**
	 * To be called at the betting round, before any Player actions.
	 */
	public Animation beforePlayerAction() {
		SequentialTransition sequence = new SequentialTransition();
		sequence.setCycleCount(1);
		sequence.setAutoReverse(false);
		
		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		pause.setOnFinished(e -> {
			Analyser analyser = new Analyser(table);
			analyser.analyse();
		});
		sequence.getChildren().add(pause);
		
		int p = table.getPlayerIndex();
		int max = list.size() - 1;
		for (int i = index; i != p; i = GameUtils.inc(i, max)) {
			PlayerEntity entity = list.get(i);
			if (entity instanceof Enemy) {
				Enemy enemy = (Enemy) entity;
				Animation animation = new ProcessAnimation(enemy).create();
				sequence.getChildren().add(animation);
			}
		}
		
		PauseTransition playerHighlight = new PauseTransition(Duration.seconds(0.1));
		playerHighlight.setOnFinished(e -> {
			table.getPlayer().setActive(true);
			table.enablePlayerOptions(true);
			GameManager.print(table.getPlayer());
		});
		sequence.getChildren().add(playerHighlight);
		return sequence;
	}
	
	/**
	 * To be called at the betting rounds, after the player has performed an 
	 * action.
	 */
	public Animation afterPlayerAction() {
		
		SequentialTransition sequence = new SequentialTransition();
		sequence.setCycleCount(1);
		sequence.setAutoReverse(false);
		
		PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
		pause.setOnFinished(e -> {
			table.getPlayer().setActive(false);
			table.enablePlayerOptions(false);
		});
		sequence.getChildren().add(pause);
		
		int p = table.getPlayerIndex();
		int max = list.size() - 1;
		for (int i = GameUtils.inc(p, max); i != index; i = GameUtils.inc(i, max)) {
			PlayerEntity entity = list.get(i);
			if (entity instanceof Enemy) {
				Enemy enemy = (Enemy) entity;
				Animation animation = new ProcessAnimation(enemy).create();
				sequence.getChildren().add(animation);
			}
		}
		return sequence;
	}
	
	public void asd() {
		Animation previous = null;
		int max = list.size() - 1;
		for (int i = index; i != index; i = GameUtils.inc(i, max)) {
			PlayerEntity entity = list.get(i);
			Animation current = entity.flash();
			if (previous != null) {
				previous.setOnFinished(e -> current.play());
			}
			previous = current;
		}
	}
}