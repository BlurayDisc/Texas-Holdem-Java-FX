package com.run.poker.deck;

import java.util.ArrayList;
import java.util.List;

import com.run.poker.entity.BasePlayerEntity;
import com.run.poker.hand.CommunityCards;
import com.run.poker.player.Enemy;
import com.run.poker.player.Player;

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
	private List<Enemy> botList;
	private Dealer dealer;
	private Deck deck;
	private CommunityCards communityCards;
	
	public Table() {
		this.playerList = new ArrayList<>();
		this.botList = new ArrayList<>();
		this.communityCards = new CommunityCards();
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
	
	/**
	 * 
	 * @return
	 */
	public List<BasePlayerEntity> playerList() {
		return playerList;
	}
	
	/**
	 * 
	 * @return
	 */
	public CommunityCards communityCards() {
		return communityCards;
	}
	
	/**
	 * 
	 * @return
	 */
	public Deck deck() {
		return deck;
	}
	
	/**
	 * Adds a player to the table.
	 * @param entity The player.
	 */
	public Player addPlayer(String name) {
		Player player = new Player(name);
		sitDown(player);
		return player;
	}
	
	/**
	 * Adds a new bot to the table
	 */
	public Enemy addBot() {
		Enemy enemy = new Enemy();
		sitDown(enemy);
		return enemy;
	}

	/**
	 * Sits down every players to the table.
	 * <p> This method must be called after all players 
	 * have been added to the table.
	 */
	private void sitDown(Player player) {
		playerList.add(player);
		player.move(80, 425);
	}
	
	private void sitDown(Enemy enemy) {
		playerList.add(enemy);
		botList.add(enemy);
		int index = botList.indexOf(enemy);
		switch (index) {
			case 0:
				enemy.move(80, 20);
				break;
			case 1:
				enemy.move(80, 220);
				break;
			case 2:
				enemy.move(80, 420);
				break;
			default:
				break;
		}
	}
	
	protected void newDeck() {
		this.deck = new Deck();
		deck.fill();
		deck.shuffle();
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