package com.run.poker.deck;

import java.util.Collections;
import java.util.LinkedList;

import com.run.poker.entity.Card;
import com.run.poker.entity.Suit;

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
	 * Fills up the deck with 1 set of poker cards, excluding 
	 * the two jokers.
	 */
	public void fill() {
		for (int value = 1; value < 14; value++) {
			for (Suit suit: Suit.values()) {
				Card card = new Card();
				card.setValue(value);
				card.setSuit(suit);
				deck.add(card);
			}
		}
	}
	
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
