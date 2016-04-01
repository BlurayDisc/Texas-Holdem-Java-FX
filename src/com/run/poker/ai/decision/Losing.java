package com.run.poker.ai.decision;

import com.run.poker.utils.GameUtils;

/**
 * When a bot is losing it would Call/Check, Fold or Bluff.
 * @author RuN
 *
 */
public class Losing extends Decision {

	@Override
	public Decision process() {
		
		Decision decision;
		int n = GameUtils.random(1, 100);
		if (n < 5) {
			decision = new Raise();
		} else if (n > 70) {
			decision = new Fold();
		} else {
			decision = new CallCheck();
		}
		return decision;
	}

}
