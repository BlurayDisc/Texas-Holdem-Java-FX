package com.run.poker.ai;

import com.run.poker.ai.decision.Decision;
import com.run.poker.ai.decision.StartPoint;
import com.run.poker.entity.player.Enemy;
import com.run.poker.entity.table.Table;

/**
 * AI Manager contains a back trace queue which holds on to a 
 * list of generated decisions. These decisions are produced 
 * by a Logic Producer object. The back trace queue is associated 
 * with a Logic Consumer that processes the decision.
 * @author RuN
 *
 */
public class AIManager {
	
	private Table table;
	
	public AIManager(Table table) {
		this.table = table;
	}
	
	public void process(Enemy enemy) {
		Decision start = new StartPoint();
		start.setTable(table);
		start.setEntity(enemy);
		start.execute();
		System.out.println(enemy.getName() + "  " + Decision.result());
		Decision.result().clear();
	}
}