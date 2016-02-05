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
public class Deck {
	
	private final LinkedList<Card> deck;
	
	public Deck() {
		this.deck = new LinkedList<>();
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
				deck.add(card);
			}
		}
	}
	
	/**
	 * Shuffles the deck
	 */
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public void clear() {
		deck.clear();
	}
	
	public Card poll() {
		return deck.poll();
	}

	@Override
	public String toString() {
		return deck.toString();
	}
}
