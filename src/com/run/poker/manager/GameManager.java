package com.run.poker.manager;

import java.util.List;

import com.run.poker.ai.decision.Decision;
import com.run.poker.ai.decision.StartPoint;
import com.run.poker.entity.player.Enemy;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.entity.table.Dealer;
import com.run.poker.entity.table.Table;
import com.run.poker.utils.GameUtils;
import com.run.poker.view.animation.BetAnimation;

import javafx.animation.Animation;

/**
 * AI Manager contains a back trace queue which holds on to a 
 * list of generated decisions. These decisions are produced 
 * by a Logic Producer object. The back trace queue is associated 
 * with a Logic Consumer that processes the decision.
 * @author RuN
 *
 */
public class GameManager {
	
	private GameState state;
	private int chips;
	private int headIndex;
	private int raiseIndex;
	private static Table table;
	private static List<PlayerEntity> list;
	
	public GameManager(Table table) {
		GameManager.table = table;
		GameManager.list = table.playerList();
		this.headIndex = 0;
		this.raiseIndex = headIndex;
		this.state = GameState.GameInit;
	}
	
	/**
	 * To be called at the betting round, before any Player actions.
	 */
	public void beforePlayerAction() {		
		BetAnimation betAnimation = new BetAnimation(table, headIndex);
		Animation animation = betAnimation.beforePlayerAction();
		animation.play();
	}
	
	/**
	 * To be called at the betting rounds, after the player has performed an 
	 * action.
	 */
	public void afterPlayerAction() {
		BetAnimation betAnimation = new BetAnimation(table, raiseIndex);
		Animation animation = betAnimation.afterPlayerAction();
		switch (state) {
			case GameInit:
				animation.setOnFinished(e -> {
					Dealer dealer = table.callDealer();
					dealer.clearCC();
					dealer.stageOne();
				});
				break;
			case TheFlop:
				animation.setOnFinished(e -> {
					Dealer dealer = table.callDealer();
					dealer.stageTwo();
				});
				break;
			case TheRiver:
				animation.setOnFinished(e -> {
					Dealer dealer = table.callDealer();
					dealer.stageThree();
				});
				break;
			case TheTurn:
				break;
		}
		state = state.increment();
		animation.play();
	}
	
	public static void enablePlayerOptions(boolean enable) {
		table.enablePlayerOptions(enable);
	}
	
	/**
	 * 
	 * @param enemy
	 * @throws InterruptedException
	 */
	public static void process(Enemy enemy) {
		Decision start = new StartPoint();
		start.setTable(table);
		start.setEntity(enemy);
		start.execute();
		
		print(enemy);
		enemy.getBackTrack().clear();
	}
	
	public static void print(PlayerEntity entity) {
		if (entity instanceof Enemy) {
			System.out.println(
				"[" + list.indexOf(entity) + "]" + 
				"[" + entity.getName().get() + "] " + 
				((Enemy) entity).getBackTrack());
		} else {
			System.out.println(
				"[" + list.indexOf(entity) + "]" + 
				"[" + entity.getName().get() + "]" + 
				"[Player Action]");
		}
	}
	
	public void notify(PlayerEntity entity, int amount) {
		raiseIndex = list.indexOf(entity);
		chips = amount;
	}
	
	/**
	 * After a round has finished.
	 */
	public void afterRound() {
		GameUtils.inc(headIndex, list.size() - 1);
		raiseIndex = headIndex;
	}
	
	public GameManager() { }
	
	public boolean isCheckable() {
		return chips == 0;
	}
	
	public void add(int chips) {
		set(this.chips + chips);
	}
	
	public void set(int chips) {
		this.chips = chips;
	}
	
	public int get() {
		return chips;
	}
}