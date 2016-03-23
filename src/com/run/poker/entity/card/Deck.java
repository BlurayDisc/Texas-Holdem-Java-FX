package com.run.poker.entity.card;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * A standard deck of 52 poker cards.
 * 
 * @author RuN
 *
 */
public class Deck extends CardList {

	public Deck() {
		setSpacing(-Card.WIDTH * 0.75 + 0.5);
	}

	public Card pop() {
		return (Card) getChildren().remove(0);
	}
	
	public Card peek() {
		return (Card) getChildren().get(0);
	}

	/**
	 * Fills up the deck with 1 set of poker cards.
	 * <p> The two jokers are excluded from this deck.
	 * <p> Cards start from 2 and finish at 14 with 14 representing Ace.
	 */
	public void fill() {
		for (int value = 2; value < 15; value++) {
			for (Suit suit: Suit.values()) {
				Card card = new Card(suit, value);
				card.setHidden(true);
				getChildren().add(card);
			}
		}
	}
	
	/**
	 * Shuffles the deck
	 */
	public void shuffle() {
		ObservableList<Node> workingCollection = FXCollections.observableArrayList(getChildren());
		Collections.shuffle(workingCollection);
		getChildren().setAll(workingCollection);
	}
}