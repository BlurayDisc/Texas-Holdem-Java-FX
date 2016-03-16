package com.run.poker.view.task;

import com.run.poker.entity.table.Dealer;
import com.run.poker.entity.table.Table;

import javafx.scene.canvas.GraphicsContext;

/**
 * Stage One: The flop.
 */
public class StageOne extends FXTask {

	public StageOne(Table table, GraphicsContext gc) {
		super(table, gc);
	}

	@Override
	protected Void call() throws Exception {

		Dealer dealer = table.callDealer();
		dealer.clearCommunityCards();
		table.draw(gc);
		
		for (int i = 0; i < 5; i++) {
			Thread.sleep(300);
			dealer.draw();
			table.draw(gc);
		}
		
		return null;
	}

}
