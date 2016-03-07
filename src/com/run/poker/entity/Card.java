package com.run.poker.entity;

import com.run.poker.utils.FileUtils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * 
 * @author RuN
 *
 */
public class Card extends GameEntity implements Comparable<Card> {

	public static final double GAP = 10.0;
	public static final Image FRONT = FileUtils.loadFromJar("images/card.jpg");
	public static final Image BACK = FileUtils.loadFromJar("images/back.jpg");
	
	private Suit suit;
	private int value;
	
	public Card(Suit suit, int value) {
		this.suit = suit;
		this.value = value;
	}
	
	/**
	 * Copy constructor.
	 */
	public Card(Card card) {
		this.suit = card.suit;
		this.value = card.value;
	}
	
	public Card copy() {
		return new Card(this);
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
	 * Returns the pretty value of the card number.
	 * @return
	 */
	public String getPrettyValue() {
		switch (value) {
			case 11: return "J";
			case 12: return "Q";
			case 13: return "K";
			case 14: return "A";
			default: return String.valueOf(value);
		}
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		draw(gc, Card.FRONT, 1);
	}
	
	/**
	 * Draws the front or the back of the card with a chosen scaling.
	 * @param gc The Graphics Context from the Canvas object.
	 * @param i An Image object to be drawn.
	 * @param s The scaling factor.
	 */
	public void draw(GraphicsContext gc, Image i, double s) {
		//Clear
		//gc.clearRect(x, y, i.getWidth() * s, i.getHeight() * s);
		
		//System.out.println("Drawing: " + this + " Coords: (" + x + "," + y + ")");
		
		//Draw image base
		gc.drawImage(i, x, y, i.getWidth() * s, i.getHeight() * s);
		
		//Draw suit and value
		if (i == Card.FRONT) {
	        gc.setFont(new Font(40 * s));
			gc.setFill(suit == Suit.Heart || suit == Suit.Diamonds ? 
					Color.RED : 
					Color.BLACK);
			//Draw text With offsets
	        gc.fillText("" + this, x + 5 * s, y + 35 * s);
		}
	}
	
	@Override
	public int compareTo(Card o) {
		if (o == this) return 0;
		if (this.value == o.value) {
			return this.suit.getRank() == o.getSuit().getRank() ? 0 : 
				   this.suit.getRank() > o.getSuit().getRank() ? 1 : -1;
		} else {
			return this.value > o.value ? 1 : -1;
		}
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
		return value;
	}
	
	@Override
	public String toString(){
		return suit.getPretty() + " " + this.getPrettyValue();
	}
}