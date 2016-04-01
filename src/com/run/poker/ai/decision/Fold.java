package com.run.poker.ai.decision;

public class Fold extends Decision {

	@Override
	public Decision process() {
		
		entity.fold();
		
		return null;
	}

}
