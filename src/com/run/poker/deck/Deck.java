package com.run.poker.deck;

import java.util.Collections;
import java.util.LinkedList;

import com.run.poker.card.Card;
import com.run.poker.card.Suit;

/**
 * A standard deck of 52 poker cards.
 * 
 * @author RuN
 *
 */
public class Deck extends LinkedList<Card> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8637787243176194920L;

	public Deck() {
		
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
				add(card);
			}
		}
	}
	
	/**
	 * Shuffles the deck
	 */
	public void shuffle() {
		Collections.shuffle(this);
	}
}
