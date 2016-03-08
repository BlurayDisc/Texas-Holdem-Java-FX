package com.run.poker.entity.player;

import com.run.poker.entity.Card;
import com.run.poker.hand.HoldCards;
import com.run.poker.hand.ShowDownCards;

import javafx.scene.canvas.GraphicsContext;

/**
 * <p> Base entity for player/enemy objects.
 * <p> Players have different properties and their names can be modified 
 * by the user on request. Players are also drawn differently: their 
 * cards will be drawn on the screen and revealed to the user where as 
 * Enemy will have their cards hidden.
 * <p> Enemy are bots which inherits all the properties of a player but 
 * with additional build in AI and functions. 
 * 
 * @author RuN
 *
 */
public abstract class PlayerEntity extends PlayerBaseEntity {
	
	/**
	 * Current hold cards.
	 */
	protected HoldCards holdCards;
	
	/**
	 * Final show down cards.
	 */
	protected ShowDownCards showDown;
	
	public PlayerEntity() {
		this.showDown = new ShowDownCards();
		this.holdCards = new HoldCards();
		this.holdCards.move(x, y);
	}
	
	/**
	 * Acquires one card.
	 * @param card A card.
	 */
	public void acquire(Card card) {
		holdCards.add(card);
	}
	
	/**
	 * 
	 * @param amount
	 */
	public void addMoney(int amount) {
		this.money.set(money.get() + amount);
	}

	/**
	 * 
	 * @param amount
	 */
	public void subtractMoney(int amount) {
		if (amount > money.get()) {
			throw new IllegalArgumentException("Out of Money.");
		}
		this.money.set(money.get() - amount);
	}
	
	@Override
	public void move(double x, double y) {
		super.move(x, y);
		this.holdCards.move(x, y);
	}
	
	public HoldCards holdCards() {
		return holdCards;
	}
	
	public ShowDownCards showDownCards() {
		return showDown;
	}
	
	public void setShowDown(ShowDownCards showDown) {
		this.showDown = showDown;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		super.draw(gc);
        
		//Draw Betting
        
		//Draw Cards
		this.holdCards.draw(gc);
	}
	
	@Override
	public int compareTo(PlayerEntity that) {
		return this.showDown.compareTo(showDown);
	}
	
	@Override
	public String toString() {
		String str = super.toString() + "\n" + 
				"    " + "Hands: " + showDown.getRank() + " ";
		for (Card card: showDown) {
			str += card + ", ";
		}
		return str;
	}
}