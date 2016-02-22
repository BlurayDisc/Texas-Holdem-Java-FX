package com.run.poker.entity;

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
	
	private final String pretty;
	private final int rank;
	
	private Suit(String pretty, int rank) {
		this.pretty = pretty;
		this.rank = rank;
	}
	
	/**
	 * Gets the pretty value of this suit.
	 * @return
	 */
	public String getPretty(){
		return pretty;
	}
	
	/**
	 * Rturns the ranking of this suit.
	 * @return
	 */
	public int getRank() {
		return rank;
	}
}