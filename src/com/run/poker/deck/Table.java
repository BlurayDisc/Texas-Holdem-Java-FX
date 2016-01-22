package com.run.poker.deck;

import java.util.ArrayList;
import java.util.List;

import com.run.poker.entity.PlayerEntity;
import com.run.poker.player.Enemy;
import com.run.poker.player.Player;

/**
 * 
 * @author RuN
 *
 */
public class Table {

	private List<PlayerEntity> playerList = new ArrayList<>();
	private Deck deck = new Deck();
	private Dealer dealer = null;
	
	public Table() {
		deck.fill();
		deck.shuffle();
	}
	
	public Dealer getDealer() {
		if (dealer == null) {
			this.dealer = new Dealer();
			this.dealer.setTable(this);
		}
		return dealer;
	}
	
	/**
	 * Draws two cards for every player in this table.
	 */
	void drawTwo() {
		for (PlayerEntity player: playerList) {
			player.acquire(deck.poll());
			player.acquire(deck.poll());
		}
	}
	
	/**
	 * 
	 */
	void drawOne() {
		
	}
	
	/**
	 * Adds a player to the table.
	 * @param player The player.
	 */
	public void addPlayer(Player player) {
		playerList.add(player);
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
		for (PlayerEntity p: playerList) {
			str += "  " + p + "\n";
		}
		return str;
	}
}