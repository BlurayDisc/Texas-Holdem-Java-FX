package com.run.poker.controller;

import java.util.List;

import com.run.poker.entity.Dealer;
import com.run.poker.entity.Table;
import com.run.poker.entity.player.Enemy;
import com.run.poker.entity.player.Player;
import com.run.poker.entity.player.PlayerEntity;

import javafx.scene.canvas.GraphicsContext;

/**
 * Controller class for FX Views.
 * @author RuN
 *
 */
public class GameController {

	private Table table;
	private Player player;
	
	private static final GameController INSTANCE = new GameController();
	
	private GameController() {
		this.table = new Table();
	}
	
	public static GameController getInstance() {
		return INSTANCE;
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
	
	public void cleanUp() {
		table.playerList().clear();
		table.botList().clear();
		table.communityCards().clear();
	}
	
	/**
	 * Creates a new Deck.
	 */
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
		dealer.stageOne();
		dealer.stageTwo();
		dealer.stageThree();
	}
	
	/**
	 * Test method.
	 * <p> Do a test sort and analyze of hands.
	 */
	public void sort() {
		Dealer dealer = table.callDealer();
		dealer.sort();
	}
	
	/**
	 * Test method.
	 * <p> Do a test sort and analyze of hands.
	 */
	public void analyse() {
		Dealer dealer = table.callDealer();
		dealer.analyse();
		System.out.println(table);
	}
	
	//*********************
	//*  GUI Controllers  *
	//*********************
	
	public Player getPlayer() {
		return player;
	}
	
	public List<Enemy> getBotList() {
		return table.botList();
	}
	
	public List<PlayerEntity> getPlayerList() {
		return table.playerList();
	}
	
	/**
	 * Returns the current ranking of the passed in player 
	 * entity amongst all other players.
	 * @param entity
	 * @return
	 */
	public int getCurrentRanking(PlayerEntity entity) {
		return table.playerList().indexOf(entity) + 1;
	}
	
	/**
	 * Draws the game entity object on the canvas. 
	 * <p> This method repaints the entire canvas.
	 * 
	 * @TODO Implement optimization which only repaints 
	 * each game entity by itself.
	 * @param gc
	 */
	public void draw(GraphicsContext gc) {
		table.draw(gc);
	}
}