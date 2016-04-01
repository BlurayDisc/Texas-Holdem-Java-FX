package com.run.poker.ai.decision;

/**
 * 
 * @author RuN
 *
 */
public class CallCheck extends Decision {

	@Override
	public Decision process() {
		
		if (manager.isCheckable()) {
			entity.check();
		} else {
			entity.call(manager.get());
		}
		
		return null;
	}

}
