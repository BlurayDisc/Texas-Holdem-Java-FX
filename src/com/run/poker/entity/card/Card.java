package com.run.poker.entity.card;

import com.run.poker.utils.FileUtils;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Card extends Group implements Comparable<Card> {
	
	public static final Image FRONT = FileUtils.loadFromJar("images/card.jpg");
	public static final Image BACK = FileUtils.loadFromJar("images/back.jpg");
	public static final double GAP = 5;
	public static final double HEIGHT = FRONT.getHeight();
	public static final double WIDTH = FRONT.getWidth();
	
	public Suit suit;
	public int value;
	
	private Text text;
	private Text pattern;
	private ImageView iv;
	
	public Card(Suit suit, int value) {
		
		this.suit = suit;
		this.value = value;
		
		this.iv = new ImageView();
		iv.setImage(FRONT);
		iv.setX(0);
		iv.setY(0);
		iv.setScaleX(0.75);
		iv.setScaleY(0.75);
		
		this.text = new Text(this + "");
	    text.setFont(new Font(24));
	    text.setX(20);
	    text.setY(43);
	    
	    this.pattern = new Text(suit + "");
	    pattern.setFont(new Font(40));
	    pattern.setX(47);
	    pattern.setY(100);
	    
	    getChildren().addAll(iv, text, pattern);
	    adjustColor();
	}
	
	public Card() {
		this(null, 0);
		setHidden(true);
	}
	
	public void setHidden(boolean hidden) {
		iv.setImage(hidden ? BACK : FRONT);
		text.setVisible(!hidden);
		pattern.setVisible(!hidden);
	}
	
	public void setSuit(Suit suit) {
		this.suit = suit;
		text.setText(this + "");
		pattern.setText(suit + "");
		adjustColor();
	}
	
	public void setValue(int value) {
		this.value = value;
		text.setText(this + "");
	}
	
	private void adjustColor() {
		Color color = suit == Suit.Heart ||  suit == Suit.Diamonds ? 
		   		  Color.RED : 
	   			  Color.BLACK;
		text.setFill(color);
		text.setStroke(color);
		pattern.setFill(color);
		pattern.setStroke(color);
	}
	
	/**
	 * Returns the pretty value of the card number.
	 * @return
	 */
	private String getPrettyValue() {
		switch (value) {
			case 11: return "J";
			case 12: return "Q";
			case 13: return "K";
			case 14: return "A";
			default: return String.valueOf(value);
		}
	}
	
	/**
	 * Creates a transition animation.
	 * @param node The node to be be performed the translation with.
	 * @param target The targeting point wish to be moved.
	 * @param duration desired time for this frame.
	 * @param e The on finish event handler.
	 * @return A Translate Animation.
	 */
	public Animation move(Node target, Duration duration, EventHandler<ActionEvent> e) {
		TranslateTransition tt = new TranslateTransition(duration, this);
     	tt.setByX(target.getLayoutX() - this.getParent().getLayoutX());
     	tt.setByY(target.getLayoutY() - this.getParent().getLayoutY());
     	tt.setOnFinished(e);
     	return tt;
	}
	
	@Override
	public int compareTo(Card o) {
		if (o == this) return 0;
		if (this.value == o.value) {
			return this.suit.rank == o.suit.rank ? 0 : 
				   this.suit.rank > o.suit.rank ? 1 : -1;
		} else {
			return this.value > o.value ? 1 : -1;
		}
	}
	
	@Override
	public String toString(){
		return suit + " " + getPrettyValue();
	}
}