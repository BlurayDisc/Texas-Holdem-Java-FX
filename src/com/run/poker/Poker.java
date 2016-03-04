package com.run.poker;

import java.util.concurrent.ExecutionException;

import com.run.poker.view.PrimaryStage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main application class for the poker game.
 * @author RuN
 *
 */
public class Poker extends Application {
	
	public static final String APP_NAME = "Texas Hold'em Poker v 0.1";
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		launch(args);
	}
	
	@Override
	public void init() throws Exception {
		//Test Code.
//		Table table = new Table();
//		table.addPlayer(new Player("Ying"));
//		table.addBot();
//		table.addBot();
//		table.addBot();
//		
//		Dealer dealer = table.callDealer();
//		dealer.drawTwo();
//		
//		System.out.println(table);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage = new PrimaryStage();
		primaryStage.show();
	}
}