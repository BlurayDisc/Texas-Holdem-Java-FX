package com.run.poker.entity;

import java.util.Collections;
import java.util.LinkedList;

import com.run.poker.card.Card;
import com.run.poker.card.Suit;

import javafx.scene.canvas.GraphicsContext;

/**
 * A standard deck of 52 poker cards.
 * 
 * @author RuN
 *
 */
public class Deck extends GameEntity {

	private LinkedList<Card> list;

	public Deck() {
		this.list = new LinkedList<>();
	}

	/**
	 * {@inheritDoc}
	 * @return
	 */
	public Card removeFirst() {
		return list.removeFirst();
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
				list.add(card);
			}
		}
	}
	
	/**
	 * Shuffles the deck
	 */
	public void shuffle() {
		Collections.shuffle(list);
	}

	@Override
	public void draw(GraphicsContext gc) {
		
	}
	
	@Override
	public String toString() {
		return list.toString();
	}
}