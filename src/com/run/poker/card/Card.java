package com.run.poker.card;

import com.run.poker.entity.GameEntity;
import com.run.poker.utils.FileUtils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Card extends GameEntity implements Comparable<Card> {

	public static final int GAP = 10;
	public static final int WIDTH = 120;
	public static final int HEIGHT = 160;
	public static final Image FRONT = FileUtils.loadFromJar("images/card.jpg");
	public static final Image BACK = FileUtils.loadFromJar("images/back.jpg");
	
	private Suit suit;
	private int value;
	
	public Card() { 
		this(null, 0);
	}
	
	public Card(Suit suit, int value) {
		this.suit = suit;
		this.value = value;
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
		//Clear
		gc.clearRect(x, y, WIDTH, HEIGHT);
		//Draw image base
		gc.drawImage(FRONT, x, y);
		//Draw suit and value
        gc.setFont(new Font(40));
		gc.setFill(suit == Suit.Heart || suit == Suit.Diamonds ? 
				Color.RED : 
				Color.BLACK);
		//Draw text With offsets
        gc.fillText(this.toString(), x + 5, y + 35);
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
		return value * suit.hashCode();
	}
	
	@Override
	public String toString(){
		return suit.getPretty() + " " + this.getPrettyValue();
	}
}