package com.run.poker.controller;

import com.run.poker.entity.PlayerEntity;
import com.run.poker.entity.Dealer;
import com.run.poker.entity.Player;
import com.run.poker.entity.Table;

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
		this.player = table.addPlayer(name);
	}
	
	/**
	 * Add n number of bots.
	 * @param n
	 */
	public void addBots(int n) {
		for (int i = 0; i < n; i++) {
			table.addBot();
		}
	}
	
	public void newDeck() {
		Dealer dealer = table.callDealer();
		dealer.clear();
		dealer.newDeck();
	}
	
	/**
	 * Test method.
	 * <p> Fills the player's hand with 2 random cards.
	 */
	public void deal() {
		Dealer dealer = table.callDealer();
		dealer.deal();
	}
	
	/**
	 * Test method.
	 * <p> Do a test sort and anaylyse of hands.
	 */
	public void sort() {
		Dealer dealer = table.callDealer();
		dealer.sort();
	}
	
	/**
	 * Test method.
	 * <p> Do a test sort and anaylyse of hands.
	 */
	public void analyse() {
		Dealer dealer = table.callDealer();
		dealer.analyse();
		System.out.println(table);
	}
	
	//*********************
	//*  GUI Controllers  *
	//*********************
	
	public void setPlayerName(String name) {
		player.setName(name);
	}
	
	public StringProperty getPlayerName() {
		return player.getName();
	}
	
	/**
	 * Draws the game entity object on the canvas. 
	 * @param gc
	 */
	public void draw(GraphicsContext gc) {
		for (PlayerEntity entity: table.playerList()) {
			entity.draw(gc);
		}
	}
}