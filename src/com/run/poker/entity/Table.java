package com.run.poker.entity;

import java.util.ArrayList;
import java.util.List;

import com.run.poker.entity.player.Enemy;
import com.run.poker.entity.player.Player;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.hand.CommunityCards;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

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
public class Table extends GameEntity {

	private List<PlayerEntity> playerList;
	private List<Enemy> botList;
	private Dealer dealer;
	private Deck deck;
	private CommunityCards communityCards;
	
	public Table() {
		this.playerList = new ArrayList<>();
		this.botList = new ArrayList<>();
		this.communityCards = new CommunityCards();
		this.communityCards.move(150, 20);
		this.dealer = new Dealer();
		this.dealer.setTable(this);
		this.deck = null;
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
	public List<PlayerEntity> playerList() {
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
	
	public void setDeck(Deck deck) {
		this.deck = deck;
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
		player.move(275, 425);
	}
	
	private void sitDown(Enemy enemy) {
		playerList.add(enemy);
		botList.add(enemy);
		int index = botList.indexOf(enemy);
		switch (index) {
			case 0:
				enemy.move(20, 250);
				break;
			case 1:
				enemy.move(300, 175);
				break;
			case 2:
				enemy.move(575, 250);
				break;
			default:
				break;
		}
	}
	
	protected void newDeck() {
		this.deck = new Deck();
		deck.setOwner(this);
		deck.fill();
		deck.shuffle();
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		
		Canvas canvas = gc.getCanvas();
		
		//Clear Canvas
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		//Draw Table
		gc.strokeRect(1, 1, canvas.getWidth() - 2, canvas.getHeight() - 2);
		gc.strokeRect(4, 4, canvas.getWidth() - 8, canvas.getHeight() - 8);
		
		//Draw Player and his Hold Cards.
		//Draw Bots and their Hold Cards but only showing the back
		for (PlayerEntity entity: playerList) {
			entity.draw(gc);
		}
		
		//Draw Dealer and Deck
		dealer.draw(gc);
		deck.draw(gc);
		
		//Draw Community Cards
		communityCards.draw(gc);
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