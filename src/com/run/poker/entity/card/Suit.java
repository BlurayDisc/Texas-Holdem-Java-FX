package com.run.poker.entity.card;

/**
 * Object representing a suit of a card.
 * @author RuN
 *
 */
public enum Suit {
	
	Spades("♠", 4), 
	Heart("♥", 3),  
	Clubs("♣", 2), 
	Diamonds("♦", 1);
	
	/**
	 * The pretty value of this suit.
	 */
	public final String pattern;
	
	/**
	 * The ranking of this suit.
	 */
	public final int rank;
	
	private Suit(String pretty, int rank) {
		this.pattern = pretty;
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return pattern;
	}
}