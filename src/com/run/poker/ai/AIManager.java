package com.run.poker.ai;

import java.util.List;

import com.run.poker.ai.decision.Decision;
import com.run.poker.ai.decision.StartPoint;
import com.run.poker.controller.GameController;
import com.run.poker.entity.player.Enemy;

/**
 * AI Manager contains a back trace queue which holds on to a 
 * list of generated decisions. These decisions are produced 
 * by a Logic Producer object. The back trace queue is associated 
 * with a Logic Consumer that processes the decision.
 * @author RuN
 *
 */
public class AIManager {

	private List<Enemy> botList;
	
	public AIManager() {
		GameController gc = GameController.getInstance();
		this.botList = gc.getBotList();
	}
	
	/**
	 * 
	 */
	public void execute() {
		for (Enemy bot: botList) {
			Decision start = new StartPoint();
			start.setEntity(bot);
			start.execute();
			System.out.println(Decision.result());
			Decision.result().clear();
		}
	}
}
