package com.run.poker.ai.decision;

import com.run.poker.entity.player.Enemy;

public abstract class Decision {

	protected Enemy enemy;
	
	public abstract void execute();
	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}