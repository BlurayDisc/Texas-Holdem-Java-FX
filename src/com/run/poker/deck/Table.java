package com.run.poker.deck;

import java.util.ArrayList;
import java.util.List;

import com.run.poker.entity.BasePlayerEntity;
import com.run.poker.player.Enemy;

/**
 * 
 * @author RuN
 *
 */
public class Table {

	private List<BasePlayerEntity> playerList = new ArrayList<>();
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
		drawOne();
		drawOne();
	}
	
	/**
	 * 
	 */
	void drawOne() {
		for (BasePlayerEntity player: playerList) {
			player.acquire(deck.poll());
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