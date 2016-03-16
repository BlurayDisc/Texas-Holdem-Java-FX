package com.run.poker.card;

import com.run.poker.entity.GameEntity;
import com.run.poker.utils.FileUtils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * A Poker Card Model.
 * <p> A Card has a suit and a value.
 * <p> There are a total of 4 different suits and 13 different values.
 * <p> In one deck there are 52 number of unique cards.
 * 
 * @author RuN
 *
 */
public class Card extends GameEntity implements Comparable<Card> {

	public static final double GAP = 10.0;
	public static final Image FRONT = FileUtils.loadFromJar("images/card.jpg");
	public static final Image BACK = FileUtils.loadFromJar("images/back.jpg");
	private Image image;
	private Suit suit;
	private int value;
	
	public Card(Suit suit, int value) {
		this.suit = suit;
		this.value = value;
		this.image = FRONT;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}
	
	public Card(Card card) {
		this.suit = card.suit;
		this.value = card.value;
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
	
	/**
	 * Draws the front or the back of the card with a chosen scaling.
	 * @param gc The Graphics Context from the Canvas object.
	 * @param i An Image object to be drawn.
	 * @param s The scaling factor.
	 */
	@Override
	public void draw(GraphicsContext gc) {
		//Clear
		//gc.clearRect(x, y, i.getWidth() * s, i.getHeight() * s);
		//System.out.println("Drawing: " + this + " Coordinates: (" + x + "," + y + ")");
		
		//Draw image base
		this.image = visible ? FRONT : BACK;
		gc.drawImage(image, x, y, width * scale, height * scale);
		
		//Draw suit and value
		if (visible) {
	        gc.setFont(new Font(40 * scale));
			gc.setFill(suit == Suit.Heart || suit == Suit.Diamonds ? 
					Color.RED : 
					Color.BLACK);
			//Draw text With offsets
	        gc.fillText("" + this, x + 5 * scale, y + 35 * scale);
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