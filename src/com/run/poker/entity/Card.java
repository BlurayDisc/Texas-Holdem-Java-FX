package com.run.poker.entity;

import javafx.scene.canvas.GraphicsContext;

public class Card extends GameEntity {

	private Suit suit;
	private int value;
	
	public Card() { 
		this.setSuit(null);
		this.setValue(0);
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * Returns the pretty value of the Suit
	 * @return
	 */
	public String getPrettySuit() {
		return suit.getPretty();
	}
	
	/**
	 * Returns the pretty value of the card number.
	 * @return
	 */
	public String getPrettyValue() {
		switch (value) {
			case 1:  return "A";
			case 11: return "J";
			case 12: return "Q";
			case 13: return "K";
			default: return String.valueOf(value);
		}
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (obj instanceof Card) {
			Card that = (Card) obj;
			return that.getValue() == this.getValue();
//			boolean fullyEq = that.getSuit() == this.getSuit();
		}
		return false;
	}

	
	@Override
	public int hashCode() {
		return value * suit.hashCode();
	}
	
	@Override
	public String toString(){
		return "[" + this.getPrettySuit() + " - " + this.getPrettyValue() + "]";
	}
}