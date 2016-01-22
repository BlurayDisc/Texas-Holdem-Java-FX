package com.run.poker.card;

import com.run.poker.entity.GameEntity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Card extends GameEntity {

	public static final int WIDTH = 120;
	public static final int HEIGHT = 160;
	private static final int FONT_SIZE = 40;
	private static final Image IMAGE = new Image("file:resources/card.jpg");
	
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
			case 1:  return "A";
			case 11: return "J";
			case 12: return "Q";
			case 13: return "K";
			default: return String.valueOf(value);
		}
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.clearRect(x, y, WIDTH, HEIGHT);
		gc.drawImage(IMAGE, x, y);
		gc.setFill(suit == Suit.Heart || suit == Suit.Clubs ? 
				Color.RED : 
				Color.BLACK);
        gc.setFont(new Font(FONT_SIZE));
        gc.fillText(this.toString(), x + 10, y + FONT_SIZE);
        
//        gc.setFill(Color.GREEN);
//        gc.setStroke(Color.BLUE);
//        gc.setLineWidth(5);
//        gc.strokeLine(40, 10, 10, 40);
//        gc.fillOval(10, 60, 30, 30);
//        gc.strokeOval(60, 60, 30, 30);
//        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
//        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
//        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
//        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
//        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
//        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
//        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
//        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
//        gc.fillPolygon(new double[]{10, 40, 10, 40},
//                       new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolygon(new double[]{60, 90, 60, 90},
//                         new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolyline(new double[]{110, 140, 110, 140},
//                          new double[]{210, 210, 240, 240}, 4);
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