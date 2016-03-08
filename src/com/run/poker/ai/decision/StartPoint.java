package com.run.poker.ai.decision;

public class StartPoint extends Decision {

	@Override
	public Decision process() {
		controller.analyse();
		int rank = controller.getCurrentRanking(entity);
		return rank == 1 ? new Winning() : new Losing();
	}
}