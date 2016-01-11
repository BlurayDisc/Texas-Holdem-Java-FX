package com.run.poker.entity;

/**
 * Object representing a suit of a card.
 * @author RuN
 *
 */
public enum Suit {
	Spades("♠"), 
	Heart("♥"),  
	Clubs("♣"), 
	Diamonds("♦");
	
	private final String pretty;
	
	private Suit(String pretty) {
		this.pretty = pretty;
	}
	
	/**
	 * Gets the pretty value of this suit.
	 * @return
	 */
	public String getPretty(){
		return pretty;
	}
}