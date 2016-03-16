package com.run.poker.view.task;

import com.run.poker.entity.table.Dealer;
import com.run.poker.entity.table.Table;

import javafx.scene.canvas.GraphicsContext;

public class DealTask extends FXTask {

	public DealTask(Table table, GraphicsContext gc) {
		super(table, gc);
	}
	
	@Override
	protected Void call() throws Exception {
		
		Dealer dealer = table.callDealer();
		dealer.clearHands();
		table.draw(gc);
		Thread.sleep(300);
		
		dealer.newDeck();
		dealer.deal();
		table.draw(gc);
		Thread.sleep(300);
		
		dealer.deal();
		table.draw(gc);
		Thread.sleep(300);
		
		dealer.sortHoldCards();
		table.draw(gc);
		
		return null;
	}
}