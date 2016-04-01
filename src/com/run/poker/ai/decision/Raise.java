package com.run.poker.ai.decision;

public class Raise extends Decision {

	@Override
	public Decision process() {
		
		entity.raise(50);
		
		return null;
	}

}
