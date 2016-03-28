package com.run.poker;

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
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage = new PrimaryStage();
		primaryStage.show();
	}
}