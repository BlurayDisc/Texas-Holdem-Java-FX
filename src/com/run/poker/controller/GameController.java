package com.run.poker.controller;

import com.run.poker.deck.Dealer;
import com.run.poker.deck.Table;
import com.run.poker.player.Player;

import javafx.beans.property.StringProperty;
import javafx.scene.canvas.GraphicsContext;

/**
 * Controller class for FX Views.
 * @author RuN
 *
 */
public class GameController {

	private Table table;
	private Player player;
	
	public GameController() {
		this.table = new Table();
	}
	
	/**
	 * Creates a player.
	 */
	public void createPlayer(String name) {
		player = new Player(name);
		player.move(20, 425);
		table.addPlayer(player);
	}
	
	public void createEnemy() {
		table.addBot();
	}
	
	/**
	 * Test method.
	 * <p> Fills the player's hand with 5 random cards.
	 * @param numCards
	 */
	public void fillCards(int numCards) {
		
		Dealer dealer = table.callDealer();
		dealer.newDeck();
		
		player.clear();
		for (int i = 0; i < numCards; i++) {
			dealer.drawOne();
		}
		player.sort();
	}
	
	public void setPlayerName(String name) {
		player.setName(name);
	}
	
	public StringProperty getPlayerName() {
		return player.getName();
	}
	
	public void draw(GraphicsContext gc) {
		player.draw(gc);
	}
}
