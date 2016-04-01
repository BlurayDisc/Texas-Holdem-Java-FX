package com.run.poker.manager;

import java.util.List;

import com.run.poker.ai.decision.Decision;
import com.run.poker.ai.decision.StartPoint;
import com.run.poker.entity.player.Enemy;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.entity.table.Table;
import com.run.poker.utils.GameUtils;
import com.run.poker.view.animation.AfterPlayerAction;
import com.run.poker.view.animation.BeforePlayerAction;

/**
 * AI Manager contains a back trace queue which holds on to a 
 * list of generated decisions. These decisions are produced 
 * by a Logic Producer object. The back trace queue is associated 
 * with a Logic Consumer that processes the decision.
 * @author RuN
 *
 */
public class GameManager {
	
	private int chips;
	private int headIndex;
	private int raiseIndex;
	private static Table table;
	private static List<PlayerEntity> list;
	
	public GameManager(Table table) {
		GameManager.table = table;
		GameManager.list = table.playerList();
		this.headIndex = 2;
		this.raiseIndex = headIndex;
	}
	
	/**
	 * To be called at the betting round, before any Player actions.
	 */
	public void beforePlayerAction() {
		new BeforePlayerAction(table, headIndex).playSequence();
	}
	
	/**
	 * To be called at the betting rounds, after the player has performed an 
	 * action.
	 */
	public void afterPlayerAction() {
		new AfterPlayerAction(table, raiseIndex).playSequence();
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