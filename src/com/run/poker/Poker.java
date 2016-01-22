package com.run.poker;

import com.run.poker.deck.Dealer;
import com.run.poker.deck.Table;
import com.run.poker.player.Player;
import com.run.poker.view.PrimaryStage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main application class for the poker game.
 * @author RuN
 *
 */
public class Poker extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() throws Exception {
		//Test Code.
		Table table = new Table();
		table.addPlayer(new Player("Ying"));
		table.addBot();
		table.addBot();
		table.addBot();
		
		Dealer dealer = table.getDealer();
		dealer.drawTwo();
		
		System.out.println(table);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		PrimaryStage stage = new PrimaryStage();
		stage.init();
		stage.show();
	}
}