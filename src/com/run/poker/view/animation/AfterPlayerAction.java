package com.run.poker.view.animation;

import java.util.List;

import com.run.poker.entity.player.Enemy;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.entity.table.Dealer;
import com.run.poker.entity.table.Table;
import com.run.poker.manager.GameManager;
import com.run.poker.utils.GameUtils;

import javafx.animation.Animation;
import javafx.util.Duration;

public class AfterPlayerAction extends BaseAnimation {
	
	private final int raiseIndex;
	private final List<PlayerEntity> list;

	public AfterPlayerAction(Table table, int raiseIndex) {
		super(table);
		this.list = table.playerList();
		this.raiseIndex = raiseIndex;
	}
	
	@Override
	public void createSequence(List<Animation> animations) {
		
		animations.add(delay(Duration.seconds(0.5), e -> {
			table.getPlayer().setActive(false);
			table.enablePlayerOptions(false);
		}));
		
		int p = table.getPlayerIndex();
		int max = list.size() - 1;
		for (int i = GameUtils.inc(p, max); i != raiseIndex; i = GameUtils.inc(i, max)) {
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
		
		animations.add(delay(Duration.seconds(0.5), e -> {
			Dealer dealer = table.callDealer();
			dealer.clearCC();
			dealer.stageOne();
		}));
	}
}