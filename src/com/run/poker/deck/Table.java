package com.run.poker.deck;

import java.util.ArrayList;
import java.util.List;

import com.run.poker.entity.BasePlayerEntity;
import com.run.poker.player.Enemy;

/**
 * <p> Table entity that consist of a list of players, a dealer and a 
 * deck of cards.
 * 
 * <p> It's API are not exposed to public use is due to the OO design. 
 * Developers should be calling the {@link #callDealer()} method 
 * first and then send commands to the dealer.
 * 
 * @author RuN
 *
 */
public class Table {

	private List<BasePlayerEntity> playerList;
	private Dealer dealer;
	private Deck deck;
	
	public Table() {
		this.playerList = new ArrayList<>();
		this.dealer = new Dealer();
		this.dealer.setTable(this);
	}
	
	/**
	 * Requests the Dealer for user commands and actions.
	 * @return the Dealer in this table.
	 */
	public Dealer callDealer() {
		return dealer;
	}
	
	void newDeck() {
		this.deck = new Deck();
		deck.fill();
		deck.shuffle();
		
	}
	
	/**
	 * Draws two cards for every player in this table.
	 */
	void drawTwo() {
		drawOne();
		drawOne();
	}
	
	/**
	 * 
	 */
	void drawOne() {
		for (BasePlayerEntity entity: playerList) {
			entity.acquire(deck.poll());
		}
	}
	
	/**
	 * Adds a player to the table.
	 * @param entity The player.
	 */
	public void addPlayer(BasePlayerEntity entity) {
		playerList.add(entity);
	}
	
	/**
	 * Adds a new bot to the table
	 */
	public void addBot() {
		playerList.add(new Enemy());
	}
	
	@Override
	public String toString() {
		String str = "Current Deck: " + deck + "\n";
		str += "  Players: \n";
		for (BasePlayerEntity p: playerList) {
			str += "  " + p + "\n";
		}
		return str;
	}
}