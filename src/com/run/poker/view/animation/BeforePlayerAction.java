package com.run.poker.view.animation;

import java.util.List;

import com.run.poker.ai.Analyser;
import com.run.poker.entity.player.Enemy;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.entity.table.Table;
import com.run.poker.manager.GameManager;
import com.run.poker.utils.GameUtils;

import javafx.animation.Animation;
import javafx.util.Duration;

public class BeforePlayerAction extends BaseAnimation {

	private final int headIndex;
	private final List<PlayerEntity> list;

	public BeforePlayerAction (Table table, int headIndex) {
		super(table);
		this.list = table.playerList();
		this.headIndex = headIndex;
	}
	
	@Override
	public void createSequence(List<Animation> animations) {
		
		animations.add(delay(Duration.seconds(3), e -> {
			Analyser analyser = new Analyser(table);
			analyser.analyse();
		}));
		
		int p = table.getPlayerIndex();
		int max = list.size() - 1;
		for (int i = headIndex; i != p; i = GameUtils.inc(i, max)) {
			PlayerEntity entity = list.get(i);
			Enemy enemy = (Enemy) entity;
			animations.add(delay(Duration.seconds(0.1), e -> {
				enemy.setActive(true);
			}));
			animations.add(delay(Duration.seconds(1), e -> {
				GameManager.process(enemy);
				enemy.setActive(false);
			}));
		}
		
		animations.add(delay(Duration.seconds(0.1), e -> {
			table.getPlayer().setActive(true);
			table.enablePlayerOptions(true);
			GameManager.print(table.getPlayer());
		}));
	}
}