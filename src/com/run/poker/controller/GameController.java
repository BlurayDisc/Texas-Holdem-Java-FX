package com.run.poker.controller;

import java.util.Collections;

import com.run.poker.deck.Deck;
import com.run.poker.player.Player;

import javafx.beans.property.StringProperty;
import javafx.scene.canvas.GraphicsContext;

/**
 * Controller class for FX Views.
 * @author RuN
 *
 */
public class GameController {

	private Player player;
	
	/**
	 * Creates a player.
	 */
	public void createPlayer(String name) {
		player = new Player(name);
		player.move(20, 425);
	}
	
	/**
	 * Test method.
	 * <p> Fills the player's hand with 5 random cards.
	 * @param numCards
	 */
	public void fillCards(int numCards) {
		Deck deck = new Deck();
		deck.fill();
		deck.shuffle();
		player.hands.clear();
		for (int i = 0; i < numCards; i++) {
			player.hands.add(deck.poll());
		}
		player.hands.sort(Collections.reverseOrder());
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
