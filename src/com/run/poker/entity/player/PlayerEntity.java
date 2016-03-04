package com.run.poker.entity.player;

import com.run.poker.entity.Card;
import com.run.poker.entity.GameEntity;
import com.run.poker.hand.HoldCards;
import com.run.poker.hand.ShowDownCards;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
public abstract class PlayerEntity extends GameEntity implements Comparable<PlayerEntity> {
	
	/**
	 * Current hold cards.
	 */
	protected HoldCards holdCards;
	
	/**
	 * Final showdown cards.
	 */
	protected ShowDownCards showDown;
	
	/**
	 * Player name.
	 */
	protected StringProperty name = new SimpleStringProperty("New Player");
	
	/**
	 * Player title
	 */
	protected StringProperty title = new SimpleStringProperty("Beginner");
	
	/**
	 * Player gold
	 */
	protected IntegerProperty gold = new SimpleIntegerProperty(1000);
	
	/**
	 * 
	 */
	public PlayerEntity() {
		this.showDown = new ShowDownCards();
		this.holdCards = new HoldCards();
		this.holdCards.setOwner(this);
	}
	
	/**
	 * Acquires one card.
	 * @param card A card.
	 */
	public void acquire(Card card) {
		holdCards.add(card);
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

	public StringProperty getName() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public IntegerProperty getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold.set(gold);
	}
	
	@Override
	public int compareTo(PlayerEntity that) {
		return this.showDown.compareTo(showDown);
	}
	
	@Override
	public String toString() {
		String str = name.get() + " The " + title.get() + 
					 " with $" + gold.get() + "." +  
					 "\n    Hands: " + showDown.getRank() + " ";
		for (Card card: showDown) {
			str += card + ", ";
		}
		return str;
	}
}